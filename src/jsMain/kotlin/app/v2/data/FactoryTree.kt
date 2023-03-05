package app.v2.data

import app.data.Item
import app.data.building.Building
import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class FactoryTree(
    val count: UInt? = null,
    val title: String? = null,
    val nodes: List<FactoryNode> = listOf(FactoryLeaf()),
    val details: Boolean = false,
    val expanded: Boolean = true,
) : FactoryNode {
    fun addNode(node: FactoryNode) = copy(nodes = nodes + node)

    fun setNode(i: Int, node: FactoryNode) = copy(nodes = nodes.subList(0, i) + node + nodes.subList(i + 1, nodes.size))

    fun removeNode(i: Int) = splice(i, 1)

    fun splice(i: Int, length: Int = 0, vararg insert: FactoryNode) =
        copy(nodes = nodes.subList(0, i) + insert + nodes.subList(i + length, nodes.size))

    private val components: Map<Item, Rational> by lazy {
        (nodes.map { it.inputs.mapValues { (_, rate) -> -rate } } + nodes.map { it.outputs }).asSequence()
            .flatMap { it.entries }
            .groupBy({ it.key }) { it.value }
            .mapValues { (_, rates) -> rates.reduce(Rational::plus) }
            .mapValues { (_, rate) -> count.q * rate }
    }
    override val inputs by lazy { components.filterValues { it < 0.q }.mapValues { (_, rate) -> -rate } }
    override val outputs by lazy { components.filterValues { it > 0.q } }
}

@Serializable
data class FactoryLeaf(
    val count: UInt? = null,
    val building: Building? = null,
    val recipe: Recipe? = null,
    val clock: Rational = 1.q,
    val details: Boolean = false,
) : FactoryNode {
    override val inputs by lazy { scale(recipe?.inputRates()) }
    override val outputs by lazy { scale(recipe?.outputRates()) }

    private fun scale(rates: Map<Item, Rational>?) =
        rates?.mapValues { (_, rate) -> count.q * clock * rate } ?: mapOf()
}

@Serializable
sealed interface FactoryNode {
    val inputs: Map<Item, Rational>
    val outputs: Map<Item, Rational>
}

private inline val UInt?.q: Rational get() = (this?.toInt() ?: 1).q

private fun Recipe.inputRates() = toRates(inputs)
private fun Recipe.outputRates() = toRates(outputs)
private fun Recipe.toRates(counts: Map<Item, Rational>) = counts.mapValues { (_, count) -> count * 60.q / time }


