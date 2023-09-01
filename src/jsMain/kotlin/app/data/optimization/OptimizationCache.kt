package app.data.optimization

import app.data.common.ResourceCache
import react.createContext

val OptimizationCache = createContext<ResourceCache<OptimizationName, Optimization>>()
val OptimizationCacheProvider = ResourceCache.createProvider("OptimizationCacheProvider", OptimizationCache)
