package app.routes.plan

import app.api.plan.v1.Plan
import mui.material.Box
import react.FC
import react.Props

external interface PlanPageProps : Props {
  var plan: Plan
}

val PlanPage = FC<PlanPageProps>("PlanPage") { props ->
  Box {
    +props.plan.displayName
  }
}
