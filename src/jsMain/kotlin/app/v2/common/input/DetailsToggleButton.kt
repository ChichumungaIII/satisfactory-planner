package app.v2.common.input

import app.util.PropsDelegate
import mui.icons.material.Info
import mui.icons.material.InfoOutlined
import react.FC
import react.Props

external interface DetailsToggleButtonProps : Props {
  var details: Boolean
  var setDetails: (Boolean) -> Unit
}

val DetailsToggleButton = FC<DetailsToggleButtonProps>("DetailsToggleButton") { props ->
  var details by PropsDelegate(props.details, props.setDetails)

  ToggleIconButton {
    toggle = details
    setToggle = { next -> details = next }

    titleOn = "Hide Details"
    iconOn = Info

    titleOff = "Show Details"
    iconOff = InfoOutlined
  }
}
