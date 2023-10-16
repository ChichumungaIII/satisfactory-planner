package app.api.optimize.v2

import app.api.optimize.v2.OptimizeResponse.Input
import app.api.optimize.v2.OptimizeResponse.Product
import app.game.data.Item
import app.game.data.Recipe
import util.math.Rational

class OptimizeResponseBuilder private constructor(
  private val inputs: MutableList<Input> = mutableListOf(),
  private val products: MutableList<Product> = mutableListOf(),
  private val byproducts: MutableMap<Item, Rational> = mutableMapOf(),
  private val rates: MutableMap<Recipe, Rational> = mutableMapOf(),
) {
  companion object {
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

  operator fun Input.unaryPlus() {
    inputs.add(this)
  }

  operator fun Product.unaryPlus() {
    products.add(this)
  }

  operator fun Pair<Item, Rational>.unaryPlus() {
    val (item, amount) = this
    byproducts[item] = amount
  }

  operator fun Recipe.plusAssign(rate: Rational) {
    check(!this@OptimizeResponseBuilder.rates.containsKey(this))
    this@OptimizeResponseBuilder.rates[this] = rate
  }

  fun input(item: Item, quantity: Rational, init: InputBuilder.() -> Unit): Input {
    val builder = InputBuilder()
    builder.init()
    return Input(
      item, quantity,
      consumption = builder.consumption ?: throw Error("consumption"),
      demand = builder.demand ?: throw Error("demand")
    )
  }

  class InputBuilder internal constructor(
    var consumption: Rational? = null,
    var demand: Rational? = null
  )

  fun product(item: Item, amount: Rational, potential: Rational) =
    Product(item, amount, potential)
}
