package app.factory

import app.common.layout.titlebar.TitleBar
import app.factory.model.Factory
import app.factory.selectunit.SelectUnitSpeedDial
import app.factory.unit.factoryUnit
import app.util.PropsDelegate
import csstype.ClassName
import mui.material.Box
import react.FC
import react.Props

external interface FactoryComponentProps : Props {
    var model: Factory
    var setModel: (Factory) -> Unit
    var onDelete: () -> Unit
}

val FactoryComponent = FC<FactoryComponentProps>("FactoryComponent") { props ->
    var factory by PropsDelegate(props.model) { next -> props.setModel(next) }
    var container by PropsDelegate(factory.container) { factory = factory.copy(container = it) }

    TitleBar {
        editDescription = "Edit Factory Name"
        deleteDescription = "Delete Factory"

        title = factory.title
        setTitle = { next -> factory = factory.copy(title = next) }
        onDelete = { props.onDelete() }
    }

    Box {
        className = ClassName("factory")

        container.units.forEachIndexed { i, unit ->
            Box {
                className = ClassName("factory__aspect")

                factoryUnit(unit) { container = container.setUnit(i, unit) }
            }
        }

        SelectUnitSpeedDial { onSelect = { container = container.addUnit(it) } }
    }
}
