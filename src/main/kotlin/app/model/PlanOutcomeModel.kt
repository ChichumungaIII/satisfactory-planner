package app.model

import app.model.game.u5.Recipe
import util.math.Rational

data class PlanOutcomeModel(
    private val recipes: Map<Recipe, Rational>,
) {
    fun recipes() = recipes
}
