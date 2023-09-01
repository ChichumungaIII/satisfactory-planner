package app.data.optimization

import app.api.common.Resource
import app.api.optimize.v2.OptimizeRequest
import app.api.optimize.v2.OptimizeRequest.Objective
import app.api.optimize.v2.OptimizeRequest.Provision
import app.api.optimize.v2.OptimizeRequest.Requirement
import app.api.optimize.v2.OptimizeRequest.Restriction
import app.api.optimize.v2.OptimizeResponse
import app.api.plan.v1.Plan.Partition
import app.api.plan.v1.Plan.Product
import app.api.plan.v1.Plan.Target
import app.game.data.Item
import util.math.Rational
import util.math.q

@Suppress("DataClassPrivateConstructor")
data class Optimization private constructor(
  val request: OptimizeRequest,
  val response: OptimizeResponse? = null,
) : Resource<OptimizationName> {
  companion object {
    private val PROVISION_ORDER = compareBy<Provision> { it.item.ordinal }
    private val REQUIREMENT_ORDER = compareBy<Requirement> { it.item.ordinal }.thenBy { it.amount }
    private val RESTRICTION_ORDER = compareBy<Restriction> { it.recipe.ordinal }.thenBy { it.rate }
    private val OBJECTIVE_ORDER = compareBy<Objective> { it.item.ordinal }.thenBy { it.weight }

    fun create(partition: Partition): Optimization {
      val directInputs = partition.inputs.map { it.item to it.quantity }
      val directConsumption = partition.partitions.flatMap { it.inputs }.map { it.item to it.quantity?.unaryMinus() }
      val indirectInputs = partition.partitions.flatMap { it.products }.map { it.item to it.amount }
      val provisions =
        (directInputs + directConsumption + indirectInputs)
          .mapNotNull { (item, quantity) -> item?.let { quantity?.let { item to quantity } } }
          .fold(mutableMapOf<Item, Rational>()) { map, (item, quantity) ->
            map[item] = quantity + (map[item] ?: 0.q)
            map
          }.map { (item, quantity) -> Provision(item, quantity) }
          .sortedWith(PROVISION_ORDER)

      val requirements = partition.products.mapNotNull { it.toRequirement() }.sortedWith(REQUIREMENT_ORDER)
      val restrictions = partition.targets.mapNotNull { it.toRestriction() }.sortedWith(RESTRICTION_ORDER)
      val objectives = partition.products.mapNotNull { it.toObjective() }.sortedWith(OBJECTIVE_ORDER)

      return Optimization(OptimizeRequest(provisions, requirements, restrictions, objectives))
    }

    private fun Product.toRequirement() =
      item?.let { amount?.takeUnless { maximize }?.let { Requirement(item, amount) } }

    private fun Target.toRestriction() =
      (0.q.takeIf { banned } ?: restriction)?.let { Restriction(recipe, it) }

    private fun Product.toObjective() =
      item?.let { weight?.takeIf { maximize }?.let { Objective(item, weight) } }
  }

  override val name = OptimizationName(request.hashCode())
}


