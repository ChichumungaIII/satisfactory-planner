package app.game.logic

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import kotlinx.serialization.Serializable

@Serializable
data class Progress(
  val phase: Phase = Phase.GAME_START,
  val milestones: List<Milestone> = listOf(),
  val research: List<Research> = listOf(),
)
