package app.model

import app.model.game.u5.Recipe
import util.math.Rational

data class PlanOutcomeModel(
    /** The operational rate for each recipe in order to execute this plan. Null if planning was unsuccessful. */
    val recipes: Map<Recipe, Rational>? = null,
)
