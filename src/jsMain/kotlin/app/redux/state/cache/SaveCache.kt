package app.redux.state.cache

import app.api.save.v1.GetSaveRequest
import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.api.save.v1.getSaveService
import app.redux.AppAction
import app.redux.state.AppState
import app.redux.state.cache.ResourceState.Loaded
import app.redux.state.cache.ResourceState.Loading
import app.redux.state.cache.ResourceState.Updating
import app.redux.useAppDispatch
import app.redux.useAppSelector
import app.util.launchMain
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.seconds

typealias SaveCache = AppCache<SaveName, Save>

abstract class SaveCacheAction : AppAction() {
  override fun AppState.update() = copy(saveCache = saveCache.update())
  abstract fun SaveCache.update(): SaveCache
}

data class InsertSave(val save: Save) : SaveCacheAction() {
  override fun SaveCache.update() = copy(cache = cache + (save.name to save))
}

data class InsertSaves(val saves: Iterable<Save>) : SaveCacheAction() {
  override fun SaveCache.update() = copy(cache = cache + saves.associateBy { it.name })
}

private data class LoadSave(val save: Save) : SaveCacheAction() {
  override fun SaveCache.update(): SaveCache {
    val cache = cache + (save.name to save)
    val requests = requests - save.name
    return copy(cache = cache, requests = requests)
  }
}

fun useSave(name: SaveName): ResourceState<SaveName, Save> {
  val dispatch = useAppDispatch()
  val save = useAppSelector({ it.saveCache[name] }) { current, new -> current == new }
  val request = useAppSelector { it.saveCache.getRequest(name) }

  if (save == null) {
    if (request != null) return Loading(name, request)

    val load = launchMain {
      val loaded = getSaveService().getSave(GetSaveRequest(name))
      delay(1.5.seconds)
      if (!isActive) return@launchMain
      dispatch(LoadSave(loaded))
    }
    return Loading(name, load)
  } else {
    return request?.let { Updating(save, request) } ?: Loaded(save)
  }
}
