package app.plan

import app.data.recipe.ProductionRecipe
import app.model.PlanOutcomeModel
import csstype.ClassName
import csstype.Margin
import csstype.px
import mui.material.*
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import util.math.q

external interface PlanOutcomeComponentProps : Props {
    var outcome: PlanOutcomeModel
}

val PlanOutcomeComponent = FC<PlanOutcomeComponentProps>("PlanOutcomeComponent") { props ->
    props.outcome.recipes?.let { recipes ->
        TableContainer {
            Table {
                TableHead {
                    TableRow {
                        TableCell { +"Recipe" }
                        TableCell { +"Rate" }
                        TableCell { +"Items" }
                    }
                }
                TableBody {
                    ProductionRecipe.values().map { it to (recipes[it] ?: 0.q) }.filter { (_, rate) -> rate > 0.q }
                        .forEach { (recipe, rate) ->
                            TableRow {
                                TableCell { +recipe.displayName }
                                TableCell { +"${rate * 100.q}%" }
                                TableCell {
                                    recipe.components
                                        .filter { (_, amount) -> amount > 0.q }
                                        .map { (item, amount) -> item to (amount * rate * 60.q / recipe.time) }
                                        .forEach { (item, amount) ->
                                            Chip {
                                                className = ClassName("plan-outcome__chip")
                                                color = ChipColor.success
                                                label = ReactNode("+$amount ${item.displayName}")
                                            }
                                        }
                                    recipe.components
                                        .filter { (_, amount) -> amount < 0.q }
                                        .map { (item, amount) -> item to (amount * rate * 60.q / recipe.time) }
                                        .forEach { (item, amount) ->
                                            Chip {
                                                className = ClassName("plan-outcome__chip")
                                                color = ChipColor.info
                                                label = ReactNode("$amount ${item.displayName}")
                                            }
                                        }
                                }
                            }
                        }
                }
            }
        }
    } ?: Box {
        sx { margin = Margin(12.px, 0.px) }
        +"""It's not possible to produce the required product amounts from the available inputs. 
            Please reduce the minimum requirements or provide additional supplies.
            """.trimIndent()
    }

}
