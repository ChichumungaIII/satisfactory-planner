package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequest
import app.api.optimize.v2.OptimizeRequest.Provision
import app.api.optimize.v2.OptimizeRequest.Requirement
import app.api.optimize.v2.OptimizeResponse
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
    } catch (e: Exception) {
      println("#optimize() call failed: [${e.message}]")
      e.printStackTrace()
      call.respond<String>(HttpStatusCode.InternalServerError, e.message ?: "Unknown error.")
    }
  }
}

private fun validate(request: OptimizeRequest) {
  val (provisions, requirements, restrictions, objectives) = request

  provisions.forEach { check(it.quantity >= 0.q) { "Illegal provision: ${it.item}" } }
  requirements.forEach { check(it.amount >= 0.q) { "Illegal requirement: ${it.item}" } }
  restrictions.forEach { check(it.rate >= 0.q) { "Illegal restriction: ${it.recipe}" } }
  objectives.forEach { check(it.weight > 0.q) { "Illegal objective: ${it.item}" } }

  check(requirements.size + objectives.size > 0) { "Optimization requires products." }
}

private suspend fun optimize(request: OptimizeRequest): OptimizeResponse {
  val provisions = request.provisions.foldToItemMap(Provision::item, Provision::quantity)
  val requirements = request.requirements.foldToItemMap(Requirement::item, Requirement::amount)
  val objectives = request.objectives

  val recipes = getReachableRecipes(provisions.keys)
  val expressions = getExpressions(recipes)

  // TODO: Return a response that recommends resource inputs.
  check(expressions.keys.containsAll(requirements.filterValues { it > 0.br }.keys)) {
    "Cannot create all required products."
  }
  check(expressions.keys.containsAll(objectives.map { it.item })) {
    "Cannot create all maximized products."
  }

  val productConstraints = requirements.mapValues { (item, requirement) ->
    val demand = requirement - (provisions[item] ?: 0.br)
    val expression = expressions[item]!!

    if (objectives.any { it.item == item })
      Constraint.atLeast(expression, demand)
    else Constraint.equalTo(expression, maxOf(demand, 0.br))
  }
  val basicConstraints = expressions.filterKeys { !productConstraints.containsKey(it) }
    .map { (item, expression) -> Constraint.atLeast(expression, -(provisions[item] ?: 0.br)) }
  val restrictionConstraints = request.restrictions.filter { recipes.contains(it.recipe) }
    .map { restriction -> Constraint.atMost(1.br * restriction.recipe, restriction.rate.br) }

  val solution = if (objectives.isEmpty()) {
    val objective = expressions[requirements.keys.first()]!!
    maximize(
      objective,
      basicConstraints + restrictionConstraints + productConstraints.values,
      BigRational.FACTORY
    )
  } else {
    val principalObjective = objectives[0]
    val principalOffset = principalObjective.item.let {
      (requirements[it] ?: 0.br) - (provisions[it] ?: 0.br)
    }
    val balanceConstraints = objectives.subList(1, objectives.size).map { secondaryObjective ->
      val secondaryOffset = secondaryObjective.item.let {
        (requirements[it] ?: 0.br) - (provisions[it] ?: 0.br)
      }

      Constraint.equalTo(
        expressions[principalObjective.item]!! * secondaryObjective.weight.br -
            expressions[secondaryObjective.item]!! * principalObjective.weight.br,
        secondaryObjective.weight.br * principalOffset -
            principalObjective.weight.br * secondaryOffset
      )
    }
    val objective = expressions[principalObjective.item]!!
    maximize(
      objective,
      basicConstraints + restrictionConstraints + productConstraints.values + balanceConstraints,
      BigRational.FACTORY
    )
  }

  return OptimizeResponse(
    demands = listOf(),
    potentials = listOf(),
    rates = solution.map { (recipe, rate) -> OptimizeResponse.Rate(recipe, rate.toRational()) }
      .filter { it.rate != 0.q },
  )
}

private fun getExpressions(recipes: Set<Recipe>) =
  recipes
    .flatMap { recipe ->
      recipe.inputs.map { Triple(recipe, it.key, -it.value) } +
          recipe.outputs.map { Triple(recipe, it.key, it.value) }
    }
    .map { (recipe, item, amount) -> item to (amount * 60.q / recipe.time).br * recipe }
    .fold(mutableMapOf<Item, Expression<Recipe, BigRational>>()) { map, (item, expression) ->
      map.also { it.merge(item, expression, Expression<Recipe, BigRational>::plus) }
    }.toMap()

private fun getReachableRecipes(items: Set<Item>): Set<Recipe> =
  Recipe.entries.filter { items.containsAll(it.inputs.keys) }.let { recipes ->
    (items + recipes.flatMap { it.outputs.keys }).takeIf { it.size > items.size }
      ?.let { getReachableRecipes(it) } ?: recipes.toSet()
  }

private fun <T> Iterable<T>.foldToItemMap(getItem: (T) -> Item, getRational: (T) -> Rational) =
  fold(mutableMapOf<Item, BigRational>()) { map, element ->
    map.also { it.merge(getItem(element), getRational(element).br, BigRational::plus) }
  }.toMap()
