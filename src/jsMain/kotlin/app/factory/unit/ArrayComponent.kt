package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitArray
import app.factory.selectunit.SelectUnitSpeedDial
import kotlinx.js.jso
import mui.material.FormControlVariant
import mui.material.TextField
import react.FC
import react.Props
import react.ReactNode
import react.dom.html.InputMode
import react.dom.onChange
import react.useState

external interface ArrayComponentProps : Props {
    var array: FactoryUnitArray
    var setUnit: (FactoryUnit) -> Unit
}

val ArrayComponent = FC<ArrayComponentProps>("ArrayComponent") { props ->
    var countText by useState("${props.array.count}")
    var errorMessage by useState<String?>(null)

    FactoryUnitComponent {
        unit = props.array
        setUnit = { props.setUnit(it) }

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
            factoryUnit(child) { props.setUnit(props.array.copy(unit = it)) }
        } ?: run {
            SelectUnitSpeedDial { onSelect = { props.setUnit(props.array.copy(unit = it)) } }
        }
    }
}
