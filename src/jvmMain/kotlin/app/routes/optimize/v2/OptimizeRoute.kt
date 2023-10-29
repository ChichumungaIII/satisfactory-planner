package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeOutput.Maximization
import app.api.optimize.v2.request.OptimizeOutput.Production
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeResponse
import app.api.optimize.v2.response.OptimizeResponse.Companion.optimizeResponse
import app.game.data.Item
import app.game.data.Recipe
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
import util.collections.merge
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.Rational
import util.math.maximize
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
  val restrictions = limits.map { (recipe, rate) -> Constraint.atMost(1.br * recipe, rate.br) }

  val rates: Map<Recipe, BigRational> = (if (weights.isEmpty()) {
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

  return optimizeResponse {
    rates.forEach { (recipe, rate) -> recipe at rate.toRational() }
  }
}

fun <T> Iterable<T>.index(key: (T) -> Item, value: (T) -> Rational) = index(key, { value(it).br }, BigRational::plus)

fun <T, V> Iterable<T>.index(key: (T) -> Item, value: (T) -> V, merger: (V, V) -> V) =
  fold(mapOf<Item, V>()) { map, e -> map.merge(key(e), value(e), merger) }

private fun maximize(
  objective: Expression<Recipe, BigRational>,
  constraints: List<Constraint<Recipe, BigRational>>
) = maximize(objective, constraints, BigRational.FACTORY)
