package app.home.savecard

import app.api.save.v1.DeleteSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveServiceJs
import app.data.save.SavesListService
import app.home.common.HomePageCard
import app.util.launchMain
import mui.icons.material.Delete
import mui.material.IconButton
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext
import web.cssom.Auto

external interface SaveCardProps : Props {
  var save: Save
}

val SaveCard = FC<SaveCardProps>("SaveCard") { props ->
  val saveService = useContext(SaveServiceJs.Context)!!
  val savesListService = useContext(SavesListService.Context)!!

  val save = props.save

  HomePageCard {
    onClick = {
      println("Navigate to ${save.name}")
    }

    content = SaveCardContent.create { this.save = save }
    actions = IconButton.create {
      sx { marginLeft = Auto.auto }
      onClick = {
        launchMain {
          saveService.deleteSave(DeleteSaveRequest(save.name))
        }
        savesListService.remove(save)
      }
      Delete {}
    }
  }
}
