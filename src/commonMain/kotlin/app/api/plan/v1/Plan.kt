package app.api.plan.v1

import kotlinx.serialization.Serializable

@Serializable
data class Plan(
  val name: PlanName,
)
