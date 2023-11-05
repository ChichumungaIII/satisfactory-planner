package app.routes.plan.partition

import app.routes.plan.partition.input.PartitionInputs
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

    // Legacy
    PartitionProductsLegacy {}
    PartitionTargetsLegacy {}
    NestedPartitionsLegacy {}
  }
}
