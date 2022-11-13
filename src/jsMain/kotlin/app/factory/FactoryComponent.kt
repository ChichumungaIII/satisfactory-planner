package app.factory

import app.common.layout.titlebar.TitleBar
import app.factory.model.Factory
import app.util.PropsDelegate
import react.FC
import react.Props

external interface FactoryComponentProps : Props {
    var model: Factory
    var setModel: (Factory) -> Unit
    var onDelete: () -> Unit
}

val FactoryComponent = FC<FactoryComponentProps>("FactoryComponent") { props ->
    var factory by PropsDelegate(props.model) { next -> props.setModel(next) }

    TitleBar {
        editDescription = "Edit Factory Name"
        deleteDescription = "Delete Factory"

        title = factory.title
        setTitle = { next -> factory = factory.copy(title = next) }
        onDelete = { props.onDelete() }
    }
}
