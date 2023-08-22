package app.routes.home

import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.data.common.RemoteData
import app.data.save.SaveCollectionLoader
import react.FC
import react.Props
import react.create
import react.useContext

external interface HomeRouteProps : Props

val HomeRoute = FC<HomeRouteProps>("HomeRoute") {
  val (saveCollection, saveCollectionLoader) = useContext(SaveCollectionLoader.Context)!!

  AppFrame {
    title = AppTitle.create { +"Satisfactory Planner" }

    when (saveCollection) {
      is RemoteData.Empty -> saveCollectionLoader.load()

      is RemoteData.Loading -> {
        content = RouteLoadingIndicator.create {}
      }

      is RemoteData.Loaded -> {
        content = HomePage.create { saves = saveCollection.value }
      }

      is RemoteData.Error -> TODO()
    }
  }
}
