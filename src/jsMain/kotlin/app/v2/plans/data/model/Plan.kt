package app.v2.plans.data.model

import app.data.Item
import app.data.recipe.Recipe
import app.data.recipe.componentRate
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
  val minimums: Map<Item, Rational>? = null,
  val maximums: Map<Item, Rational>? = null,
) {
  private val resultsIndex by lazy { results?.associate { it.recipe to it } }
  fun getResult(recipe: Recipe) = resultsIndex?.get(recipe)

  val consumed by lazy { components.filterValues { it < 0.q }.mapValues { (_, rate) -> -rate } }
  val produced by lazy { components.filterValues { it > 0.q } }
  val components by lazy {
    val components = mutableMapOf<Item, Rational>()
    results?.forEach { result ->
      result.recipe.components.keys.forEach { item ->
        components[item] = result.recipe.componentRate(item, result.clock) + (components[item] ?: 0.q)
      }
    }
    components.toMap()
  }
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
