package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Recipe(
    val milestone: U6Milestone,
    val displayName: String,
    val time: Rational,
    val components: Map<U6Item, Rational>,
    val buildings: List<U6Building>,
    val alternate: Boolean = false,
) {
    /* Onboarding -- Game Start */

    IRON_INGOT(
        U6Milestone.GAME_START,
        "Iron Ingot",
        time = 2.q,
        components = mapOf(
            U6Item.IRON_ORE to -(1.q),
            U6Item.IRON_INGOT to 1.q,
        ),
        buildings = listOf(U6Building.SMELTER, U6Building.CRAFT_BENCH),
    ),
    IRON_PLATE(
        U6Milestone.GAME_START,
        "Iron Plate",
        time = 6.q,
        components = mapOf(
            U6Item.IRON_INGOT to -(3.q),
            U6Item.IRON_PLATE to 2.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    IRON_ROD(
        U6Milestone.GAME_START,
        "Iron Rod",
        time = 4.q,
        components = mapOf(
            U6Item.IRON_INGOT to -(1.q),
            U6Item.IRON_ROD to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    XENO_ZAPPER(
        U6Milestone.GAME_START,
        "Xeno-Zapper",
        time = 40.q,
        components = mapOf(
            U6Item.IRON_ROD to -(10.q),
            U6Item.REINFORCED_IRON_PLATE to -(2.q),
            U6Item.CABLE to -(15.q),
            U6Item.WIRE to -(50.q),
            U6Item.XENO_ZAPPER to 1.q,
        ),
        buildings = listOf(U6Building.EQUIPMENT_WORKSHOP),
    ),

    /* Onboarding -- HUB Upgrade 1 */

    PORTABLE_MINER(
        U6Milestone.HUB_UPGRADE_1,
        "Portable Miner",
        time = 40.q,
        components = mapOf(
            U6Item.IRON_PLATE to -(2.q),
            U6Item.IRON_ROD to -(4.q),
            U6Item.PORTABLE_MINER to 1.q,
        ), buildings = listOf(U6Building.EQUIPMENT_WORKSHOP)
    ),

    /* Onboarding -- HUB Upgrade 2 */

    COPPER_INGOT(
        U6Milestone.HUB_UPGRADE_2,
        "Copper Ingot",
        time = 2.q,
        components = mapOf(
            U6Item.COPPER_ORE to -(1.q),
            U6Item.COPPER_INGOT to 1.q,
        ),
        buildings = listOf(U6Building.SMELTER, U6Building.CRAFT_BENCH),
    ),
    WIRE(
        U6Milestone.HUB_UPGRADE_2,
        "Wire",
        time = 4.q,
        components = mapOf(
            U6Item.COPPER_INGOT to -(1.q),
            U6Item.WIRE to 2.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    CABLE(
        U6Milestone.HUB_UPGRADE_2,
        "Cable",
        time = 2.q,
        components = mapOf(
            U6Item.WIRE to -(2.q),
            U6Item.CABLE to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),

    /* Onboarding -- HUB Upgrade 3 */

    CONCRETE(
        U6Milestone.HUB_UPGRADE_3,
        "Concrete",
        time = 4.q,
        components = mapOf(
            U6Item.LIMESTONE to -(3.q),
            U6Item.CONCRETE to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    SCREW(
        U6Milestone.HUB_UPGRADE_3,
        "Screw",
        time = 6.q,
        components = mapOf(
            U6Item.IRON_ROD to -(1.q),
            U6Item.SCREW to 4.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    REINFORCED_IRON_PLATE(
        U6Milestone.HUB_UPGRADE_3,
        "Reinforced Iron Plate",
        time = 12.q,
        components = mapOf(
            U6Item.IRON_PLATE to -(6.q),
            U6Item.SCREW to -(12.q),
            U6Item.REINFORCED_IRON_PLATE to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
}
