package app.common.layout.arrangedlist

import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.icons.material.Delete
import mui.material.Box
import mui.material.IconButton
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.SvgIconColor
import mui.material.SvgIconSize
import mui.system.responsive
import mui.system.sx
import react.FC
import react.PropsWithChildren
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.px

external interface ArrangedListItemProps : PropsWithChildren {
  var onDelete: () -> Unit

  var canMoveUp: Boolean
  var onMoveUp: () -> Unit

  var canMoveDown: Boolean
  var onMoveDown: () -> Unit
}

val ArrangedListItem = FC<ArrangedListItemProps>("ArrangedListItem") { props ->
  Stack {
    sx {
      alignItems = AlignItems.center
    }

    direction = responsive(StackDirection.row)
    spacing = responsive(6.px)
    useFlexGap = true

    IconButton {
      Delete {}
      onClick = { props.onDelete() }
    }

    Box {
      sx {
        display = Display.flex
        flexDirection = FlexDirection.column
      }

      IconButton {
        size = Size.small
        disabled = !props.canMoveUp
        onClick = { props.onMoveUp() }
        ArrowDropUp {
          fontSize = SvgIconSize.small
          if (!props.canMoveUp) {
            color = SvgIconColor.disabled
          }
        }
      }

      IconButton {
        size = Size.small
        disabled = !props.canMoveDown
        onClick = { props.onMoveDown() }
        ArrowDropDown {
          fontSize = SvgIconSize.small
          if (!props.canMoveDown) {
            color = SvgIconColor.disabled
          }
        }
      }
    }

    +props.children
  }
}
