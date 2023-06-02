package app.v2.data.factory

import kotlinx.serialization.Serializable

@Serializable
data class Factory(
  val id: ULong,
  val displayName: String,
  val tree: FactoryTree = FactoryTree(),
) {
}
