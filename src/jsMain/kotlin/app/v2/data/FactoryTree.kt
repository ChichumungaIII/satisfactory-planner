package app.v2.data

import app.data.building.Building
import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class FactoryTree(
    val nodes: List<FactoryNode> = listOf(FactoryLeaf()),
    val count: UInt? = null,
) : FactoryNode {
    fun addNode(node: FactoryNode) =
        copy(nodes = nodes + node)

    fun setNode(i: Int, node: FactoryNode) =
        copy(nodes = nodes.subList(0, i) + node + nodes.subList(i + 1, nodes.size))

    fun removeNode(i: Int) = splice(i, 1)

    fun splice(i: Int, length: Int = 0, vararg insert: FactoryNode) =
        copy(nodes = nodes.subList(0, i) + insert + nodes.subList(i + length, nodes.size))

}

@Serializable
data class FactoryLeaf(
    val count: UInt? = null,
    val building: Building? = null,
    val recipe: Recipe? = null,
    val clock: Rational = 1.q,
    val details: Boolean = false,
) : FactoryNode

@Serializable
sealed interface FactoryNode {}