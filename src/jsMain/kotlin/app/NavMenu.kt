package app

import mui.material.Drawer
import mui.material.DrawerVariant
import mui.material.ListItemText
import mui.material.MenuItem
import mui.material.MenuList
import react.FC
import react.Props
import react.router.useNavigate

external interface NavMenuProps : Props {
    var isOpen: Boolean
    var close: () -> Unit
}

val NavMenu = FC<NavMenuProps>("NavMenu") { props ->
    val navigate = useNavigate()

    Drawer {
        open = props.isOpen
        variant = DrawerVariant.persistent

        HeaderSpacer {}
        MenuList {
            listOf(
                Target("Production Plans", AppRoute.V1_PRODUCTION.url),
                Target("Factories", AppRoute.V1_FACTORIES.url),
            ).forEach { target ->
                MenuItem {
                    onClick = {
                        navigate.invoke(target.destination)
                        props.close()
                    }
                    ListItemText { +target.title }
                }
            }
        }
    }
}

private data class Target(
    val title: String,
    val destination: String,
)
