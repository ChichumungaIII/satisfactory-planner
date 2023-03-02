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
}

@Serializable
data class FactoryLeaf(
    val building: Building? = null,
    val recipe: Recipe? = null,
    val clock: Rational = 1.q,
    val count: UInt? = null,
) : FactoryNode {
}

@Serializable
sealed interface FactoryNode {}