package app.game.logic

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research

data class Progress(
  val phases: List<Phase>,
  val milestones: List<Milestone>,
  val research: List<Research>,
)
