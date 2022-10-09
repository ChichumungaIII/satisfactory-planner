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

    /* Onboarding -- HUB Upgrade 6 */

    BIOMASS_WOOD(
        U6Milestone.HUB_UPGRADE_6,
        "Biomass (Wood)",
        time = 4.q,
        components = mapOf(
            U6Item.WOOD to -(4.q),
            U6Item.BIOMASS to 20.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    BIOMASS_LEAVES(
        U6Milestone.HUB_UPGRADE_6,
        "Biomass (Leaves)",
        time = 5.q,
        components = mapOf(
            U6Item.LEAVES to -(10.q),
            U6Item.BIOMASS to 5.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),

    /* Tier 2 -- Part Assembly */

    COPPER_SHEET(
        U6Milestone.PART_ASSEMBLY,
        "Copper Sheet",
        time = 6.q,
        components = mapOf(
            U6Item.COPPER_INGOT to -(2.q),
            U6Item.COPPER_SHEET to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    ROTOR(
        U6Milestone.PART_ASSEMBLY,
        "Rotor",
        time = 15.q,
        components = mapOf(
            U6Item.IRON_ROD to -(5.q),
            U6Item.SCREW to -(25.q),
            U6Item.ROTOR to 1.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER, U6Building.CRAFT_BENCH),
    ),
    MODULAR_FRAME(
        U6Milestone.PART_ASSEMBLY,
        "Modular Frame",
        time = 60.q,
        components = mapOf(
            U6Item.REINFORCED_IRON_PLATE to -(3.q),
            U6Item.IRON_ROD to -(12.q),
            U6Item.MODULAR_FRAME to 2.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER, U6Building.CRAFT_BENCH),
    ),
    SMART_PLATING(
        U6Milestone.PART_ASSEMBLY,
        "Smart Plating",
        time = 30.q,
        components = mapOf(
            U6Item.REINFORCED_IRON_PLATE to -(1.q),
            U6Item.ROTOR to -(1.q),
            U6Item.SMART_PLATING to 1.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER),
    ),

    /* Tier 2 -- Obstacle Clearing */

    SOLID_BIOFUEL(
        U6Milestone.OBSTACLE_CLEARING,
        "Solid Biofuel",
        time = 4.q,
        components = mapOf(
            U6Item.BIOMASS to -(8.q),
            U6Item.SOLID_BIOFUEL to 4.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    CHAINSAW(
        U6Milestone.OBSTACLE_CLEARING,
        "Chainsaw",
        time = 60.q,
        components = mapOf(
            U6Item.REINFORCED_IRON_PLATE to -(5.q),
            U6Item.IRON_ROD to -(25.q),
            U6Item.SCREW to -(160.q),
            U6Item.CABLE to -(15.q),
            U6Item.CHAINSAW to 1.q,
        ),
        buildings = listOf(U6Building.EQUIPMENT_WORKSHOP),
    ),

    /* Tier 2 -- Resource Sink Bonus Program */

    COLOR_CARTRIDGE(
        U6Milestone.RESOURCE_SINK_BONUS_PROGRAM,
        "Color Cartridge",
        time = 6.q,
        components = mapOf(
            U6Item.FLOWER_PETALS to -(5.q),
            U6Item.COLOR_CARTRIDGE to 10.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),

    /* Tier 3 -- Coal Power */

    BURN_COAL(
        U6Milestone.COAL_POWER,
        "Burn Coal",
        time = U6Item.COAL.energy / U6Building.COAL_GENERATOR.power,
        components = mapOf(
            U6Item.COAL to -(1.q),
            U6Item.WATER to -((45.q / 60.q) * (U6Item.COAL.energy / U6Building.COAL_GENERATOR.power)),
        ),
        buildings = listOf(U6Building.COAL_GENERATOR),
    ),
}
