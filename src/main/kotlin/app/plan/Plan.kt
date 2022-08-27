package app.plan

import app.model.PlanInputModel
import app.model.PlanModel
import app.model.PlanProductModel
import app.util.PropsDelegate
import csstype.FlexDirection
import csstype.Margin
import csstype.Padding
import csstype.px
import mui.icons.material.Add
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Box
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Fab
import mui.material.FabColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.Table
import mui.material.TableBody
import mui.material.TableCell
import mui.material.TableContainer
import mui.material.TableHead
import mui.material.TableRow
import mui.material.Typography
import mui.system.sx
import react.FC
import react.Props
import react.create
import util.math.q

external interface PlanProps : Props {
    var plan: PlanModel
    var setPlan: (PlanModel) -> Unit
    var onDelete: () -> Unit
}

val Plan = FC<PlanProps>("Plan") { props ->
    var plan by PropsDelegate(props.plan) { next -> props.setPlan(next) }

    PlanHeader {
        title = plan.title
        setTitle = { next -> plan = plan.copy(title = next) }

        onDelete = { props.onDelete() }
    }

    Accordion {
        disableGutters = true

        Summary { title = "Inputs" }
        AccordionDetails {
            plan.inputs.withIndex().forEach { (i, planInput) ->
                PlanInput {
                    isFirst = i == 0

                    input = planInput
                    setInput = { next -> plan = plan.setInput(i, next) }
                    onDelete = { plan = plan.removeInput(i) }
                }
            }

            Box {
                sx {
                    margin = if (plan.inputs.isEmpty()) Margin(0.px, 0.px, 12.px)
                    else Margin(12.px, 0.px)
                }

                Fab {
                    color = FabColor.primary
                    size = Size.large
                    Add { fontSize = SvgIconSize.medium }

                    onClick = {
                        plan = plan.addInput(PlanInputModel())
                    }
                }
            }
        }
    }

    Accordion {
        disableGutters = true

        Summary { title = "Products" }
        AccordionDetails {
            plan.products.withIndex().forEach { (i, planProduct) ->
                PlanProduct {
                    isFirst = i == 0

                    product = planProduct
                    setProduct = { next -> plan = plan.setProduct(i, next) }
                    onDelete = { plan = plan.removeProduct(i) }
                }
            }

            Box {
                sx {
                    margin = if (plan.products.isEmpty()) Margin(0.px, 0.px, 12.px)
                    else Margin(12.px, 0.px)
                }

                Fab {
                    color = FabColor.primary
                    size = Size.large
                    Add { fontSize = SvgIconSize.medium }

                    onClick = {
                        plan = plan.addProduct(PlanProductModel())
                    }
                }
            }
        }
    }

    Accordion {
        disableGutters = true
        defaultExpanded = false

        Summary { title = "Outcome" }
        AccordionDetails {
            Button {
                variant = ButtonVariant.contained
                onClick = { plan = plan.optimize() }

                if (plan.outcome == null) {
                    +"Calculate"
                } else {
                    +"Recalculate"
                }
            }

            when (val outcome = plan.outcome) {
                null -> {}
                else -> TableContainer {
                    Table {
                        TableHead {
                            TableRow {
                                TableCell { +"Recipe" }
                                TableCell { +"Rate" }
                                TableCell { +" Exact" }
                            }
                        }
                        TableBody {
                            outcome.recipes.filter { (_, rate) -> rate > 0.q }
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
    }
}

external interface SummaryProps : Props {
    var title: String
}

val Summary = FC<SummaryProps> { props ->
    AccordionSummary {
        sx { flexDirection = FlexDirection.rowReverse }

        expandIcon = ExpandMore.create()
        Typography {
            sx { padding = Padding(0.px, 4.px) }
            +props.title
        }
    }
}
