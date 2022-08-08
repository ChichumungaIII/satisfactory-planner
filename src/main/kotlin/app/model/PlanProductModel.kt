package app.model

import app.model.game.u5.Item
import util.math.Rational

data class PlanProductModel(
    private val item: Item = Item.IRON_ORE,
    private val target: Rational = Rational.ZERO,
    private val maximum: Rational? = null,
) {
    fun item() = item
    fun target() = target
    fun maximum() = maximum

    fun setItem(item: Item) =
        copy(item = item)
}
