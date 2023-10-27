package app.api.optimize.v2.request

import app.game.data.Item
import app.game.data.Recipe
import util.math.Rational
import util.math.q

data class OptimizeRequest(
  val inputs: List<OptimizeInput>,
  val outputs: List<OptimizeOutput>,
  val recipes: Set<Recipe>,
  val limits: Map<Recipe, Rational>,
) {
  companion object {
    fun optimizeRequest(init: Builder.() -> Unit) = Builder().apply(init).build()
  }

  class Builder {
    private val inputs = mutableListOf<OptimizeInput>()
    private val outputs = mutableListOf<OptimizeOutput>()
    private val recipes = mutableSetOf<Recipe>()
    private val limits = mutableMapOf<Recipe, Rational>()

    fun input(item: Item, amount: Rational, required: Boolean = false) =
      inputs.add(OptimizeInput(item, amount, required))

    fun produce(item: Item, amount: Rational, exact: Boolean = false) =
      outputs.add(OptimizeOutput.Production(item, amount, exact))

    fun maximize(item: Item, weight: Rational) =
      outputs.add(OptimizeOutput.Maximization(item, weight))

    fun allow(recipe: Recipe) = recipes.add(recipe)
    fun allowAll(recipes: Collection<Recipe>) = this.recipes.addAll(recipes)

    infix fun Recipe.limitTo(clock: Rational) {
      limits[this] = clock / 100.q
    }

    fun build() = OptimizeRequest(
      inputs.toList(),
      outputs.toList(),
      recipes.toSet(),
      limits.toMap()
    )
  }
}
