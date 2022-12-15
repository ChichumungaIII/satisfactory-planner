package app.data.building

import app.data.recipe.ProductionRecipe
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
            ProductionRecipe.IRON_INGOT,
            ProductionRecipe.COPPER_INGOT,
            ProductionRecipe.CATERIUM_INGOT,
        ),
    ),
    CONSTRUCTOR(
        "Constructor",
        consumption = 4.q,
        recipes = listOf(
            ProductionRecipe.IRON_PLATE,
            ProductionRecipe.IRON_ROD,
            ProductionRecipe.WIRE,
            ProductionRecipe.CABLE,
            ProductionRecipe.CONCRETE,
            ProductionRecipe.SCREW,
            ProductionRecipe.BIOMASS_WOOD,
            ProductionRecipe.BIOMASS_LEAVES,
            ProductionRecipe.COPPER_SHEET,
            ProductionRecipe.SOLID_BIOFUEL,
            ProductionRecipe.COLOR_CARTRIDGE,
            ProductionRecipe.STEEL_BEAM,
            ProductionRecipe.STEEL_PIPE,
            // Research
            ProductionRecipe.HOG_PROTEIN,
            ProductionRecipe.HATCHER_PROTEIN,
            ProductionRecipe.SPITTER_PROTEIN,
            ProductionRecipe.STINGER_PROTEIN,
            ProductionRecipe.ALIEN_DNA_CAPSULE,
            ProductionRecipe.IRON_REBAR,
            ProductionRecipe.QUICKWIRE,
            ProductionRecipe.BIOMASS_MYCELIA,
            ProductionRecipe.POWER_SHARD_1,
            ProductionRecipe.POWER_SHARD_2,
            ProductionRecipe.POWER_SHARD_5,
        ),
    ),
    ASSEMBLER(
        "Assembler",
        consumption = 15.q,
        recipes = listOf(
            ProductionRecipe.REINFORCED_IRON_PLATE,
            ProductionRecipe.ROTOR,
            ProductionRecipe.MODULAR_FRAME,
            ProductionRecipe.SMART_PLATING,
            ProductionRecipe.VERSATILE_FRAMEWORK,
            ProductionRecipe.ENCASED_INDUSTRIAL_BEAM,
            ProductionRecipe.STATOR,
            ProductionRecipe.MOTOR,
            ProductionRecipe.AUTOMATED_WIRING,
            ProductionRecipe.CIRCUIT_BOARD,
            // Research
            ProductionRecipe.AI_LIMITER,
            ProductionRecipe.FABRIC,
        ),
    ),
    FOUNDRY(
        "Foundry",
        consumption = 16.q,
        recipes = listOf(
            ProductionRecipe.STEEL_INGOT,
        ),
    ),
    REFINERY(
        "Refinery",
        consumption = 30.q,
        recipes = listOf(
            ProductionRecipe.PLASTIC,
            ProductionRecipe.RESIDUAL_PLASTIC,
            ProductionRecipe.RUBBER,
            ProductionRecipe.RESIDUAL_RUBBER,
            ProductionRecipe.PETROLEUM_COKE,
            ProductionRecipe.FUEL,
            ProductionRecipe.RESIDUAL_FUEL,
            // Research
            ProductionRecipe.POLYESTER_FABRIC,
        ),
    ),
    MANUFACTURER(
        "Manufacturer",
        consumption = 55.q,
        recipes = listOf(
            ProductionRecipe.HEAVY_MODULAR_FRAME,
            ProductionRecipe.COMPUTER,
            ProductionRecipe.MODULAR_ENGINE,
            ProductionRecipe.ADAPTIVE_CONTROL_UNIT,
            // Research
            ProductionRecipe.CRYSTAL_OSCILLATOR,
        ),
    );
}