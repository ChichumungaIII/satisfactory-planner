package app.data.plan

import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.PlanServiceJs
import app.api.plan.v1.UpdatePlanRequest
import app.data.common.ResourceManager
import react.createContext

val PlanManager = createContext<ResourceManager<PlanName, Plan>>()
val PlanManagerProvider = ResourceManager.createProvider(
  "PlanManagerProvider",
  PlanManager,
  PlanServiceJs.Context,
  { plan -> updatePlan(UpdatePlanRequest(plan, listOf("*"))) },
  PlanCache,
)
