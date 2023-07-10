package app.common.input

import mui.icons.material.SvgIconComponent
import mui.material.IconButton
import react.FC
import react.Props
import react.StateInstance

external interface ToggleProps : Props {
  var value: StateInstance<Boolean>
  var active: SvgIconComponent
  var inactive: SvgIconComponent
}

val Toggle = FC<ToggleProps>("Toggle") { props ->
  var value by props.value

  IconButton {
    (props.active.takeIf { value } ?: props.inactive) {}
    onClick = { value = !value }
  }
}
