package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Item
import app.game.data.Recipe
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import util.collections.merge
import util.math.Expression
import util.math.Expression.Companion.times

data class Expressions(private val expressions: Map<Item, Expression<OpVar, BigRational>>) {
  val items = expressions.keys

  companion object {
    fun create(recipes: Set<Recipe>, inputs: Set<Item>) = Expressions(
      getUsableRecipes(recipes, inputs)
        .flatMap { it.rates.map { (item, rate) -> item to rate.br * (RecipeVar(it) as OpVar) } }
        .fold(mapOf()) { map, (item, expression) -> map.merge(item, expression, Expression<OpVar, BigRational>::plus) }
    )

    private tailrec fun getUsableRecipes(allRecipes: Set<Recipe>, items: Set<Item>): Set<Recipe> {
      val usable = allRecipes.filter { items.containsAll(it.inputs.keys) }.toSet()
      val next = items + usable.flatMap { it.outputs.keys }
      return usable.takeIf { items.containsAll(next) } ?: getUsableRecipes(allRecipes, next)
    }
  }

  fun checkProducible(expected: Set<Item>) {
    val impossible = expected - items
    check(impossible.isEmpty()) { "Cannot produce $impossible from the provided inputs." }
  }

  fun productionOf(item: Item) = expressions[item] ?: throw Error("Unsupported item [$item].")
  fun consumptionOf(item: Item) = -productionOf(item)
}
