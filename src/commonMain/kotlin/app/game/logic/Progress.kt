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
    private val EMPTY = Progress(
      phase = Phase.GAME_START,
      milestones = listOf(),
      research = listOf(),
    )

    fun empty() = EMPTY
  }
}
