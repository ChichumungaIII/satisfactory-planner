package app.routes.plan.partition

import app.api.optimize.v2.OptimizeRequest
import app.api.optimize.v2.OptimizeResponse
import app.api.optimize.v2.getOptimizeService
import app.api.plan.v1.Plan
import app.api.plan.v1.Plan.Partition
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

    if (partition.partitions.isNotEmpty() && !partition.partitions.all { it.optimized }) return

    val request = launchMain {
      if (partition.partitions.isEmpty()) {
        delay(3.seconds)
        if (!isActive) return@launchMain
      }

      val optimizeRequest = toRequest(partition)
      val response = getOptimizeService().optimize(optimizeRequest)
      if (!isActive) return@launchMain

      callback(integrate(partition, response))
    }
    dispatch(RegisterOptimizationRequest(partition.id, request))
  }
}

fun toRequest(partition: Partition): OptimizeRequest {
  val inputs = partition.inputs.mapNotNull { it.toOptimizeInput() } +
      partition.partitions.flatMap { it.products }.mapNotNull { it.toOptimizeInput() }
  val products = partition.products.mapNotNull { it.toOptimizeProduct() } +
      partition.partitions.flatMap { it.inputs }.mapNotNull { it.toOptimizeProduct() }
  val restrictions = partition.targets.mapNotNull { it.toOptimizeRestriction() }
  return OptimizeRequest(
    inputs = inputs,
    products = products,
    restrictions = restrictions,
    alternates = listOf() // TODO: add unlocked alternate recipes.
  )
}

private fun Plan.Input.toOptimizeInput() = item?.let { quantity?.let { OptimizeRequest.Input(item, quantity) } }
private fun Plan.Product.toOptimizeInput() = item?.let { amount?.let { (OptimizeRequest.Input(item, amount)) } }

private fun Plan.Product.toOptimizeProduct() = item?.let {
  if (maximize) weight?.let { OptimizeRequest.Product.weight(item, weight) }
  else amount?.let { OptimizeRequest.Product.amount(item, amount) }
}

private fun Plan.Input.toOptimizeProduct() =
  item?.let { quantity?.let { OptimizeRequest.Product.amount(item, quantity) } }

private fun Plan.Target.toOptimizeRestriction() = when (limit) {
  Plan.Target.Limit.NONE -> null
  Plan.Target.Limit.BANNED -> OptimizeRequest.Restriction(recipe, 0.q)
  Plan.Target.Limit.RESTRICTED -> restriction?.let { OptimizeRequest.Restriction(recipe, restriction) }
}

fun integrate(partition: Partition, optimization: OptimizeResponse): Partition {
  val inputs = partition.inputs.mapIndexed { i, input -> input.copyFrom(optimization.inputs[i]) }
  val products = partition.products.mapIndexed { i, product -> product.copyFrom(optimization.products[i]) }

  val targets = (optimization.rates.mapValues { (recipe, rate) -> Plan.Target(recipe, rate) } +
      partition.targets.filter { optimization.rates.containsKey(it.recipe) || it.limit != Plan.Target.Limit.NONE }
        .map { it.copy(rate = optimization.rates[it.recipe] ?: 0.q) }
        .associateBy { it.recipe })
    .values.sortedBy { it.recipe }

  return partition.copy(
    inputs = inputs,
    products = products,
    targets = targets,
    optimized = true,
  )
}

private fun Plan.Input.copyFrom(input: OptimizeResponse.Input) = copy(
  item = input.item,
  quantity = input.quantity,
  consumption = input.consumption,
  demand = input.demand
)

private fun Plan.Product.copyFrom(product: OptimizeResponse.Product) = copy(
  item = product.item,
  amount = product.amount,
  potential = product.potential,
)
