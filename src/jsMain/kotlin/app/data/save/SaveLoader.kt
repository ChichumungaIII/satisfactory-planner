package app.data.save

import app.api.save.v1.GetSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.data.common.ResourceLoader
import react.createContext

val SaveLoader = createContext<ResourceLoader<SaveName, Save>>()
val SaveLoaderProvider = ResourceLoader.createProvider(
  "SaveLoaderProvider",
  SaveLoader,
  SaveServiceJs.Context,
  { name -> getSave(GetSaveRequest(name)) },
  SaveCache,
)
