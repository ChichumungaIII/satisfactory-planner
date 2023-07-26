package app.routes.save

import app.api.save.v1.SaveName
import app.common.layout.AppFrame
import app.common.layout.RouteLoadingIndicator
import app.common.util.AppTitle
import app.data.common.RemoteData
import app.data.save.SaveLoader
import mui.material.Box
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams
import react.useContext

external interface SaveRouteProps : Props

val SaveRoute = FC<SaveRouteProps>("SaveRoute") {
  val saveIdParam = useParams()["saveId"]
  val name = saveIdParam?.toIntOrNull()?.let { SaveName(it) }

  val (saveData, saveLoader) = useContext(SaveLoader.Context)!!

  println(saveData.name)

  AppFrame {
    title = AppTitle.create { +"Save" }

    if (name == null) {
      content = ReactNode("[saves/$saveIdParam] is malformed and cannot be loaded.")
    } else when (val save = saveData.takeIf { it.name == name } ?: RemoteData.empty()) {
      is RemoteData.Empty -> saveLoader.load(name)
      is RemoteData.Loading -> content = RouteLoadingIndicator.create()
      is RemoteData.Loaded -> content = Box.create { +save.data.displayName }
      is RemoteData.Error -> TODO()
    }
  }
}
