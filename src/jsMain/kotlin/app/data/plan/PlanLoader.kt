package app.data.plan

import app.api.plan.v1.GetPlanRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.PlanServiceJs
import app.data.common.ResourceLoader
import react.createContext

val PlanLoader = createContext<ResourceLoader<PlanName, Plan>>()
val PlanLoaderProvider = ResourceLoader.createProvider(
  "PlanLoaderProvider",
  PlanLoader,
  PlanServiceJs.Context,
  { name -> getPlan(GetPlanRequest(name)) },
  PlanCache,
)
