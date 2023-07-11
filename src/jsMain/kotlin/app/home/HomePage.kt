package app.home

import app.api.save.v1.CreateSaveRequest
import app.api.save.v1.DeleteSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.common.layout.LoadingIndicator
import app.data.save.SavesListService
import app.theme.AppThemeContext
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.icons.material.Delete
import mui.material.Container
import mui.material.Grid
import mui.material.IconButton
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext
import react.useState
import web.cssom.Auto.Companion.auto
import kotlin.time.Duration.Companion.milliseconds

external interface HomePageProps : Props {
  var saves: List<Save>
}

val HomePage = FC<HomePageProps>("HomePage") { props ->
  val saveService = useContext(SaveServiceJs.Context)!!
  val savesListService = useContext(SavesListService.Context)!!

  val appTheme by useContext(AppThemeContext)!!

  var creating by useState(false)

  Container {
    sx { paddingTop = appTheme.spacing(3) }

    Grid {
      container = true
      spacing = responsive(4)

      props.saves.forEach { save ->
        HomePageCard {
          onClick = {
            println("Navigate to ${save.name}")
          }

          content = SaveCardContent.create { this.save = save }
          actions = IconButton.create {
            sx { marginLeft = auto }
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

      HomePageCard.takeIf { creating }?.invoke {
        disabled = true
        content = LoadingIndicator.create {}
      }

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
  }
}
