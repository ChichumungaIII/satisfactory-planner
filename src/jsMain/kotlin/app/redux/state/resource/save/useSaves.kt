package app.redux.state.resource.save

import app.api.save.v1.ListSavesRequest
import app.api.save.v1.Save
import app.api.save.v1.getSaveService
import app.redux.RThunk
import app.redux.state.AppState
import app.redux.state.resource.ResourceState
import app.redux.state.resource.ResourceState.Empty
import app.redux.state.resource.ResourceState.Loaded.Stable
import app.redux.state.resource.ResourceState.Loading
import app.redux.useAppSelector
import app.util.launchMain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import redux.RAction
import redux.WrapperAction
import kotlin.time.Duration.Companion.seconds

data object LoadSaves : RThunk {
  override fun invoke(dispatch: (RAction) -> WrapperAction, getState: () -> AppState) {
    val request = launchMain {
      val response = getSaveService().listSaves(ListSavesRequest())
      delay(2.5.seconds)
      if (!isActive) return@launchMain
      dispatch(RegisterSaves(response.saves))
    }
    dispatch(RegisterRequest(request))
  }
}

private data class RegisterRequest(val request: Job) : SaveCacheAction() {
  override fun SaveCache.update() = copy(collectionRequest = request)
}

private data class RegisterSaves(
  val saves: List<Save>,
) : SaveCacheAction() {
  override fun SaveCache.update(): SaveCache {
    val cache = saves.associateBy { it.name }
    val resources = saves.map { it.name }
    return copy(
      cache = cache,
      collection = resources,
      collectionRequest = null,
    )
  }
}

fun useSaves(): ResourceState<Unit, List<Save>> {
  val saves = useAppSelector({ it.saveCache.getCollection() }) { current, new -> current == new }
  val request = useAppSelector { it.saveCache.collectionRequest }
  return if (saves == null)
    (if (request == null) Empty(Unit) else Loading(request))
  else
    Stable(saves)
}
