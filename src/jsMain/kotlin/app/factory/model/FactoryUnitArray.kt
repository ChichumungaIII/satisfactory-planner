package app.factory.model

import app.data.u6.U6Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
class FactoryUnitArray(
    val unit: FactoryUnit,
    val count: Int,
) : FactoryUnit {
    override val outcome: Map<U6Item, Rational>
        get() = unit.outcome.map { (item, result) -> item to result * count.q }.toMap()

    override val generation: Double
        get() = unit.generation * count
    override val consumption: Double
        get() = unit.consumption * count
}
