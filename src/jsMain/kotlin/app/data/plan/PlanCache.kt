package app.data.plan

import app.api.common.Resource
import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.api.save.v1.SaveName
import app.data.common.ResourceCache
import app.data.common.ResourceCacheV2
import react.createContext

val PlanCacheContext = createContext<ResourceCacheV2<PlanName, Plan>>()
val PlanCacheProvider = ResourceCacheV2.createProvider("PlanCacheProvider", PlanCacheContext)

data class PlanCollection(
  override val name: SaveName,
  val plans: List<PlanName>,
) : Resource<SaveName> {
  fun add(plan: PlanName) = copy(plans = plans + plan)

  fun remove(plan: PlanName) = copy(plans = plans - plan)
}

val PlanCollectionCache = ResourceCache<SaveName, PlanCollection>()
