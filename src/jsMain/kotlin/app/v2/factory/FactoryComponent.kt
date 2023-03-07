package app.v2.factory

import app.util.PropsDelegate
import app.v2.data.Factory
import app.v2.factory.content.FactoryContentComponent
import app.v2.frame.title.TitleContext
import react.FC
import react.Props
import react.useContext
import react.useEffectOnce

external interface FactoryComponentProps : Props {
    var factory: Factory
    var setFactory: (Factory) -> Unit
}

val FactoryComponent = FC<FactoryComponentProps>("FactoryComponent") { props ->
    val (_, setTitle) = useContext(TitleContext)
    useEffectOnce { setTitle(props.factory.displayName) }

    var factory by PropsDelegate(props.factory, props.setFactory)

    FactoryContentComponent {
        content = factory.tree
        setContent = { next -> factory = factory.copy(tree = next) }

        depth = 1
    }
}
