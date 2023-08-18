package app.data.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.data.common.ResourceCache
import react.createContext

val SaveCache = createContext<ResourceCache<SaveName, Save>>()
val SaveCacheProvider = ResourceCache.createProvider("SaveCacheProvider", SaveCache)
