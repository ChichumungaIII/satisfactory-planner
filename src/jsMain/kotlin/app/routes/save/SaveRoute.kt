package app.routes.save

import app.RouteParams
import app.api.save.v1.SaveName
import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.redux.state.resource.ResourceState
import app.redux.state.resource.ResourceState.Empty
import app.redux.state.resource.ResourceState.Loaded
import app.redux.state.resource.save.LoadSave
import app.redux.state.resource.save.useSave
import app.redux.useAppDispatch
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams

external interface SaveRouteProps : Props

val SaveRoute = FC<SaveRouteProps>("SaveRoute") {
  val saveIdParam = useParams()[RouteParams.SAVE_ID.key]
  val name = RouteParams.parseInt(saveIdParam) { SaveName(it) }

  val dispatch = useAppDispatch()

  if (name == null) {
    AppFrame {
      title = AppTitle.create { +"Malformed Save ID" }
      content = ReactNode("[saves/$saveIdParam] is malformed and cannot be loaded.")
    }
  } else {
    when (val save = useSave(name)) {
      is Empty -> dispatch(LoadSave(name))

      is ResourceState.Loading -> AppFrame {
        title = AppTitle.create { +"Loading ${name.getResourceName()}..." }
        content = RouteLoadingIndicator.create()
      }

      is Loaded -> save.resource.also {
        AppFrame {
          title = AppTitle.create { +it.displayName }
          content = SavePage.create { this.save = it }
        }
      }
    }
  }
}
