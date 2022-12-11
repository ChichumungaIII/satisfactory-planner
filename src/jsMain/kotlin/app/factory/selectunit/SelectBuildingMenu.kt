package app.factory.selectunit

import app.data.Building
import app.factory.model.FactoryUnit
import app.factory.model.ProductionBuilding
import csstype.ClassName
import mui.material.Divider
import mui.material.Menu
import mui.material.MenuItem
import mui.material.PopoverOrigin
import org.w3c.dom.Element
import react.FC
import react.Props

external interface SelectBuildingMenuProps : Props {
    var parent: Element?
    var onClose: () -> Unit

    var onSelect: (FactoryUnit) -> Unit
}

val SelectBuildingMenu = FC<SelectBuildingMenuProps>("SelectBuildingMenu") { props ->
    Menu {
        className = ClassName("select-building-menu")

        open = props.parent != null
        anchorEl = props.parent?.let { anchor -> { anchor } }
        anchorOrigin = object : PopoverOrigin {
            override var vertical = "top"
            override var horizontal = "left"
        }
        transformOrigin = object : PopoverOrigin {
            override var vertical = "bottom"
            override var horizontal = "left"
        }

        onClose = { props.onClose() }

        MenuItem {
            +"Constructor"
            onClick = { props.onSelect(ProductionBuilding(Building.CONSTRUCTOR)) }
        }
        MenuItem {
            +"Assembler"
            onClick = { props.onSelect(ProductionBuilding(Building.ASSEMBLER)) }
        }
        MenuItem {
            +"Refinery"
            onClick = { props.onSelect(ProductionBuilding(Building.REFINERY)) }
        }
        MenuItem {
            +"Manufacturer"
            onClick = { props.onSelect(ProductionBuilding(Building.MANUFACTURER)) }
        }

        Divider {}

        MenuItem {
            +"Smelter"
            onClick = { props.onSelect(ProductionBuilding(Building.SMELTER)) }
        }
        MenuItem {
            +"Foundry"
            onClick = { props.onSelect(ProductionBuilding(Building.FOUNDRY)) }
        }
    }
}
