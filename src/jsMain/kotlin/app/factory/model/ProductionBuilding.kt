package app.factory.model

import app.data.u6.U6Building
import app.data.u6.U6Item
import app.data.u6.U6Recipe
import app.util.math.toFixed
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q
import kotlin.math.pow

@Serializable
data class ProductionBuilding(
    val building: U6Building,
    val recipe: U6Recipe? = null,
    val clock: Rational = 1.q,
    override val open: Boolean = true,
    override val details: Boolean = false,
) : FactoryUnit {
    override val outcome: Map<U6Item, Rational>
        get() {
            if (recipe == null) return mapOf()
            return recipe.components.map { (item, amount) ->
                item to amount * clock * 60.q / recipe.time
            }.toMap()
        }

    override val consumption: Double
        get() = building.power.toDouble() * clock.toDouble().pow(1.6)

    override val title = building.displayName + (recipe?.let(this::productionDisplay) ?: "")

    private fun productionDisplay(recipe: U6Recipe): String {
        var clockDisplay = (clock * 100.q).toFixed(4)
        if (clockDisplay.endsWith(".0000")) clockDisplay = clockDisplay.substringBefore(".")
        return " (${recipe.displayName} @$clockDisplay%)"
    }

    override fun clone(open: Boolean?, details: Boolean?) =
        copy(open = open ?: this.open, details = details ?: this.details)
}
