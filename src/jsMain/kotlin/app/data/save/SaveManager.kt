package app.data.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.UpdateSaveRequest
import app.api.save.v1.getSaveService
import app.data.common.ResourceManager
import react.createContext

val SaveManager = createContext<ResourceManager<SaveName, Save>>()
val SaveManagerProvider = ResourceManager.createProvider(
  "SaveManagerProvider",
  SaveManager,
  { save -> getSaveService().updateSave(UpdateSaveRequest(save, listOf("displayName", "progress"))) },
  SaveCache,
)
