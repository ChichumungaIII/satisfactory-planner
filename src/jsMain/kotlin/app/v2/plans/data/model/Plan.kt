package app.v2.plans.data.model

import app.data.Item
import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class Plan(
  val id: ULong,
  val title: String,
  val activeStep: Int = 0,
  val inputs: List<PlanInput> = listOf(),
  val products: List<PlanProduct> = listOf(),
  val results: List<PlanResult>? = null,
) {
  private val resultsIndex by lazy { results?.associate { it.recipe to it } }
  fun getResult(recipe: Recipe) = resultsIndex?.get(recipe)
}

@Serializable
data class PlanInput(
  val item: Item? = null,
  val amount: Rational = 0.q,
)

@Serializable
data class PlanProduct(
  val item: Item? = null,
  val exact: Boolean = false,
  val amount: Rational = 0.q,
  val maximum: Rational? = null,
)

@Serializable
data class PlanResult(
  val recipe: Recipe,
  val clock: Rational,
  val details: Boolean,
)
