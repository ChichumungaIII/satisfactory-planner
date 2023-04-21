package app.v2.plans.data.model

import app.api.OptimizeRequest
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
  val details: Boolean = false,
)

@Serializable
data class PlanProduct(
  val item: Item? = null,
  val requirement: Requirement = AtLeast(0.q),
  val details: Boolean = false,
) {
  @Serializable
  sealed interface Requirement {
    fun toAtLeast(): AtLeast
    fun toExactly(): Exactly
    fun toBetween(): Between

    fun toOutcome(item: Item): OptimizeRequest.Outcome
  }

  data class AtLeast(val minimum: Rational) : Requirement {
    override fun toAtLeast() = this
    override fun toExactly() = Exactly(minimum)
    override fun toBetween() = Between(minimum, minimum)

    override fun toOutcome(item: Item) = OptimizeRequest.Minimum(item, minimum)
  }

  data class Exactly(val amount: Rational) : Requirement {
    override fun toAtLeast() = AtLeast(amount)
    override fun toExactly() = this
    override fun toBetween() = Between(amount, amount)

    override fun toOutcome(item: Item) = OptimizeRequest.Exact(item, amount)
  }

  data class Between(val minimum: Rational, val maximum: Rational) : Requirement {
    override fun toAtLeast() = AtLeast(minimum)
    override fun toExactly() = Exactly(minimum)
    override fun toBetween() = this

    override fun toOutcome(item: Item) = OptimizeRequest.Range(item, minimum, maximum)
  }
}

@Serializable
data class PlanResult(
  val recipe: Recipe,
  val clock: Rational,
  val details: Boolean,
)
