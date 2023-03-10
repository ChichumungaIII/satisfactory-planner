package app.v2.factories

import app.v2.data.Factory
import mui.icons.material.Delete
import mui.icons.material.Factory
import mui.material.Avatar
import mui.material.IconButton
import mui.material.ListItem
import mui.material.ListItemAvatar
import mui.material.ListItemButton
import mui.material.ListItemText
import mui.material.Typography
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useState

external interface FactoriesListComponentProps : Props {
    var factories: List<Factory>
}

val FactoriesListComponent = FC<FactoriesListComponentProps>("FactoriesListComponent") { props ->
    val navigate = useNavigate()
    val (_, updateFactories) = useContext(FactoriesContext)

    var factoryToDelete by useState<Factory?>(null)

    mui.material.List {
        props.factories.forEach { factory ->
            ListItem {
                disablePadding = true
                secondaryAction = IconButton.create {
                    Delete {}

                    onClick = { factoryToDelete = factory }
                }

                ListItemButton {
                    ListItemAvatar {
                        Avatar { Factory {} }
                    }
                    ListItemText {
                        primary = Typography.create { +factory.displayName }
                    }

                    onClick = {
                        navigate.invoke("${factory.id}")
                    }
                }
            }
        }
    }

    factoryToDelete?.also {
        DeleteFactoryDialog {
            factory = it
            onCancel = { factoryToDelete = null }
            onDelete = { updateFactories(DeleteFactory(it.id)) }
        }
    }
}
