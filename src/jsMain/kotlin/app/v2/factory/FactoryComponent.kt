package app.v2.factory

import app.AppRoute
import app.util.PropsDelegate
import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.data.Factory
import app.v2.factories.DeleteFactory
import app.v2.factories.DeleteFactoryDialog
import app.v2.factories.FactoriesContext
import app.v2.factory.content.FactoryContentComponent
import csstype.ClassName
import mui.icons.material.MoreVert
import mui.material.IconButton
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import react.useContext
import react.useState

external interface FactoryComponentProps : Props {
    var factory: Factory
    var setFactory: (Factory) -> Unit
}

val FactoryComponent = FC<FactoryComponentProps>("FactoryComponent") { props ->
    val navigate = useNavigate()
    val (_, updateFactories) = useContext(FactoriesContext)

    var factory by PropsDelegate(props.factory, props.setFactory)
    var factoryToDelete by useState<Factory?>(null)

    FrameComponent {
        titleBar = {
            it.title = AppTitleComponent.create { title = factory.displayName }
            it.controls = IconButton.create {
                className = ClassName("title-bar__icon")
                MoreVert {}
                onClick = { }
            }
        }

        content = FactoryContentComponent.create {
            content = factory.tree
            setContent = { next -> factory = factory.copy(tree = next) }
        }
    }

    factoryToDelete?.also {
        DeleteFactoryDialog {
            this.factory = it
            onCancel = { factoryToDelete = null }
            onDelete = {
                updateFactories(DeleteFactory(it.id))
                navigate(AppRoute.FACTORIES.url)
            }
        }
    }
}
