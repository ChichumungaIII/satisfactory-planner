package app.routes.plan

import app.api.plan.v1.PlanName
import app.api.save.v1.SaveName
import app.common.layout.AppFrame
import app.common.layout.RouteLoadingIndicator
import app.common.util.AppTitle
import app.data.common.RemoteData
import app.data.plan.PlanLoader
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams
import react.useContext

external interface PlanRouteProps : Props {

}

val PlanRoute = FC<PlanRouteProps>("PlanRoute") { props ->
  val params = useParams()
  val saveIdParam = params["saveId"]
  val planIdParam = params["planId"]
  val name = saveIdParam?.toUIntOrNull()?.toInt()?.let { SaveName(it) }?.let { save ->
    planIdParam?.toUIntOrNull()?.toInt()?.let { PlanName(save, it) }
  }

  val (planData, planLoader) = useContext(PlanLoader.Context)!!

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
      content = PlanPage.create { this.plan = plan.data }
    }

    is RemoteData.Error -> TODO()
  }
}
