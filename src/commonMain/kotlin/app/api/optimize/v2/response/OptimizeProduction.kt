package app.api.optimize.v2.response

import app.game.data.Item
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeProduction(
  val item: Item,
  val amount: Rational,
  val potential: Rational,
)
