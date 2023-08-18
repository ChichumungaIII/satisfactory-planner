package app.data.plan

import app.api.save.v1.SaveName
import app.data.common.ResourceCache
import react.createContext

val PlanCollectionCache = createContext<ResourceCache<SaveName, PlanCollection>>()
val PlanCollectionCacheProvider =
  ResourceCache.createProvider("PlanCollectionCacheProvider", PlanCollectionCache)
