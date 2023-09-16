package app.api.plan.v1

import app.api.common.Resource
import app.game.data.Item
import app.game.data.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class Plan(
  override val name: PlanName,
  val displayName: String,
  val partition: Partition,
) : Resource<PlanName> {
  val parent = name.save

  @Serializable
  data class Partition(
    val inputs: List<Input>,
    val products: List<Product>,
    val byproducts: List<Byproduct>,
    val partitions: List<Partition>,
    val targets: List<Target>,
    val optimized: Boolean,
  ) {
    companion object {
      private val EMPTY = Partition(
        inputs = listOf(),
        products = listOf(),
        byproducts = listOf(),
        partitions = listOf(),
        targets = listOf(),
        optimized = false,
      )

      fun empty() = EMPTY
    }
  }

  @Serializable
  data class Input(
    val item: Item?,
    val quantity: Rational?,
    val consumption: Rational? = null,
    val demand: Rational? = null,
  )

  @Serializable
  data class Product(
    val item: Item?,
    val maximize: Boolean,
    val weight: Rational?,
    val amount: Rational? = null,
    val potential: Rational? = null,
  )

  @Serializable
  data class Byproduct(
    val item: Item,
    val amount: Rational,
    val banned: Boolean,
  )

  @Serializable
  data class Target(
    val recipe: Recipe,
    val rate: Rational,
    val banned: Boolean = false,
    val restriction: Rational? = null,
    val details: Boolean = false,
  )
}
