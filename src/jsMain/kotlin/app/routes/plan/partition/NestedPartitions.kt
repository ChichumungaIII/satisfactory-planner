package app.routes.plan.partition

import app.api.plan.v1.Plan.Partition
import app.redux.useAppDispatch
import app.routes.plan.PartitionContext
import app.routes.plan.PartitionContextValue
import app.routes.plan.usePartition
import app.routes.plan.useProgress
import mui.icons.material.Delete
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Box
import mui.material.Button
import mui.material.IconButton
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.create
import web.cssom.Margin
import web.cssom.Padding
import web.cssom.px

external interface NestedPartitionsProps : Props

val NestedPartitions: FC<NestedPartitionsProps> = FC("NestedPartitions") { props ->
  val dispatch = useAppDispatch()
  val progress = useProgress()
  var parent by usePartition()

  fun setChild(i: Int, child: Partition) {
    parent = parent.copy(partitions = parent.partitions.toMutableList().also { it[i] = child }.toList())
  }

  Box {
    sx { padding = Padding(4.px, 4.px) }

    Typography {
      sx { marginLeft = 66.px }
      variant = TypographyVariant.h2
      +"Partitions"
    }

    parent.partitions.forEachIndexed { i, child ->
      PartitionContext(PartitionContextValue(child) { next ->
        val updated = next.copy(optimized = false)
        setChild(i, updated)
        dispatch(OptimizePartition(updated, progress) { optimized -> setChild(i, optimized) })
      }) {
        Accordion {
          AccordionSummary {
            IconButton {
              Delete {}
              onClick = {
                parent = parent.copy(
                  partitions = parent.partitions.subList(0, i) +
                      parent.partitions.subList(i + 1, parent.partitions.size)
                )
              }
            }
            Typography {
              variant = TypographyVariant.caption
              +child.displayName
            }
            expandIcon = ExpandMore.create()
          }
          AccordionDetails { PartitionComponent {} }
        }
      }
    }

    Button {
      sx {
        margin = Margin(4.px, 0.px, 2.px, 66.px)
        width = 442.125.px
      }
      onClick = { parent = parent.copy(partitions = parent.partitions + Partition.empty()) }
      +"Create Partition"
    }
  }
}
