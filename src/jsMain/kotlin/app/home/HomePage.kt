package app.home

import app.api.save.v1.CreateSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.common.layout.LoadingIndicator
import app.theme.AppThemeContext
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.material.Container
import mui.material.Grid
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds

external interface HomePageProps : Props {
  var saves: List<Save>
  var addSave: (Save) -> Unit
}

val HomePage = FC<HomePageProps>("HomePage") { props ->
  val saveService = useContext(SaveServiceJs.Context)!!

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

          SaveCardContent {
            this.save = save
          }
        }
      }

      HomePageCard.takeIf { creating }?.invoke {
        disabled = true
        LoadingIndicator {}
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
            props.addSave(saveService.createSave(CreateSaveRequest(save)))
            creating = false
          }

          creating = true
        }

        NewSaveCardContent {}
      }
    }
  }
}
