package app.routes.plan.partition

import mui.material.Divider
import mui.system.Breakpoint
import mui.system.Stack
import mui.system.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.create

external interface PartitionComponentProps : Props

val PartitionComponent = FC<PartitionComponentProps>("PartitionComponent") {
  Stack {
    direction = responsive(StackDirection.column)
    divider = Divider.create {}

    Stack {
      direction = responsive(
        Breakpoint.xs to StackDirection.column,
        Breakpoint.lg to StackDirection.row,
      )
      divider = Divider.create {}

      PartitionInputs {}
      PartitionProducts {}
    }

//    PartitionTargets {}
//    NestedPartitions {}
  }
}
