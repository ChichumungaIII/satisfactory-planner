package app.api.optimize.v2.request

import app.game.data.Item
import util.math.Rational

sealed interface OptimizeOutput {
  val item: Item

  data class Production(
    override val item: Item,
    val amount: Rational,
    val exact: Boolean = true,
  ) : OptimizeOutput

  data class Maximization(
    override val item: Item,
    val weight: Rational,
  ) : OptimizeOutput
}
