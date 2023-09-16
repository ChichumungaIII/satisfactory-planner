package app.redux.state.resource.plan

import app.api.plan.v1.GetPlanRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.getPlanService
import app.redux.state.resource.LoadResource
import app.redux.state.resource.RegisterResource
import app.redux.state.resource.RegisterResourceRequest
import app.redux.state.resource.useResource
import kotlinx.coroutines.Job

class LoadPlan(name: PlanName) :
  LoadResource<PlanName, Plan>(
    name,
    { getPlanService().getPlan(GetPlanRequest(name)) },
    { plan -> RegisterPlan(plan) },
    { request -> RegisterPlanRequest(name, request) }
  )

private class RegisterPlan(plan: Plan) :
  PlanCacheAction(RegisterResource(plan.parent, plan))

private class RegisterPlanRequest(
  name: PlanName,
  request: Job,
) : PlanCacheAction(RegisterResourceRequest(name, request))

fun usePlan(name: PlanName) = useResource(name) { it.planCache }
