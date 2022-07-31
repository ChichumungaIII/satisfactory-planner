package app

import app.themes.ThemeModule
import csstype.Display
import csstype.FlexDirection
import csstype.Padding
import csstype.integer
import csstype.px
import csstype.rgb
import mui.icons.material.Menu
import mui.icons.material.MenuOpen
import mui.material.AppBar
import mui.material.Box
import mui.material.Drawer
import mui.material.IconButton
import mui.material.ListItemText
import mui.material.MenuItem
import mui.material.MenuList
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.router.Outlet
import react.router.useNavigate
import react.useState

val TOOLBAR_HEIGHT = 58.px

val App = FC<Props> {
    val navigate = useNavigate()

    var isOpen by useState(false)

    ThemeModule {
        AppBar {
            sx {
                zIndex = integer(1300)

                display = Display.flex
                flexDirection = FlexDirection.row

                padding = Padding(8.px, 16.px)
            }

            IconButton {
                if (isOpen) {
                    MenuOpen { sx { color = rgb(255, 255, 255) } }
                } else {
                    Menu { sx { color = rgb(255, 255, 255) } }
                }
                onClick = { isOpen = !isOpen }
            }

            Typography {
                variant = TypographyVariant.h1
                +"Satisfactory Planner"
            }
        }

        Drawer {
            open = isOpen

            Box {
                sx {
                    marginTop = TOOLBAR_HEIGHT
                }
                MenuList {
                    MenuItem {
                        onClick = {
                            navigate("/production")
                            isOpen = false
                        }

                        ListItemText {
                            +"Production Plans"
                        }
                    }

                    MenuItem {
                        onClick = {
                            navigate("/factories")
                            isOpen = false
                        }

                        ListItemText {
                            +"Factories"
                        }
                    }
                }
            }
        }

        Box {
            sx {
                marginTop = TOOLBAR_HEIGHT
            }

            Outlet {}
        }
    }
}
