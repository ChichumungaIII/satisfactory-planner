package app.redux.state

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.redux.state.cache.AppCache

data class AppState(
  val saveCache: AppCache<SaveName, Save> = AppCache(),
)
