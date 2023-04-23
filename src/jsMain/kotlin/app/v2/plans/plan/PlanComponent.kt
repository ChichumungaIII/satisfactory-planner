package app.v2.plans.plan

import app.util.PropsDelegate
import app.v2.common.input.TooltipIconButton
import app.v2.plans.data.model.Plan
import app.v2.plans.plan.inputs.PlanInputsComponent
import app.v2.plans.plan.products.PlanProductsComponent
import app.v2.plans.plan.results.PlanResultsComponent
import mui.icons.material.ArrowRight
import mui.material.Divider
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.createContext
import react.useContext

external interface PlanComponentProps : Props {
  var plan: Plan
  var setPlan: (Plan) -> Unit
}

val PlanComponentContext = createContext<PropsDelegate<Plan>>()

val PlanComponent = FC<PlanComponentProps>("PlanComponent") { props ->
  val (_, computeOutcome) = useContext(ComputeOutcomeContext)
  val delegate = PropsDelegate(props.plan) { next ->
    props.setPlan(next)
    computeOutcome(ForPlan(next))
  }

  PlanComponentContext(delegate) {
    Stack {
      direction = responsive(StackDirection.row)

      PlanInputsComponent {}

      TooltipIconButton {
        title = "Select recipes"
        icon = ArrowRight
        onClick = {}
      }

      PlanProductsComponent {}
    }

    Divider {}

    PlanResultsComponent {}
  }
}
