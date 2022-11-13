package app.factory.model

import app.data.u6.U6Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
class FactoryUnitArray(
    val unit: FactoryUnit? = null,
    val count: Int = 1,
) : FactoryUnit {
    override val outcome: Map<U6Item, Rational>
        get() = unit?.outcome?.map { (item, result) -> item to result * count.q }?.toMap() ?: mapOf()

    override val generation: Double
        get() = (unit?.generation ?: 0.0) * count
    override val consumption: Double
        get() = (unit?.consumption ?: 0.0) * count
}
