package app.factory.selectunit

import app.factory.model.FactoryUnit
import app.factory.model.Generator
import csstype.ClassName
import mui.material.Menu
import mui.material.MenuItem
import mui.material.PopoverOrigin
import org.w3c.dom.Element
import react.FC
import react.Props

external interface SelectGeneratorMenuProps : Props {
    var parent: Element?
    var onClose: () -> Unit

    var onSelect: (FactoryUnit) -> Unit
}

val SelectGeneratorMenu = FC<SelectGeneratorMenuProps>("SelectGeneratorMenu") { props ->
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

        for (building in app.data.building.Generator.values()) {
            MenuItem {
                +building.displayName
                onClick = { props.onSelect(Generator(building)) }
            }
        }
    }
}
