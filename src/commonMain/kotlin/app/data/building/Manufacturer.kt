package app.data.building

import app.data.recipe.Recipe
import util.math.Rational
import util.math.q

enum class Manufacturer(
    override val displayName: String,
    override val consumption: Rational,
    override val recipes: List<Recipe>,
) : ProductionBuilding {
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
    FOUNDRY(
        "Foundry",
        consumption = 16.q,
        recipes = listOf(
            Recipe.STEEL_INGOT,
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
    );
}
