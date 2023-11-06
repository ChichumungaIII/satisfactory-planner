package app.redux.state.resource.save

import app.api.save.v1.GetSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.UpdateSaveRequest
import app.api.save.v1.getSaveService
import app.redux.state.resource.LoadResource
import app.redux.state.resource.RegisterResource
import app.redux.state.resource.RegisterResourceRequest
import app.redux.state.resource.SaveResource
import app.redux.state.resource.useResource
import kotlinx.coroutines.Job
import kotlin.time.Duration.Companion.seconds

class LoadSave(name: SaveName) :
  LoadResource<SaveName, Save>(
    name,
    { state -> state.saveCache },
    { getSaveService().getSave(GetSaveRequest(name)) },
    { save -> RegisterSave(save) },
    { request -> RegisterSaveRequest(name, request) }
  )

class SaveSave(save: Save) :
  SaveResource<SaveName, Save>(
    save,
    { state -> state.saveCache },
    3.seconds,
    { getSaveService().updateSave(UpdateSaveRequest(save, listOf())) },
    { RegisterSave(it) },
    { request -> RegisterSaveRequest(save.name, request) })

private class RegisterSave(save: Save) :
  SaveCacheAction(RegisterResource(Unit, save))

private class RegisterSaveRequest(
  name: SaveName,
  request: Job,
) : SaveCacheAction(RegisterResourceRequest(name, request))

fun useSave(name: SaveName) = useResource(name) { it.saveCache }
