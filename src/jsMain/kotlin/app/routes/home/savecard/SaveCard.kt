package app.routes.home.savecard

import app.AppRoute
import app.RouteParams
import app.api.save.v1.DeleteSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.getSaveService
import app.redux.state.resource.save.DeleteSave
import app.redux.useAppDispatch
import app.routes.home.common.HomePageCard
import app.util.launchMain
import mui.icons.material.Delete
import mui.material.IconButton
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.router.useNavigate
import web.cssom.Auto

external interface SaveCardProps : Props {
  var save: Save
}

val SaveCard = FC<SaveCardProps>("SaveCard") { props ->
  val navigate = useNavigate()
  val dispatch = useAppDispatch()

  val save = props.save

  HomePageCard {
    onClick = {
      navigate(to = AppRoute.V3_SAVE.url(RouteParams.SAVE_ID to save.name.id))
    }

    content = SaveCardContent.create { this.save = save }
    actions = IconButton.create {
      sx { marginLeft = Auto.auto }
      onClick = {
        launchMain { getSaveService().deleteSave(DeleteSaveRequest(save.name)) }
        dispatch(DeleteSave(save.name))
      }
      Delete {}
    }
  }
}
