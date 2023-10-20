package app.api.optimize.v2

import app.api.optimize.v2.OptimizeResponse.Input
import app.api.optimize.v2.OptimizeResponse.Product
import app.game.data.Item
import app.game.data.Recipe
import util.math.Rational
import util.math.q

class OptimizeResponseBuilder private constructor(
  private val inputs: MutableList<Input> = mutableListOf(),
  private val products: MutableList<Product> = mutableListOf(),
  private val byproducts: MutableMap<Item, Rational> = mutableMapOf(),
  private val rates: MutableMap<Recipe, Rational> = mutableMapOf(),
) {
  companion object {
    class InputBuilder internal constructor(
      var consumption: Rational? = null,
      var demand: Rational? = null
    )

    fun optimizeResponse(init: OptimizeResponseBuilder.() -> Unit): OptimizeResponse {
      val builder = OptimizeResponseBuilder()
      builder.init()
      return OptimizeResponse(
        builder.inputs,
        builder.products,
        builder.byproducts,
        builder.rates,
      )
    }
  }

  fun input(item: Item, quantity: Rational, init: InputBuilder.() -> Unit) {
    val builder = InputBuilder()
    builder.init()
    inputs.add(
      Input(
        item, quantity,
        consumption = builder.consumption ?: throw Error("consumption"),
        demand = builder.demand ?: throw Error("demand")
      )
    )
  }

  fun product(item: Item, amount: Rational, potential: Rational) =
    products.add(Product(item, amount, potential))

  fun rate(recipe: Recipe, clock: Rational) {
    check(!this@OptimizeResponseBuilder.rates.containsKey(recipe))
    this@OptimizeResponseBuilder.rates[recipe] = clock / 100.q
  }
}
