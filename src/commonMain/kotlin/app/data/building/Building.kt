package app.data.building

import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
sealed interface Building {
    val displayName: String

    val generation: Rational
    val consumption: Rational

    val recipes: List<Recipe>
}
