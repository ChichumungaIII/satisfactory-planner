package app.routes.plan

import app.theme.AppThemeContext
import mui.material.Box
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.dom.onChange
import react.useContext

external interface PlanPageProps : Props

val PlanPage = FC<PlanPageProps>("PlanPage") {
  val (appTheme) = useContext(AppThemeContext)!!
  val (plan, manager) = useContext(PlanManager)!!

  Box {
    sx { padding = appTheme.spacing(2) }

    TextField {
      autoFocus = true
      value = plan.displayName
      onChange = { event ->
        manager.update(plan.copy(displayName = event.target.asDynamic().value as String))
      }
    }
  }
}
