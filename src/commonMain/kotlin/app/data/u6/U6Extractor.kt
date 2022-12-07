package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Extractor(
    val milestone: U6Milestone,
    val displayName: String,
    val cost: Map<U6Item, Rational>,
    val power: Rational = 0.q,
) {
    MINER_MK_1(
        U6Milestone.HUB_UPGRADE_5,
        "Miner Mk. 1",
        cost = mapOf(
            U6Item.PORTABLE_MINER to 1.q,
            U6Item.IRON_PLATE to 10.q,
            U6Item.CONCRETE to 10.q,
        ),
        power = 5.q,
    ),
    WATER_EXTRACTOR(
        U6Milestone.COAL_POWER,
        "Water Extractor",
        cost = mapOf(
            U6Item.COPPER_SHEET to 20.q,
            U6Item.REINFORCED_IRON_PLATE to 10.q,
            U6Item.ROTOR to 10.q,
        ),
    ),
    OIL_EXTRACTOR(
        U6Milestone.OIL_PROCESSING,
        "Oil Extractor",
        cost = mapOf(
            U6Item.MOTOR to 15.q,
            U6Item.ENCASED_INDUSTRIAL_BEAM to 20.q,
            U6Item.CABLE to 60.q,
        ),
        power = 40.q,
    ),
}
