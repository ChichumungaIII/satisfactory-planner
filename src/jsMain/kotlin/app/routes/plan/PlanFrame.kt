package app.routes.plan

import app.common.layout.AppFrame
import app.common.util.AppTitle
import app.common.util.SaveIndicator
import app.data.plan.PlanManager
import react.FC
import react.Props
import react.create
import react.useContext

external interface PlanFrameProps : Props

val PlanFrame = FC<PlanFrameProps>("PlanFrame") {
  val (plan, manager) = useContext(PlanManager)!!

  AppFrame {
    title = AppTitle.create { +plan.displayName }
    actions = listOf(
      SaveIndicator.create { state = manager.state() },
    )
    content = PlanPage.create {}
  }
}
