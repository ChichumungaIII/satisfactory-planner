package app.redux.state.resource.plan

import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.save.v1.SaveName
import app.redux.AppAction
import app.redux.state.AppState
import app.redux.state.resource.DeleteResource
import app.redux.state.resource.InsertResource
import app.redux.state.resource.ResourceCache
import app.redux.state.resource.ResourceCacheUpdater

typealias PlanCache = ResourceCache<SaveName, PlanName, Plan>
typealias PlanCacheUpdater = ResourceCacheUpdater<SaveName, PlanName, Plan>

abstract class PlanCacheAction(
  private val updater: PlanCacheUpdater,
) : AppAction() {
  override fun AppState.update() = copy(planCache = with(updater) { planCache.update() })
}

class InsertPlan(val plan: Plan) : PlanCacheAction(InsertResource(plan.parent, plan))
class DeletePlan(val plan: PlanName) : PlanCacheAction(DeleteResource(plan.save, plan))
