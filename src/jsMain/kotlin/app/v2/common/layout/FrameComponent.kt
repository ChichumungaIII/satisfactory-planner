package app.v2.common.layout

import app.themes.ThemeContext
import csstype.ClassName
import csstype.integer
import mui.material.AppBar
import mui.material.AppBarPosition
import mui.material.Box
import mui.material.styles.create
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext

external interface FrameComponentProps : Props {
    var titleBar: ReactNode
    var content: ReactNode?
}

val FrameComponent = FC<FrameComponentProps>("FrameComponent") { props ->
    val theme by useContext(ThemeContext)
    var drawer by useContext(DrawerContext)

    AppBar {
        sx { zIndex = integer(theme.zIndex.drawer.toInt() + 1) }
        position = AppBarPosition.sticky
        +props.titleBar
    }

    NavigationDrawerComponent {
        className = ClassName("frame__drawer")
        drawerOpen = drawer
    }

    Box {
        className = buildString {
            append("frame__content")
            if (drawer) append(" frame__content--drawer-open")
        }.let { ClassName(it) }
        sx {
            transition = theme.transitions.create("margin") {
                if (drawer) {
                    easing = theme.transitions.easing.easeOut
                    duration = theme.transitions.duration.enteringScreen
                } else {
                    easing = theme.transitions.easing.sharp
                    duration = theme.transitions.duration.leavingScreen
                }
            }
        }

        +props.content
    }
}
