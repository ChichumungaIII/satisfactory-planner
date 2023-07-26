package app.routes.createsave.steps.milestones

import app.game.data.Phase
import app.game.data.Tier
import app.routes.createsave.NewSaveContext
import mui.material.Card
import mui.material.CardContent
import mui.material.PaperVariant
import react.FC
import react.Props
import react.useContext

external interface MilestonesStepProps : Props

val MilestonesStep = FC<MilestonesStepProps>("MilestonesStep") {
  val newSave by useContext(NewSaveContext)!!

  Card {
    variant = PaperVariant.outlined

    CardContent {
      mui.material.List {
        newSave.phases.mapNotNull(STARTING_PHASE_TO_AVAILABLE_TIERS::get).flatten().forEach { tier ->
          TierMilestonesList { this.tier = tier }
        }
      }
    }
  }
}

private val STARTING_PHASE_TO_AVAILABLE_TIERS = mapOf(
  Phase.GAME_START to listOf(Tier.TIER_0),
  Phase.ONBOARDING to listOf(Tier.TIER_1, Tier.TIER_2),
  Phase.PHASE_1 to listOf(Tier.TIER_3, Tier.TIER_4),
  Phase.PHASE_2 to listOf(Tier.TIER_5, Tier.TIER_6),
  Phase.PHASE_3 to listOf(Tier.TIER_7, Tier.TIER_8),
)
