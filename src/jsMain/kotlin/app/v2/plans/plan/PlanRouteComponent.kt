package app.v2.plans.plan

import app.v2.common.layout.AppTitleComponent
import app.v2.common.layout.FrameComponent
import app.v2.common.layout.TitleBarComponent
import app.v2.common.layout.ZeroStateComponent
import app.v2.data.LoadState.Loaded
import app.v2.plans.data.GetPlan
import app.v2.plans.data.PlanContext
import app.v2.plans.data.SavePlan
import mui.material.CircularProgress
import react.FC
import react.Props
import react.create
import react.router.useParams
import react.useContext
import react.useEffectOnce

external interface PlanRouteComponentProps : Props

val PlanRouteComponent = FC<PlanRouteComponentProps>("PlanRouteComponent") { props ->
  val planId = useParams()["planId"]!!.toULong()
  val (plan, updatePlan) = useContext(PlanContext)!!

  useEffectOnce { updatePlan(GetPlan(planId)) }

  if (plan is Loaded && plan.data.id == planId) {
    ComputeOutcomeContextComponent {
      PlanComponent {
        this.plan = plan.data
        setPlan = { next -> updatePlan(SavePlan(next)) }
      }
    }
  } else {
    FrameComponent {
      titleBar = TitleBarComponent.create {
        title = AppTitleComponent.create { title = "Plans" }
      }

      ZeroStateComponent {
        CircularProgress { size = 80; thickness = 4.8 }
      }
    }
  }
}
