package app.factory.unit

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitArray
import app.factory.model.FactoryUnitContainer
import app.factory.model.Generator
import app.factory.model.ProductionBuilding
import app.factory.unit.array.ArrayModule
import app.factory.unit.container.ContainerModule
import app.factory.unit.generation.GeneratorModule
import app.factory.unit.production.ProductionBuildingModule
import react.ChildrenBuilder

fun ChildrenBuilder.factoryUnit(unit: FactoryUnit, setUnit: (FactoryUnit) -> Unit, deleteUnit: () -> Unit) {
  FactoryUnitComponent {
    this.unit = unit
    this.setUnit = setUnit
    this.deleteUnit = deleteUnit

    when (unit) {
      is ProductionBuilding -> ProductionBuildingModule {
        this.production = unit
        this.setUnit = setUnit
      }

      is Generator -> GeneratorModule {
        this.generator = unit
        this.setUnit = setUnit
      }

      is FactoryUnitContainer -> ContainerModule {
        this.container = unit
        this.setUnit = setUnit
      }

      is FactoryUnitArray -> ArrayModule {
        this.array = unit
        this.setUnit = setUnit
      }

    }
  }
}
