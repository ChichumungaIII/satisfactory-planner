package app.v2.factory

import app.util.PropsDelegate
import app.v2.data.Factory
import app.v2.factories.DeleteFactory
import app.v2.factories.DeleteFactoryDialog
import app.v2.factories.FactoriesContext
import app.v2.factory.content.FactoryContentComponent
import app.v2.frame.FrameMenuOptionsContext
import app.v2.frame.title.TitleContext
import mui.icons.material.Delete
import mui.icons.material.Edit
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.MenuItem
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useEffect
import react.useEffectOnce
import react.useState

external interface FactoryComponentProps : Props {
    var factory: Factory
    var setFactory: (Factory) -> Unit
}

val FactoryComponent = FC<FactoryComponentProps>("FactoryComponent") { props ->
    val navigate = useNavigate()
    var title by useContext(TitleContext)
    useEffect(props.factory.displayName) { title = props.factory.displayName }

    val (_, updateFactories) = useContext(FactoriesContext)

    var factory by PropsDelegate(props.factory, props.setFactory)
    var factoryToDelete by useState<Factory?>(null)

    var frameMenuOptions by useContext(FrameMenuOptionsContext)
    useEffectOnce {
        frameMenuOptions = listOf(
            MenuItem.create {
                ListItemIcon { Edit {} }
                ListItemText { +"Rename Factory" }
            },
            MenuItem.create {
                ListItemIcon { Delete {} }
                ListItemText { +"Delete Factory" }
                onClick = { factoryToDelete = props.factory }
            })
    }

    FactoryContentComponent {
        content = factory.tree
        setContent = { next -> factory = factory.copy(tree = next) }
    }

    factoryToDelete?.also {
        DeleteFactoryDialog {
            this.factory = it
            onCancel = { factoryToDelete = null }
            onDelete = {
                updateFactories(DeleteFactory(it.id))
                navigate(to = "..")
            }
        }
    }
}
