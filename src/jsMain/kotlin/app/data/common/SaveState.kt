package app.data.common

import kotlinx.datetime.Instant

data class SaveState(
  val lastSaved: Instant? = null,
  val saving: Boolean = false,
)
