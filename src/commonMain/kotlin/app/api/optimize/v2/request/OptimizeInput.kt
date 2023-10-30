package app.api.optimize.v2.request

import app.game.data.Item
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeInput(
  val item: Item,
  val amount: Rational,
  val required: Boolean = false,
)
