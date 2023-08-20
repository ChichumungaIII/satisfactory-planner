package app.routes.plan

import app.RouteParams
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.common.layout.AppFrame
import app.common.layout.RouteLoadingIndicator
import app.common.util.AppTitle
import app.data.common.RemoteData
import app.data.plan.PlanLoader
import app.data.plan.PlanManagerProvider
import app.data.save.SaveLoader
import app.data.save.SaveManagerProvider
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams
import react.useContext

external interface PlanRouteProps : Props

val PlanRoute = FC<PlanRouteProps>("PlanRoute") {
  val params = useParams()

  val saveIdParam = params[RouteParams.SAVE_ID.key]
  val saveName = RouteParams.parseInt(saveIdParam) { SaveName(it) }

  val planIdParam = params[RouteParams.PLAN_ID.key]
  val planName = saveName?.let { save -> RouteParams.parseInt(planIdParam) { PlanName(save, it) } }

  val (loadedSaveData, saveLoader) = useContext(SaveLoader)!!
  val (loadedPlanData, planLoader) = useContext(PlanLoader)!!

  val saveData = (loadedSaveData.takeIf { it.name == saveName }) ?: RemoteData.empty()
  val planData = (loadedPlanData.takeIf { it.name == planName }) ?: RemoteData.empty()
  val resources = planData.merge(saveData) { plan, save -> PlanRouteData(save, plan) }
  if (planName == null) {
    AppFrame {
      title = AppTitle.create { +"Malformed Plan ID" }
      content = ReactNode("[saves/$saveIdParam/plans/$planIdParam] is malformed and cannot be loaded.")
    }
  } else when (resources) {
    is RemoteData.Empty -> {
      saveLoader.load(saveName)
      planLoader.load(planName)
    }

    is RemoteData.Loading -> AppFrame {
      title = AppTitle.create { +"Loading ${planName.getResourceName()}..." }
      content = RouteLoadingIndicator.create()
    }

    is RemoteData.Loaded -> AppFrame {
      title = AppTitle.create { +resources.value.plan.displayName }
      content = SaveManagerProvider.create {
        resource = resources.value.save
        PlanManagerProvider {
          resource = resources.value.plan
          PlanPage {}
        }
      }
    }

    is RemoteData.Error -> TODO()
  }
}

data class PlanRouteData(val save: Save, val plan: Plan)
