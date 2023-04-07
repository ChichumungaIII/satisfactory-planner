package app.v2.plans.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Plan(
    val id: ULong,
    val title: String,
)
