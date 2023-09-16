package app.routes.plan

import app.api.plan.v1.Plan
import app.api.save.v1.Save
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface PlanPageProps : Props {
  var save: Save
  var plan: Plan
}

val PlanPage = FC<PlanPageProps>("PlanPage") { props ->
  Typography {
    variant = TypographyVariant.h2
    +props.plan.displayName
  }
}
