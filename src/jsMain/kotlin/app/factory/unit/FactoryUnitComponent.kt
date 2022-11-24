package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.unit.info.FactoryUnitInfoComponent
import app.util.PropsDelegate
import csstype.ClassName
import mui.icons.material.MoreVert
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.IconButton
import mui.material.Typography
import mui.material.styles.TypographyVariant
import org.w3c.dom.Element
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

    var menuAnchor by useState<Element?>(null)
    var showDetails by useState(false)

    Accordion {
        className = ClassName("factory-unit")
        disableGutters = true

        expanded = open

        AccordionSummary {
            onClick = { open = !open }
            expandIcon = IconButton.create {
                MoreVert { }
                onClick = { event ->
                    menuAnchor = event.currentTarget
                    event.stopPropagation()
                }
            }

            Typography {
                className = ClassName("factory-unit__title")
                variant = TypographyVariant.caption

                +unit.title
            }
        }

        AccordionDetails {
            if (showDetails) {
                FactoryUnitInfoComponent { this.unit = unit }
            }

            +props.children
        }
    }

    FactoryUnitComponentMenu {
        this.unit = unit

        parent = menuAnchor
        onClose = { menuAnchor = null }

        expanded = open
        setExpanded = { next ->
            open = next
            menuAnchor = null
        }

        details = showDetails
        setDetails = { next ->
            showDetails = next
            menuAnchor = null
        }

        onDelete = {
            props.deleteUnit()
            menuAnchor = null
        }
    }
}
