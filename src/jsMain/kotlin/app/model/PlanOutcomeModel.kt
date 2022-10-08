package app.model

import app.data.u5.U5Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class PlanOutcomeModel(
    /** The operational rate for each recipe in order to execute this plan. Null if planning was unsuccessful. */
    val recipes: Map<U5Recipe, Rational>? = null,
)
