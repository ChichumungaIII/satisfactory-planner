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

    /* Tier 3 -- Basic Steel Production */

    STEEL_INGOT(
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Steel Ingot",
        time = 4.q,
        components = mapOf(
            U6Item.IRON_ORE to -(3.q),
            U6Item.COAL to -(3.q),
            U6Item.STEEL_INGOT to 3.q,
        ),
        buildings = listOf(U6Building.FOUNDRY, U6Building.CRAFT_BENCH),
    ),
    STEEL_BEAM(
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Steel Beam",
        time = 4.q,
        components = mapOf(
            U6Item.STEEL_INGOT to -(4.q),
            U6Item.STEEL_BEAM to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    STEEL_PIPE(
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Steel Pipe",
        time = 6.q,
        components = mapOf(
            U6Item.STEEL_INGOT to -(3.q),
            U6Item.STEEL_PIPE to 2.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    VERSATILE_FRAMEWORK(
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Versatile Framework",
        time = 24.q,
        components = mapOf(
            U6Item.MODULAR_FRAME to -(1.q),
            U6Item.STEEL_BEAM to -(12.q),
            U6Item.VERSATILE_FRAMEWORK to 2.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER),
    ),

    /* Tier 4 -- Advanced Steel Production */

    ENCASED_INDUSTRIAL_BEAM(
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Encased Industrial Beam",
        time = 10.q,
        components = mapOf(
            U6Item.STEEL_BEAM to -(4.q),
            U6Item.CONCRETE to -(5.q),
            U6Item.ENCASED_INDUSTRIAL_BEAM to 1.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER, U6Building.CRAFT_BENCH),
    ),
    STATOR(
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Stator",
        time = 12.q,
        components = mapOf(
            U6Item.STEEL_PIPE to -(3.q),
            U6Item.WIRE to -(8.q),
            U6Item.STATOR to 1.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER, U6Building.CRAFT_BENCH),
    ),
    MOTOR(
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Motor",
        time = 12.q,
        components = mapOf(
            U6Item.ROTOR to -(2.q),
            U6Item.STATOR to -(2.q),
            U6Item.MOTOR to 1.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER, U6Building.CRAFT_BENCH),
    ),
    AUTOMATED_WIRING(
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Automated Wiring",
        time = 24.q,
        components = mapOf(
            U6Item.STATOR to -(1.q),
            U6Item.CABLE to -(20.q),
            U6Item.AUTOMATED_WIRING to 1.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER),
    ),
    HEAVY_MODULAR_FRAME(
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Heavy Modular Frame",
        time = 30.q,
        components = mapOf(
            U6Item.MODULAR_FRAME to -(5.q),
            U6Item.STEEL_PIPE to -(15.q),
            U6Item.ENCASED_INDUSTRIAL_BEAM to -(5.q),
            U6Item.SCREW to -(100.q),
            U6Item.HEAVY_MODULAR_FRAME to 1.q,
        ),
        // TODO: Unlock
        buildings = listOf(/* U6Building.MANUFACTURER, */U6Building.CRAFT_BENCH),
    ),

    /* Tier 4 -- Improved Melee Combat */

    XENO_BASHER(
        U6Milestone.IMPROVED_MELEE_COMBAT,
        "Xeno-Basher",
        time = 80.q,
        components = mapOf(
            U6Item.MODULAR_FRAME to -(5.q),
            U6Item.XENO_ZAPPER to -(2.q),
            U6Item.CABLE to -(25.q),
            U6Item.WIRE to -(500.q),
            U6Item.XENO_BASHER to 1.q,
        ),
        buildings = listOf(U6Building.EQUIPMENT_WORKSHOP),
    ),

    /* Research -- Alien Organisms */

    HOG_PROTEIN(
        U6Milestone.FIELD_RESEARCH,
        "Hog Protein",
        time = 3.q,
        components = mapOf(
            U6Item.HOG_REMAINS to -(1.q),
            U6Item.ALIEN_PROTEIN to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    HATCHER_PROTEIN(
        U6Milestone.FIELD_RESEARCH,
        "Hatcher Protein",
        time = 3.q,
        components = mapOf(
            U6Item.HATCHER_REMAINS to -(1.q),
            U6Item.ALIEN_PROTEIN to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    SPITTER_PROTEIN(
        U6Milestone.FIELD_RESEARCH,
        "Spitter Protein",
        time = 3.q,
        components = mapOf(
            U6Item.SPITTER_REMAINS to -(1.q),
            U6Item.ALIEN_PROTEIN to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    STINGER_PROTEIN(
        U6Milestone.FIELD_RESEARCH,
        "Stinger Protein",
        time = 3.q,
        components = mapOf(
            U6Item.STINGER_REMAINS to -(1.q),
            U6Item.ALIEN_PROTEIN to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),

    ORGANIC_DATA_CAPSULE(
        U6Milestone.FIELD_RESEARCH,
        "Organic Data Capsule",
        time = 6.q,
        components = mapOf(
            U6Item.ALIEN_PROTEIN to -(1.q),
            U6Item.ORGANIC_DATA_CAPSULE to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),

    /* Research -- Caterium */

    CATERIUM_INGOT(
        U6Milestone.FIELD_RESEARCH,
        "Caterium Ingot",
        time = 4.q,
        components = mapOf(
            U6Item.CATERIUM_ORE to -(3.q),
            U6Item.CATERIUM_INGOT to 1.q,
        ),
        buildings = listOf(U6Building.SMELTER, U6Building.CRAFT_BENCH),
    ),
    QUICKWIRE(
        U6Milestone.FIELD_RESEARCH,
        "Quickwire",
        time = 5.q,
        components = mapOf(
            U6Item.CATERIUM_INGOT to -(1.q),
            U6Item.QUICKWIRE to 5.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    AI_LIMITER(
        U6Milestone.FIELD_RESEARCH,
        "AI Limiter",
        time = 12.q,
        components = mapOf(
            U6Item.COPPER_SHEET to -(5.q),
            U6Item.QUICKWIRE to -(20.q),
            U6Item.AI_LIMITER to 1.q,
        ),
        buildings = listOf(U6Building.ASSEMBLER, U6Building.CRAFT_BENCH),
    ),

    ZIPLINE(
        U6Milestone.FIELD_RESEARCH,
        "Zipline",
        time = 40.q,
        components = mapOf(
            U6Item.XENO_ZAPPER to -(1.q),
            U6Item.QUICKWIRE to -(30.q),
            U6Item.IRON_ROD to -(3.q),
            U6Item.CABLE to -(15.q),
            U6Item.ZIPLINE to 1.q,
        ),
        buildings = listOf(U6Building.EQUIPMENT_WORKSHOP),
    ),

    BLADE_RUNNERS(
        U6Milestone.FIELD_RESEARCH,
        "Blade Runners",
        time = 60.q,
        components = mapOf(
            U6Item.SILICA to -(20.q),
            U6Item.MODULAR_FRAME to -(3.q),
            U6Item.ROTOR to -(3.q),
            U6Item.BLADE_RUNNERS to 1.q,
        ),
        buildings = listOf(U6Building.EQUIPMENT_WORKSHOP),
    ),

    /* Research -- Mycelia */

    BIOMASS_MYCELIA(
        U6Milestone.FIELD_RESEARCH,
        "Biomass (Mycelia)",
        time = 4.q,
        components = mapOf(
            U6Item.MYCELIA to -(1.q),
            U6Item.BIOMASS to 10.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),

    /* Research -- Power Slugs */

    POWER_SHARD_1(
        U6Milestone.FIELD_RESEARCH,
        "Power Shard (1)",
        time = 8.q,
        components = mapOf(
            U6Item.BLUE_POWER_SLUG to -(1.q),
            U6Item.POWER_SHARD to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    POWER_SHARD_2(
        U6Milestone.FIELD_RESEARCH,
        "Power Shard (2)",
        time = 12.q,
        components = mapOf(
            U6Item.YELLOW_POWER_SLUG to -(1.q),
            U6Item.POWER_SHARD to 1.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
    POWER_SHARD_5(
        U6Milestone.FIELD_RESEARCH,
        "Power Shard (5)",
        time = 24.q,
        components = mapOf(
            U6Item.PURPLE_POWER_SLUG to -(1.q),
            U6Item.POWER_SHARD to 5.q,
        ),
        buildings = listOf(U6Building.CONSTRUCTOR, U6Building.CRAFT_BENCH),
    ),
}
