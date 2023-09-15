package app.redux.state

import app.api.save.v1.Save
import app.api.save.v1.SaveName
import app.redux.state.resource.ResourceCache

data class AppState(
  val saveCache: ResourceCache<SaveName, Save> = ResourceCache(),
)
