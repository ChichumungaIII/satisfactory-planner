package app.api.save.v1

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import app.game.data.Tier
import kotlinx.serialization.Serializable

@Serializable
data class Save(
  val name: SaveName,
  val displayName: String,
  val phases: List<Phase>,
  val tiers: List<Tier>,
  val milestones: List<Milestone>,
  val research: List<Research>,
)
