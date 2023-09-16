package app.common.layout.arrangedlist

import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.icons.material.Delete
import mui.material.Box
import mui.material.IconButton
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.PropsWithChildren

external interface ArrangedListItemProps : PropsWithChildren {
  var onDelete: () -> Unit

  var canMoveUp: Boolean
  var onMoveUp: () -> Unit

  var canMoveDown: Boolean
  var onMoveDown: () -> Unit
}

val ArrangedListItem = FC<ArrangedListItemProps>("ArrangedListItem") { props ->
  Stack {
    direction = responsive(StackDirection.row)

    IconButton {
      Delete {}
      onClick = { props.onDelete() }
    }

    Box {
      sx { }

      IconButton {
        ArrowDropUp {}
        disabled = !props.canMoveUp
        onClick = { props.onMoveUp() }
      }

      IconButton {
        ArrowDropDown {}
        disabled = !props.canMoveDown
        onClick = { props.onMoveDown() }
      }
    }

    +props.children
  }
}
