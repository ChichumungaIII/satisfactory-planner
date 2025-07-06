package app.api.save.v1

import app.api.common.Resource
import kotlinx.serialization.Serializable

@Serializable
data class Save(
  override val name: SaveName,
  val displayName: String,
) : Resource<SaveName> {
  companion object {
    private val EMPTY = Save(
      name = SaveName(0),
      displayName = "",
    )

    fun empty() = EMPTY
  }
}
