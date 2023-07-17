package app.routes.createsave

import app.api.save.v1.CreateSaveRequest
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.data.save.SavesListService
import app.theme.AppThemeContext
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.lab.LoadingButton
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.PropsWithChildren
import react.useContext
import web.cssom.px
import kotlin.time.Duration.Companion.milliseconds

external interface CreateSaveStepProps : PropsWithChildren

val CreateSaveStep = FC<CreateSaveStepProps>("CreateSaveStep") { props ->
  val appTheme by useContext(AppThemeContext)!!
  val saveService = useContext(SaveServiceJs.Context)!!
  val (_, savesListService) = useContext(SavesListService.Context)!!

  val newSave by useContext(NewSaveContext)!!
  var creating by useContext(NewSaveCreatingContext)!!

  Stack {
    direction = responsive(StackDirection.column)

    +props.children

    LoadingButton {
      sx {
        width = 192.px
        marginTop = appTheme.spacing(3)
      }

      disabled = newSave.displayName.isBlank()
      loading = creating
      onClick = {
        launchMain {
          delay(3000.milliseconds)

          val request = CreateSaveRequest(
            save = newSave.copy(name = SaveName.createRandom())
          )
          val save = saveService.createSave(request)
          savesListService.ifLoaded { it.add(save) }

          creating = false
        }

        creating = true
      }

      +"Create Save"
    }
  }
}
