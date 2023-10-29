package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Item
import app.game.data.Recipe
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.maximize
import util.math.minimize

data class OptimizedPlan(
  val rates: Map<Recipe, BigRational>,
  val consumption: Map<Item, BigRational>,
  val demand: Map<Item, BigRational>,
  val production: Map<Item, BigRational>,
  val potential: Map<Item, BigRational>,
)

fun optimize(
  inputs: Map<Item, BigRational>,
  requirements: Map<Item, BigRational>,
  weights: Map<Item, BigRational>,
  restrictions: Map<Recipe, BigRational>,
  alternates: List<Recipe>,
): OptimizedPlan {
  check(inputs.values.all { it > 0.br }) { "Inputs must be positive." }
  check(requirements.values.all { it >= 0.br }) { "Requirements must be positive." }
  check(weights.values.all { it > 0.br }) { "Weights must be positive." }
  check(requirements.size + weights.size > 0) { "Must optimize at least one product." }
  checkDisjoint(inputs.keys, requirements.keys + weights.keys)

  val recipes = Recipe.entries.filterNot { it.alternate } + alternates
  val data = OptimizationData.create(inputs.keys, recipes.toSet() - restrictions.filterValues { it == 0.br }.keys)
  data.checkProducible(requirements)
  data.checkProducible(weights.keys)

  val itemConstraints = data.expressions.keys.associateWith { item ->
    val amount = requirements.getOrDefault(item, 0.br) - inputs.getOrDefault(item, 0.br)
    if (requirements.containsKey(item) && !weights.containsKey(item))
      Constraint.equalTo(data[item], amount)
    else Constraint.atLeast(data[item], amount)
  }
  val recipeConstraints = restrictions.filterValues { it > 0.br }.map { (recipe, rate) ->
    Constraint.atMost(1.br * recipe, rate)
  }

  /**********/
  /* TARGET */
  /**********/

  val rates = (if (weights.isEmpty()) {
    val objective = data[requirements.keys.first()]
    maximize(objective, itemConstraints.values + recipeConstraints, BigRational.FACTORY)
  } else {
    val objectives = weights.map { (item, weight) ->
      Objective(data[item], weight, requirements.getOrDefault(item, 0.br))
    }
    val primary = objectives[0]
    val balance = objectives.drop(1).map { secondary ->
      Constraint.equalTo(
        primary.expression * secondary.weight - secondary.expression * primary.weight,
        secondary.weight * primary.offset - primary.weight * secondary.offset
      )
    }
    maximize(primary.expression, itemConstraints.values + recipeConstraints + balance, BigRational.FACTORY)
  }).filterValues { it != 0.br }
  val amounts = data(rates)
  val consumption = amounts.filterValues { it < 0.br }.mapValues { (_, amount) -> -amount }
  val production = amounts.filterValues { it > 0.br }

  val planConstraints = (requirements.keys + weights.keys).associateWith { item ->
    Constraint.equalTo(data[item], production.getOrDefault(item, 0.br))
  }

  /***************/
  /* REQUIREMENT */
  /***************/

  val demandConstraints = (itemConstraints + planConstraints).values + recipeConstraints
  val demand = inputs.keys.associateWith { item -> -data[item] }
    .mapValues { (_, consumed) ->
      consumed(minimize(consumed, demandConstraints, BigRational.FACTORY))
    }

  /*************/
  /* POTENTIAL */
  /*************/

  val potential = planConstraints.keys.associateWith { data[it] }
    .mapValues { (item, produced) ->
      val constraints = (itemConstraints + planConstraints - item).values + recipeConstraints
      produced(maximize(produced, constraints, BigRational.FACTORY))
    }

  /********/
  /* PLAN */
  /********/

  return OptimizedPlan(
    rates = rates,
    consumption = consumption,
    demand = demand,
    production = production,
    potential = potential,
  )
}

private data class OptimizationData(
  val recipes: Set<Recipe>,
  val expressions: Map<Item, Expression<Recipe, BigRational>>,
) {
  companion object {
    fun create(inputs: Set<Item>, recipes: Set<Recipe>): OptimizationData {
      // TODO: Reintroduce the recipe reduction optimization.
      // val recipes = getReachableRecipes(inputs, recipes)
      val expressions = getExpressions(recipes)
      return OptimizationData(recipes, expressions)
    }

    private fun getExpressions(recipes: Set<Recipe>): Map<Item, Expression<Recipe, BigRational>> =
      recipes.flatMap { it.rates.map { (item, rate) -> item to rate.br * it } }
        .fold(mutableMapOf()) { map, (item, expression) ->
          map.also { it.merge(item, expression, Expression<Recipe, BigRational>::plus) }
        }

    // private tailrec fun getReachableRecipes(
    //   items: Set<Item>,
    //   recipes: Set<Recipe>,
    // ): Set<Recipe> {
    //   val reachable = recipes.filter { items.containsAll(it.inputs.keys) }.toSet()
    //   val next = items + reachable.flatMap { it.outputs.keys }
    //   return reachable.takeIf { items == next } ?: getReachableRecipes(next, recipes)
    // }
  }

  operator fun get(item: Item) = expressions[item] ?: throw Error("Unsupported item [$item].")

  operator fun invoke(rates: Map<Recipe, BigRational>) =
    expressions.mapValues { (_, expression) -> expression(rates) }

  // TODO: Using default recipes, suggest resource input amounts that yield these rates.
  fun checkProducible(products: Map<Item, BigRational>) = checkProducible(products.keys)

  // TODO: Using default recipes, suggest resource inputs.
  fun checkProducible(items: Set<Item>) {
    val impossible = items - expressions.keys
    check(impossible.isEmpty()) { "Cannot produce products $impossible from the provided inputs." }
  }
}

private fun checkDisjoint(inputs: Set<Item>, products: Set<Item>) {
  val intersection = inputs.intersect(products)
  check(intersection.isEmpty()) { "Cannot optimize items $intersection which are both inputs and products." }
}
