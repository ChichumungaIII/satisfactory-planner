package app.data.plan

import app.api.common.Resource
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.save.v1.SaveName
import app.data.common.ResourceCache
import react.createContext

val PlanCache = createContext<ResourceCache<PlanName, Plan>>()
val PlanCacheProvider = ResourceCache.createProvider("PlanCacheProvider", PlanCache)

data class PlanCollection(
  override val name: SaveName,
  val plans: List<PlanName>,
) : Resource<SaveName> {
  fun add(plan: PlanName) = copy(plans = plans + plan)

  fun remove(plan: PlanName) = copy(plans = plans - plan)
}

val PlanCollectionCache = createContext<ResourceCache<SaveName, PlanCollection>>()
val PlanCollectionCacheProvider =
  ResourceCache.createProvider("PlanCollectionCacheProvider", PlanCollectionCache)
