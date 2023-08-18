package app.data.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.data.common.ResourceCacheV2
import react.createContext

val SaveCacheContext = createContext<ResourceCacheV2<SaveName, Save>>()
val SaveCacheProvider = ResourceCacheV2.createProvider("SaveCacheProvider", SaveCacheContext)
