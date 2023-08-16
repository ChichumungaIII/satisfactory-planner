package app.routes.createsave.steps.phase

import app.game.data.Milestone
import app.game.data.Phase
import app.game.logic.Progress
import app.routes.createsave.NewSaveContext
import mui.material.FormControl
import mui.material.InputLabel
import mui.material.MenuItem
import mui.material.Select
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import web.cssom.px

external interface PhaseStepProps : Props

val PhaseStep = FC<PhaseStepProps>("PhaseStep") {
  var newSave by useContext(NewSaveContext)!!

  FormControl {
    InputLabel { +"Starting Phase" }
    Select {
      sx { width = 384.px }

      label = ReactNode("Starting Phase")
      autoFocus = true

      value = newSave.progress.phase.name
      onChange = { event, _ ->
        val phase = Phase.valueOf(event.target.value.unsafeCast<String>())
        val milestones = phase.previous?.let { Progress(phase = it) }
          ?.let { progress -> Milestone.entries.filter { it.tier.requirement.test(progress) } }
          ?: listOf()
        newSave = newSave.copy(
          progress = newSave.progress.copy(
            phase = phase,
            milestones = milestones,
          )
        )
      }

      Phase.entries.forEach { phase ->
        MenuItem {
          value = phase.name
          +phase.displayName
        }
      }
    }
  }
}
