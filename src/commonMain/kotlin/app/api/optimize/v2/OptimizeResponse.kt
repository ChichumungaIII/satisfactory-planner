package app.api.optimize.v2

import app.game.data.Item
import app.game.data.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
  val demands: List<Demand>,
  val potentials: List<Potential>,
  val rates: List<Rate>,
) {
  @Serializable
  data class Demand(
    val item: Item,
    val demand: Rational,
  )

  @Serializable
  data class Potential(
    val item: Item,
    val amount: Rational,
  )

  @Serializable
  data class Rate(
    val recipe: Recipe,
    val rate: Rational,
  )
}
