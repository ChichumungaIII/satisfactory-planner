package app.routes.plan.partition

import app.api.plan.v1.Plan
import react.Dispatch
import react.FC
import react.PropsWithChildren
import react.createContext
import react.useEffect
import react.useReducer

external interface PartitionManagerProps : PropsWithChildren {
  var partition: Plan.Partition
  var setPartition: (Plan.Partition) -> Unit
  var deletePartition: () -> Unit
}

class PartitionManager private constructor(
  private val partition: Plan.Partition,
  private val updatePartition: Dispatch<PartitionManagerAction>,
) {
  companion object {
    private sealed interface PartitionManagerAction
    private data class Apply(val update: (partition: Plan.Partition) -> Plan.Partition) : PartitionManagerAction
    private data object Delete : PartitionManagerAction
    private data class Set(val parition: Plan.Partition) : PartitionManagerAction

    val Context = createContext<PartitionManager>()
    val Provider = FC<PartitionManagerProps>("PartitionManagerProvider") { props ->
      val (partition, updatePartition) = useReducer({ partition: Plan.Partition, action: PartitionManagerAction ->
        when (action) {
          is Apply -> action.update(partition).also { props.setPartition(it) }
          is Delete -> partition.also { props.deletePartition() }
          is Set -> action.parition
        }
      }, initialState = props.partition)
      useEffect(props.partition) { updatePartition(Set(props.partition)) }

      Context(PartitionManager(partition, updatePartition)) { +props.children }
    }
  }

  fun update(updater: (Plan.Partition) -> Plan.Partition) = updatePartition(Apply(updater))

  fun delete() = updatePartition(Delete)

  operator fun component1() = partition
  operator fun component2() = this
}
