package app.routes.plan

import app.data.plan.PlanManager
import app.data.save.SaveManager
import app.theme.AppThemeContext
import mui.material.Stack
import mui.material.StackDirection
import mui.material.TextField
import mui.material.Typography
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.dom.onChange
import react.useContext

external interface PlanPageProps : Props

val PlanPage = FC<PlanPageProps>("PlanPage") {
  val (appTheme) = useContext(AppThemeContext)!!
  val (save) = useContext(SaveManager)!!
  val (plan, manager) = useContext(PlanManager)!!

  Stack {
    sx { padding = appTheme.spacing(2) }
    direction = responsive(StackDirection.row)

    Typography {
      +save.displayName
    }

    TextField {
      autoFocus = true
      value = plan.displayName
      onChange = { event ->
        manager.update(plan.copy(displayName = event.target.asDynamic().value as String))
      }
    }
  }
}
