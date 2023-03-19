package app.v2.common.layout

import app.util.PropsDelegate
import app.v2.common.input.ToggleIconButton
import csstype.ClassName
import csstype.number
import mui.icons.material.Menu
import mui.icons.material.MenuOpen
import mui.material.Box
import mui.material.Toolbar
import mui.system.sx
import react.FC
import react.Props
import react.ReactElement

external interface TitleBarComponentProps : Props {
    var drawer: Boolean
    var setDrawer: (Boolean) -> Unit

    var title: ReactElement<*>
    var controls: ReactElement<*>?
}

val TitleBarComponent = FC<TitleBarComponentProps>("TitleBarComponent") { props ->
    var drawer by PropsDelegate(props.drawer, props.setDrawer)

    Toolbar {
        ToggleIconButton {
            className = ClassName("title-bar__nav-menu-icon title-bar__icon")

            toggle = drawer
            setToggle = { next -> drawer = next }

            titleOn = "Close Menu"
            iconOn = MenuOpen

            titleOff = "Open Menu"
            iconOff = Menu
        }

        +props.title
        Box { sx { flexGrow = number(1.0) } }
        +props.controls
    }
}
