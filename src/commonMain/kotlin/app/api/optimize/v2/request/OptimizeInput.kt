package app.api.optimize.v2.request

import app.game.data.Item
import util.math.Rational

data class OptimizeInput(
  val item: Item,
  val amount: Rational,
  val required: Boolean = false,
)
