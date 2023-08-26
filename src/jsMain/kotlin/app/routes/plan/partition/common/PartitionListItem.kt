package app.routes.plan.partition.common

import mui.icons.material.Delete
import mui.material.IconButton
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.PropsWithChildren

external interface PartitionListItemProps : PropsWithChildren {
  var deleteItem: () -> Unit
}

val PartitionListItem = FC<PartitionListItemProps>("PartitionListItem") { props ->
  Stack {
    direction = responsive(StackDirection.row)

    +props.children

    IconButton {
      Delete {}
      onClick = { props.deleteItem() }
    }
  }
}
