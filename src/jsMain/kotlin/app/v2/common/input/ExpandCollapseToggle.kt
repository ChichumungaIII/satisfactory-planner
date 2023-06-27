package app.v2.common.input

import app.util.PropsDelegate
import mui.icons.material.KeyboardDoubleArrowDown
import mui.icons.material.KeyboardDoubleArrowUp
import react.FC
import react.Props
import web.cssom.ClassName

external interface ExpandCollapseToggleProps : Props {
  var expanded: Boolean
  var setExpanded: (Boolean) -> Unit
}

val ExpandCollapseToggle = FC<ExpandCollapseToggleProps>("ExpandCollapseToggle") { props ->
  var expanded by PropsDelegate(props.expanded, props.setExpanded)

  ToggleIconButton {
    className = ClassName("expand-collapse-toggle")

    toggle = expanded
    setToggle = { next -> expanded = next }

    titleOn = "Collapse"
    iconOn = KeyboardDoubleArrowUp

    titleOff = "Expand"
    iconOff = KeyboardDoubleArrowDown
  }
}
