package app.factory.model

import app.data.u6.U6Item
import app.data.u6.U6Recipe
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q
import kotlin.math.pow

@Serializable
sealed class ProductionBuilding(
    val recipe: U6Recipe? = null,
    val clock: Rational = 1.q,
) : FactoryUnit {
    abstract val basePower: Int

    override val outcome: Map<U6Item, Rational>
        get() = recipe?.components?.map { (item, quantity) ->
            item to quantity * clock
        }?.toMap() ?: mapOf()

    override val consumption: Double
        get() = basePower * clock.toDouble().pow(1.6)
}