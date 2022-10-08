package app.api

import app.data.u5.U5Item
import app.data.u5.U5Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
    val outcome: Map<U5Recipe, Rational>,
    val minimums: Map<U5Item, Rational>,
    val maximums: Map<U5Item, Rational>,
)
