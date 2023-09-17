package app.routes.plan.partition

import app.api.optimize.v2.OptimizeRequest
import app.api.optimize.v2.OptimizeRequest.Objective
import app.api.optimize.v2.OptimizeRequest.Provision
import app.api.optimize.v2.OptimizeRequest.Requirement
import app.api.optimize.v2.OptimizeRequest.Restriction
import app.api.optimize.v2.OptimizeResponse
import app.api.optimize.v2.getOptimizeService
import app.api.plan.v1.Plan.Partition
import app.api.plan.v1.Plan.Target
import app.redux.RThunk
import app.redux.state.AppState
import app.redux.state.optimization.RegisterOptimizationRequest
import app.util.launchMain
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import redux.RAction
import redux.WrapperAction
import util.math.q
import kotlin.time.Duration.Companion.seconds

class OptimizePartition(
  private val partition: Partition,
  private val callback: (optimized: Partition) -> Unit,
) : RThunk {
  override fun invoke(dispatch: (RAction) -> WrapperAction, getState: () -> AppState) {
    getState().optimizations[partition.id]?.cancel()
    val request = launchMain {
      delay(5.seconds)
      if (!isActive) return@launchMain

      val optimizeRequest = toRequest(partition)
      val response = getOptimizeService().optimize(optimizeRequest)
      if (!isActive) return@launchMain

      callback(integrate(partition, response))
    }
    dispatch(RegisterOptimizationRequest(partition.id, request))
  }
}

fun toRequest(partition: Partition): OptimizeRequest {
  val provisions = partition.inputs.mapNotNull { (item, quantity) ->
    item?.let { quantity?.let { Provision(item, quantity) } }
  }

  val requirements = partition.products.filterNot { it.maximize }.mapNotNull { (item, _, _, amount) ->
    item?.let { amount?.let { Requirement(item, amount) } }
  }

  val restrictions = partition.targets.filterNot { it.banned }.mapNotNull { (recipe, _, _, restriction) ->
    restriction?.let { Restriction(recipe, restriction) }
  } + partition.targets.filter { it.banned }.map { (recipe) ->
    Restriction(recipe, 0.q)
  }

  val objectives = partition.products.filter { it.maximize }.mapNotNull { (item, _, weight) ->
    item?.let { weight?.let { Objective(item, weight) } }
  }

  return OptimizeRequest(
    provisions = provisions,
    requirements = requirements,
    restrictions = restrictions,
    objectives = objectives,
  )
}

fun integrate(partition: Partition, optimization: OptimizeResponse): Partition {
  val productions = optimization.productions.associate { it.item to it.amount }
  val demands = optimization.demands.associate { it.item to it.demand }
  val potentials = optimization.potentials.associate { it.item to it.potential }

  val inputs = partition.inputs.map { input ->
    input.copy(
      consumption = productions[input.item]?.let { -it } ?: 0.q,
      demand = demands[input.item]!!,
    )
  }

  val products = partition.products.map { product ->
    product.copy(
      amount = productions[product.item] ?: 0.q,
      potential = potentials[product.item]!!
    )
  }

  val rates = optimization.rates.associate { it.recipe to it.rate }.toMutableMap()
  val existingTargets = partition.targets.mapNotNull { target ->
    target.takeIf { rates.containsKey(it.recipe) || it.banned }?.copy(
      rate = rates[target.recipe] ?: 0.q,
    ).also { rates.remove(target.recipe) }
  }
  val newTargets = rates.map { (recipe, rate) -> Target(recipe = recipe, rate = rate) }.filter { it.rate != 0.q }
  val targets = (existingTargets + newTargets).sortedBy { it.recipe }

  return partition.copy(
    inputs = inputs,
    products = products,
    targets = targets,
    optimized = true,
  )
}
