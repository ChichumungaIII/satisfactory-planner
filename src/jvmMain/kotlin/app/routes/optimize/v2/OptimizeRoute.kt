package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeOutput.Maximization
import app.api.optimize.v2.request.OptimizeOutput.Production
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeConsumption
import app.api.optimize.v2.response.OptimizeProduction
import app.api.optimize.v2.response.OptimizeResponse
import app.game.data.Item
import app.game.data.RecipeV2
import app.serialization.AppJson
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import util.collections.augment
import util.collections.join
import util.collections.merge
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.Rational
import util.math.maximize
import util.math.minimize
import util.math.q
import java.util.concurrent.Executors

private val executor = Executors.newFixedThreadPool(12)

fun Routing.optimizeRouteV2() {
  post("/v2/optimize") {
    try {
      val request = AppJson.decodeFromString<OptimizeRequest>(call.receiveText())
      validate(request)
      call.respondText {
        val response = async(executor.asCoroutineDispatcher()) { optimize(request) }.await()
        AppJson.encodeToString(response)
      }
    } catch (e: Throwable) {
      println("#optimize() call failed: [${e.message}]")
      e.printStackTrace()
      call.respond<String>(HttpStatusCode.InternalServerError, e.message ?: "Unknown error.")
    }
  }
}

private fun validate(request: OptimizeRequest) {
  val (inputs, outputs, _, limits) = request

  inputs.forEach { check(it.amount > 0.q) { "Illegal input: $it" } }
  outputs.forEach {
    when (it) {
      is Production -> check(it.amount >= 0.q) { "Illegal product: ${it.item}." }
      is Maximization -> check(it.weight > 0.q) { "Illegal maximization: ${it.item}." }
    }
  }
  limits.forEach { (recipe, rate) -> check(rate > 0.q) { "Illegal restriction: $recipe" } }

  check(inputs.isNotEmpty()) { "Optimization requires inputs." }
  check(outputs.isNotEmpty()) { "Optimization requires products." }
}

suspend fun optimize(request: OptimizeRequest) = coroutineScope {
  val availability = request.inputs.index({ it.item }) { it.amount }
  val inputItems = availability.keys
  val requirements = request.outputs.filterIsInstance<Production>()
    .index({ it.item }, Requirement::from, Requirement::merge)
  val weights = request.outputs.filterIsInstance<Maximization>().index({ it.item }) { it.weight }

  val supplyExpressions = inputItems.associateWith { item -> 1.br * OpVar.create(item) }
  val usableRecipes = getUsableRecipes(request.recipes, inputItems)
  val itemExpressions = usableRecipes.flatMap { it.rates.map { (item, rate) -> item to rate.br * OpVar.create(it) } }
    .fold(mapOf<Item, OpExpr>()) { map, (item, expression) -> map.merge(item, expression, OpExpr::plus) }
    .join(supplyExpressions, OpExpr::plus)
  (request.outputs.map { it.item }.toSet() - itemExpressions.keys).takeUnless { it.isEmpty() }?.run {
    throw IllegalArgumentException("Cannot produce items: $this")
  }

  val requirementConstraints =
    requirements.mapValues { (item, requirement) ->
      val production = itemExpressions[item]!!
      if (requirement.exact && !weights.containsKey(item))
        Constraint.equalTo(production, requirement.amount)
      else Constraint.atLeast(production, requirement.amount)
    }
  val fixedConstraints =
    availability.map { (item, amount) -> Constraint.atMost(supplyExpressions[item]!!, amount) } +
        (itemExpressions.keys - requirementConstraints.keys).map { item ->
          Constraint.atLeast(itemExpressions[item]!!, 0.br)
        } + request.limits.filter { usableRecipes.contains(it.key) }
      .map { (recipe, rate) -> Constraint.atMost(1.br * OpVar.create(recipe), rate.br) }

  /****************/
  /** PLAN RATES **/
  /****************/

  val rates: Map<OpVar, BigRational> = (if (weights.isEmpty()) {
    val objective = itemExpressions[requirements.keys.first()]!!
    maximize(objective, fixedConstraints + requirementConstraints.values)
  } else {
    val objectives = weights.map { (item, weight) ->
      Objective(itemExpressions[item]!!, weight, requirements[item]?.amount ?: 0.br)
    }
    val primary = objectives.first()
    val balanceConstraints = objectives.drop(1).map { secondary ->
      Constraint.equalTo(
        primary.expression * secondary.weight - secondary.expression * primary.weight,
        secondary.weight * primary.offset - primary.weight * secondary.offset
      )
    }
    maximize(primary.expression, fixedConstraints + requirementConstraints.values + balanceConstraints)
  }).filterValues { it != 0.br }
  val planConstraints = requirementConstraints +
      request.outputs.map { it.item }.associateWith { item ->
        val production = itemExpressions[item]!!
        Constraint.equalTo(production, production(rates))
      }

  /*******************/
  /** INPUT DEMANDS **/
  /*******************/

  val totalConsumed = inputItems.associateWith { item -> supplyExpressions[item]!!(rates) }
  val totalDemandDeferred = inputItems.associateWith { item ->
    async {
      val consumption = supplyExpressions[item]!!
      consumption(minimize(consumption, fixedConstraints + planConstraints.values))
    }
  }

  /**************************/
  /** PRODUCTION POTENTIAL **/
  /**************************/

  val totalProduced = itemExpressions.keys
    .associateWith { item -> itemExpressions[item]!!(rates) }
    .filterValues { it > 0.br }
  val totalPotentialDeferred = request.outputs.map { it.item }.associateWith { item ->
    async {
      val production = itemExpressions[item]!!
      production(maximize(production, fixedConstraints + (planConstraints - item).values))
    }
  }

  /******************/
  /** DISTRIBUTION **/
  /******************/

  val totalDemand = totalDemandDeferred.mapValues { (_, deferred) -> deferred.await() }
  val (optimizedConsumption) = request.inputs.augment(totalConsumed) { consumption, (item, amount) ->
    val consumed = minOf(consumption[item] ?: 0.br, amount.br)
    val demand = amount.br + (totalDemand[item] ?: 0.br) - (availability[item] ?: 0.br)
    add(OptimizeConsumption(item, amount, consumed.toRational(), demand.toRational()))
    consumption + (item to (consumption[item] ?: 0.br) - consumed)
  }

  val totalPotential = totalPotentialDeferred.mapValues { (_, deferred) -> deferred.await() }
  val (distributedProduced, byproducts) = request.outputs.augment(totalProduced) { produced, output ->
    val item = output.item
    val amount: BigRational = when (output) {
      is Production -> output.amount.br
      is Maximization -> {
        val surplus = ((totalProduced[item] ?: 0.br) - (requirements[item]?.amount ?: 0.br))
        surplus * output.weight.br / weights[item]!!
      }
    }
    add(OptimizeProduction(item, amount.toRational(), 0.q))
    produced + (item to (produced[item] ?: 0.br) - amount)
  }
  val optimizeProduced = distributedProduced.map { (item, amount) ->
    val potential = amount.br + (totalPotential[item] ?: 0.br) - (totalProduced[item] ?: 0.br)
    OptimizeProduction(item, amount, potential.toRational())
  }

  /**************/
  /** RESPONSE **/
  /**************/

  OptimizeResponse(
    consumed = optimizedConsumption,
    produced = optimizeProduced,
    byproducts = byproducts.filterValues { it != 0.br }.mapValues { (_, amount) -> amount.toRational() },
    rates = rates.mapNotNull { (v, rate) -> (v as? RecipeVar)?.let { it.recipe to rate.toRational() } }.toMap(),
  )
}

fun <T> Iterable<T>.index(key: (T) -> Item, value: (T) -> Rational) = index(key, { value(it).br }, BigRational::plus)

fun <T, V> Iterable<T>.index(key: (T) -> Item, value: (T) -> V, merger: (V, V) -> V) =
  fold(mapOf<Item, V>()) { map, e -> map.merge(key(e), value(e), merger) }

tailrec fun getUsableRecipes(allRecipes: Set<RecipeV2>, items: Set<Item>): Set<RecipeV2> {
  val usable = allRecipes.filter { items.containsAll(it.inputsMap.keys) }.toSet()
  val next = items + usable.flatMap { it.outputsMap.keys }
  return usable.takeIf { items.containsAll(next) } ?: getUsableRecipes(allRecipes, next)
}

fun maximize(
  objective: Expression<OpVar, BigRational>,
  constraints: List<Constraint<OpVar, BigRational>>
) = maximize(objective, constraints, BigRational.FACTORY)

fun minimize(
  objective: Expression<OpVar, BigRational>,
  constraints: List<Constraint<OpVar, BigRational>>
) = minimize(objective, constraints, BigRational.FACTORY)
