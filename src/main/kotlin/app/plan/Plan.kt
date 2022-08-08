package app.plan

import app.model.PlanInputModel
import app.model.PlanModel
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
import mui.material.Fab
import mui.material.FabColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.Typography
import mui.system.sx
import react.FC
import react.Props
import react.create

external interface PlanProps : Props {
    var plan: PlanModel
    var setPlan: (PlanModel) -> Unit
    var onDelete: () -> Unit
}

val Plan = FC<PlanProps>("Plan") { props ->
    var plan by PropsDelegate(props.plan) { next -> props.setPlan(next) }

    PlanHeader {
        title = plan.title()
        setTitle = { newTitle ->
            plan = plan.setTitle(newTitle)
        }

        onDelete = { props.onDelete() }
    }

    Accordion {
        disableGutters = true

        Summary { title = "Inputs" }
        AccordionDetails {
            plan.inputs().withIndex().forEach { (i, planInput) ->
                PlanInput {
                    isFirst = i == 0

                    input = planInput
                    setInput = { nextInput ->
                        plan = plan.setInput(i, nextInput)
                    }
                    onDelete = {
                        plan = plan.removeInput(i)
                    }
                }
            }

            Box {
                sx {
                    margin = if (plan.inputs().isEmpty())
                        Margin(0.px, 0.px, 12.px)
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
        AccordionDetails {}
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
