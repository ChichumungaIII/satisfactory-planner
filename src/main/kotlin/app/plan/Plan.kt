package app.plan

import app.model.PlanModel
import csstype.FlexDirection
import csstype.Padding
import csstype.px
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Typography
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useState

external interface PlanProps : Props {
    var plan: PlanModel
    var setPlan: (PlanModel) -> Unit
}

val Plan = FC<PlanProps>("Plan") { props ->
    val plan by useState(props.plan)

    PlanHeader {
        title = plan.title()
        setTitle = { newTitle ->
            props.setPlan(plan.copy(title = newTitle))
        }
    }

    Accordion {
        disableGutters = true

        Summary { title = "Inputs" }
        AccordionDetails {}
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
