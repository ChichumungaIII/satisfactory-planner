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

      value = newSave.phases.size - 1
      onChange = { event, _ ->
        val phases = Phase.values().take(event.target.value.unsafeCast<Int>() + 1)
        val tiers = phases.mapNotNull(STARTING_PHASE_TO_COMPLETED_TIERS::get).flatten()
        val milestones = Milestone.values().filter { tiers.contains(it.tier) }
        newSave = newSave.copy(
          phases = phases,
          tiers = tiers,
          milestones = milestones,
        )
      }

      Phase.values().withIndex().forEach { (i, phase) ->
        MenuItem {
          value = i
          +phase.displayName
        }
      }
    }
  }
}

private val STARTING_PHASE_TO_COMPLETED_TIERS = mapOf(
  Phase.ONBOARDING to listOf(Tier.TIER_0),
  Phase.PHASE_1 to listOf(Tier.TIER_1, Tier.TIER_2),
  Phase.PHASE_2 to listOf(Tier.TIER_3, Tier.TIER_4),
  Phase.PHASE_3 to listOf(Tier.TIER_5, Tier.TIER_6),
  Phase.PHASE_4 to listOf(Tier.TIER_7, Tier.TIER_8),
)
