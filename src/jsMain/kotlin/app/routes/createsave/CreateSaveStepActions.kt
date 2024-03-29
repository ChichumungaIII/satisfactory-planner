package app.routes.createsave

import app.AppRoute
import app.RouteParams
import app.api.save.v1.CreateSaveRequest
import app.api.save.v1.SaveName
import app.api.save.v1.getSaveService
import app.redux.state.resource.save.InsertSave
import app.redux.useAppDispatch
import app.theme.AppThemeContext
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.lab.LoadingButton
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.router.useNavigate
import react.useContext
import web.cssom.px
import kotlin.time.Duration.Companion.seconds

external interface CreateSaveStepActionsProps : Props {
  var initial: Boolean
  var final: Boolean
}

val CreateSaveStepActions = FC<CreateSaveStepActionsProps>("CreateSaveStepActions") { props ->
  val appTheme by useContext(AppThemeContext)!!

  val navigate = useNavigate()
  val dispatch = useAppDispatch()

  var step by useContext(CreateSaveStepContext)!!
  val newSave by useContext(NewSaveContext)!!
  var creating by useContext(NewSaveCreatingContext)!!

  Stack {
    direction = responsive(StackDirection.row)
    spacing = responsive(appTheme.spacing(4))

    if (props.final) {
      LoadingButton {
        sx { width = 192.px }

        disabled = newSave.displayName.isBlank()
        loading = creating
        onClick = {
          launchMain {
            val request = CreateSaveRequest(newSave.copy(name = SaveName.createRandom()))
            val save = getSaveService().createSave(request)
            delay(3.seconds)

            dispatch(InsertSave(save))
            creating = false
            navigate(to = AppRoute.V3_SAVE.url(RouteParams.SAVE_ID to save.name.id))
          }

          creating = true
        }

        +"Create Save"
      }
    } else {
      Button {
        sx { width = 192.px }

        disabled = newSave.displayName.isBlank()
        onClick = { step += 1 }

        +"Next"
      }
    }

    if (!props.initial) {
      Button {
        sx { width = 128.px }

        variant = ButtonVariant.outlined
        disabled = creating
        onClick = { step -= 1 }

        +"Back"
      }
    }
  }
}
