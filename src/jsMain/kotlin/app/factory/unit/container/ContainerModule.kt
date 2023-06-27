package app.factory.unit.container

import app.factory.model.FactoryUnit
import app.factory.model.FactoryUnitContainer
import app.factory.selectunit.SelectUnitSpeedDial
import app.factory.unit.factoryUnit
import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.material.Box
import mui.material.IconButton
import mui.material.Size
import react.FC
import react.Props
import web.cssom.ClassName

external interface ContainerModuleProps : Props {
  var container: FactoryUnitContainer
  var setUnit: (FactoryUnit) -> Unit
}

val ContainerModule = FC<ContainerModuleProps>("ContainerModule") { props ->
  props.container.units.forEachIndexed { i, unit ->
    Box {
      className = ClassName("factory-unit-container")

      Box {
        className = ClassName("factory-unit-container__child-control")

        IconButton {
          className = ClassName("factory-unit-container__child-control__button")

          size = Size.small
          ArrowDropUp {}

          disabled = (i == 0)
          onClick = { props.setUnit(props.container.swapUnits(i - 1, i)) }
        }

        IconButton {
          className = ClassName("factory-unit-container__child-control__button")

          size = Size.small
          ArrowDropDown {}

          disabled = (i == props.container.units.size - 1)
          onClick = { props.setUnit(props.container.swapUnits(i, i + 1)) }
        }
      }

      Box {
        className = ClassName("factory-unit-container__child")

        factoryUnit(unit,
          { props.setUnit(props.container.setUnit(i, it)) },
          { props.setUnit(props.container.removeUnit(i)) })
      }
    }
  }

  SelectUnitSpeedDial { onSelect = { props.setUnit(props.container.addUnit(it)) } }
}
