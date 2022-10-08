package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Milestone(
    val tier: U6Tier,
    val displayName: String,
    val cost: Map<U6Item, Rational>,
) {
    GAME_START(
        U6Tier.ONBOARDING,
        "Game start",
        cost = mapOf(),
    ),
    HUB_UPGRADE_1(
        U6Tier.ONBOARDING,
        "HUB Upgrade 1",
        cost = mapOf(
            U6Item.IRON_ROD to 10.q,
        ),
    ),
    HUB_UPGRADE_2(
        U6Tier.ONBOARDING,
        "HUB Upgrade 2",
        cost = mapOf(
            U6Item.IRON_ROD to 20.q,
            U6Item.IRON_PLATE to 10.q,
        ),
    ),
    HUB_UPGRADE_3(
        U6Tier.ONBOARDING,
        "HUB Upgrade 3",
        cost = mapOf(
            U6Item.IRON_PLATE to 20.q,
            U6Item.IRON_ROD to 20.q,
            U6Item.WIRE to 20.q,
        ),
    ),
    HUB_UPGRADE_4(
        U6Tier.ONBOARDING,
        "HUB Upgrade 4",
        cost = mapOf(
            U6Item.IRON_PLATE to 75.q,
            U6Item.CABLE to 20.q,
            U6Item.CONCRETE to 10.q,
        ),
    ),
}
