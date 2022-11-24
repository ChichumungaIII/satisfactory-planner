package app.factory.unit.container

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitContainer
import app.factory.selectunit.SelectUnitSpeedDial
import app.factory.unit.factoryUnit
import react.FC
import react.Props

external interface ContainerModuleProps : Props {
    var container: FactoryUnitContainer
    var setUnit: (FactoryUnit) -> Unit
}

val ContainerModule = FC<ContainerModuleProps>("ContainerModule") { props ->
    props.container.units.forEachIndexed { i: Int, unit: FactoryUnit ->
        factoryUnit(unit,
            { props.setUnit(props.container.setUnit(i, it)) },
            { props.setUnit(props.container.removeUnit(i)) })
    }

    SelectUnitSpeedDial { onSelect = { props.setUnit(props.container.addUnit(it)) } }
}
