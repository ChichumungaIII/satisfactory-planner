package app.routes.plan

import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.PlanServiceJs
import app.api.plan.v1.UpdatePlanRequest
import app.api.save.v1.SaveName
import app.common.layout.AppFrame
import app.common.layout.RouteLoadingIndicator
import app.common.util.AppTitle
import app.data.common.RemoteData
import app.data.common.ResourceManager
import app.data.plan.PlanCache
import app.data.plan.PlanLoader
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.createContext
import react.router.useParams
import react.useContext

external interface PlanRouteProps : Props

val PlanManager = createContext<ResourceManager<PlanName, Plan>>()
val PlanManagerProvider = ResourceManager.createProvider(
  "PlanManagerProvider",
  PlanManager,
  PlanServiceJs.Context,
  { plan -> updatePlan(UpdatePlanRequest(plan, listOf("*"))) },
  PlanCache,
)

val PlanRoute = FC<PlanRouteProps>("PlanRoute") {
  val params = useParams()
  val saveIdParam = params["saveId"]
  val planIdParam = params["planId"]
  val name = saveIdParam?.toUIntOrNull()?.toInt()?.let { SaveName(it) }?.let { save ->
    planIdParam?.toUIntOrNull()?.toInt()?.let { PlanName(save, it) }
  }

  val (planData, planLoader) = useContext(PlanLoader)!!

  val plan = (planData.takeIf { it.name == name }) ?: RemoteData.empty()
  if (name == null) {
    AppFrame {
      title = AppTitle.create { +"Malformed Plan ID" }
      content = ReactNode("[saves/$saveIdParam/plans/$planIdParam] is malformed and cannot be loaded.")
    }
  } else when (plan) {
    is RemoteData.Empty -> planLoader.load(name)

    is RemoteData.Loading -> AppFrame {
      title = AppTitle.create { +"Loading ${name.getResourceName()}..." }
      content = RouteLoadingIndicator.create()
    }

    is RemoteData.Loaded -> AppFrame {
      title = AppTitle.create { +plan.data.displayName }
      content = PlanManagerProvider.create {
        resource = plan.data
        PlanPage {}
      }
    }

    is RemoteData.Error -> TODO()
  }
}
