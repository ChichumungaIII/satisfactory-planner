package app.routes.createsave.steps.phase

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Tier
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
        val phases = priorPhases(phase)
        val tiers = Tier.entries.filter { phases.contains(it.phase) }
        val milestones = Milestone.entries.filter { tiers.contains(it.tier) }
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

fun priorPhases(phase: Phase): Set<Phase> = buildSet {
  val previous = phase.previous
  if (previous != null) {
    addAll(priorPhases(previous))
    add(previous)
  }
}
