package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitContainer
import app.factory.selectunit.SelectUnitSpeedDial
import react.FC
import react.Props

external interface ContainerComponentProps : Props {
    var container: FactoryUnitContainer
    var setUnit: (FactoryUnit) -> Unit
}

val ContainerComponent = FC<ContainerComponentProps>("ContainerComponent") { props ->
    FactoryUnitComponent {
        unit = props.container
        setUnit = { props.setUnit(it) }

        props.container.units.forEachIndexed { i: Int, unit: FactoryUnit ->
            factoryUnit(unit) { props.setUnit(props.container.setUnit(i, it)) }
        }

        SelectUnitSpeedDial { onSelect = { props.setUnit(props.container.addUnit(it)) } }
    }
}
