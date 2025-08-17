package app.api.optimize.v2.request

import app.game.data.Item
import app.game.data.RecipeV2
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class OptimizeRequest(
  val inputs: List<OptimizeInput>,
  val outputs: List<OptimizeOutput>,
  val recipes: Set<RecipeV2>,
  val limits: Map<RecipeV2, Rational>,
) {
  companion object {
    fun optimizeRequest(init: Builder.() -> Unit) = Builder().apply(init).build()
  }

  class Builder {
    private val inputs = mutableListOf<OptimizeInput>()
    private val outputs = mutableListOf<OptimizeOutput>()
    private val recipes = mutableSetOf<RecipeV2>()
    private val limits = mutableMapOf<RecipeV2, Rational>()

    fun input(item: Item, amount: Rational, required: Boolean = false) =
      inputs.add(OptimizeInput(item, amount, required))

    fun produce(item: Item, amount: Rational) =
      outputs.add(OptimizeOutput.Production(item, amount, exact = true))

    fun require(item: Item, amount: Rational) =
      outputs.add(OptimizeOutput.Production(item, amount, exact = false))

    fun maximize(item: Item, weight: Rational) =
      outputs.add(OptimizeOutput.Maximization(item, weight))

    fun allow(recipe: RecipeV2) = recipes.add(recipe)
    fun allowAll(recipes: Collection<RecipeV2>) = this.recipes.addAll(recipes)
    fun allowOnly(vararg recipes: RecipeV2) {
      this.recipes.clear()
      this.recipes.addAll(recipes)
    }

    infix fun RecipeV2.limitToClock(clock: Rational) {
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
