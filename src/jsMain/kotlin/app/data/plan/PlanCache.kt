package app.data.plan

import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.save.v1.SaveName
import app.data.common.ResourceCache

val PlanCache = ResourceCache(Plan::name)

data class PlanCollection(val save: SaveName, val plans: List<PlanName>) {
  fun add(plan: PlanName) = copy(plans = plans + plan)

  fun remove(plan: PlanName) = copy(plans = plans - plan)
}

val PlanCollectionCache = ResourceCache(PlanCollection::save)
