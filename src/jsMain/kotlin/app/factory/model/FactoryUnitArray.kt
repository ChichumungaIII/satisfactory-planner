package app.factory.model

import app.data.u6.U6Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class FactoryUnitArray(
    override var title: String = "Array",
    val unit: FactoryUnit? = null,
    val count: Int = 1,
    override val open: Boolean = true,
    override val details: Boolean = false,
) : FactoryUnit {
    override val outcome: Map<U6Item, Rational>
        get() = unit?.outcome?.map { (item, result) -> item to result * count.q }?.toMap() ?: mapOf()

    override val generation: Double
        get() = (unit?.generation ?: 0.0) * count
    override val consumption: Double
        get() = (unit?.consumption ?: 0.0) * count

    override fun clone(open: Boolean?, details: Boolean?) =
        copy(open = open ?: this.open, details = details ?: this.details)
}
