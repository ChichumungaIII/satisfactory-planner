package app.redux.state.resource.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.redux.AppAction
import app.redux.state.AppState
import app.redux.state.resource.ResourceCache

typealias SaveCache = ResourceCache<SaveName, Save>

abstract class SaveCacheAction : AppAction() {
  override fun AppState.update() = copy(saveCache = saveCache.update())
  abstract fun SaveCache.update(): SaveCache
}

data class InsertSave(val save: Save) : SaveCacheAction() {
  override fun SaveCache.update(): SaveCache {
    val cache = cache + (save.name to save)
    val resources = resources?.let { it + save.name }
    return copy(cache = cache, resources = resources)
  }
}

data class DeleteSave(val save: SaveName) : SaveCacheAction() {
  override fun SaveCache.update() = copy(
    cache = cache - save,
    requests = requests - save,
    resources = resources?.let { it - save },
  )
}
