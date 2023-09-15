package app.redux.state.resource.save

import app.api.save.v1.GetSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.getSaveService
import app.redux.RThunk
import app.redux.state.AppState
import app.redux.state.resource.ResourceState
import app.redux.state.resource.ResourceState.Empty
import app.redux.state.resource.ResourceState.Loaded.Stable
import app.redux.state.resource.ResourceState.Loaded.Updating
import app.redux.state.resource.ResourceState.Loading
import app.redux.useAppSelector
import app.util.launchMain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import redux.RAction
import redux.WrapperAction
import kotlin.time.Duration.Companion.seconds

data class LoadSave(val name: SaveName) : RThunk {
  override fun invoke(dispatch: (RAction) -> WrapperAction, getState: () -> AppState) {
    val request = launchMain {
      val loaded = getSaveService().getSave(GetSaveRequest(name))
      delay(1.5.seconds)
      if (!isActive) return@launchMain
      dispatch(RegisterSave(loaded))
    }
    dispatch(RegisterSaveRequest(name, request))
  }
}

private data class RegisterSaveRequest(val name: SaveName, val request: Job) : SaveCacheAction() {
  override fun SaveCache.update() = copy(requests = requests + (name to request))
}

private data class RegisterSave(val save: Save) : SaveCacheAction() {
  override fun SaveCache.update(): SaveCache {
    val cache = cache + (save.name to save)
    val requests = requests - save.name
    return copy(cache = cache, requests = requests)
  }
}

fun useSave(name: SaveName): ResourceState<SaveName, Save> {
  val save = useAppSelector({ it.saveCache[name] }) { current, new -> current == new }
  val request = useAppSelector { it.saveCache.getRequest(name) }
  return if (save == null)
    (if (request == null) Empty(name) else Loading(request))
  else
    (if (request == null) Stable(save) else Updating(save, request))
}
