package app.routes.plan.partition.nested

import app.api.plan.v1.Plan
import app.routes.plan.partition.PartitionManager
import app.routes.plan.partition.common.PartitionList
import react.FC
import react.Props
import react.create
import react.useContext
import web.cssom.px

private val NEW_PARTITION = Plan.Partition.empty()

external interface NestedPartitionsProps : Props

val NestedPartitions = FC<NestedPartitionsProps>("NestedPartitions") {
  val (parent, manager) = useContext(PartitionManager.Context)!!

  PartitionList {
    spacing = 8.px
    onAdd = { manager.update { it.copy(partitions = it.partitions + NEW_PARTITION, optimized = false) } }

    partitionListItems = parent.partitions.mapIndexed { i, child ->
      PartitionManager.Provider.create {
        partition = child
        setPartition = { next ->
          manager.update { partition ->
            val partitions = partition.partitions.toMutableList().also { it[i] = next }.toList()
            partition.copy(partitions = partitions, optimized = false)
          }
        }
        deletePartition = {
          manager.update { partition ->
            val partitions = partition.partitions.let { it.subList(0, i) + it.subList(i + 1, it.size) }
            partition.copy(partitions = partitions, optimized = false)
          }
        }

        NestedPartition {}
      }
    }
  }
}
