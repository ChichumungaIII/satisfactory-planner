package app.redux.state.resource.plan

import app.api.plan.v1.ListPlansRequest
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.plan.v1.getPlanService
import app.api.save.v1.SaveName
import app.redux.state.resource.LoadCollection
import app.redux.state.resource.RegisterCollection
import app.redux.state.resource.RegisterCollectionRequest
import app.redux.state.resource.useCollection
import kotlinx.coroutines.Job

class LoadPlans(private val parent: SaveName) :
  LoadCollection<SaveName, PlanName, Plan>(
    { getPlanService().listPlans(ListPlansRequest(parent = parent)).plans },
    { plans -> RegisterPlans(parent, plans) },
    { request -> RegisterPlansCollectionRequest(parent, request) }
  )

private data class RegisterPlansCollectionRequest(
  val parent: SaveName,
  val request: Job,
) : PlanCacheAction(RegisterCollectionRequest(parent, request))

private data class RegisterPlans(
  val parent: SaveName,
  val plans: List<Plan>,
) : PlanCacheAction(RegisterCollection(parent, plans))

fun usePlans(save: SaveName) = useCollection(save) { state -> state.planCache }
