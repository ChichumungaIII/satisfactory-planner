package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitArray
import app.factory.model.FactoryUnitContainer
import app.factory.unit.info.FactoryUnitInfoComponent
import app.util.PropsDelegate
import csstype.ClassName
import mui.icons.material.MoreVert
import mui.material.*
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

    var menuAnchor by useState<Element?>(null)

    Accordion {
        className = ClassName("factory-unit")
        disableGutters = true

        expanded = unit.open

        AccordionSummary {
            onClick = { unit = unit.clone(open = !unit.open) }
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
            if (unit.details) {
                FactoryUnitInfoComponent { this.unit = unit }
            }

            +props.children
        }
    }

    FactoryUnitComponentMenu {
        this.unit = unit

        parent = menuAnchor
        onClose = { menuAnchor = null }

        expanded = unit.open
        setExpanded = { next ->
            unit = unit.clone(open = next)
            menuAnchor = null
        }

        details = unit.details
        setDetails = { next ->
            unit = unit.clone(details = next)
            menuAnchor = null
        }

        val factoryUnit = unit
        if (factoryUnit is FactoryUnitContainer) {
            setTitle = {
                unit = factoryUnit.copy(title = it)
                menuAnchor = null
            }
        } else if (factoryUnit is FactoryUnitArray) {
            setTitle = {
                unit = factoryUnit.copy(title = it)
                menuAnchor = null
            }
        }

        onWrapInContainer = {
            unit = FactoryUnitContainer(units = listOf(unit))
            menuAnchor = null
        }
        onWrapInArray = {
            unit = FactoryUnitArray(unit = unit)
            menuAnchor = null
        }

        onDelete = {
            props.deleteUnit()
            menuAnchor = null
        }
    }
}
