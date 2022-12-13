package app.data.building

import app.data.Recipe
import util.math.Rational
import util.math.q

enum class Building(
    val displayName: String,
    val generation: Rational = 0.q,
    val consumption: Rational = 0.q,
    val recipes: List<Recipe>,
) {
    CRAFT_BENCH(
        "Craft Bench",
        recipes = listOf(
            Recipe.IRON_INGOT,
            Recipe.IRON_PLATE,
            Recipe.IRON_ROD,
            Recipe.COPPER_INGOT,
            Recipe.WIRE,
            Recipe.CABLE,
            Recipe.CONCRETE,
            Recipe.SCREW,
            Recipe.REINFORCED_IRON_PLATE,
            Recipe.BIOMASS_WOOD,
            Recipe.BIOMASS_LEAVES,
            Recipe.COPPER_SHEET,
            Recipe.ROTOR,
            Recipe.MODULAR_FRAME,
            Recipe.SOLID_BIOFUEL,
            Recipe.COLOR_CARTRIDGE,
            Recipe.STEEL_INGOT,
            Recipe.STEEL_BEAM,
            Recipe.STEEL_PIPE,
            Recipe.ENCASED_INDUSTRIAL_BEAM,
            Recipe.STATOR,
            Recipe.MOTOR,
            Recipe.HEAVY_MODULAR_FRAME,
            Recipe.COMPUTER,
            Recipe.CIRCUIT_BOARD,
            // Research
            Recipe.HOG_PROTEIN,
            Recipe.HATCHER_PROTEIN,
            Recipe.SPITTER_PROTEIN,
            Recipe.STINGER_PROTEIN,
            Recipe.ALIEN_DNA_CAPSULE,
            Recipe.IRON_REBAR,
            Recipe.CATERIUM_INGOT,
            Recipe.QUICKWIRE,
            Recipe.AI_LIMITER,
            Recipe.BIOMASS_MYCELIA,
            Recipe.FABRIC,
            Recipe.POWER_SHARD_1,
            Recipe.POWER_SHARD_2,
            Recipe.POWER_SHARD_5,
            Recipe.QUARTZ_CRYSTAL,
            Recipe.SILICA,
            Recipe.CRYSTAL_OSCILLATOR,
        ),
    ),
    EQUIPMENT_WORKSHOP(
        "Equipment Workshop",
        recipes = listOf(
            Recipe.XENO_ZAPPER,
            Recipe.PORTABLE_MINER,
            Recipe.CHAINSAW,
            Recipe.XENO_BASHER,
            Recipe.ZIPLINE,
            Recipe.BLADE_RUNNERS,
            Recipe.NUTRITIONAL_INHALER,
            Recipe.PROTEIN_INHALER,
            Recipe.REBAR_GUN,
            Recipe.PARACHUTE,
            Recipe.VITAMIN_INHALER,
            Recipe.THERAPUTIC_INHALER,
        ),
    ),
    SMELTER(
        "Smelter",
        consumption = 4.q,
        recipes = listOf(
            Recipe.IRON_INGOT,
            Recipe.COPPER_INGOT,
            Recipe.CATERIUM_INGOT,
        ),
    ),
    CONSTRUCTOR(
        "Constructor",
        consumption = 4.q,
        recipes = listOf(
            Recipe.IRON_PLATE,
            Recipe.IRON_ROD,
            Recipe.WIRE,
            Recipe.CABLE,
            Recipe.CONCRETE,
            Recipe.SCREW,
            Recipe.BIOMASS_WOOD,
            Recipe.BIOMASS_LEAVES,
            Recipe.COPPER_SHEET,
            Recipe.SOLID_BIOFUEL,
            Recipe.COLOR_CARTRIDGE,
            Recipe.STEEL_BEAM,
            Recipe.STEEL_PIPE,
            // Research
            Recipe.HOG_PROTEIN,
            Recipe.HATCHER_PROTEIN,
            Recipe.SPITTER_PROTEIN,
            Recipe.STINGER_PROTEIN,
            Recipe.ALIEN_DNA_CAPSULE,
            Recipe.IRON_REBAR,
            Recipe.QUICKWIRE,
            Recipe.BIOMASS_MYCELIA,
            Recipe.POWER_SHARD_1,
            Recipe.POWER_SHARD_2,
            Recipe.POWER_SHARD_5,
        ),
    ),
    MINER_MK_1(
        "Miner Mk. 1",
        consumption = 5.q,
        recipes = listOf(
            // TODO: Add miner recipes
        ),
    ),
    BIOMASS_BURNER(
        "Biomass Burner",
        generation = 30.q,
        recipes = listOf(
            // TODO: Add biofuel recipes
        ),
    ),
    ASSEMBLER(
        "Assembler",
        consumption = 15.q,
        recipes = listOf(
            Recipe.REINFORCED_IRON_PLATE,
            Recipe.ROTOR,
            Recipe.MODULAR_FRAME,
            Recipe.SMART_PLATING,
            Recipe.VERSATILE_FRAMEWORK,
            Recipe.ENCASED_INDUSTRIAL_BEAM,
            Recipe.STATOR,
            Recipe.MOTOR,
            Recipe.AUTOMATED_WIRING,
            Recipe.CIRCUIT_BOARD,
            // Research
            Recipe.AI_LIMITER,
            Recipe.FABRIC,
        ),
    ),
    AWESOME_SINK(
        "AWESOME Sink",
        consumption = 30.q,
        recipes = listOf(
            // TODO: Add sink recipes
        ),
    ),
    COAL_GENERATOR(
        "Coal Generator",
        generation = 75.q,
        recipes = listOf(
            Recipe.BURN_COAL,
        ),
    ),
    WATER_EXTRACTOR(
        "Water Extractor",
        consumption = 20.q,
        recipes = listOf(
            // TODO: Add extractor recipes
        ),
    ),
    FOUNDRY(
        "Foundry",
        consumption = 16.q,
        recipes = listOf(
            Recipe.STEEL_INGOT,
        ),
    ),
    OIL_EXTRACTOR(
        "Oil Extractor",
        consumption = 40.q,
        recipes = listOf(
            // TODO: Add extractor recipes
        ),
    ),
    REFINERY(
        "Refinery",
        consumption = 30.q,
        recipes = listOf(
            Recipe.PLASTIC,
            Recipe.RESIDUAL_PLASTIC,
            Recipe.RUBBER,
            Recipe.RESIDUAL_RUBBER,
            Recipe.PETROLEUM_COKE,
            Recipe.FUEL,
            Recipe.RESIDUAL_FUEL,
            // Research
            Recipe.POLYESTER_FABRIC,
        ),
    ),
    MANUFACTURER(
        "Manufacturer",
        consumption = 55.q,
        recipes = listOf(
            Recipe.HEAVY_MODULAR_FRAME,
            Recipe.COMPUTER,
            Recipe.MODULAR_ENGINE,
            Recipe.ADAPTIVE_CONTROL_UNIT,
            // Research
            Recipe.CRYSTAL_OSCILLATOR,
        ),
    ),
    FUEL_GENERATOR(
        "Fuel Generator",
        generation = 150.q,
        recipes = listOf(
            Recipe.BURN_FUEL,
        ),
    );
}
