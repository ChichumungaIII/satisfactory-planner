package app.data.building

import app.data.recipe.Recipe
import util.math.q

enum class Workstation(
    override val displayName: String,
    override val recipes: List<Recipe>,
) : ProductionBuilding {
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
    );

    override val consumption = 0.q
}
