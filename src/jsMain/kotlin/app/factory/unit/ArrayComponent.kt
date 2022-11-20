package app.factory.unit

import app.factory.info.FactoryUnitInfoComponent
import app.factory.model.FactoryUnitArray
import app.factory.selectunit.SelectUnitSpeedDial
import app.util.PropsDelegate
import csstype.ClassName
import kotlinx.js.jso
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.FormControlVariant
import mui.material.TextField
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.dom.html.InputMode
import react.dom.onChange
import react.useState

external interface ArrayComponentProps : Props {
    var array: FactoryUnitArray
    var setArray: (FactoryUnitArray) -> Unit
}

val ArrayComponent = FC<ArrayComponentProps>("ArrayComponent") { props ->
    var array by PropsDelegate(props.array) { props.setArray(it) }
    var open by useState(true)

    var countText by useState("${array.count}")
    var errorMessage by useState<String?>(null)

    Accordion {
        className = ClassName("factory-unit factory-accordion")
        disableGutters = true

        expanded = open
        onChange = { _, next -> open = next }

        AccordionSummary {
            expandIcon = ExpandMore.create {}

            Typography {
                className = ClassName("factory-unit__title")
                variant = TypographyVariant.body1

                +array.title
            }
        }

        AccordionDetails {
            FactoryUnitInfoComponent { unit = array }

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
                        array = array.copy(count = next)
                        errorMessage = null
                    }
                }
            }

            array.unit?.also { child ->
                factoryUnit(child) { array = array.copy(unit = it) }
            } ?: run {
                SelectUnitSpeedDial { onSelect = { array = array.copy(unit = it) } }
            }
        }
    }
}
