package com.chichumunga.satisfactory

import app.api.optimize.v1.OptimizeRequest
import app.api.optimize.v1.OptimizeResponse
import app.data.Item
import app.data.recipe.Recipe
import app.serialization.AppJson
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.InfeasibleSolutionException
import util.math.maximize
import util.math.minimize
import util.math.q
import java.util.concurrent.Executors

private val EMPTY = OptimizeResponse(mapOf(), mapOf(), mapOf())
private val THREAD_POOL = Executors.newFixedThreadPool(16)

fun Routing.optimizeRoute() {
  post("/v1/optimize") {
    launch(THREAD_POOL.asCoroutineDispatcher()) {
      try {
        val request = AppJson.decodeFromString<OptimizeRequest>(call.receiveText())
        call.respondText { AppJson.encodeToString(optimize(request)) }
      } catch (e: Exception) {
        println(e.message)
        e.printStackTrace()
        throw e
      }
    }.join()
  }
}

private suspend fun optimize(request: OptimizeRequest) = coroutineScope {
  val (recipes, inputs, outcomes) = request

  if (inputs.isEmpty() || outcomes.isEmpty()) return@coroutineScope EMPTY

  val expressions = consider(recipes)
  check(expressions.keys.containsAll(inputs.map { it.item }))
  check(expressions.keys.containsAll(outcomes.map { it.item }))
  check(outcomes.map { it.item }.toSet().size == outcomes.size) { "Outcome items must be unique." }

  val provisions = inputs.groupBy { it.item }
    .mapValues { (_, inputs) -> inputs.map { it.quantity.br }.reduce(BigRational::plus) }
  val requirements = outcomes.associate {
    it.item to when (it) {
      is OptimizeRequest.Exact -> it.amount.br
      is OptimizeRequest.Minimum -> it.minimum.br
      is OptimizeRequest.Range -> it.minimum.br
    }
  }
  val limits = outcomes.filterIsInstance<OptimizeRequest.Exact>().associate { it.item to it.amount.br } +
      outcomes.filterIsInstance<OptimizeRequest.Range>().associate { it.item to it.maximum.br }

  val outcomeConstraints = outcomes.associate {
    val provision = provisions.getOrDefault(it.item, 0.br)
    it.item to when (it) {
      is OptimizeRequest.Exact -> listOf(Constraint.equalTo(expressions[it.item]!!, it.amount.br - provision))
      is OptimizeRequest.Minimum -> listOf(Constraint.atLeast(expressions[it.item]!!, it.minimum.br - provision))
      is OptimizeRequest.Range -> listOf(
        Constraint.atLeast(expressions[it.item]!!, it.minimum.br - provision),
        Constraint.atMost(expressions[it.item]!!, it.maximum.br - provision)
      )
    }
  }.toMutableMap()
  val initialConstraints = outcomeConstraints.toMap()
  val basicConstraints = expressions.filterKeys { !outcomeConstraints.containsKey(it) }
    .map { (item, expression) -> Constraint.atLeast(expression, -provisions.getOrDefault(item, 0.br)) }

  /* PRIMARY PLAN */

  val unlimited = outcomes.filterIsInstance<OptimizeRequest.Minimum>().map { it.item }
  val unrealized = outcomes.filterIsInstance<OptimizeRequest.Range>().map { it.item }.toMutableSet()

  var solution: Map<Recipe, BigRational>
  do {
    val principal = (unlimited + unrealized).firstOrNull() ?: outcomes.first().item
    val objective = expressions[principal]!!

    val limitConstraints =
      unrealized.map { item -> Constraint.atMost(expressions[item]!!, limits[item]!!) }
    val balanceConstraints = (unlimited + unrealized).filterNot { it == principal }.map { item ->
      Constraint.equalTo(
        expressions[item]!! - objective,
        requirements[item]!! - requirements[principal]!!
      )
    }

    try {
      val constraints = outcomeConstraints.values.flatten() + basicConstraints + limitConstraints + balanceConstraints
      solution = maximize(objective, constraints, BigRational.FACTORY)
    } catch (e: InfeasibleSolutionException) {
      return@coroutineScope EMPTY
    }

    val newlyRealized = unrealized.associateWith { expressions[it]!!(solution) }
      .filter { (item, produced) -> produced == limits[item]!! }
      .onEach { (item, limit) ->
        unrealized -= item
        outcomeConstraints[item] = listOf(Constraint.equalTo(expressions[item]!!, limit))
      }.keys
  } while (newlyRealized.isNotEmpty() && (unrealized.isNotEmpty() || unlimited.isNotEmpty()))

  /* MINIMUMS FOR INPUTS */

  val inputMinimums = inputs.map { it.item }
    .associateWith { item ->
      async {
        (-expressions[item]!!).let {
          it(minimize(it, initialConstraints.values.flatten() + basicConstraints, BigRational.FACTORY))
        }
      }
    }

  /* MAXIMUMS FOR PRODUCTS */

  val productMaximums = outcomes.map { it.item }
    .associateWith { item ->
      async {
        val singularConstraints = initialConstraints.toMutableMap()
        singularConstraints[item] = listOf(Constraint.atLeast(expressions[item]!!, requirements[item]!!))
        (expressions[item]!!).let {
          it(maximize(it, singularConstraints.values.flatten() + basicConstraints, BigRational.FACTORY))
        }
      }
    }

  /* FINAL COMPILATION */

  OptimizeResponse(
    solution.mapValues { (_, x) -> x.toRational() },
    inputMinimums.mapValues { (_, x) -> x.await().toRational() },
    productMaximums.mapValues { (_, x) -> x.await().toRational() })
}

private fun consider(recipes: Iterable<Recipe>): Map<Item, Expression<Recipe, BigRational>> {
  val expressions = mutableMapOf<Item, Expression<Recipe, BigRational>>()
  recipes.forEach { recipe ->
    recipe.components.forEach { (item, quantity) ->
      val expression = (quantity * 60.q / recipe.time).br * recipe
      expressions[item] = expressions[item]?.let { it + expression } ?: expression
    }
  }
  return expressions
}
