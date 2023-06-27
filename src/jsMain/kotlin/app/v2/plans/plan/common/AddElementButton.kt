package app.v2.plans.plan.common

import mui.icons.material.Add
import mui.material.Fab
import mui.material.FabColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.Tooltip
import react.FC
import react.Props
import react.ReactNode
import web.cssom.ClassName

external interface AddElementButtonProps : Props {
  var title: ReactNode
  var onClick: () -> Unit
}

val AddElementButton = FC<AddElementButtonProps>("AddElementButton") { props ->
  Tooltip {
    className = ClassName("add-element-button")
    title = props.title

    Fab {
      color = FabColor.primary
      size = Size.small
      Add { fontSize = SvgIconSize.medium }

      onClick = { props.onClick() }
    }
  }
}
