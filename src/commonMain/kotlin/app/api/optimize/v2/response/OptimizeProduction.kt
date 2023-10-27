package app.api.optimize.v2.response

import app.game.data.Item
import util.math.Rational

data class OptimizeProduction(
  val item: Item,
  val weight: Rational?,
  val amount: Rational,
  val potential: Rational,
)
