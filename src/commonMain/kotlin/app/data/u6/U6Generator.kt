package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Generator(
    val milestone: U6Milestone,
    val displayName: String,
    val cost: Map<U6Item, Rational>,
    val power: Rational = 0.q,
) {
    BIOMASS_BURNER(
        U6Milestone.HUB_UPGRADE_6,
        "Biomass Burner",
        cost = mapOf(
            U6Item.IRON_PLATE to 15.q,
            U6Item.IRON_ROD to 15.q,
            U6Item.WIRE to 25.q,
        ),
        power = 30.q,
    ),
    COAL_GENERATOR(
        U6Milestone.COAL_POWER,
        "Coal Generator",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 20.q,
            U6Item.ROTOR to 10.q,
            U6Item.CABLE to 30.q,
        ),
        power = 75.q,
    ),
}
