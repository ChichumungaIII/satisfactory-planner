package app.api.optimize.v2

import app.game.data.Item
import app.game.data.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
  val inputs: List<Input>,
  val products: List<Product>,
  val byproducts: Map<Item, Rational>,
  val rates: Map<Recipe, Rational>
) {
  @Serializable
  data class Input(
    val item: Item,
    val quantity: Rational,
    val consumption: Rational,
    val demand: Rational,
  )

  @Serializable
  data class Product(
    val item: Item,
    val amount: Rational,
    val potential: Rational,
  )
}
