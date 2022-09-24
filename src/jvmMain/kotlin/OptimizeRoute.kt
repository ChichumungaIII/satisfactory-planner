package com.chichumunga.satisfactory

import app.api.OptimizeRequest
import app.api.OptimizeResponse
import app.data.u5.Item
import app.data.u5.Recipe
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.InfeasibleSolutionException
import util.math.Rational
import util.math.maximize
import util.math.minimize
import util.math.q

private val EMPTY = OptimizeResponse(mapOf(), mapOf(), mapOf())

fun Routing.optimizeRoute() {
    post("/v1/optimize") {
        val request = Json.decodeFromString<OptimizeRequest>(call.receiveText())
        call.respondText { Json.encodeToString(optimize(request)) }
    }
}

private suspend fun optimize(request: OptimizeRequest) = coroutineScope {
    val (recipes, inputs, products) = request

    if (inputs.isEmpty() || products.isEmpty()) return@coroutineScope EMPTY

    val expressions = consider(recipes)
    check(expressions.keys.containsAll(inputs.map { it.item }))
    check(expressions.keys.containsAll(products.map { it.item }))

    val provisions = inputs.associate { it.item to it.quantity }
    val requirements = products.associate { it.item to it.minimum }
    val limits = products.filterNot { it.maximum == null }.associate { it.item to it.maximum!! }

    val planConstraints =
        expressions.map { (item, expression) ->
            val result = requirements.getOrElse(item) { 0.q } - provisions.getOrElse(item) { 0.q }
            Constraint.atLeast(expression, result)
        }

    /* PRIMARY PLAN */

    val unlimited = products.filter { it.maximum == null }.map { it.item }
    val unrealized = products.filterNot { it.maximum == null }.mapTo(mutableSetOf()) { it.item }
    val realized = mutableSetOf<Item>()

    var solution: Map<Recipe, Rational>
    do {
        val principal = (unlimited + unrealized).first()
        val objective = expressions[principal]!!

        val limitConstraints =
            unrealized.map { item -> Constraint.atMost(expressions[item]!!, limits[item]!!) }
        val realizedConstraints =
            realized.map { item -> Constraint.equalTo(expressions[item]!!, limits[item]!!) }
        val balanceConstraints = (unlimited + unrealized).filterNot { it == principal }.map { item ->
            Constraint.equalTo(
                expressions[item]!! - objective,
                requirements[item]!! - requirements[principal]!!
            )
        }

        try {
            val constraints = planConstraints + limitConstraints + realizedConstraints + balanceConstraints
            solution = maximize(objective, constraints, Rational.FACTORY)
        } catch (e: InfeasibleSolutionException) {
            return@coroutineScope EMPTY
        }

        val newlyRealized = unrealized.filter { item -> expressions[item]!!(solution) == limits[item]!! }.toSet()
        unrealized -= newlyRealized
        realized += newlyRealized
    } while (newlyRealized.isNotEmpty())

    /* MINIMUMS FOR INPUTS */

    val inputMinimums = inputs.map { it.item }
        .associateWith { item ->
            (-expressions[item]!!).let {
                it(minimize(it, planConstraints, Rational.FACTORY))
            }
        }

    /* MAXIMUMS FOR PRODUCTS */

    val productMaximums = products.map { it.item }
        .associateWith { item -> (expressions[item]!!).let { it(maximize(it, planConstraints, Rational.FACTORY)) } }

    /* FINAL COMPILATION */

    return@coroutineScope OptimizeResponse(solution, inputMinimums, productMaximums)
}

private fun consider(recipes: Iterable<Recipe>): Map<Item, Expression<Recipe, Rational>> {
    val expressions = mutableMapOf<Item, Expression<Recipe, Rational>>()
    for (recipe in recipes) {
        for (component in recipe.products) {
            val item = component.item
            val expression = (component.quantity * 60.q / recipe.time) * recipe
            expressions[item] = expressions[item]?.let { it + expression } ?: expression
        }
        for (component in recipe.inputs) {
            val item = component.item
            val expression = -(component.quantity * 60.q / recipe.time) * recipe
            expressions[item] = expressions[item]?.let { it + expression } ?: expression
        }
    }
    return expressions
}
