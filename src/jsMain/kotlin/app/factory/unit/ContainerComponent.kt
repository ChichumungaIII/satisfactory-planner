package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitContainer
import app.util.PropsDelegate
import react.FC
import react.Props

external interface ContainerComponentProps : Props {
    var container: FactoryUnitContainer
    var setContainer: (FactoryUnitContainer) -> Unit
}

val ContainerComponent = FC<ContainerComponentProps>("ContainerComponent") { props ->
    var container: FactoryUnitContainer by PropsDelegate(props.container) { props.setContainer(it) }

    container.units.forEachIndexed { i: Int, unit: FactoryUnit ->
        factoryUnit(unit) { container = container.setUnit(i, it) }
    }
}
