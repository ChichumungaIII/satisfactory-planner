package app.home.newsavecard

import app.api.save.v1.CreateSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.data.save.SavesListService
import app.home.common.HomePageCard
import app.util.launchMain
import kotlinx.coroutines.delay
import react.FC
import react.Props
import react.StateInstance
import react.create
import react.useContext
import kotlin.time.Duration.Companion.milliseconds

external interface NewSaveCardProps : Props {
  var creating: StateInstance<Boolean>
}

val NewSaveCard = FC<NewSaveCardProps>("NewSaveCard") { props ->
  val saveService = useContext(SaveServiceJs.Context)!!
  val savesListService = useContext(SavesListService.Context)!!

  var creating by props.creating

  HomePageCard {
    disabled = creating
    onClick = {
      launchMain {
        delay(2000.milliseconds)

        val save = Save(
          SaveName.createRandom(),
          "New Save",
          listOf(),
          listOf(),
          listOf(),
          listOf(),
        )
        saveService.createSave(CreateSaveRequest(save))
        savesListService.add(save)
        creating = false
      }

      creating = true
    }

    content = NewSaveCardContent.create {}
  }
}
