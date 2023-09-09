package app.routes.plan.partition

import app.api.optimize.v2.OptimizeResponse
import app.api.optimize.v2.OptimizeServiceJs
import app.api.plan.v1.Plan
import app.api.plan.v1.Plan.Partition
import app.data.optimization.Optimization
import app.data.optimization.OptimizationCache
import app.game.data.Item
import app.util.launchMain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import react.Dispatch
import react.FC
import react.PropsWithChildren
import react.createContext
import react.useContext
import react.useEffect
import react.useReducer
import react.useState
import util.math.Rational
import util.math.q
import kotlin.time.Duration.Companion.milliseconds

external interface PartitionManagerProps : PropsWithChildren {
  var partition: Partition
  var setPartition: (Partition) -> Unit
  var deletePartition: () -> Unit
}

class PartitionManager private constructor(
  private val partition: Partition,
  private val updatePartition: Dispatch<PartitionManagerAction>,
) {
  companion object {
    private sealed interface PartitionManagerAction
    private data class Apply(val update: (partition: Partition) -> Partition) : PartitionManagerAction
    private data object Delete : PartitionManagerAction
    private data class Set(val partition: Partition) : PartitionManagerAction

    val Context = createContext<PartitionManager>()
    val Provider = FC<PartitionManagerProps>("PartitionManagerProvider") { props ->
      val optimizeService = useContext(OptimizeServiceJs.Context)!!
      val optimizationCache = useContext(OptimizationCache)!!

      var job by useState<Job?>(null)

      val (partition, updatePartition) = useReducer({ current: Partition, action: PartitionManagerAction ->
        when (action) {
          is Apply -> {
            job?.cancel()
            var partition = action.update(current)
            if (!partition.optimized && partition.partitions.all { it.optimized }) {
              val optimization = Optimization.create(partition).let { optimizationCache[it.name] ?: it }
              optimization.response?.also { partition = integrate(partition, it) } ?: run {
                if (optimization.request.let {
                    it.provisions.isEmpty() || (it.requirements.isEmpty() && it.objectives.isEmpty())
                  }) {
                  return@run
                }

                job = launchMain {
                  delay(2000.milliseconds)
                  if (!isActive) return@launchMain

                  val response = optimizeService.optimize(optimization.request)
                  optimizationCache.insert(optimization.copy(response = response))
                  if (!isActive) return@launchMain

                  integrate(partition, response).also { props.setPartition(it) }
                  job = null
                }
              }
            }

            partition.also { props.setPartition(it) }
          }

          is Delete -> current.also { props.deletePartition() }
          is Set -> action.partition
        }
      }, initialState = props.partition)
      useEffect(props.partition) { updatePartition(Set(props.partition)) }

      Context(PartitionManager(partition, updatePartition)) { +props.children }
    }

    private fun integrate(partition: Partition, response: OptimizeResponse): Partition {
      val demands = response.demands.associate { it.item to it.demand }.toMutableMap()
      val inputs = partition.inputs.map {
        both(it.item, it.quantity) { item, quantity ->
          val demanded = demands[item] ?: 0.q
          val demand = minOf(quantity, demanded)
          demands[item] = demanded - demand
          it.copy(demand = demand)
        } ?: it
      }

      val productions = response.productions.associate { it.item to it.amount }.toMutableMap().withDefault { 0.q }
      partition.products.filterNot { it.maximize }
        .mapNotNull { both(it.item, it.amount) { item, amount -> item to amount } }
        .forEach { (item, amount) -> productions[item] = productions[item]!! - amount }
      val weights = partition.products.filter { it.maximize }
        .mapNotNull { both(it.item, it.weight) { item, weight -> item to weight } }
        .fold(mutableMapOf<Item, Rational>()) { weights, (item, weight) ->
          weights.also { it[item] = it.getOrElse(item) { 0.q } + weight }
        }
      val potentials = response.potentials.associate { it.item to it.potential }.withDefault { 0.q }
      val products = partition.products.map {
        it.item?.let { item ->
          var product = it.copy(potential = potentials[item]!!)
          if (it.maximize) {
            it.weight?.let { weight -> productions[item]!! * weight / weights[item]!! }?.also { amount ->
              product = product.copy(amount = amount)
              productions[item] = productions[item]!! - amount
            }
          }
          product
        } ?: it
      }

      val existingByproducts = partition.byproducts.associateBy { it.item }
      val newByproducts = productions.filterValues { it != 0.q }
        .mapValues { (item, amount) ->
          existingByproducts[item]?.copy(amount = amount) ?: Plan.Byproduct(item, amount, false)
        }
      val byproducts = (newByproducts.values + existingByproducts.filterKeys { newByproducts.containsKey(it) }.values)
        .sortedBy { it.item.ordinal }

      val existingTargets = partition.targets.associateBy { it.recipe }
      val newTargets = response.rates.map {
        existingTargets[it.recipe]?.copy(
          recipe = it.recipe,
          rate = it.rate,
        ) ?: Plan.Target(
          recipe = it.recipe,
          rate = it.rate,
        )
      }.associateBy { it.recipe }
      val targets = (newTargets.values + existingTargets.filterKeys { !existingTargets.containsKey(it) }.values)
        .sortedBy { it.recipe.ordinal }

      return partition.copy(
        inputs = inputs,
        products = products,
        byproducts = byproducts,
        targets = targets,
        optimized = partition.partitions.all { it.optimized },
      )
    }

    private fun <A, B, Z> both(a: A?, b: B?, mapper: (A, B) -> Z) = a?.let { b?.let { mapper(a, b) } }
  }

  fun update(updater: (Partition) -> Partition) = updatePartition(Apply(updater))

  fun delete() = updatePartition(Delete)

  operator fun component1() = partition
  operator fun component2() = this
}
