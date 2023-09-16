package app.routes.plan.partition

import mui.system.Stack
import mui.system.StackDirection
import mui.system.responsive
import react.FC
import react.Props

external interface PartitionComponentProps : Props

val PartitionComponent = FC<PartitionComponentProps>("PartitionComponent") {
  Stack {
    direction = responsive(StackDirection.column)

    Stack {
      direction = responsive(StackDirection.row)

      PartitionInputs {}
//      PartitionProducts {}
    }

//    PartitionTargets {}
//    NestedPartitions {}
  }
}
