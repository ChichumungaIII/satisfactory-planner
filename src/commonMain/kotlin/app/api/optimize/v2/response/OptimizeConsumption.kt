package app.api.optimize.v2.response

import app.game.data.Item
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeConsumption(
  val item: Item,
  val amount: Rational,
  val consumed: Rational,
  val demand: Rational,
)
