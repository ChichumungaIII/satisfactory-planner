package app.data.building

import app.data.recipe.Recipe
import util.math.Rational
import util.math.q

enum class Generator(
    override val displayName: String,
    override val generation: Rational,
    override val recipes: List<Recipe>,
) : Building {
    BIOMASS_BURNER(
        "Biomass Burner",
        generation = 30.q,
        recipes = listOf(
            // TODO: Add biofuel recipes
        ),
    ),
    COAL_GENERATOR(
        "Coal Generator",
        generation = 75.q,
        recipes = listOf(
            Recipe.BURN_COAL,
        ),
    ),
    FUEL_GENERATOR(
        "Fuel Generator",
        generation = 150.q,
        recipes = listOf(
            Recipe.BURN_FUEL,
        ),
    );

    override val consumption = 0.q;
}
