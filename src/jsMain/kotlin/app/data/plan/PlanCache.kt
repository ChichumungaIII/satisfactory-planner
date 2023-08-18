package app.data.plan

import app.api.plan.v1.Plan
import app.api.plan.v1.PlanName
import app.data.common.ResourceCache
import react.createContext

val PlanCache = createContext<ResourceCache<PlanName, Plan>>()
val PlanCacheProvider = ResourceCache.createProvider("PlanCacheProvider", PlanCache)
