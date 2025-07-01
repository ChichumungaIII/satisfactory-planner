package app.routes.createsave.steps.milestones

import app.game.data.Tier
import mui.material.Card
import mui.material.CardContent
import mui.material.PaperVariant
import react.FC
import react.Props

external interface MilestonesStepProps : Props

val MilestonesStep = FC<MilestonesStepProps>("MilestonesStep") {
  Card {
    variant = PaperVariant.outlined

    CardContent {
      mui.material.List {
        Tier.entries.forEach { tier ->
          TierMilestonesList { this.tier = tier }
        }
      }
    }
  }
}
