package app.api.optimize.v2

import app.game.data.Item
import app.game.data.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
  val demands: List<Demand>,
  val productions: List<Production>,
  val rates: List<Rate>,
) {
  @Serializable
  data class Demand(
    val item: Item,
    val demand: Rational,
  )

  @Serializable
  data class Production(
    val item: Item,
    val amount: Rational,
    val potential: Rational,
  )

  @Serializable
  data class Rate(
    val recipe: Recipe,
    val rate: Rational,
  )
}
