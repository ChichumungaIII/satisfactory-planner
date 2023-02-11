package app.v2.frame

import app.AppRoutes
import app.v2.AppScope
import app.v2.data.FactoryServiceContext
import app.v2.data.FactoryStoreContext
import app.v2.data.SetFactory
import app.v2.factories.AddFactory
import app.v2.factories.FactoriesContext
import csstype.ClassName
import js.core.jso
import kotlinx.coroutines.launch
import mui.icons.material.AccountTree
import mui.icons.material.Add
import mui.icons.material.Factory
import mui.material.Backdrop
import mui.material.CircularProgress
import mui.material.Divider
import mui.material.Drawer
import mui.material.DrawerVariant
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.ListSubheader
import mui.material.Toolbar
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useState
import kotlin.random.Random
import kotlin.random.nextULong

external interface NavigationDrawerComponentProps : Props {
    var drawerOpen: Boolean
}

val NavigationDrawerComponent = FC<NavigationDrawerComponentProps>("NavigationDrawerComponent") { props ->
    val navigate = useNavigate()
    val factoryService = useContext(FactoryServiceContext)
    val (_, updateStore) = useContext(FactoryStoreContext)
    val (_, updateFactories) = useContext(FactoriesContext)

    // TODO: Extract "creating" to a common context.
    var creating by useState(false)

    Drawer {
        variant = DrawerVariant.persistent
        open = props.drawerOpen
        PaperProps = jso { className = ClassName("frame__drawer") }

        Toolbar {} // Adds space equivalent to the header
        mui.material.List {
            subheader = ListSubheader.create { +"Factories" }

            ListItem {
                ListItemButton {
                    ListItemIcon { Factory {} }
                    ListItemText { +"All Factories" }

                    onClick = { navigate.invoke(AppRoutes.FACTORIES.segment) }
                }
            }

            mui.material.List {
                disablePadding = true

                ListItem {
                    className = ClassName("frame__drawer__create-new")

                    ListItemButton {
                        ListItemIcon { Add {} }
                        ListItemText { +"Create new" }

                        onClick = {
                            creating = true
                            AppScope.launch {
                                val factory = app.v2.data.Factory(Random.nextULong(), "New Factory")
                                updateStore(SetFactory(factoryService.createFactory(factory)))
                                updateFactories(AddFactory(factory))
                                creating = false
                            }
                        }
                    }
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

                    onClick = { navigate.invoke(AppRoutes.PLANS.segment) }
                }
            }
        }

        Backdrop {
            open = creating
            CircularProgress { size = 80; thickness = 4.8 }
        }
    }
}
