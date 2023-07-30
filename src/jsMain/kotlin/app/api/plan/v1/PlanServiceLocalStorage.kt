package app.api.plan.v1

import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object PlanServiceLocalStorage : PlanService {
  private const val PLANS_STORAGE = "//satisfactory.chichumunga.com/v1/plans"

  private var plans: Map<PlanName, Plan>
    get() =
      window.localStorage.getItem(PLANS_STORAGE)
        ?.let { Json.decodeFromString(it) }
        ?: mapOf()
    set(value) =
      window.localStorage.setItem(PLANS_STORAGE, Json.encodeToString(value))

  override suspend fun createPlan(request: CreatePlanRequest): Plan {
    val plan = request.plan
    check(plan.parent == request.parent) {
      "Plan [${plan.name.getResourceName()}] must have parent Save [${request.parent.getResourceName()}]."
    }
    check(!plans.containsKey(plan.name)) { "Plan [${plan.name.getResourceName()}] already exists." }

    plans = plans + (plan.name to plan)
    return plan
  }

  override suspend fun getPlan(request: GetPlanRequest) =
    plans[request.name] ?: throw Error("Plan [${request.name.getResourceName()}] does not exist.")

  override suspend fun updatePlan(request: UpdatePlanRequest): Plan {
    val existing = getPlan(GetPlanRequest(request.plan.name))

    val choose = { field: String ->
      request.plan.takeIf { request.updateMask.contains(field) } ?: existing
    }
    val plan = existing.copy(
      displayName = choose("displayName").displayName,
      inputs = choose("inputs").inputs,
      products = choose("products").products,
      byproducts = choose("byproducts").byproducts,
      targets = choose("targets").targets,
    )

    plans = plans + (plan.name to plan)
    return plan
  }

  override suspend fun deletePlan(request: DeletePlanRequest) {
    getPlan(GetPlanRequest(request.name))
    plans = plans - request.name
  }

  override suspend fun listPlans(request: ListPlansRequest): ListPlansResponse {
    val afterToken = plans.values.toList()
      .filter { it.parent == request.parent }
      .dropWhile { plan -> request.pageToken?.let { it != plan.name.getResourceName() } ?: false }
    val pageSize = request.pageSize ?: 100
    return ListPlansResponse(
      afterToken.take(pageSize),
      afterToken.drop(pageSize).firstOrNull()?.name?.getResourceName(),
    )
  }
}
