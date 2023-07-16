package app.routes.createsave

import app.api.save.v1.CreateSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.data.save.SavesListService
import app.theme.AppThemeContext
import app.util.launchMain
import kotlinx.coroutines.delay
import mui.lab.LoadingButton
import mui.material.Orientation
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Step
import mui.material.StepContent
import mui.material.StepLabel
import mui.material.Stepper
import mui.material.TextField
import mui.system.responsive
import mui.system.sx
import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.ReactNode
import react.StateInstance
import react.createContext
import react.dom.onChange
import react.useContext
import react.useState
import web.cssom.Padding
import web.cssom.px
import kotlin.time.Duration.Companion.milliseconds

external interface CreateSavePageProps : Props

val CreateSaveStepContext = createContext<StateInstance<Int>>()
val NewSaveContext = createContext<StateInstance<Save>>()

private val DEFAULT_SAVE = Save(
  name = SaveName(0),
  displayName = "",
  phases = listOf(),
  tiers = listOf(),
  milestones = listOf(),
  research = listOf(),
)

val CreateSavePage = FC<CreateSavePageProps>("CreateSavePage") {
  val appTheme by useContext(AppThemeContext)!!

  val saveService = useContext(SaveServiceJs.Context)!!
  val (_, savesListService) = useContext(SavesListService.Context)!!

  val stepState = useState(0)
  val step by stepState

  val newSaveState = useState(DEFAULT_SAVE)
  var newSave by newSaveState

  var creating by useState(false)

  CreateSaveStepContext(stepState) {
    NewSaveContext(newSaveState) {
      Stepper {
        sx {
          padding = Padding(6.px, 12.px)
        }

        activeStep = step
        orientation = Orientation.vertical

        Step {
          StepLabel { +"Name" }
          StepContent {
            Stack {
              direction = responsive(StackDirection.column)

              TextField {
                sx { width = 384.px }

                label = ReactNode("Save name")
                required = true
                fullWidth = false

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
        }
      }
    }
  }
}
