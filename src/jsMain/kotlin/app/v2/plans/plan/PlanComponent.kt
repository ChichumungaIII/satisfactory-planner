package app.v2.plans.plan

import app.util.PropsDelegate
import app.v2.plans.data.model.Plan
import mui.material.Typography
import react.FC
import react.Props

external interface PlanComponentProps : Props {
  var plan: Plan
  var setPlan: (Plan) -> Unit
}

val PlanComponent = FC<PlanComponentProps>("PlanComponent") { props ->
  var plan by PropsDelegate(props.plan, props.setPlan)

  Typography {
    +plan.title
  }
}
