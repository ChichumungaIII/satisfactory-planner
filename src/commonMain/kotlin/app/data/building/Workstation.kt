package app.data.building

import app.data.recipe.ProductionRecipe
import app.data.recipe.Recipe
import util.math.q

enum class Workstation(
    override val displayName: String,
    override val recipes: List<Recipe>,
) : ProductionBuilding {
    CRAFT_BENCH(
        "Craft Bench",
        recipes = listOf(
            ProductionRecipe.IRON_INGOT,
            ProductionRecipe.IRON_PLATE,
            ProductionRecipe.IRON_ROD,
            ProductionRecipe.COPPER_INGOT,
            ProductionRecipe.WIRE,
            ProductionRecipe.CABLE,
            ProductionRecipe.CONCRETE,
            ProductionRecipe.SCREW,
            ProductionRecipe.REINFORCED_IRON_PLATE,
            ProductionRecipe.BIOMASS_WOOD,
            ProductionRecipe.BIOMASS_LEAVES,
            ProductionRecipe.COPPER_SHEET,
            ProductionRecipe.ROTOR,
            ProductionRecipe.MODULAR_FRAME,
            ProductionRecipe.SOLID_BIOFUEL,
            ProductionRecipe.COLOR_CARTRIDGE,
            ProductionRecipe.STEEL_INGOT,
            ProductionRecipe.STEEL_BEAM,
            ProductionRecipe.STEEL_PIPE,
            ProductionRecipe.ENCASED_INDUSTRIAL_BEAM,
            ProductionRecipe.STATOR,
            ProductionRecipe.MOTOR,
            ProductionRecipe.HEAVY_MODULAR_FRAME,
            ProductionRecipe.COMPUTER,
            ProductionRecipe.CIRCUIT_BOARD,
            ProductionRecipe.EMPTY_CANISTER,
            ProductionRecipe.GAS_FILTER,
            // Research
            ProductionRecipe.HOG_PROTEIN,
            ProductionRecipe.HATCHER_PROTEIN,
            ProductionRecipe.SPITTER_PROTEIN,
            ProductionRecipe.STINGER_PROTEIN,
            ProductionRecipe.ALIEN_DNA_CAPSULE,
            ProductionRecipe.IRON_REBAR,
            ProductionRecipe.CATERIUM_INGOT,
            ProductionRecipe.QUICKWIRE,
            ProductionRecipe.AI_LIMITER,
            ProductionRecipe.BIOMASS_MYCELIA,
            ProductionRecipe.FABRIC,
            ProductionRecipe.POWER_SHARD_1,
            ProductionRecipe.POWER_SHARD_2,
            ProductionRecipe.POWER_SHARD_5,
            ProductionRecipe.QUARTZ_CRYSTAL,
            ProductionRecipe.SILICA,
            ProductionRecipe.CRYSTAL_OSCILLATOR,
        ),
    ),
    EQUIPMENT_WORKSHOP(
        "Equipment Workshop",
        recipes = listOf(
            ProductionRecipe.XENO_ZAPPER,
            ProductionRecipe.PORTABLE_MINER,
            ProductionRecipe.CHAINSAW,
            ProductionRecipe.XENO_BASHER,
            ProductionRecipe.GAS_MASK,
            // Research
            ProductionRecipe.ZIPLINE,
            ProductionRecipe.BLADE_RUNNERS,
            ProductionRecipe.NUTRITIONAL_INHALER,
            ProductionRecipe.PROTEIN_INHALER,
            ProductionRecipe.REBAR_GUN,
            ProductionRecipe.PARACHUTE,
            ProductionRecipe.VITAMIN_INHALER,
            ProductionRecipe.THERAPUTIC_INHALER,
            ProductionRecipe.NOBELISK,
            ProductionRecipe.NOBELISK_DETONATOR,
        ),
    );

    override val consumption = 0.q
}
