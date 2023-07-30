package app.api.plan.v1

import app.api.save.v1.SaveName
import kotlinx.serialization.Serializable

interface PlanService {
  suspend fun createPlan(request: CreatePlanRequest): Plan
  suspend fun getPlan(request: GetPlanRequest): Plan
  suspend fun updatePlan(request: UpdatePlanRequest): Plan
  suspend fun deletePlan(request: DeletePlanRequest)
  suspend fun listPlans(request: ListPlansRequest): ListPlansResponse
}

@Serializable
data class CreatePlanRequest(
  val parent: SaveName,
  val plan: Plan,
)

@Serializable
data class GetPlanRequest(
  val name: PlanName,
)

@Serializable
data class UpdatePlanRequest(
  val plan: Plan,
  val updateMask: List<String>,
)

@Serializable
data class DeletePlanRequest(
  val name: PlanName,
)

@Serializable
data class ListPlansRequest(
  val parent: SaveName,
  val pageSize: Int? = null,
  val pageToken: String? = null,
)

@Serializable
data class ListPlansResponse(
  val plans: List<Plan>,
  val nextPageToken: String? = null,
)

