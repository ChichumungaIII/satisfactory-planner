package app.api.plan.v1

import app.data.Item
import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class Plan(
  val name: PlanName,
  val displayName: String,
  val partition: Partition,
) {
  val parent = name.save

  @Serializable
  data class Partition(
    val inputs: List<Input>,
    val products: List<Product>,
    val byproducts: List<Byproduct>,
    val partitions: List<Partition>,
    val targets: List<Target>,
  )

  @Serializable
  data class Input(
    val item: Item,
    val quantity: Rational,
    val requirement: Rational? = null,
  )

  @Serializable
  data class Product(
    val item: Item,
    val maximize: Boolean,
    val weight: Rational,
    val amount: Rational? = null,
    val potential: Rational? = null,
  )

  @Serializable
  data class Byproduct(
    val item: Item,
    val banned: Boolean,
  )

  @Serializable
  data class Target(
    val recipe: Recipe,
    val rate: Rational,
    val restriction: Rational? = null,
    val details: Boolean = false,
  )
}
