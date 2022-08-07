package app.plan

import app.model.PlanInputModel
import app.model.PlanModel
import csstype.FlexDirection
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
    PlanHeader {
        title = props.plan.title()
        setTitle = { newTitle ->
            props.setPlan(props.plan.setTitle(newTitle))
        }

        onDelete = { props.onDelete() }
    }

    Accordion {
        disableGutters = true

        Summary { title = "Inputs" }
        AccordionDetails {
            props.plan.inputs().forEach { planInput ->
                Box { +planInput.item().displayName() }
            }

            Fab {
                color = FabColor.primary
                size = Size.medium
                Add { fontSize = SvgIconSize.medium }

                onClick = {
                    props.setPlan(props.plan.addInput(PlanInputModel()))
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
