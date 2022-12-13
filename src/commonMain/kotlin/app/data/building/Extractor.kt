package app.data.building

import app.data.Recipe
import util.math.Rational
import util.math.q

enum class Extractor(
    override val displayName: String,
    override val consumption: Rational,
    override val recipes: List<Recipe>,
) : ProductionBuilding {
    MINER_MK_1(
        "Miner Mk. 1",
        consumption = 5.q,
        recipes = listOf(
            // TODO: Add extractor recipes
        ),
    ),
    WATER_EXTRACTOR(
        "Water Extractor",
        consumption = 20.q,
        recipes = listOf(
            // TODO: Add extractor recipes
        ),
    ),
    MINER_MK_2(
        "Miner Mk. 2",
        consumption = 12.q,
        recipes = listOf(
            // TODO: Add extractor recipes
        ),
    ),
    OIL_EXTRACTOR(
        "Oil Extractor",
        consumption = 40.q,
        recipes = listOf(
            // TODO: Add extractor recipes
        ),
    );
}
