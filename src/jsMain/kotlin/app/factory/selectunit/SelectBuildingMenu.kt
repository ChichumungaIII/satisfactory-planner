package app.factory.selectunit

import app.factory.model.Constructor
import app.factory.model.FactoryUnit
import csstype.ClassName
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
            override var horizontal = "right"
        }

        onClose = { props.onClose() }

        MenuItem {
            +"Constructor"
            onClick = { props.onSelect(Constructor()) }
        }
        MenuItem {
            +"Assembler"
        }
    }
}
