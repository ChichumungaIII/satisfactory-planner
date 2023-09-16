package app.routes.home

import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.redux.state.resource.ResourceState.Companion.Empty
import app.redux.state.resource.ResourceState.Companion.Loaded
import app.redux.state.resource.ResourceState.Companion.Loading
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
      is Empty -> dispatch(LoadSaves)

      is Loading -> {
        content = RouteLoadingIndicator.create {}
      }

      is Loaded -> {
        content = HomePage.create { this.saves = saves.resource }
      }
    }
  }
}
