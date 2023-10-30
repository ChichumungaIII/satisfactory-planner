package app.api.optimize.v2.response

import app.game.data.Item
import app.game.data.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
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

    infix fun Recipe.clock(rate: Rational) {
      this at rate / 100.q
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

  override fun toString() = buildString {
    appendLine("Consumed:")
    consumed.forEach { consumption ->
      appendLine("  ${consumption.amount} ${consumption.item} (${consumption.consumed} / ${consumption.demand})")
    }

    appendLine("Produced:")
    produced.forEach { production ->
      appendLine("  ${production.amount} ${production.item} (${production.potential})")
    }

    appendLine("Byproducts:")
    byproducts.forEach { (item, surplus) ->
      appendLine("  $surplus $item")
    }

    appendLine("Rates:")
    rates.entries.sortedBy { (recipe) -> recipe.ordinal }.forEach { (recipe, rate) ->
      appendLine("  $recipe @${rate * 100.q})")
    }
  }
}
