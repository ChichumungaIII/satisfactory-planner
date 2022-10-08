package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Building(
    val milestone: U6Milestone,
    val displayName: String,
    val cost: Map<U6Item, Rational>,
) {
    CRAFT_BENCH(
        U6Milestone.GAME_START,
        "Craft Bench",
        cost = mapOf(
            U6Item.IRON_PLATE to 3.q,
            U6Item.IRON_ROD to 2.q,
        )
    ),
    EQUIPMENT_WORKSHOP(
        U6Milestone.HUB_UPGRADE_1,
        "Equipment Workshop",
        cost = mapOf(
            U6Item.IRON_PLATE to 6.q,
            U6Item.IRON_ROD to 4.q,
        ),
    ),
    SMELTER(
        U6Milestone.HUB_UPGRADE_2,
        "Smelter",
        cost = mapOf(
            U6Item.IRON_ROD to 5.q,
            U6Item.WIRE to 8.q,
        ),
    ),
    CONSTRUCTOR(
        U6Milestone.HUB_UPGRADE_3,
        "Constructor",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 2.q,
            U6Item.CABLE to 8.q,
        ),
    ),
    MINER_MK_1(
        U6Milestone.HUB_UPGRADE_5,
        "Miner Mk. 1",
        cost = mapOf(
            U6Item.PORTABLE_MINER to 1.q,
            U6Item.IRON_PLATE to 10.q,
            U6Item.CONCRETE to 10.q,
        ),
    ),
    BIOMASS_BURNER(
        U6Milestone.HUB_UPGRADE_6,
        "Biomass Burner",
        cost = mapOf(
            U6Item.IRON_PLATE to 15.q,
            U6Item.IRON_ROD to 15.q,
            U6Item.WIRE to 25.q,
        ),
    ),
}
