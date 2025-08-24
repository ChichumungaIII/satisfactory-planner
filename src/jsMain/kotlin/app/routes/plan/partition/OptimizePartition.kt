package app.routes.plan.partition

import app.api.optimize.v2.getOptimizeService
import app.api.optimize.v2.request.OptimizeInput
import app.api.optimize.v2.request.OptimizeOutput
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeConsumption
import app.api.optimize.v2.response.OptimizeProduction
import app.api.optimize.v2.response.OptimizeResponse
import app.api.plan.v1.Plan
import app.api.plan.v1.Plan.Partition
import app.api.plan.v1.Plan.Target.Limit
import app.game.data.RecipeV2
import app.game.data.RecipeV2.Type
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

  val outputs = partition.products.mapNotNull { it.toOptimizeOutput() } +
      partition.partitions.flatMap { it.inputs }.mapNotNull { it.toOptimizeOutput() } +
      partition.byproducts.mapNotNull { it.toOptimizeOutput() }

  val limits = partition.targets.filter { it.limit == Limit.RESTRICTED }
    .mapNotNull { it.restriction?.let { restriction -> it.recipe to restriction } }
    .toMap()
  val banned = partition.targets.filter { it.limit == Limit.BANNED }.map { it.recipe }.toSet()

  return OptimizeRequest(
    inputs = inputs,
    outputs = outputs,
    recipes = RecipeV2.allRecipes(Type.PRODUCTION, Type.GENERATION).toSet() - banned,
    limits = limits,
  )
}

private fun Plan.Input.toOptimizeInput() = item?.let { quantity?.let { OptimizeInput(item, quantity) } }
private fun Plan.Input.toOptimizeOutput() =
  item?.let { consumption?.let { OptimizeOutput.Production(item, consumption, exact = false) } }

private fun Plan.Product.toOptimizeInput() =
  item?.let { amount?.let { (OptimizeInput(item, amount, required = true)) } }

private fun Plan.Product.toOptimizeOutput() = item?.let {
  if (maximize) weight?.let { OptimizeOutput.Maximization(item, weight) }
  else amount?.let { OptimizeOutput.Production(item, amount) }
}

private fun Plan.Byproduct.toOptimizeOutput() = OptimizeOutput.Production(item, 0.q).takeIf { banned }

fun integrate(partition: Partition, optimization: OptimizeResponse): Partition {
  // TODO: Interleave the results with (potentially invalid) inputs instead of assuming a 1:1 correspondence.
  val inputs = partition.inputs.mapIndexed { i, input -> input.copyFrom(optimization.consumed[i]) }
  val products = partition.products.mapIndexed { i, product -> product.copyFrom(optimization.produced[i]) }

  val banned = partition.byproducts.filter { it.banned }.map { it.item }
  val byproducts = (banned.map { Plan.Byproduct(it, 0.q, true) } +
      optimization.byproducts.map { (item, amount) -> Plan.Byproduct(item, amount, banned.contains(item)) })
    .associateBy { it.item }
    .values.sortedBy { it.item }

  val targets = (optimization.rates.mapValues { (recipe, rate) -> Plan.Target(recipe, rate) } +
      partition.targets.filter { optimization.rates.containsKey(it.recipe) || it.limit != Limit.NONE }
        .map { it.copy(rate = optimization.rates[it.recipe] ?: 0.q) }
        .associateBy { it.recipe })
    .values.sortedBy { it.recipe }

  return partition.copy(
    inputs = inputs,
    products = products,
    byproducts = byproducts,
    targets = targets,
    optimized = true,
  )
}

private fun Plan.Input.copyFrom(input: OptimizeConsumption) = copy(
  item = input.item,
  quantity = input.amount,
  consumption = input.consumed,
  demand = input.demand
)

private fun Plan.Product.copyFrom(product: OptimizeProduction) = copy(
  item = product.item,
  amount = product.amount,
  potential = product.potential,
)
