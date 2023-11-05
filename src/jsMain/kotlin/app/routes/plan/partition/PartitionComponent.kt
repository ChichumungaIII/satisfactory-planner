package app.routes.plan.partition

import app.routes.plan.partition.input.PartitionInputs
import app.routes.plan.partition.nested.NestedPartitions
import app.routes.plan.partition.product.PartitionProducts
import mui.material.Divider
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.create

external interface PartitionComponentProps : Props

val PartitionComponent = FC<PartitionComponentProps>("PartitionComponent") {
  Stack {
    direction = responsive(StackDirection.column)
    divider = Divider.create {}

    PartitionInputs {}
    PartitionProducts {}

    PartitionTargetsLegacy {}

    NestedPartitions {}
  }
}
