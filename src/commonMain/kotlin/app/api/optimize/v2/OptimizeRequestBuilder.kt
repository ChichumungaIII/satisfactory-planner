package app.api.optimize.v2

import app.api.optimize.v2.OptimizeRequest.Input
import app.api.optimize.v2.OptimizeRequest.Product
import app.api.optimize.v2.OptimizeRequest.Restriction
import app.game.data.Item
import app.game.data.Recipe
import util.math.Rational

class OptimizeRequestBuilder private constructor(
  private val inputs: MutableList<Input> = mutableListOf(),
  private val products: MutableList<Product> = mutableListOf(),
  private val restrictions: MutableList<Restriction> = mutableListOf(),
  private val alternates: MutableList<Recipe> = mutableListOf(),
) {
  companion object {
    fun optimizeRequest(init: OptimizeRequestBuilder.() -> Unit): OptimizeRequest {
      val builder = OptimizeRequestBuilder()
      builder.init()
      return OptimizeRequest(
        builder.inputs,
        builder.products,
        builder.restrictions,
        builder.alternates,
      )
    }
  }

  operator fun Input.unaryPlus() {
    inputs.add(this)
  }

  operator fun Product.unaryPlus() {
    products.add(this)
  }

  operator fun Restriction.unaryPlus() {
    restrictions.add(this)
  }

  operator fun Recipe.unaryPlus() {
    check(alternate) { "Recipe [$this] is not an alternate recipe." }
    alternates.add(this)
  }

  fun input(item: Item, quantity: Rational, required: Boolean = false) = Input(item, quantity, required)
  fun productAmount(item: Item, amount: Rational) = Product.amount(item, amount)
  fun productWeight(item: Item, weight: Rational) = Product.weight(item, weight)
  fun restriction(recipe: Recipe, rate: Rational) = Restriction(recipe, rate)
}
