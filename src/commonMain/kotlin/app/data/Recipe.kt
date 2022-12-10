package app.data

import util.math.Rational
import util.math.q

enum class Recipe(
    val displayName: String,
    val time: Rational,
    val inputs: Map<Item, Rational>,
    val outputs: Map<Item, Rational>,
    val alternate: Boolean = false,
) {
    /* Onboarding -- Game Start */
    IRON_INGOT(
        "Iron Ingot",
        time = 2.q,
        inputs = mapOf(Item.IRON_ORE to 1.q),
        outputs = mapOf(Item.IRON_INGOT to 1.q),
    ),
    IRON_PLATE(
        "Iron Plate",
        time = 6.q,
        inputs = mapOf(Item.IRON_INGOT to 3.q),
        outputs = mapOf(Item.IRON_PLATE to 2.q),
    ),
    IRON_ROD(
        "Iron Rod",
        time = 4.q,
        inputs = mapOf(Item.IRON_INGOT to 1.q),
        outputs = mapOf(Item.IRON_ROD to 1.q),
    ),
    XENO_ZAPPER(
        "Xeno-Zapper",
        time = 40.q,
        inputs = mapOf(
            Item.IRON_ROD to 10.q,
            Item.REINFORCED_IRON_PLATE to 2.q,
            Item.CABLE to 15.q,
            Item.WIRE to 50.q,
        ),
        outputs = mapOf(Item.XENO_ZAPPER to 1.q),
    ),

    /* Onboarding -- HUB Upgrade 1 */
    PORTABLE_MINER(
        "Portable Miner",
        time = 40.q,
        inputs = mapOf(
            Item.IRON_PLATE to 2.q,
            Item.IRON_ROD to 4.q,
        ),
        outputs = mapOf(Item.PORTABLE_MINER to 1.q),
    ),

    /* Onboarding -- HUB Upgrade 2 */
    COPPER_INGOT(
        "Copper Ingot",
        time = 2.q,
        inputs = mapOf(Item.COPPER_ORE to 1.q),
        outputs = mapOf(Item.COPPER_INGOT to 1.q),
    ),
    WIRE(
        "Wire",
        time = 4.q,
        inputs = mapOf(Item.COPPER_INGOT to 1.q),
        outputs = mapOf(Item.WIRE to 2.q),
    ),
    CABLE(
        "Cable",
        time = 2.q,
        inputs = mapOf(Item.WIRE to 2.q),
        outputs = mapOf(Item.CABLE to 1.q),
    ),

    /* Onboarding -- HUB Upgrade 3 */
    CONCRETE(
        "Concrete",
        time = 4.q,
        inputs = mapOf(Item.LIMESTONE to 3.q),
        outputs = mapOf(Item.CONCRETE to 1.q),
    ),
    SCREW(
        "Screw",
        time = 6.q,
        inputs = mapOf(Item.IRON_ROD to 1.q),
        outputs = mapOf(Item.SCREW to 4.q),
    ),
    REINFORCED_IRON_PLATE(
        "Reinforced Iron Plate",
        time = 12.q,
        inputs = mapOf(
            Item.IRON_PLATE to 6.q,
            Item.SCREW to 12.q,
        ),
        outputs = mapOf(Item.REINFORCED_IRON_PLATE to 1.q),
    ),

    /* Onboarding -- HUB Upgrade 6 */
    BIOMASS_WOOD(
        "Biomass (Wood)",
        time = 4.q,
        inputs = mapOf(Item.WOOD to 4.q),
        outputs = mapOf(Item.BIOMASS to 20.q),
    ),
    BIOMASS_LEAVES(
        "Biomass (Leaves)",
        time = 5.q,
        inputs = mapOf(Item.LEAVES to 10.q),
        outputs = mapOf(Item.BIOMASS to 5.q),
    ),

    /* Tier 2 -- Part Assembly */
    COPPER_SHEET(
        "Copper Sheet",
        time = 6.q,
        inputs = mapOf(Item.COPPER_INGOT to 2.q),
        outputs = mapOf(Item.COPPER_SHEET to 1.q),
    ),
    ROTOR(
        "Rotor",
        time = 15.q,
        inputs = mapOf(
            Item.IRON_ROD to 5.q,
            Item.SCREW to 25.q,
        ),
        outputs = mapOf(Item.ROTOR to 1.q),
    ),
    MODULAR_FRAME(
        "Modular Frame",
        time = 60.q,
        inputs = mapOf(
            Item.REINFORCED_IRON_PLATE to 3.q,
            Item.IRON_ROD to 12.q,
        ),
        outputs = mapOf(Item.MODULAR_FRAME to 2.q),
    ),
    SMART_PLATING(
        "Smart Plating",
        time = 30.q,
        inputs = mapOf(
            Item.REINFORCED_IRON_PLATE to 1.q,
            Item.ROTOR to 1.q,
        ),
        outputs = mapOf(Item.SMART_PLATING to 1.q),
    ),

    /* Tier 2 -- Obstacle Clearing */
    SOLID_BIOFUEL(
        "Solid Biofuel",
        time = 4.q,
        inputs = mapOf(Item.BIOMASS to 8.q),
        outputs = mapOf(Item.SOLID_BIOFUEL to 4.q),
    ),
    CHAINSAW(
        "Chainsaw",
        time = 60.q,
        inputs = mapOf(
            Item.REINFORCED_IRON_PLATE to 5.q,
            Item.IRON_ROD to 25.q,
            Item.SCREW to 160.q,
            Item.CABLE to 15.q,
        ),
        outputs = mapOf(Item.CHAINSAW to 1.q),
    ),

    /* Tier 2 -- Resource Sink Bonus Program */
    COLOR_CARTRIDGE(
        "Color Cartridge",
        time = 6.q,
        inputs = mapOf(Item.FLOWER_PETALS to 5.q),
        outputs = mapOf(Item.COLOR_CARTRIDGE to 10.q),
    ),

    /* Tier 3 -- Coal Power */
    BURN_COAL(
        "Coal",
        time = 4.q,
        inputs = mapOf(
            Item.COAL to 1.q,
            Item.WATER to 3.q,
        ),
        outputs = mapOf(),
    ),

    /* Tier 3 -- Basic Steel Production */
    STEEL_INGOT(
        "Steel Ingot",
        time = 4.q,
        inputs = mapOf(
            Item.IRON_ORE to 3.q,
            Item.COAL to 3.q,
        ),
        outputs = mapOf(Item.STEEL_INGOT to 3.q),
    ),
    STEEL_BEAM(
        "Steel Beam",
        time = 4.q,
        inputs = mapOf(Item.STEEL_INGOT to 4.q),
        outputs = mapOf(Item.STEEL_BEAM to 1.q),
    ),
    STEEL_PIPE(
        "Steel Pipe",
        time = 6.q,
        inputs = mapOf(Item.STEEL_INGOT to 3.q),
        outputs = mapOf(Item.STEEL_PIPE to 2.q),
    ),
    VERSATILE_FRAMEWORK(
        "Versatile Framework",
        time = 24.q,
        inputs = mapOf(
            Item.MODULAR_FRAME to 1.q,
            Item.STEEL_BEAM to 12.q,
        ),
        outputs = mapOf(Item.VERSATILE_FRAMEWORK to 2.q),
    ),

    /* Tier 4 -- Advanced Steel Production */
    ENCASED_INDUSTRIAL_BEAM(
        "Encased Industrial Beam",
        time = 10.q,
        inputs = mapOf(
            Item.STEEL_BEAM to 4.q,
            Item.CONCRETE to 5.q,
        ),
        outputs = mapOf(Item.ENCASED_INDUSTRIAL_BEAM to 1.q),
    ),
    STATOR(
        "Stator",
        time = 12.q,
        inputs = mapOf(
            Item.STEEL_PIPE to 3.q,
            Item.WIRE to 8.q,
        ),
        outputs = mapOf(Item.STATOR to 1.q),
    ),
    MOTOR(
        "Motor",
        time = 12.q,
        inputs = mapOf(
            Item.ROTOR to 2.q,
            Item.STATOR to 2.q,
        ),
        outputs = mapOf(Item.MOTOR to 1.q),
    ),
    AUTOMATED_WIRING(
        "Automated Wiring",
        time = 24.q,
        inputs = mapOf(
            Item.STATOR to 1.q,
            Item.CABLE to 20.q,
        ),
        outputs = mapOf(Item.AUTOMATED_WIRING to 1.q),
    ),
    HEAVY_MODULAR_FRAME(
        "Heavy Modular Frame",
        time = 30.q,
        inputs = mapOf(
            Item.MODULAR_FRAME to 5.q,
            Item.STEEL_PIPE to 15.q,
            Item.ENCASED_INDUSTRIAL_BEAM to 5.q,
            Item.SCREW to 100.q,
        ),
        outputs = mapOf(Item.HEAVY_MODULAR_FRAME to 1.q),
    ),

    /* Tier 4 -- Improved Melee Combat */
    XENO_BASHER(
        "Xeno-Basher",
        time = 80.q,
        inputs = mapOf(
            Item.MODULAR_FRAME to 5.q,
            Item.XENO_ZAPPER to 2.q,
            Item.CABLE to 25.q,
            Item.WIRE to 500.q,
        ),
        outputs = mapOf(Item.XENO_BASHER to 1.q),
    ),

    /* Tier 5 -- Oil Processing */
    PLASTIC(
        "Plastic",
        time = 6.q,
        inputs = mapOf(Item.CRUDE_OIL to 3.q),
        outputs = mapOf(
            Item.PLASTIC to 2.q,
            Item.HEAVY_OIL_RESIDUE to 1.q,
        ),
    ),
    RESIDUAL_PLASTIC(
        "Residual Plastic",
        time = 6.q,
        inputs = mapOf(
            Item.POLYMER_RESIN to 6.q,
            Item.WATER to 2.q,
        ),
        outputs = mapOf(Item.PLASTIC to 2.q),
    ),
    RUBBER(
        "Rubber",
        time = 6.q,
        inputs = mapOf(Item.CRUDE_OIL to 3.q),
        outputs = mapOf(
            Item.RUBBER to 2.q,
            Item.HEAVY_OIL_RESIDUE to 2.q,
        ),
    ),
    RESIDUAL_RUBBER(
        "Residual Rubber",
        time = 6.q,
        inputs = mapOf(
            Item.POLYMER_RESIN to 4.q,
            Item.WATER to 4.q,
        ),
        outputs = mapOf(Item.RUBBER to 2.q),
    ),
    PETROLEUM_COKE(
        "Petroleum Coke",
        time = 6.q,
        inputs = mapOf(Item.HEAVY_OIL_RESIDUE to 4.q),
        outputs = mapOf(Item.PETROLEUM_COKE to 12.q),
    ),
    FUEL(
        "Fuel",
        time = 6.q,
        inputs = mapOf(Item.CRUDE_OIL to 6.q),
        outputs = mapOf(
            Item.FUEL to 4.q,
            Item.POLYMER_RESIN to 3.q,
        ),
    ),
    RESIDUAL_FUEL(
        "Residual Fuel",
        time = 6.q,
        inputs = mapOf(Item.HEAVY_OIL_RESIDUE to 6.q),
        outputs = mapOf(Item.FUEL to 4.q),
    ),
    CIRCUIT_BOARD(
        "Circuit Board",
        time = 8.q,
        inputs = mapOf(
            Item.COPPER_SHEET to 2.q,
            Item.PLASTIC to 4.q,
        ),
        outputs = mapOf(Item.CIRCUIT_BOARD to 1.q),
    ),

    /* Research -- Alien Organisms */
    HOG_PROTEIN(
        "Hog Protein",
        time = 3.q,
        inputs = mapOf(Item.HOG_REMAINS to 1.q),
        outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    ),
    HATCHER_PROTEIN(
        "Hatcher Protein",
        time = 3.q,
        inputs = mapOf(Item.HATCHER_REMAINS to 1.q),
        outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    ),
    SPITTER_PROTEIN(
        "Spitter Protein",
        time = 3.q,
        inputs = mapOf(Item.SPITTER_REMAINS to 1.q),
        outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    ),
    STINGER_PROTEIN(
        "Stinger Protein",
        time = 3.q,
        inputs = mapOf(Item.STINGER_REMAINS to 1.q),
        outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    ),
    ORGANIC_DATA_CAPSULE(
        "Organic Data Capsule",
        time = 6.q,
        inputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
        outputs = mapOf(Item.ORGANIC_DATA_CAPSULE to 1.q),
    ),

    /* Research -- Caterium */
    CATERIUM_INGOT(
        "Caterium Ingot",
        time = 4.q,
        inputs = mapOf(Item.CATERIUM_ORE to 3.q),
        outputs = mapOf(Item.CATERIUM_INGOT to 1.q),
    ),
    QUICKWIRE(
        "Quickwire",
        time = 5.q,
        inputs = mapOf(Item.CATERIUM_INGOT to 1.q),
        outputs = mapOf(Item.QUICKWIRE to 5.q),
    ),
    AI_LIMITER(
        "AI Limiter",
        time = 12.q,
        inputs = mapOf(
            Item.COPPER_SHEET to 5.q,
            Item.QUICKWIRE to 20.q,
        ),
        outputs = mapOf(Item.AI_LIMITER to 1.q),
    ),
    ZIPLINE(
        "Zipline",
        time = 40.q,
        inputs = mapOf(
            Item.XENO_ZAPPER to 1.q,
            Item.QUICKWIRE to 30.q,
            Item.IRON_ROD to 3.q,
            Item.CABLE to 15.q,
        ),
        outputs = mapOf(Item.ZIPLINE to 1.q),
    ),
    BLADE_RUNNERS(
        "Blade Runners",
        time = 60.q,
        inputs = mapOf(
            Item.SILICA to 20.q,
            Item.MODULAR_FRAME to 3.q,
            Item.ROTOR to 3.q,
        ),
        outputs = mapOf(Item.BLADE_RUNNERS to 1.q),
    ),

    /* Research -- Mycelia */
    BIOMASS_MYCELIA(
        "Biomass (Mycelia)",
        time = 4.q,
        inputs = mapOf(Item.MYCELIA to 1.q),
        outputs = mapOf(Item.BIOMASS to 10.q),
    ),

    /* Research -- Power Slugs */
    POWER_SHARD_1(
        "Power Shard (1)",
        time = 8.q,
        inputs = mapOf(Item.BLUE_POWER_SLUG to 1.q),
        outputs = mapOf(Item.POWER_SHARD to 1.q),
    ),
    POWER_SHARD_2(
        "Power Shard (2)",
        time = 12.q,
        inputs = mapOf(Item.YELLOW_POWER_SLUG to 1.q),
        outputs = mapOf(Item.POWER_SHARD to 1.q),
    ),
    POWER_SHARD_5(
        "Power Shard (5)",
        time = 24.q,
        inputs = mapOf(Item.PURPLE_POWER_SLUG to 1.q),
        outputs = mapOf(Item.POWER_SHARD to 5.q),
    );

    val components: Map<Item, Rational> get() = inputs + outputs.mapValues { (_, count) -> -count }
}
