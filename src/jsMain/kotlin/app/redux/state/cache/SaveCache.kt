package app.redux.state.cache

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.redux.AppAction
import app.redux.state.AppState

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
