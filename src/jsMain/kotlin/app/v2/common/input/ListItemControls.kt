package app.v2.common.input

import csstype.ClassName
import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.icons.material.Clear
import mui.material.IconButton
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.SvgIconSize
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.create

external interface ListItemControlsProps : Props {
  var primaryItemControl: ReactNode?
  var onDelete: () -> Unit

  var onMoveUp: () -> Unit
  var disableMoveUp: Boolean

  var onMoveDown: () -> Unit
  var disableMoveDown: Boolean
}

val ListItemControls = FC<ListItemControlsProps>("ListItemControls") { props ->
  +(props.primaryItemControl ?: TooltipIconButton.create {
    className = ClassName("list-item-controls__delete-button")
    title = "Delete"
    icon = Clear
    onClick = { props.onDelete() }
  })

  Stack {
    direction = responsive(StackDirection.column)

    IconButton {
      className = ClassName("list-item-controls__position-control")
      size = Size.small
      ArrowDropUp { fontSize = SvgIconSize.medium }
      disabled = props.disableMoveUp
      onClick = { props.onMoveUp() }
    }

    IconButton {
      className = ClassName("list-item-controls__position-control")
      size = Size.small
      ArrowDropDown { fontSize = SvgIconSize.medium }
      disabled = props.disableMoveDown
      onClick = { props.onMoveDown() }
    }
  }
}
