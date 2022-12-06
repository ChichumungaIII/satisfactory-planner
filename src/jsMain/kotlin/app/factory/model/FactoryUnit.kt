package app.factory.model

import app.data.u6.U6Item
import app.util.math.toFixed
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
sealed interface FactoryUnit {
    /** The net production and consumption of items within this factory unit.  */
    val outcome: Map<U6Item, Rational> get() = mapOf()

    /** The total amount of power produced by all generators in this factory unit. */
    val generation: Double get() = 0.0

    /** The total amount of power consumed by all buildings in this factory unit. */
    val consumption: Double get() = 0.0

    /** This factory's net power production. */
    val power get() = generation - consumption

    val title: String
    val open: Boolean
    val details: Boolean

    fun clone(open: Boolean? = null, details: Boolean? = null): FactoryUnit

    fun Rational.toClockDisplay(): String {
        val display = times(100.q).toFixed(4)
        return if (display.endsWith(".0000")) display.substringBefore('.') else display
    }
}
