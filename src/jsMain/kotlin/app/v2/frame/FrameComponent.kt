package app.v2.frame

import app.AppRoutes
import app.themes.Colors
import app.themes.ThemeContext
import csstype.ClassName
import csstype.integer
import kotlinx.js.jso
import mui.icons.material.AccountTree
import mui.icons.material.Factory
import mui.icons.material.Menu
import mui.icons.material.MenuOpen
import mui.material.AppBar
import mui.material.Box
import mui.material.Divider
import mui.material.Drawer
import mui.material.DrawerVariant
import mui.material.IconButton
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.ListSubheader
import mui.material.Toolbar
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.material.styles.create
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.router.Outlet
import react.router.useNavigate
import react.useContext
import react.useState

external interface FrameComponentProps : Props

val FrameComponent = FC<FrameComponentProps>("FrameComponent") { props ->
    val navigate = useNavigate()
    val theme by useContext(ThemeContext)

    var drawerOpen by useState(false)

    Box {
        className = ClassName("frame")

        AppBar {
            sx { zIndex = integer(theme.zIndex.drawer.toInt() + 1) }

            Toolbar {
                IconButton {
                    if (drawerOpen) {
                        MenuOpen { sx { color = Colors.white } } // TODO: Use a theme color.
                        onClick = { drawerOpen = false }
                    } else {
                        Menu { sx { color = Colors.white } } // TODO: Use a theme color.
                        onClick = { drawerOpen = true }
                    }
                }

                Typography {
                    variant = TypographyVariant.h1
                    +"Satisfactory Planner" // TODO: Replace with a route-based title.
                }
            }
        }

        Drawer {
            variant = DrawerVariant.persistent
            open = drawerOpen
            PaperProps = jso {
                className = ClassName("frame__drawer")
            }

            Toolbar {} // Adds space equivalent to the header
            mui.material.List {
                subheader = ListSubheader.create { +"Factories" }

                ListItem {
                    ListItemButton {
                        ListItemIcon { Factory {} }
                        ListItemText { +"All Factories" }

                        onClick = { navigate(AppRoutes.FACTORIES.segment) }
                    }
                }
            }
            Divider {}
            mui.material.List {
                subheader = ListSubheader.create { +"Production Plans" }

                ListItem {
                    ListItemButton {
                        ListItemIcon { AccountTree {} }
                        ListItemText { +"All Plans" }

                        onClick = { navigate(AppRoutes.PLANS.segment) }
                    }
                }
            }
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
