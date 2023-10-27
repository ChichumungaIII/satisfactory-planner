package app.api.optimize.v2.response

import app.game.data.Item
import app.game.data.Recipe
import util.math.Rational

data class OptimizeResponse(
  val consumed: List<OptimizeConsumption>,
  val produced: List<OptimizeProduction>,
  val byproducts: Map<Item, Rational>,
  val rates: Map<Recipe, Rational>,
) {
  companion object {
    fun optimizeResponse(init: Builder.() -> Unit) = Builder().apply(init).build()
  }

  class Builder {
    private val consumed = mutableListOf<OptimizeConsumption>()
    private val produced = mutableListOf<OptimizeProduction>()
    private val byproducts = mutableMapOf<Item, Rational>()
    private val rates = mutableMapOf<Recipe, Rational>()

    fun consume(item: Item, amount: Rational, consumed: Rational, demand: Rational) =
      this.consumed.add(OptimizeConsumption(item, amount, consumed, demand))

    fun produce(item: Item, amount: Rational, potential: Rational) =
      produced.add(OptimizeProduction(item, amount, potential))

    infix fun Item.byproduct(amount: Rational) {
      byproducts[this] = amount
    }

    infix fun Recipe.at(rate: Rational) {
      this@Builder.rates[this] = rate
    }

    fun build() = OptimizeResponse(
      consumed.toList(),
      produced.toList(),
      byproducts.toMap(),
      rates.toMap(),
    )
  }
}
