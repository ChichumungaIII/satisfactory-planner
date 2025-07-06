package app.api.optimize.v1

import app.game.data.Item
import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeResponse(
  val outcome: Map<Recipe, Rational>,
  val minimums: Map<Item, Rational>,
  val maximums: Map<Item, Rational>,
)
