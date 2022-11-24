package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitArray
import app.factory.model.FactoryUnitContainer
import app.factory.model.ProductionBuilding
import react.ChildrenBuilder

fun ChildrenBuilder.factoryUnit(unit: FactoryUnit, setUnit: (FactoryUnit) -> Unit) {
    when (unit) {
        is ProductionBuilding -> ProductionBuildingComponent {
            production = unit
            this.setUnit = { setUnit(it) }
        }

        is FactoryUnitContainer -> ContainerComponent {
            container = unit
            this.setUnit = { setUnit(it) }
        }

        is FactoryUnitArray -> ArrayComponent {
            array = unit
            this.setUnit = { setUnit(it) }
        }
    }
}
