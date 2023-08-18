package app.api.save.v1

import app.api.common.Resource
import app.game.data.Milestone
import app.game.logic.Progress
import kotlinx.serialization.Serializable

@Serializable
data class Save(
  override val name: SaveName,
  val displayName: String,
  val progress: Progress,
) : Resource<SaveName> {
  companion object {
    private val EMPTY = Save(
      name = SaveName(0),
      displayName = "",
      progress = Progress.create(),
    )

    fun empty() = EMPTY
  }

  fun setMilestones(milestones: List<Milestone>) = copy(progress = progress.copy(milestones = milestones))
}
