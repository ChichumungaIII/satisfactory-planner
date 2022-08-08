package app.model

import app.model.game.u5.Item
import util.math.Rational

data class PlanInputModel(
    private val item: Item = Item.IRON_ORE,
    private val target: Rational = Rational.ZERO,
    private val maximum: Rational = Rational.ZERO,
    private val minimum: Rational? = null,
) {
    fun item() = item
    fun target() = target
    fun maximum() = maximum
    fun minimum() = minimum

    fun setItem(item: Item) =
        copy(item = item)

    fun setTarget(target: Rational) =
        if (minimum() == null)
            copy(target = target, maximum = target)
        else
            copy(target = target)
}
