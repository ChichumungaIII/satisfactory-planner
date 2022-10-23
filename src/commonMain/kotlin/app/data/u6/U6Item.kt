package app.data.u6

import util.math.Rational
import util.math.q

enum class U6Item(
    val type: Type,
    val milestone: U6Milestone,
    val displayName: String,
    val research: Set<U6Research> = setOf(),
    val energy: Rational = 0.q,
) {
    /* Environment */

    BERYL_NUT(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Beryl Nut",
    ),
    BLUE_POWER_SLUG(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Blue Power Slug",
    ),
    FLOWER_PETALS(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Flower Petals",
        energy = 100.q,
    ),
    HATCHER_REMAINS(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Hatcher Remains",
        energy = 250.q,
    ),
    HOG_REMAINS(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Hog Remains",
        energy = 250.q,
    ),
    LEAVES(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Leaves",
        energy = 15.q,
    ),
    MYCELIA(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Mycelia",
        energy = 20.q,
    ),
    PALEBERRY(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Paleberry",
    ),
    PURPLE_POWER_SLUG(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Purple Power Slug",
    ),
    SPITTER_REMAINS(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Spitter Remains",
        energy = 250.q,
    ),
    STINGER_REMAINS(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Stinger Remains",
        energy = 250.q,
    ),
    WOOD(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Wood",
        energy = 100.q,
    ),
    YELLOW_POWER_SLUG(
        Type.ENVIRONMENT,
        U6Milestone.GAME_START,
        "Yellow Power Slug",
    ),

    /* Tier 0 -- Game Start */

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

    /* Tier 0 - HUB Upgrade 1 */

    PORTABLE_MINER(
        Type.EQUIPMENT,
        U6Milestone.HUB_UPGRADE_1,
        "Portable Miner",
    ),

    /* Tier 0 -- HUB Upgrade 2 */

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

    /* Tier 0 -- HUB Upgrade 3 */

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

    /* Tier 0 -- HUB Upgrade 6 */

    BIOMASS(
        Type.PART,
        U6Milestone.HUB_UPGRADE_6,
        "Biomass",
        energy = 180.q,
    ),

    /* Tier 1 -- Field Research */

    BEACON(
        Type.EQUIPMENT,
        U6Milestone.FIELD_RESEARCH,
        "Beacon",
    ),

    /* Tier 2 -- Part Assembly */

    COPPER_SHEET(
        Type.PART,
        U6Milestone.PART_ASSEMBLY,
        "Copper Sheet",
    ),
    ROTOR(
        Type.PART,
        U6Milestone.PART_ASSEMBLY,
        "Rotor",
    ),
    MODULAR_FRAME(
        Type.PART,
        U6Milestone.PART_ASSEMBLY,
        "Modular Frame",
    ),
    SMART_PLATING(
        Type.PART,
        U6Milestone.PART_ASSEMBLY,
        "Smart Plating",
    ),

    /* Tier 2 -- Obstacle Clearing */

    SOLID_BIOFUEL(
        Type.PART,
        U6Milestone.OBSTACLE_CLEARING,
        "Solid Biofuel",
        energy = 450.q,
    ),

    CHAINSAW(
        Type.EQUIPMENT,
        U6Milestone.OBSTACLE_CLEARING,
        "Chainsaw",
    ),

    /* Tier 2 -- Resource Sink Bonus Program */

    COLOR_CARTRIDGE(
        Type.PART,
        U6Milestone.RESOURCE_SINK_BONUS_PROGRAM,
        "Color Cartridge",
        energy = 900.q,
    ),

    /* Tier 3 -- Coal Power */

    COAL(
        Type.RESOURCE,
        U6Milestone.COAL_POWER,
        "Coal",
        energy = 300.q,
    ),
    WATER(
        Type.RESOURCE,
        U6Milestone.COAL_POWER,
        "Water",
    ),

    /* Tier 3 -- Basic Steel Production */

    STEEL_INGOT(
        Type.PART,
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Steel Ingot",
    ),
    STEEL_BEAM(
        Type.PART,
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Steel Beam",
    ),
    STEEL_PIPE(
        Type.PART,
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Steel Pipe",
    ),
    VERSATILE_FRAMEWORK(
        Type.PART,
        U6Milestone.BASIC_STEEL_PRODUCTION,
        "Versatile Framework",
    ),

    /* Tier 4 -- Advanced Steel Production */

    ENCASED_INDUSTRIAL_BEAM(
        Type.PART,
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Encased Industrial Beam",
    ),
    STATOR(
        Type.PART,
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Stator",
    ),
    MOTOR(
        Type.PART,
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Motor",
    ),
    AUTOMATED_WIRING(
        Type.PART,
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Automated Wiring",
    ),
    HEAVY_MODULAR_FRAME(
        Type.PART,
        U6Milestone.ADVANCED_STEEL_PRODUCTION,
        "Heavy Modular Frame",
    ),

    /* Tier 4 -- Improved Melee Combat */

    XENO_BASHER(
        Type.EQUIPMENT,
        U6Milestone.IMPROVED_MELEE_COMBAT,
        "Xeno-Basher",
    ),

    /* Other -- TO BE CLASSIFIED */
    // TODO: Classify these when the appropriate tier is unlocked

    CATERIUM_ORE(
        Type.RESOURCE,
        U6Milestone.GAME_START,
        "Caterium Ore",
    ),
    CATERIUM_INGOT(
        Type.PART,
        U6Milestone.FIELD_RESEARCH,
        "Caterium Ingot",
    ),
    QUICKWIRE(
        Type.PART,
        U6Milestone.FIELD_RESEARCH,
        "Quickwire",
    ),
    AI_LIMITER(
        Type.PART,
        U6Milestone.FIELD_RESEARCH,
        "AI Limiter",
    ),

    ZIPLINE(
        Type.EQUIPMENT,
        U6Milestone.FIELD_RESEARCH,
        "Zipline",
    ),

    QUARTZ(
        Type.RESOURCE,
        U6Milestone.GAME_START,
        "Quartz",
    ),

    SULFUR(
        Type.RESOURCE,
        U6Milestone.GAME_START,
        "Sulfur",
    ),

    /* Research */

    ALIEN_PROTEIN(
        Type.PART,
        U6Milestone.FIELD_RESEARCH,
        "Alien Protein",
        research = setOf(
            U6Research.HOG_RESEARCH,
            U6Research.HATCHER_RESEARCH,
            U6Research.STINGER_RESEARCH,
            U6Research.SPITTER_RESEARCH,
        ),
    ),
    BLADE_RUNNERS(
        Type.EQUIPMENT,
        U6Milestone.FIELD_RESEARCH,
        "Blade Runners",
    ),
    ORGANIC_DATA_CAPSULE(
        Type.PART,
        U6Milestone.FIELD_RESEARCH,
        "Organic Data Capsule",
        research = setOf(U6Research.BIO_ORGANIC_PROPERTIES),
    ),
    POWER_SHARD(
        Type.PART,
        U6Milestone.FIELD_RESEARCH,
        "Power Shard",
        research = setOf(U6Research.BLUE_POWER_SLUGS),
    ),
    SILICA(
        Type.PART,
        U6Milestone.FIELD_RESEARCH,
        "Silica",
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
