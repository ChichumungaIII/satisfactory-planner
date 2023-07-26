package app.routes.createsave

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.game.data.Phase
import app.routes.createsave.steps.displayname.DisplayNameStep
import app.routes.createsave.steps.milestones.MilestonesStep
import app.routes.createsave.steps.phase.PhaseStep
import mui.material.Orientation
import mui.material.Step
import mui.material.StepContent
import mui.material.StepLabel
import mui.material.Stepper
import mui.system.sx
import react.FC
import react.Props
import react.StateInstance
import react.createContext
import react.useState
import web.cssom.Padding
import web.cssom.px

external interface CreateSavePageProps : Props

val CreateSaveStepContext = createContext<StateInstance<Int>>()
val NewSaveContext = createContext<StateInstance<Save>>()
val NewSaveCreatingContext = createContext<StateInstance<Boolean>>()

private val DEFAULT_SAVE = Save(
  name = SaveName(0),
  displayName = "",
  phases = listOf(Phase.GAME_START),
  tiers = listOf(),
  milestones = listOf(),
  research = listOf(),
)

val CreateSavePage = FC<CreateSavePageProps>("CreateSavePage") {
  val stepState = useState(0)
  val step by stepState

  val newSaveState = useState(DEFAULT_SAVE)
  val creatingState = useState(false)

  CreateSaveStepContext(stepState) {
    NewSaveContext(newSaveState) {
      NewSaveCreatingContext(creatingState) {
        Stepper {
          sx { padding = Padding(6.px, 12.px) }

          activeStep = step
          orientation = Orientation.vertical

          Step {
            StepLabel { +"Name" }
            StepContent {
              CreateSaveStep {
                initial = true

                DisplayNameStep { }
              }
            }
          }

          Step {
            StepLabel { +"Phase" }
            StepContent {
              CreateSaveStep {
                PhaseStep {}
              }
            }
          }

          Step {
            StepLabel { +"Milestones" }
            StepContent {
              CreateSaveStep {
                final = true

                MilestonesStep {}
              }
            }
          }
        }
      }
    }
  }
}
