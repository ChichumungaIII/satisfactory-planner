package app.routes.plan.partition.common

import mui.icons.material.Delete
import mui.material.IconButton
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.PropsWithChildren
import web.cssom.AlignItems
import web.cssom.px

external interface PartitionListItemProps : PropsWithChildren {
  var alignItems: AlignItems?
  var deleteItem: () -> Unit
}

val PartitionListItem = FC<PartitionListItemProps>("PartitionListItem") { props ->
  Stack {
    sx { alignItems = props.alignItems ?: AlignItems.center }
    direction = responsive(StackDirection.row)
    spacing = responsive(6.px)

    +props.children

    IconButton {
      Delete {}
      onClick = { props.deleteItem() }
    }
  }
}
