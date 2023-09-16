package app.redux.state.resource.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.redux.AppAction
import app.redux.state.AppState
import app.redux.state.resource.ResourceCache

typealias SaveCache = ResourceCache<Unit, SaveName, Save>

private fun SaveCache.updateCollection(updater: (collection: List<SaveName>?) -> List<SaveName>?) =
  collections[Unit]?.let(updater)?.let { mapOf(Unit to it) } ?: mapOf()

abstract class SaveCacheAction : AppAction() {
  override fun AppState.update() = copy(saveCache = saveCache.update())
  abstract fun SaveCache.update(): SaveCache
}

data class InsertSave(val save: Save) : SaveCacheAction() {
  override fun SaveCache.update(): SaveCache {
    val cache = cache + (save.name to save)
    val collections = updateCollection { it?.let { it + save.name } }
    return copy(cache = cache, collections = collections)
  }
}

data class DeleteSave(val save: SaveName) : SaveCacheAction() {
  override fun SaveCache.update() = copy(
    cache = cache - save,
    requests = requests - save,
    collections = updateCollection { it?.let { it - save } },
  )
}
