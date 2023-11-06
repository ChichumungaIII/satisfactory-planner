package app.routes.editsave

import app.RouteParams
import app.api.save.v1.SaveName
import app.common.layout.RouteLoadingIndicator
import app.common.layout.appframe.AppFrame
import app.common.util.AppTitle
import app.redux.state.resource.ResourceState.Companion.Empty
import app.redux.state.resource.ResourceState.Companion.Loaded
import app.redux.state.resource.ResourceState.Companion.Loading
import app.redux.state.resource.save.LoadSave
import app.redux.state.resource.save.useSave
import app.redux.useAppDispatch
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.router.useParams

val EditSaveRoute = FC<Props>("EditSaveRoute") {
  val saveIdParam = useParams()[RouteParams.SAVE_ID.key]
  val name = RouteParams.parseInt(saveIdParam) { SaveName(it) }

  val dispatch = useAppDispatch()

  if (name == null) {
    AppFrame {
      title = AppTitle.create { +"Malformed Save ID" }
      content = ReactNode("[saves/$saveIdParam] is malformed and cannot be loaded.")
    }
  } else when (val save = useSave(name)) {
    is Empty -> dispatch(LoadSave(name))

    is Loading -> AppFrame {
      title = AppTitle.create { +"Loading ${name.getResourceName()}..." }
      content = RouteLoadingIndicator.create()
    }

    is Loaded -> {
      AppFrame {
        title = AppTitle.create { +"Edit ${save.resource.displayName}" }
        content = EditSavePage.create { this.save = save.resource }
      }
    }
  }
}
