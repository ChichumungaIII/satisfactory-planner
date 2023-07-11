package app.home

import app.common.layout.AppFrame
import app.common.layout.RouteLoadingIndicator
import app.data.common.RemoteData
import app.data.save.SavesListService
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import react.useContext

external interface HomeRouteProps : Props

val HomeRoute = FC<HomeRouteProps>("HomeRoute") {
  val (savesData, savesListService) = useContext(SavesListService.Context)!!

  AppFrame {
    title = Typography.create {
      variant = TypographyVariant.h1
      +"Satisfactory Planner"
    }

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
