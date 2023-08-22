package app.routes.save

import app.RouteParams
import app.api.save.v1.SaveName
import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.data.common.RemoteData
import app.data.save.SaveLoader
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams
import react.useContext

external interface SaveRouteProps : Props

val SaveRoute = FC<SaveRouteProps>("SaveRoute") {
  val saveIdParam = useParams()[RouteParams.SAVE_ID.key]
  val name = RouteParams.parseInt(saveIdParam) { SaveName(it) }

  val (saveData, saveLoader) = useContext(SaveLoader)!!

  val save = (saveData.takeIf { it.name == name } ?: RemoteData.empty())
  if (name == null) {
    AppFrame {
      title = AppTitle.create { +"Malformed Save ID" }
      content = ReactNode("[saves/$saveIdParam] is malformed and cannot be loaded.")
    }
  } else when (save) {
    is RemoteData.Empty -> saveLoader.load(name)

    is RemoteData.Loading -> AppFrame {
      title = AppTitle.create { +"Loading ${name.getResourceName()}..." }
      content = RouteLoadingIndicator.create()
    }

    is RemoteData.Loaded -> AppFrame {
      title = AppTitle.create { +save.value.displayName }
      content = SavePage.create { this.save = save.value }
    }

    is RemoteData.Error -> TODO()
  }
}
