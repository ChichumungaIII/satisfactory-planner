package app.api

import app.data.u6.U6Item
import app.data.u6.U6Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
    val outcome: Map<U6Recipe, Rational>,
    val minimums: Map<U6Item, Rational>,
    val maximums: Map<U6Item, Rational>,
)
