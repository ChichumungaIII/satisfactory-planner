package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitContainer
import app.factory.selectunit.SelectUnitSpeedDial
import app.util.PropsDelegate
import csstype.ClassName
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.useState

external interface ContainerComponentProps : Props {
    var container: FactoryUnitContainer
    var setContainer: (FactoryUnitContainer) -> Unit
}

val ContainerComponent = FC<ContainerComponentProps>("ContainerComponent") { props ->
    var container: FactoryUnitContainer by PropsDelegate(props.container) { props.setContainer(it) }
    var open by useState(true)

    Accordion {
        className = ClassName("factory-unit factory-container")
        disableGutters = true

        expanded = open
        onChange = { _, next -> open = next }

        AccordionSummary {
            expandIcon = ExpandMore.create {}

            Typography {
                className = ClassName("factory-unit__title")
                variant = TypographyVariant.body1

                +container.title
            }
        }

        AccordionDetails {
            container.units.forEachIndexed { i: Int, unit: FactoryUnit ->
                factoryUnit(unit) { container = container.setUnit(i, it) }
            }

            SelectUnitSpeedDial { onSelect = { container = container.addUnit(it) } }
        }
    }
}
