package app.routes.home

import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.redux.state.resource.ResourceState
import app.redux.state.resource.save.LoadSaves
import app.redux.state.resource.save.useSaves
import app.redux.useAppDispatch
import react.FC
import react.Props
import react.create

external interface HomeRouteProps : Props

val HomeRoute = FC<HomeRouteProps>("HomeRoute") {
  val dispatch = useAppDispatch()
  val saves = useSaves()

  AppFrame {
    title = AppTitle.create { +"Satisfactory Planner" }

    when (saves) {
      is ResourceState.Empty -> dispatch(LoadSaves)

      is ResourceState.Loading -> {
        content = RouteLoadingIndicator.create {}
      }

      is ResourceState.Loaded -> {
        content = HomePage.create { this.saves = saves.resource }
      }
    }
  }
}
