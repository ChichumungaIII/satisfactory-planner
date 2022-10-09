package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Milestone(
    val tier: U6Tier,
    val displayName: String,
    val cost: Map<U6Item, Rational>,
) {
    /* Tier 0 */

    GAME_START(
        U6Tier.TIER_0,
        "Game start",
        cost = mapOf(),
    ),
    HUB_UPGRADE_1(
        U6Tier.TIER_0,
        "HUB Upgrade 1",
        cost = mapOf(
            U6Item.IRON_ROD to 10.q,
        ),
    ),
    HUB_UPGRADE_2(
        U6Tier.TIER_0,
        "HUB Upgrade 2",
        cost = mapOf(
            U6Item.IRON_ROD to 20.q,
            U6Item.IRON_PLATE to 10.q,
        ),
    ),
    HUB_UPGRADE_3(
        U6Tier.TIER_0,
        "HUB Upgrade 3",
        cost = mapOf(
            U6Item.IRON_PLATE to 20.q,
            U6Item.IRON_ROD to 20.q,
            U6Item.WIRE to 20.q,
        ),
    ),
    HUB_UPGRADE_4(
        U6Tier.TIER_0,
        "HUB Upgrade 4",
        cost = mapOf(
            U6Item.IRON_PLATE to 75.q,
            U6Item.CABLE to 20.q,
            U6Item.CONCRETE to 10.q,
        ),
    ),
    HUB_UPGRADE_5(
        U6Tier.TIER_0,
        "HUB Upgrade 5",
        cost = mapOf(
            U6Item.IRON_ROD to 75.q,
            U6Item.CABLE to 50.q,
            U6Item.CONCRETE to 20.q,
        ),
    ),
    HUB_UPGRADE_6(
        U6Tier.TIER_0,
        "HUB Upgrade 6",
        cost = mapOf(
            U6Item.IRON_ROD to 100.q,
            U6Item.IRON_PLATE to 100.q,
            U6Item.WIRE to 100.q,
            U6Item.CONCRETE to 50.q,
        ),
    ),

    /* Tier 1 */

    BASE_BUILDING(
        U6Tier.TIER_1,
        "Base Building",
        cost = mapOf(
            U6Item.CONCRETE to 200.q,
            U6Item.IRON_PLATE to 100.q,
            U6Item.IRON_ROD to 100.q,
        ),
    ),
    LOGISTICS(
        U6Tier.TIER_1,
        "Logistics",
        cost = mapOf(
            U6Item.IRON_PLATE to 150.q,
            U6Item.IRON_ROD to 150.q,
            U6Item.WIRE to 300.q,
        ),
    ),
    FIELD_RESEARCH(
        U6Tier.TIER_1,
        "Field Research",
        cost = mapOf(
            U6Item.WIRE to 300.q,
            U6Item.SCREW to 300.q,
            U6Item.IRON_PLATE to 100.q,
        ),
    ),

    /* Tier 2 */

    PART_ASSEMBLY(
        U6Tier.TIER_2,
        "Part Assembly",
        cost = mapOf(
            U6Item.CABLE to 200.q,
            U6Item.IRON_ROD to 200.q,
            U6Item.SCREW to 500.q,
            U6Item.IRON_PLATE to 300.q,
        ),
    ),
    OBSTACLE_CLEARING(
        U6Tier.TIER_2,
        "Obstacle Clearing",
        cost = mapOf(
            U6Item.SCREW to 500.q,
            U6Item.CABLE to 100.q,
            U6Item.CONCRETE to 100.q,
        ),
    ),
    JUMP_PADS(
        U6Tier.TIER_2,
        "Jump Pads",
        cost = mapOf(
            U6Item.ROTOR to 50.q,
            U6Item.IRON_PLATE to 300.q,
            U6Item.CABLE to 150.q,
        ),
    ),
    RESOURCE_SINK_BONUS_PROGRAM(
        U6Tier.TIER_2,
        "Resource Sink Bonus Program",
        cost = mapOf(
            U6Item.CONCRETE to 400.q,
            U6Item.WIRE to 500.q,
            U6Item.IRON_ROD to 200.q,
            U6Item.IRON_PLATE to 200.q,
        ),
    ),
    LOGISTICS_MK_2(
        U6Tier.TIER_2,
        "Logistics Mk. 2",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 50.q,
            U6Item.CONCRETE to 200.q,
            U6Item.IRON_ROD to 300.q,
            U6Item.IRON_PLATE to 300.q,
        ),
    ),

    /* Tier 3 */

    COAL_POWER(
        U6Tier.TIER_3,
        "Coal Power",
        cost = mapOf(
            U6Item.REINFORCED_IRON_PLATE to 150.q,
            U6Item.ROTOR to 50.q,
            U6Item.CABLE to 300.q,
        ),
    ),
    VEHICULAR_TRANSPORT(
        U6Tier.TIER_3,
        "Vehicular Transport",
        cost = mapOf(
            U6Item.MODULAR_FRAME to 25.q,
            U6Item.ROTOR to 100.q,
            U6Item.CABLE to 200.q,
            U6Item.IRON_ROD to 400.q,
        ),
    ),
    BASIC_STEEL_PRODUCTION(
        U6Tier.TIER_3,
        "Basic Steel Production",
        cost = mapOf(
            U6Item.MODULAR_FRAME to 50.q,
            U6Item.ROTOR to 150.q,
            U6Item.CONCRETE to 300.q,
            U6Item.WIRE to 1000.q,
        ),
    ),

    /* Tier 4 */

    // TODO: Fill these in
    // ADVANCED_STEEL_PRODUCTION(),
    // IMPROVED_MELEE_COMBAT(),
    // HYPERTUBES(),
    // LOGISTICS_MK_3(),
}
