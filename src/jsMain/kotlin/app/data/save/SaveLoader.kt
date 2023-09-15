package app.data.save

import app.api.save.v1.GetSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.getSaveService
import app.data.common.ResourceLoader
import app.redux.state.AppState
import app.redux.state.cache.InsertSave
import react.createContext

val SaveLoader = createContext<ResourceLoader<SaveName, Save>>()
val SaveLoaderProvider = ResourceLoader.createProvider(
  "SaveLoaderProvider",
  SaveLoader,
  { name -> getSaveService().getSave(GetSaveRequest(name)) },
  AppState::saveCache,
  { save -> InsertSave(save) },
)
