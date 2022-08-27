package app.model

import app.model.game.u5.Item
import util.math.Rational

data class PlanInputModel(
    /** The item being provided. */
    val item: Item = Item.IRON_ORE,
    /** The most of `item` that can be supplied as part of this plan. Set by user input. */
    val provision: Rational = Rational.ZERO,
    /**
     * The amount of `item` that will be consumed by the most recent calculated plan. Set to `null`
     * if a plan has not yet been computed with this input.
     */
    val target: Rational? = null,
    /**
     * The minimum amount of `item` that must be consumed in order to produce all plan products at
     * their respective minimum levels.
     */
    val minimum: Rational? = null,
)
