package app.routes.createsave.steps.milestones

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
        Tier.entries.filter { it.requirement.test(newSave.progress) }.forEach { tier ->
          TierMilestonesList { this.tier = tier }
        }
      }
    }
  }
}
