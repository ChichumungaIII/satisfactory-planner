package app.routes.plan

import app.api.plan.v1.Plan
import app.theme.AppThemeContext
import mui.material.Box
import mui.system.sx
import react.FC
import react.Props
import react.useContext

external interface PlanPageProps : Props {
  var plan: Plan
}

val PlanPage = FC<PlanPageProps>("PlanPage") { props ->
  val (appTheme) = useContext(AppThemeContext)!!

  Box {
    sx { padding = appTheme.spacing(2) }

    +props.plan.displayName
  }
}
