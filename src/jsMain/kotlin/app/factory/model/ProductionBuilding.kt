package app.factory.model

import app.data.u6.U6Building
import app.data.u6.U6Item
import app.data.u6.U6Recipe
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q
import kotlin.math.pow

@Serializable
data class ProductionBuilding(
    val building: U6Building,
    val recipe: U6Recipe? = null,
    val clock: Rational = 1.q,
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
}
