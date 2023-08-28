package app.api.optimize.v2

import app.api.common.ResourceName
import app.game.data.Item
import app.game.data.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeRequest(
  val provisions: List<Provision>,
  val requirements: List<Requirement>,
  val restrictions: List<Restriction>,
  val objectives: List<Objective>,
) : ResourceName {
  override fun getResourceName() = "Optimization-${hashCode()}"

  @Serializable
  data class Provision(
    val item: Item,
    val quantity: Rational,
  )

  @Serializable
  data class Requirement(
    val item: Item,
    val amount: Rational,
  )

  @Serializable
  data class Restriction(
    val recipe: Recipe,
    val rate: Rational,
  )

  @Serializable
  data class Objective(
    val item: Item,
    val weight: Rational,
  )
}
