package app.v2.frame

import app.themes.Colors
import app.themes.ThemeContext
import app.v2.frame.title.TitleContext
import csstype.ClassName
import csstype.integer
import js.core.jso
import mui.icons.material.MenuOpen
import mui.icons.material.MoreVert
import mui.material.AppBar
import mui.material.Box
import mui.material.IconButton
import mui.material.Menu
import mui.material.Toolbar
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.material.styles.create
import mui.system.sx
import react.FC
import react.Props
import react.router.Outlet
import react.useContext
import react.useState
import web.dom.Element

external interface FrameComponentProps : Props

val FrameComponent = FC<FrameComponentProps>("FrameComponent") {
    val theme by useContext(ThemeContext)
    val title by useContext(TitleContext)
    val menuOptions by useContext(FrameMenuOptionsContext)

    var drawerOpen by useState(true)
    var menuElement by useState<Element?>(null)

    Box {
        className = ClassName("frame")

        AppBar {
            sx { zIndex = integer(theme.zIndex.drawer.toInt() + 1) }

            Toolbar {
                IconButton {
                    // TODO: Use themed colors instead of hard-coding white.
                    if (drawerOpen) {
                        MenuOpen { sx { color = Colors.white } }
                        onClick = { drawerOpen = false }
                    } else {
                        mui.icons.material.Menu { sx { color = Colors.white } }
                        onClick = { drawerOpen = true }
                    }
                }

                Typography {
                    className = ClassName("frame__title")
                    variant = TypographyVariant.h1
                    +title
                }

                menuOptions.takeUnless { it.isEmpty() }?.also { options ->
                    IconButton {
                        MoreVert { sx { color = Colors.white } }
                        onClick = { event -> menuElement = event.currentTarget }
                    }

                    Menu {
                        open = menuElement != null
                        menuElement?.also { anchorEl = { it } }
                        anchorOrigin = jso {
                            vertical = "top"
                            horizontal = "right"
                        }
                        transformOrigin = jso {
                            vertical = "top"
                            horizontal = "right"
                        }

                        onClose = { menuElement = null }

                        options.forEach { +it }
                    }
                }
            }
        }

        NavigationDrawerComponent {
            this.drawerOpen = drawerOpen
        }

        Box {
            className = ClassName(buildString {
                append("frame__content")
                if (drawerOpen) append(" frame__content--drawer-open")
            })
            sx {
                if (drawerOpen) {
                    transition = theme.transitions.create("margin") {
                        this.easing = theme.transitions.easing.easeOut
                        this.duration = theme.transitions.duration.enteringScreen
                    }
                } else {
                    transition = theme.transitions.create("margin") {
                        this.easing = theme.transitions.easing.sharp
                        this.duration = theme.transitions.duration.leavingScreen
                    }
                }
            }

            Toolbar {} // Adds space equivalent to the header
            Outlet {}
        }
    }
}
