package app.api

import app.data.u5.Item
import app.data.u5.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
    val outcome: Map<Recipe, Rational>,
    val minimums: Map<Item, Rational>,
    val maximums: Map<Item, Rational>,
)
