package app.factory.unit

import app.util.PropsDelegate
import mui.material.Menu
import mui.material.MenuItem
import mui.material.PopoverOrigin
import org.w3c.dom.Element
import react.FC
import react.Props

external interface FactoryUnitComponentMenuProps : Props {
    var parent: Element?
    var onClose: () -> Unit

    var expanded: Boolean
    var setExpanded: (Boolean) -> Unit

    var details: Boolean
    var setDetails: (Boolean) -> Unit
}

val FactoryUnitComponentMenu = FC<FactoryUnitComponentMenuProps>("FactoryUnitComponentMenu") { props ->
    var expanded by PropsDelegate(props.expanded) { props.setExpanded(it) }
    var details by PropsDelegate(props.details) { props.setDetails(it) }

    Menu {
        open = props.parent != null
        anchorEl = props.parent?.let { anchor -> { anchor } }
        anchorOrigin = object : PopoverOrigin {
            override var vertical = "top"
            override var horizontal = "left"
        }
        transformOrigin = object : PopoverOrigin {
            override var vertical = "top"
            override var horizontal = "right"
        }

        onClose = { props.onClose() }

        MenuItem {
            if (expanded) {
                +"Collapse"
                onClick = { expanded = false }
            } else {
                +"Expand"
                onClick = { expanded = true }
            }
        }

        MenuItem {
            if (details) {
                +"Hide details"
                onClick = { details = false }
            } else {
                +"Show details"
                onClick = { details = true }
            }
        }
    }
}
