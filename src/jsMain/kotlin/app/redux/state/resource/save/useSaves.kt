package app.redux.state.resource.save

import app.api.save.v1.ListSavesRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.getSaveService
import app.redux.state.resource.LoadCollection
import app.redux.state.resource.RegisterCollection
import app.redux.state.resource.RegisterCollectionRequest
import app.redux.state.resource.useCollection
import kotlinx.coroutines.Job

data object LoadSaves :
  LoadCollection<Unit, SaveName, Save>(
    { getSaveService().listSaves(ListSavesRequest()).saves },
    { saves -> RegisterSaves(saves) },
    { request -> RegisterSavesCollectionRequest(request) }
  )

private data class RegisterSavesCollectionRequest(val request: Job) :
  SaveCacheAction(RegisterCollectionRequest(Unit, request))

private data class RegisterSaves(val saves: List<Save>) :
  SaveCacheAction(RegisterCollection(Unit, saves))

fun useSaves() = useCollection(Unit) { state -> state.saveCache }
