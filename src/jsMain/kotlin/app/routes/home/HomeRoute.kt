package app.routes.home

import app.common.layout.AppFrame
import app.common.layout.RouteLoadingIndicator
import app.common.util.AppTitle
import app.data.common.RemoteData
import app.data.save.SavesListService
import react.FC
import react.Props
import react.create
import react.useContext

external interface HomeRouteProps : Props

val HomeRoute = FC<HomeRouteProps>("HomeRoute") {
  val (savesData, savesListService) = useContext(SavesListService.Context)!!

  AppFrame {
    title = AppTitle.create { +"Satisfactory Planner" }

    when (savesData) {
      is RemoteData.Empty -> savesListService.load()

      is RemoteData.Loading -> {
        content = RouteLoadingIndicator.create {}
      }

      is RemoteData.Loaded -> {
        content = HomePage.create { saves = savesData.data }
      }

      is RemoteData.Error -> TODO()
    }
  }
}
