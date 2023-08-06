package app.game.logic

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import app.game.data.Tier

data class Progress(
  val phases: List<Phase>,
  val tiers: List<Tier>,
  val milestones: List<Milestone>,
  val research: List<Research>,
)
