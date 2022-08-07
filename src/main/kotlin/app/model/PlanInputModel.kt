package app.model

import app.model.game.u5.Item
import util.math.Rational

data class PlanInputModel(
    private val item: Item = Item.IRON_ORE,
    private val maximum: Rational = Rational.ZERO,
    private val minimum: Rational? = null,
) {
    fun item() = item
    fun maximum() = maximum
    fun minimum() = minimum
}
