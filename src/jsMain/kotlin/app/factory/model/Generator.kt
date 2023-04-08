package app.factory.model

import app.data.Item
import app.data.building.Building
import app.data.recipe.Recipe
import app.util.math.toFixed
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class Generator(
  val building: Building,
  val recipe: Recipe? = null,
  val clock: Rational = 1.q,
  override val open: Boolean = true,
  override val details: Boolean = false,
) : FactoryUnit {
  override val outcome: Map<Item, Rational>
    get() {
      if (recipe == null) return mapOf()
      return recipe.components.map { (item, amount) ->
        item to amount * clock * 60.q / recipe.time
      }.toMap()
    }

  override val generation: Double
    get() = (building.generation * clock).toDouble()

  override val title = building.displayName + (recipe?.let(this::productionDisplay) ?: "")

  private fun productionDisplay(recipe: Recipe): String {
    var clockDisplay = (clock * 100.q).toFixed(4)
    if (clockDisplay.endsWith(".0000")) clockDisplay = clockDisplay.substringBefore(".")
    return " (${recipe.displayName} @$clockDisplay%)"
  }

  override fun clone(open: Boolean?, details: Boolean?) =
    copy(open = open ?: this.open, details = details ?: this.details)
}
