package app.factory.model

import app.data.u6.U6Item
import kotlinx.serialization.Serializable
import util.math.Rational

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
}
