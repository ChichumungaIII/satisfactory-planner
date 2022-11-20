package app.factory.info

import app.factory.model.FactoryUnit
import app.util.math.toFixed
import mui.material.Table
import mui.material.TableBody
import mui.material.TableCell
import mui.material.TableContainer
import mui.material.TableRow
import react.FC
import react.Props
import util.math.q

external interface FactoryUnitInfoComponentProps : Props {
    var unit: FactoryUnit
}

val FactoryUnitInfoComponent = FC<FactoryUnitInfoComponentProps>("FactoryUnitInfoComponent") { props ->
    TableContainer {
        Table {
            TableBody {
                TableRow {
                    TableCell { +"Power" }
                    TableCell { +(props.unit.power.asDynamic().toFixed(4) as String) }
                }
                props.unit.outcome
                    .filter { (_, amount) -> amount != 0.q }
                    .forEach { (item, amount) ->
                        TableRow {
                            TableCell { +item.displayName }
                            TableCell { +amount.toFixed(4) }
                        }
                    }
            }
        }
    }
}
