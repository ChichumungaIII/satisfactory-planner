package app.factory.model

import app.data.u6.U6Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class FactoryUnitContainer(
    override val title: String = "New Container",
    val units: List<FactoryUnit> = listOf(),
) : FactoryUnit {
    override val outcome: Map<U6Item, Rational>
        get() = units.map { it.outcome }.fold(mutableMapOf<U6Item, Rational>()) { acc, outcome ->
            outcome.forEach { (item, result) -> acc.put(item, acc.getOrElse(item) { 0.q } + result) }
            acc
        }.toMap()

    override val generation: Double
        get() = units.map { it.generation }.sum()
    override val consumption: Double
        get() = units.map { it.consumption }.sum()

    fun addUnit(unit: FactoryUnit) = copy(units = units + unit)
    fun setUnit(i: Int, unit: FactoryUnit) = copy(units = units.toMutableList().also { it[i] = unit }.toList())
    fun removeUnit(i: Int) = copy(units = units.toMutableList().also { it.removeAt(i) })

    fun swapUnits(x: Int, y: Int): FactoryUnit {
        if (x == y) return this
        if (x > y) return swapUnits(y, x)
        check(x >= 0) { "Swap lower bound [$x] must be at least 0." }
        check(y < units.size) { "Swap upper bound [$y] can be at most [${units.size - 1}]" }

        val before = units.subList(0, x)
        val between = units.subList(x + 1, y)
        val after = units.subList(y + 1, units.size)
        return copy(units = before + units[y] + between + units[x] + after)
    }
}
