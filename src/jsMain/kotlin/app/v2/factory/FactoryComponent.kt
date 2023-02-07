package app.v2.factory

import app.v2.data.Factory
import app.v2.frame.title.TitleContext
import mui.material.Typography
import react.FC
import react.Props
import react.useContext
import react.useEffectOnce

external interface FactoryComponentProps : Props {
    var factory: Factory
}

val FactoryComponent = FC<FactoryComponentProps>("FactoryComponent") { props ->
    val (_, setTitle) = useContext(TitleContext)
    useEffectOnce { setTitle(props.factory.displayName) }

    Typography {
        +props.factory.displayName
    }
}
