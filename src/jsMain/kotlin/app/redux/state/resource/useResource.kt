package app.redux.state.resource

import app.api.common.Resource
import app.api.common.ResourceName
import app.redux.AppAction
import app.redux.RThunk
import app.redux.state.AppState
import app.redux.useAppSelector
import app.util.launchMain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import redux.RAction
import redux.WrapperAction
import kotlin.time.Duration.Companion.seconds

open class LoadResource<N : ResourceName, R : Resource<N>>(
  private val name: N,
  private val loader: suspend () -> R,
  private val registerResource: (resource: R) -> AppAction,
  private val registerResourceRequest: (request: Job) -> AppAction,
) : RThunk {
  override fun invoke(dispatch: (RAction) -> WrapperAction, getState: () -> AppState) {
    val request = launchMain {
      val resource = loader()
      delay(1.5.seconds)
      if (!isActive) return@launchMain
      dispatch(registerResource(resource))
    }
    dispatch(registerResourceRequest(request))
  }
}

class RegisterResource<P, N : ResourceName, R : Resource<N>>(
  private val parent: P,
  private val resource: R,
) : ResourceCacheUpdater<P, N, R>() {
  override fun ResourceCache<P, N, R>.update() =
    with(InsertResource(parent, resource)) {
      update().copy(requests = requests - resource.name)
    }
}

class RegisterResourceRequest<P, N : ResourceName, R : Resource<N>>(
  private val name: N,
  private val request: Job,
) : ResourceCacheUpdater<P, N, R>() {
  override fun ResourceCache<P, N, R>.update() = copy(requests = requests + (name to request))
}

fun <N : ResourceName, R : Resource<N>> useResource(
  name: N,
  cacheSelector: (AppState) -> ResourceCache<*, N, R>
): ResourceState<R> {
  val resource = useAppSelector { state -> cacheSelector(state)[name] }
  val request = useAppSelector { state -> cacheSelector(state).getRequest(name) }
  return ResourceState.create(resource, request)
}
