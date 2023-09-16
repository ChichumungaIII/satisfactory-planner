package app.routes.plan

import app.RouteParams
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.redux.state.resource.ResourceState.Companion.Empty
import app.redux.state.resource.ResourceState.Companion.Loaded
import app.redux.state.resource.ResourceState.Companion.Loading
import app.redux.state.resource.plan.LoadPlan
import app.redux.state.resource.plan.usePlan
import app.redux.state.resource.save.LoadSave
import app.redux.state.resource.save.useSave
import app.redux.useAppDispatch
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams

external interface PlanRouteProps : Props

val PlanRoute = FC<PlanRouteProps>("PlanRoute") {
  val params = useParams()
  val saveIdParam = params[RouteParams.SAVE_ID.key]
  val saveName = RouteParams.parseInt(saveIdParam) { SaveName(it) }
  val planIdParam = params[RouteParams.PLAN_ID.key]
  val planName = saveName?.let { save -> RouteParams.parseInt(planIdParam) { PlanName(save, it) } }

  val dispatch = useAppDispatch()

  if (saveName == null || planName == null) {
    AppFrame {
      title = AppTitle.create { +"Malformed Plan ID" }
      content = ReactNode("[saves/$saveIdParam/plans/$planIdParam] is malformed and cannot be loaded.")
    }
    return@FC
  }
  val data = useSave(saveName).merge(usePlan(planName)) { save, plan -> PlanRouteData(save, plan) }

  when (data) {
    is Empty -> {
      dispatch(LoadSave(saveName))
      dispatch(LoadPlan(planName))
    }

    is Loading -> AppFrame {
      title = AppTitle.create { +"Loading ${planName.getResourceName()}..." }
      content = RouteLoadingIndicator.create()
    }

    is Loaded -> AppFrame {
      title = AppTitle.create { +data.resource.plan.displayName }
      content = PlanPage.create {
        save = data.resource.save
        plan = data.resource.plan
      }
    }
  }
}

data class PlanRouteData(val save: Save, val plan: Plan)
