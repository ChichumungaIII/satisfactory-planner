package app.game.logic

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import kotlinx.serialization.Serializable

@Serializable
data class Progress(
  val phase: Phase,
  val milestones: List<Milestone>,
  val research: List<Research>,
) {
  companion object {
    fun create(
      phase: Phase = Phase.GAME_START,
      milestones: List<Milestone> = listOf(),
      research: List<Research> = listOf(),
    ) = Progress(phase, milestones, research)
  }
}
