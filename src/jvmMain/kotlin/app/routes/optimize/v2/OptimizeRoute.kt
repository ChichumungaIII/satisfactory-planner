package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeOutput.Maximization
import app.api.optimize.v2.request.OptimizeOutput.Production
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeConsumption
import app.api.optimize.v2.response.OptimizeProduction
import app.api.optimize.v2.response.OptimizeResponse
import app.game.data.Item
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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import util.collections.augment
import util.collections.merge
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.Rational
import util.math.maximize
import util.math.minimize
import util.math.q

fun Routing.optimizeRouteV2() {
  post("/v2/optimize") {
    try {
      val request = AppJson.decodeFromString<OptimizeRequest>(call.receiveText())
      validate(request)
      call.respondText { AppJson.encodeToString(optimize(request)) }
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

internal fun optimize(request: OptimizeRequest): OptimizeResponse {
  val (inputs, outputs, recipes, limits) = request

  val available = inputs.index({ it.item }) { it.amount }
  val required = outputs.filterIsInstance<Production>().index({ it.item }, Requirement::from, Requirement::merge)
  val weights = outputs.filterIsInstance<Maximization>().index({ it.item }) { it.weight }
  val offset = (available.keys + required.keys)
    .associateWith { item -> (required[item]?.amount ?: 0.br) - (available[item] ?: 0.br) }

  val expressions = Expressions.create(recipes, available.keys)
  expressions.checkProducible(outputs.map { it.item }.toSet())

  val constraints = expressions.items.associateWith { item ->
    val production = expressions.productionOf(item)
    if (required[item]?.exact == true && !weights.containsKey(item))
      Constraint.equalTo(production, offset[item] ?: 0.br)
    else Constraint.atLeast(production, offset[item] ?: 0.br)
  }
  val restrictions = limits.map { (recipe, rate) -> Constraint.atMost(1.br * (RecipeVar(recipe) as OpVar), rate.br) }

  /****************/
  /** PLAN RATES **/
  /****************/

  val rates: Map<OpVar, BigRational> = (if (weights.isEmpty()) {
    val objective = expressions.productionOf(required.keys.first())
    maximize(objective, constraints.values + restrictions)
  } else {
    val objectives = weights.map { (item, weight) ->
      Objective(expressions.productionOf(item), weight, offset[item] ?: 0.br)
    }
    val primary = objectives.first()
    val balance = objectives.drop(1).map { secondary ->
      Constraint.equalTo(
        primary.expression * secondary.weight - secondary.expression * primary.weight,
        secondary.weight * primary.offset - primary.weight * secondary.offset
      )
    }
    maximize(primary.expression, constraints.values + restrictions + balance)
  }).filterValues { it != 0.br }
  val targets = outputs.map { it.item }.associateWith { item ->
    val expression = expressions.productionOf(item)
    Constraint.equalTo(expression, expression(rates))
  }

  /*******************/
  /** INPUT DEMANDS **/
  /*******************/

  val implicitConsumed = available.mapValues { (item, available) -> minOf(required[item]?.amount ?: 0.br, available) }
  val totalConsumed = available.keys.associateWith { item ->
    expressions.consumptionOf(item)(rates) + (implicitConsumed[item] ?: 0.br)
  }

  val demandConstraints = (constraints + targets).values + restrictions
  val totalDemand = available.keys.associateWith { expressions.consumptionOf(it) }.mapValues { (item, consumption) ->
    consumption(minimize(consumption, demandConstraints)) + (implicitConsumed[item] ?: 0.br)
  }

  val (optimizeConsumed) = inputs.augment(totalConsumed) { consumption, (item, amount) ->
    val consumed = minOf(consumption[item] ?: 0.br, amount.br)
    val demand = amount.br + (totalDemand[item] ?: 0.br) - (available[item] ?: 0.br)
    add(OptimizeConsumption(item, amount, consumed.toRational(), demand.toRational()))
    consumption + (item to (consumption[item] ?: 0.br) - consumed)
  }

  /**************************/
  /** PRODUCTION POTENTIAL **/
  /**************************/

  val implicitProduced = required.mapValues { (_, requirement) -> requirement.amount }
    .mapValues { (item, amount) -> minOf(available[item] ?: 0.br, amount) }
  val totalProduced = expressions.items
    .associateWith { item ->
      val explicit = maxOf(expressions.productionOf(item)(rates), 0.br)
      val implicit = implicitProduced[item] ?: 0.br
      explicit + implicit
    }
    .filterValues { it > 0.br }

  val totalPotential = outputs.map { it.item }.distinct()
    .associateWith { expressions.productionOf(it) }
    .mapValues { (item, production) ->
      val potentialConstraints = (constraints + targets - item).values + restrictions
      val explicit = maxOf(production(maximize(production, potentialConstraints)), 0.br)
      val implicit = implicitProduced[item] ?: 0.br
      explicit + implicit
    }

  val (distributedProduced, byproducts) = outputs.augment(totalProduced) { produced, output ->
    val item = output.item
    val amount: BigRational = when (output) {
      is Production -> output.amount.br
      is Maximization -> {
        val surplus = ((totalProduced[item] ?: 0.br) - (required[item]?.amount ?: 0.br))
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

  return OptimizeResponse(
    consumed = optimizeConsumed,
    produced = optimizeProduced,
    byproducts = byproducts.filterValues { it != 0.br }.mapValues { (_, amount) -> amount.toRational() },
    rates = rates.mapNotNull { (v, rate) -> (v as? RecipeVar)?.let { it.recipe to rate.toRational() } }.toMap(),
  )
}

fun <T> Iterable<T>.index(key: (T) -> Item, value: (T) -> Rational) = index(key, { value(it).br }, BigRational::plus)

fun <T, V> Iterable<T>.index(key: (T) -> Item, value: (T) -> V, merger: (V, V) -> V) =
  fold(mapOf<Item, V>()) { map, e -> map.merge(key(e), value(e), merger) }

fun maximize(
  objective: Expression<OpVar, BigRational>,
  constraints: List<Constraint<OpVar, BigRational>>
) = maximize(objective, constraints, BigRational.FACTORY)

fun minimize(
  objective: Expression<OpVar, BigRational>,
  constraints: List<Constraint<OpVar, BigRational>>
) = minimize(objective, constraints, BigRational.FACTORY)
