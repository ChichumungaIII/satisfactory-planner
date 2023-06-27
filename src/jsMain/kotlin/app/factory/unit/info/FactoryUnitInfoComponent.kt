package app.factory.unit.info

import app.factory.model.FactoryUnit
import app.util.math.toFixed
import mui.icons.material.Bolt
import mui.icons.material.OfflineBolt
import mui.icons.material.OfflineBoltOutlined
import mui.material.Grid
import mui.material.GridProps
import mui.material.Paper
import mui.material.Table
import mui.material.TableBody
import mui.material.TableCell
import mui.material.TableContainer
import mui.material.TableHead
import mui.material.TableRow
import mui.system.ResponsiveStyleValue
import mui.system.responsive
import react.ChildrenBuilder
import react.FC
import react.Props
import react.dom.html.TdAlign
import util.math.q
import web.cssom.ClassName

external interface FactoryUnitInfoComponentProps : Props {
  var unit: FactoryUnit
}

val FactoryUnitInfoComponent = FC<FactoryUnitInfoComponentProps>("FactoryUnitInfoComponent") { props ->
  Grid {
    className = ClassName("factory-info")
    container = true
    columns = responsive(12)

    /* Power */

    Grid {
      className = ClassName("factory-info__tile factory-info__tile--power")
      item = true
      xs(responsive(6))

      Paper {
        className = ClassName("factory-info__content")

        val power = props.unit.power
        Bolt { }
        +"${power.toFixed(4)} MW"
      }
    }
    Grid {
      className = ClassName("factory-info__tile factory-info__tile--generation")
      item = true
      xs(responsive(3))

      Paper {
        className = ClassName("factory-info__content")

        OfflineBolt {}
        +"Generates ${props.unit.generation.toFixed(0)} MW"
      }
    }
    Grid {
      className = ClassName("factory-info__tile factory-info__tile--consumption")
      item = true
      xs(responsive(3))

      Paper {
        className = ClassName("factory-info__content")

        OfflineBoltOutlined {}
        +"Requires ${props.unit.consumption.toFixed(0)} MW"
      }
    }

    /* Production */

    Grid {
      className = ClassName("factory-info__tile factory-info__tile--production")
      item = true
      xs(responsive(6))

      Paper {
        className = ClassName("factory-info__content")

        infoTable("Production") {
          val outputs = props.unit.outcome.filter { (_, amount) -> amount > 0.q }
          if (outputs.isNotEmpty()) {
            outputs.forEach { (item, amount) ->
              TableRow {
                TableCell { +item.displayName }
                TableCell { +"$amount" }
              }
            }
          } else {
            TableRow {
              TableCell {
                colSpan = 2
                +"Nothing Produced"
              }
            }
          }
        }
      }
    }
    Grid {
      className = ClassName("factory-info__tile factory-info__tile--production")
      item = true
      xs(responsive(6))

      Paper {
        className = ClassName("factory-info__content")

        infoTable("Inputs") {
          val inputs = props.unit.outcome.filter { (_, amount) -> amount < 0.q }
          if (inputs.isNotEmpty()) {
            inputs.forEach { (item, amount) ->
              TableRow {
                TableCell { +item.displayName }
                TableCell { +"$amount" }
              }
            }
          } else {
            TableRow {
              TableCell {
                colSpan = 2
                +"Nothing consumed"
              }
            }
          }
        }
      }
    }
  }
}

private fun GridProps.xs(value: ResponsiveStyleValue<Any>) {
  asDynamic().xs = value
}

private fun ChildrenBuilder.infoTable(header: String, content: ChildrenBuilder.() -> Unit) {
  TableContainer {
    Table {
      TableHead {
        TableRow {
          TableCell {
            colSpan = 2
            align = TdAlign.center
            +header
          }
        }
      }
      TableBody {
        content()
      }
    }
  }
}
