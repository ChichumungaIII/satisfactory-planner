package app.plan

import app.data.u6.U6Recipe
import app.model.PlanOutcomeModel
import csstype.Margin
import csstype.px
import mui.material.*
import mui.system.sx
import react.FC
import react.Props
import util.math.q

external interface PlanOutcomeComponentProps : Props {
    var outcome: PlanOutcomeModel
}

val PlanOutcomeComponent = FC<PlanOutcomeComponentProps>("PlanOutcomeComponent") { props ->
    val recipes = props.outcome.recipes
    if (recipes == null) {
        Box {
            sx { margin = Margin(12.px, 0.px) }
            +"""It's not possible to produce the required product amounts from the available inputs. 
                Please reduce the minimum requirements or provide additional supplies.
                """.trimIndent()
        }
    } else {
        TableContainer {
            Table {
                TableHead {
                    TableRow {
                        TableCell { +"Recipe" }
                        TableCell { +"Rate" }
                        TableCell { +" Exact" }
                    }
                }
                TableBody {
                    U6Recipe.values().map { it to (recipes[it] ?: 0.q) }.filter { (_, rate) -> rate > 0.q }
                        .forEach { (recipe, rate) ->
                            TableRow {
                                TableCell { +recipe.name }
                                TableCell {
                                    val percent = (rate * 100.q).toDouble()
                                    +"${percent.asDynamic().toFixed(4)}%"
                                }
                                TableCell { +"$rate" }
                            }
                        }
                }
            }
        }
    }
}
