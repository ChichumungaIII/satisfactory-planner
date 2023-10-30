package app.api.optimize.v2.request

import app.game.data.Item
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
sealed interface OptimizeOutput {
  val item: Item

  @Serializable
  data class Production(
    override val item: Item,
    val amount: Rational,
    val exact: Boolean = true,
  ) : OptimizeOutput

  @Serializable
  data class Maximization(
    override val item: Item,
    val weight: Rational,
  ) : OptimizeOutput
}
