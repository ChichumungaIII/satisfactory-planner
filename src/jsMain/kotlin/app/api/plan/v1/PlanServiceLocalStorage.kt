package app.api.plan.v1

import app.api.save.v1.SaveName
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object PlanServiceLocalStorage : PlanService {
  private val collections = mutableMapOf<SaveName, Map<PlanName, Plan>>()

  override suspend fun createPlan(request: CreatePlanRequest): Plan {
    val save = request.parent

    val plan = request.plan
    check(plan.parent == save) {
      "Plan [${plan.name.getResourceName()}] must have parent Save [${save.getResourceName()}]."
    }

    val existing = getCollection(save)
    check(!existing.containsKey(plan.name)) { "Plan [${plan.name.getResourceName()}] already exists." }

    setCollection(save, existing + (plan.name to plan))
    return plan
  }

  override suspend fun getPlan(request: GetPlanRequest) =
    getCollection(request.name.save)[request.name]
      ?: throw Error("Plan [${request.name.getResourceName()}] does not exist.")

  override suspend fun updatePlan(request: UpdatePlanRequest): Plan {
    val existing = getPlan(GetPlanRequest(request.plan.name))

    val plan = existing.copy(
      displayName = request.plan.displayName,
      partition = request.plan.partition,
    )

    plan.parent.also { save -> setCollection(save, getCollection(save) + (plan.name to plan)) }
    return plan
  }

  override suspend fun deletePlan(request: DeletePlanRequest) {
    getPlan(GetPlanRequest(request.name))
    request.name.save.also { save -> setCollection(save, getCollection(save) - request.name) }
  }

  override suspend fun listPlans(request: ListPlansRequest): ListPlansResponse {
    val afterToken = getCollection(request.parent).values.toList()
      .dropWhile { plan -> request.pageToken?.let { it != plan.name.getResourceName() } ?: false }
    val pageSize = request.pageSize ?: 100
    return ListPlansResponse(
      afterToken.take(pageSize),
      afterToken.drop(pageSize).firstOrNull()?.name?.getResourceName(),
    )
  }

  private fun getCollection(parent: SaveName) = collections.getOrPut(parent) {
    window.localStorage.getItem(parent.collection())?.let { Json.decodeFromString(it) } ?: mapOf()
  }

  private fun setCollection(parent: SaveName, collection: Map<PlanName, Plan>) {
    collections[parent] = collection
    window.localStorage.setItem(parent.collection(), Json.encodeToString(collection))
  }

  fun deleteCollection(parent: SaveName) = window.localStorage.removeItem(parent.collection())

  private fun SaveName.collection() = "//satisfactory.chichumunga.com/v1/${getResourceName()}/plans"
}
