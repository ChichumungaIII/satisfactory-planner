package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Item(
    val type: Type,
    val milestone: U6Milestone,
    val displayName: String,
    val energy: Rational = 0.q,
) {
    /* Environment */

    BERYL_NUT(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Beryl Nut",
    ),
    HOG_REMAINS(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Hog Remains",
        energy = 250.q,
    ),

    /* Onboarding - Game Start */

    IRON_ORE(
        Type.RESOURCE,
        U6Milestone.GAME_START,
        "Iron Ore",
    ),

    IRON_INGOT(
        Type.PART,
        U6Milestone.GAME_START,
        "Iron Ingot",
    ),
    IRON_PLATE(
        Type.PART,
        U6Milestone.GAME_START,
        "Iron Plate",
    ),
    IRON_ROD(
        Type.PART,
        U6Milestone.GAME_START,
        "Iron Rod",
    ),

    XENO_ZAPPER(
        Type.EQUIPMENT,
        U6Milestone.GAME_START,
        "Xeno-Zapper"
    ),

    /* Onboarding - HUB Upgrade 1 */

    PORTABLE_MINER(
        Type.EQUIPMENT,
        U6Milestone.HUB_UPGRADE_1,
        "Portable Miner",
    ),

    /* Onboarding - HUB Upgrade 2 */

    COPPER_ORE(
        Type.RESOURCE,
        U6Milestone.HUB_UPGRADE_2,
        "Copper Ore",
    ),

    COPPER_INGOT(
        Type.PART,
        U6Milestone.HUB_UPGRADE_2,
        "Copper Ingot",
    ),
    WIRE(
        Type.PART,
        U6Milestone.HUB_UPGRADE_2,
        "Wire",
    ),
    CABLE(
        Type.PART,
        U6Milestone.HUB_UPGRADE_2,
        "Cable",
    ),

    /* Onboarding - HUB Upgrade 3 */

    LIMESTONE(
        Type.RESOURCE,
        U6Milestone.HUB_UPGRADE_3,
        "Limestone",
    ),

    CONCRETE(
        Type.PART,
        U6Milestone.HUB_UPGRADE_3,
        "Concrete",
    ),
    SCREW(
        Type.PART,
        U6Milestone.HUB_UPGRADE_3,
        "Screw",
    ),
    REINFORCED_IRON_PLATE(
        Type.PART,
        U6Milestone.HUB_UPGRADE_3,
        "Reinforced Iron Plate",
    ),
    ;

    enum class Type(
        val displayName: String,
    ) {
        ENVIRONMENT("Environment"),
        RESOURCE("Resources"),
        PART("Parts"),
        EQUIPMENT("Equipment"), ;
    }
}
