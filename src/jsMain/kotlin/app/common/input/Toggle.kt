package app.common.input

import app.util.PropsDelegate
import mui.icons.material.SvgIconComponent
import mui.material.IconButton
import react.FC
import react.Props

external interface ToggleProps : Props {
  var value: Boolean
  var setValue: (Boolean) -> Unit

  var active: SvgIconComponent
  var inactive: SvgIconComponent
}

val Toggle = FC<ToggleProps>("Toggle") { props ->
  var value by PropsDelegate(props.value, props.setValue)

  IconButton {
    (props.active.takeIf { value } ?: props.inactive) {}
    onClick = { value = !value }
  }
}
