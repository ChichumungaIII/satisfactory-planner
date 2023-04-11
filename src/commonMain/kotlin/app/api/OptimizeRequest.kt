package app.api

import app.data.Item
import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeRequest(
  val recipes: Set<Recipe>,
  val inputs: List<Input>,
  val outcomes: List<Outcome>,
) {
  @Serializable
  data class Input(
    val item: Item,
    val quantity: Rational,
  )

  @Serializable
  sealed interface Outcome {
    val item: Item
  }

  @Serializable
  data class Exact(override val item: Item, val amount: Rational) : Outcome

  @Serializable
  data class Minimum(override val item: Item, val minimum: Rational) : Outcome

  @Serializable
  data class Range(override val item: Item, val minimum: Rational, val maximum: Rational) : Outcome
}
