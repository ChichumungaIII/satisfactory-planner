package app.factory.unit

import app.factory.info.FactoryUnitInfoComponent
import app.factory.model.FactoryUnit
import app.util.PropsDelegate
import csstype.ClassName
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.PropsWithChildren
import react.create
import react.useState

external interface FactoryUnitComponentProps : PropsWithChildren {
    var unit: FactoryUnit
    var setUnit: (FactoryUnit) -> Unit
    var deleteUnit: () -> Unit
}

val FactoryUnitComponent = FC<FactoryUnitComponentProps>("FactoryUnitComponent") { props ->
    var unit by PropsDelegate(props.unit) { props.setUnit(it) }
    var open by useState(true)

    Accordion {
        className = ClassName("factory-unit")
        disableGutters = true

        expanded = open
        onChange = { _, next -> open = next }

        AccordionSummary {
            expandIcon = ExpandMore.create {}

            Typography {
                className = ClassName("factory-unit__title")
                variant = TypographyVariant.caption

                +unit.title
            }
        }

        AccordionDetails {
            FactoryUnitInfoComponent { this.unit = unit }

            +props.children
        }
    }
}
