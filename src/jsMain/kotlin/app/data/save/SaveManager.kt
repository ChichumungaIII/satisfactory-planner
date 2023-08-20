package app.data.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.SaveServiceJs
import app.api.save.v1.UpdateSaveRequest
import app.data.common.ResourceManager
import react.createContext

val SaveManager = createContext<ResourceManager<SaveName, Save>>()
val SaveManagerProvider = ResourceManager.createProvider(
  "SaveManagerProvider",
  SaveManager,
  SaveServiceJs.Context,
  { save -> updateSave(UpdateSaveRequest(save, listOf("displayName", "progress"))) },
  SaveCache,
)
