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
import mui.material.TextField
import mui.system.responsive
import mui.system.sx
import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import react.useContext
import web.cssom.px
import kotlin.time.Duration.Companion.milliseconds

external interface DisplayNameStepProps : Props

val DisplayNameStep = FC<DisplayNameStepProps>("DisplayNameStep") {
  val appTheme by useContext(AppThemeContext)!!
  val saveService = useContext(SaveServiceJs.Context)!!
  val (_, savesListService) = useContext(SavesListService.Context)!!

  var newSave by useContext(NewSaveContext)!!
  var creating by useContext(NewSaveCreatingContext)!!

  Stack {
    direction = responsive(StackDirection.column)

    TextField {
      sx { width = 384.px }

      label = ReactNode("Save name")
      required = true
      disabled = creating

      value = newSave.displayName
      onChange = { event ->
        val displayName = event.target.unsafeCast<HTMLInputElement>().value
        newSave = newSave.copy(displayName = displayName)
      }
    }

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
