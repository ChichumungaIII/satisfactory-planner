package app.routes.save

import app.RouteParams
import app.api.save.v1.SaveName
import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.redux.state.cache.ResourceState.Loaded
import app.redux.state.cache.ResourceState.Updating
import app.redux.state.cache.useSave
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams

external interface SaveRouteProps : Props

val SaveRoute = FC<SaveRouteProps>("SaveRoute") {
  val saveIdParam = useParams()[RouteParams.SAVE_ID.key]
  val name = RouteParams.parseInt(saveIdParam) { SaveName(it) }

  if (name == null) {
    AppFrame {
      title = AppTitle.create { +"Malformed Save ID" }
      content = ReactNode("[saves/$saveIdParam] is malformed and cannot be loaded.")
    }
  } else {
    val save = useSave(name).let {
      when (it) {
        is Loaded -> it.resource
        is Updating -> it.resource
        else -> null
      }
    }
    when (save) {
      null -> AppFrame {
        title = AppTitle.create { +"Loading ${name.getResourceName()}..." }
        content = RouteLoadingIndicator.create()
      }

      else -> AppFrame {
        title = AppTitle.create { +save.displayName }
        content = SavePage.create { this.save = save }
      }
    }
  }
}
