package app.v2.data

import kotlinx.serialization.Serializable

@Serializable
data class Factory(
    val id: ULong,
    val displayName: String,
) {
}
