package app.routes.plan.partition.nested

import app.api.plan.v1.Plan
import app.redux.useAppDispatch
import app.routes.plan.PartitionContext
import app.routes.plan.PartitionContextValue
import app.routes.plan.partition.OptimizePartition
import app.routes.plan.usePartition
import app.routes.plan.useProgress
import app.util.PropsDelegate
import mui.material.Button
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import web.cssom.px

val NestedPartitions: FC<Props> = FC<Props>("NestedPartitions") {
  val dispatch = useAppDispatch()
  val progress = useProgress()

  var parent by usePartition()
  var partitions by PropsDelegate(parent.partitions) { next -> parent = parent.copy(partitions = next) }

  Typography {
    variant = TypographyVariant.h2
    +"Partitions"
  }

  Stack {
    direction = responsive(StackDirection.column)
    spacing = responsive(8.px)

    partitions.forEachIndexed { i, partition ->
      val setPartition = { next: Plan.Partition ->
        partitions = partitions.toMutableList().also { it[i] = next }
      }

      PartitionContext(PartitionContextValue(partition) { next ->
        val updated = next.copy(optimized = false)
        setPartition(next)
        dispatch(OptimizePartition(updated, progress) { optimized -> setPartition(optimized) })
      }) {
        NestedPartition {
          this.partition = partition
          this.setPartition = setPartition

          onDelete = { partitions = partitions.toMutableList().also { it.removeAt(i) } }
        }
      }
    }
  }

  Button {
    sx { width = 442.125.px }
    onClick = { partitions = partitions + Plan.Partition.empty() }
    +"New Partition"
  }
}
