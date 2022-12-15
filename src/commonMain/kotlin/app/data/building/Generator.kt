package app.data.building

import app.data.recipe.GenerationRecipe
import util.math.Rational
import util.math.q

enum class Generator(
    override val displayName: String,
    override val generation: Rational,
    val water: Rational = 0.q,
) : Building {
    BIOMASS_BURNER(
        "Biomass Burner",
        generation = 30.q,
    ),
    COAL_GENERATOR(
        "Coal Generator",
        generation = 75.q,
        water = 45.q,
    ),
    FUEL_GENERATOR(
        "Fuel Generator",
        generation = 150.q,
    );

    override val consumption = 0.q;

    override val recipes by lazy { GenerationRecipe.values().filter { it.generator == this } }
}
