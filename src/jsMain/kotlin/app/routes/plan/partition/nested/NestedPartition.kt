package app.routes.plan.partition.nested

import app.api.plan.v1.Plan
import app.routes.plan.partition.PartitionComponent
import app.util.PropsDelegate
import mui.icons.material.Delete
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.IconButton
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create

external interface NestedPartitionProps : Props {
  var partition: Plan.Partition
  var setPartition: (Plan.Partition) -> Unit

  var onDelete: () -> Unit
}

val NestedPartition = FC<NestedPartitionProps>("NestedPartition") { props ->
  var partition by PropsDelegate(props.partition, props.setPartition)

  Accordion {
    AccordionSummary {
      IconButton {
        Delete {}
        onClick = { props.onDelete() }
      }
      Typography {
        variant = TypographyVariant.subtitle1
        +partition.displayName
      }
      expandIcon = ExpandMore.create()
    }

    AccordionDetails { PartitionComponent {} }
  }
}
