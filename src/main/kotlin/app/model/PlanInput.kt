package app.model

import util.math.Rational

data class PlanInput(
    private val item: Item = Item.IRON_ORE,
    private val maximum: Rational = Rational.ZERO,
    private val minimum: Rational?,
) {
    fun item() = item
    fun maximum() = maximum
    fun minimum() = minimum
}
