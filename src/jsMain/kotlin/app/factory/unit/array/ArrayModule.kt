package app.factory.unit.array

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitArray
import app.factory.selectunit.SelectUnitSpeedDial
import app.factory.unit.factoryUnit
import js.core.jso
import mui.material.FormControlVariant
import mui.material.TextField
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import react.useState
import web.html.InputMode

external interface ArrayModuleProps : Props {
  var array: FactoryUnitArray
  var setUnit: (FactoryUnit) -> Unit
}

val ArrayModule = FC<ArrayModuleProps>("ArrayModule") { props ->
  var countText by useState("${props.array.count}")
  var errorMessage by useState<String?>(null)

  TextField {
    inputProps = jso {
      inputMode = InputMode.numeric
    }

    variant = FormControlVariant.outlined
    label = ReactNode("Count")

    errorMessage?.let {
      error = true
      helperText = ReactNode(it)
    }

    value = countText
    onChange = { event ->
      val nextText = event.target.asDynamic().value as String
      countText = nextText

      val next = nextText.toIntOrNull()
      if (next == null) {
        errorMessage = "Value must be an integer."
      } else if (next < 0) {
        errorMessage = "Count must be non-negative."
      } else {
        props.setUnit(props.array.copy(count = next))
        errorMessage = null
      }
    }
  }

  props.array.unit?.also { child ->
    factoryUnit(child,
      { props.setUnit(props.array.copy(unit = it)) },
      { props.setUnit(props.array.copy(unit = null)) })
  } ?: run {
    SelectUnitSpeedDial { onSelect = { props.setUnit(props.array.copy(unit = it)) } }
  }
}
