package app.redux.state.resource.save

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.redux.AppAction
import app.redux.state.AppState
import app.redux.state.resource.DeleteResource
import app.redux.state.resource.InsertResource
import app.redux.state.resource.ResourceCache
import app.redux.state.resource.ResourceCacheUpdater

typealias SaveCache = ResourceCache<Unit, SaveName, Save>
typealias SaveCacheUpdater = ResourceCacheUpdater<Unit, SaveName, Save>

abstract class SaveCacheAction(
  private val updater: SaveCacheUpdater,
) : AppAction() {
  override fun AppState.update() = copy(saveCache = with(updater) { saveCache.update() })
}

class InsertSave(val save: Save) : SaveCacheAction(InsertResource(Unit, save))
class DeleteSave(val save: SaveName) : SaveCacheAction(DeleteResource(Unit, save))
