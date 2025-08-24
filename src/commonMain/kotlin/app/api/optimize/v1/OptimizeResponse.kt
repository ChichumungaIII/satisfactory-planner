package app.api.optimize.v1

import app.game.data.Item
import app.game.data.RecipeV2
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
  val outcome: Map<RecipeV2, Rational>,
  val minimums: Map<Item, Rational>,
  val maximums: Map<Item, Rational>,
)
