package app.factory.model

import app.data.u6.U6Generator
import app.data.u6.U6Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

// TODO: Fix this for different clock speeds
@Serializable
data class Generator(
    val generator: U6Generator,
    val fuel: U6Item? = null,
    val clock: Rational = 1.q,
    override val open: Boolean = true,
    override val details: Boolean = false,
) : FactoryUnit {
    override val outcome: Map<U6Item, Rational>
        get() {
            if (fuel == null) return mapOf()
            val time = fuel.energy / generator.power
            return mapOf(
                fuel to 60.q / time,
                U6Item.WATER to 60.q * generator.water / time,
            )
        }

    override val generation = generator.power.toDouble()

    override val title =
        generator.displayName + (fuel?.let { " (${it.displayName}) @${clock.toClockDisplay()}" } ?: "")

    override fun clone(open: Boolean?, details: Boolean?) =
        copy(open = open ?: this.open, details = details ?: this.details)
}
