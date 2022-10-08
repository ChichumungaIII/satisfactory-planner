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
}
