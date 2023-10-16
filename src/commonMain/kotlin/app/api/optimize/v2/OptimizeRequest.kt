package app.api.optimize.v2

import app.game.data.Item
import app.game.data.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeRequest(
  val inputs: List<Input>,
  val products: List<Product>,
  val restrictions: List<Restriction>,
  val alternates: List<Recipe>,
) {
  @Serializable
  data class Input(
    val item: Item,
    val quantity: Rational,
  )

  @Serializable
  data class Product(
    val item: Item,
    val objective: Objective,
  ) {
    @Serializable
    data class Objective(
      val kind: Kind,
      val amount: Rational? = null,
      val weight: Rational? = null,
    ) {
      enum class Kind { AMOUNT, WEIGHT }

      companion object {
        fun amount(amount: Rational) = Objective(Kind.AMOUNT, amount = amount)
        fun weight(weight: Rational) = Objective(Kind.WEIGHT, weight = weight)
      }

      fun toAmount() = amount ?: throw Error("Cannot call #toAmount() on Objective with kind=$kind")
      fun toWeight() = weight ?: throw Error("Cannot call #toWeight() on Objective with kind=$kind")
    }

    companion object {
      fun amount(item: Item, amount: Rational) = Product(item, Objective.amount(amount))
      fun weight(item: Item, weight: Rational) = Product(item, Objective.weight(weight))
    }
  }

  @Serializable
  data class Restriction(
    val recipe: Recipe,
    val rate: Rational,
  )
}
