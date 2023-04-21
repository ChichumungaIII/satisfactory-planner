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
  val inputs: List<PlanInput> = listOf(),
  val products: List<PlanProduct> = listOf(),
  val results: List<PlanResult>? = null,
  val minimums: Map<Item, Rational>? = null,
  val maximums: Map<Item, Rational>? = null,
) {
  fun addInput(input: PlanInput) =
    copy(inputs = inputs + input)

  fun setInput(index: Int, input: PlanInput) =
    copy(inputs = inputs.subList(0, index) + input + inputs.subList(index + 1, inputs.size))

  fun removeInput(index: Int) =
    copy(inputs = inputs.subList(0, index) + inputs.subList(index + 1, inputs.size))

  fun addProduct(product: PlanProduct) =
    copy(products = products + product)

  fun setProduct(index: Int, product: PlanProduct) =
    copy(products = products.subList(0, index) + product + products.subList(index + 1, products.size))

  fun removeProduct(index: Int) =
    copy(products = products.subList(0, index) + products.subList(index + 1, products.size))

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
) {
  fun toInput() = item?.let { OptimizeRequest.Input(it, amount) }
}

@Serializable
data class PlanProduct(
  val item: Item? = null,
  val requirement: Requirement = AtLeast(0.q),
  val details: Boolean = false,
) {
  fun toOutcome() = item?.let(requirement::toOutcome)

  @Serializable
  sealed interface Requirement {
    fun toAtLeast(): AtLeast
    fun toExactly(): Exactly
    fun toBetween(): Between

    fun toOutcome(item: Item): OptimizeRequest.Outcome
  }

  @Serializable
  data class AtLeast(val minimum: Rational) : Requirement {
    override fun toAtLeast() = this
    override fun toExactly() = Exactly(minimum)
    override fun toBetween() = Between(minimum, minimum)

    override fun toOutcome(item: Item) = OptimizeRequest.Minimum(item, minimum)
  }

  @Serializable
  data class Exactly(val amount: Rational) : Requirement {
    override fun toAtLeast() = AtLeast(amount)
    override fun toExactly() = this
    override fun toBetween() = Between(amount, amount)

    override fun toOutcome(item: Item) = OptimizeRequest.Exact(item, amount)
  }

  @Serializable
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
