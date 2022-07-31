package app.plan

import app.model.Plan
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
import react.StateInstance
import react.create
import react.createContext
import react.useState

val PlanContext = createContext<StateInstance<Plan>>()

external interface ProductionPlanProps : Props {
    var plan: Plan
}

val ProductionPlan = FC<ProductionPlanProps> { props ->
    val plan = useState(props.plan)

    PlanContext(plan) {
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
