package app.v2.data

import app.game.data.Building
import app.game.data.Item
import app.game.data.RecipeV2
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import util.math.Rational
import util.math.q

@Serializable
data class FactoryTree(
  override val count: UInt? = null,
  val title: String? = null,
  val nodes: List<FactoryNode> = listOf(FactoryLeaf()),
  val details: Boolean = false,
  val expanded: Boolean = true,
  @Transient val newGroup: List<Int>? = null,
) : FactoryNode {
  fun addNode(node: FactoryNode) = copy(nodes = nodes + node)

  fun setNode(i: Int, node: FactoryNode) = copy(nodes = nodes.subList(0, i) + node + nodes.subList(i + 1, nodes.size))

  fun removeNode(i: Int) = splice(i, 1)

  fun splice(i: Int, length: Int = 0, insert: Collection<FactoryNode> = listOf()) =
    splice(i, length, *insert.toTypedArray())

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

  override val buildings by lazy {
    nodes.map { it.buildings }
      .fold(mutableMapOf<Building, UInt>()) { result, counts ->
        counts.forEach { (building, childCount) ->
          val additional = childCount * (count ?: 1.toUInt())
          result[building] = (result[building] ?: 0.toUInt()) + additional
        }
        result
      }
  }

  override fun clone(count: UInt?) = copy(count = count)
}

@Serializable
data class FactoryLeaf(
  override val count: UInt? = null,
  val building: Building? = null,
  val recipe: RecipeV2? = null,
  val clock: Rational = 1.q,
  val details: Boolean = false,
) : FactoryNode {
  override val inputs by lazy { scale(recipe?.inputRates()) }
  override val outputs by lazy { scale(recipe?.outputRates()) }

  private fun scale(rates: Map<Item, Rational>?) =
    rates?.mapValues { (_, rate) -> count.q * clock * rate } ?: mapOf()

  override val buildings by lazy { building?.let { mapOf(it to (count ?: 1.toUInt())) } ?: mapOf() }

  override fun clone(count: UInt?) = copy(count = count)
}

@Serializable
sealed interface FactoryNode {
  val count: UInt?

  val inputs: Map<Item, Rational>
  val outputs: Map<Item, Rational>

  val buildings: Map<Building, UInt>

  fun clone(count: UInt?): FactoryNode
}

private inline val UInt?.q: Rational get() = (this?.toInt() ?: 1).q

private fun RecipeV2.inputRates() = rates.filterValues { it < 0.q }.mapValues { (_, rate) -> -rate }
private fun RecipeV2.outputRates() = rates.filterValues { it > 0.q }


