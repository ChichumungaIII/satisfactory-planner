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

open class LoadCollection<P, N : ResourceName, R : Resource<N>>(
  val loadCollection: suspend () -> List<R>,
  val registerCollection: (collection: List<R>) -> AppAction,
  val registerCollectionRequest: (request: Job) -> AppAction,
) : RThunk {
  override fun invoke(dispatch: (RAction) -> WrapperAction, getState: () -> AppState) {
    val request = launchMain {
      val collection = loadCollection()
      delay(2.5.seconds)
      if (!isActive) return@launchMain
      dispatch(registerCollection(collection))
    }
    dispatch(registerCollectionRequest(request))
  }
}

class RegisterCollection<P, N : ResourceName, R : Resource<N>>(
  private val parent: P,
  private val collection: List<R>,
) : ResourceCacheUpdater<P, N, R>() {
  override fun ResourceCache<P, N, R>.update() =
    with(InsertCollection(parent, collection)) {
      update().copy(collectionRequests = collectionRequests - parent)
    }
}

class RegisterCollectionRequest<P, N : ResourceName, R : Resource<N>>(
  private val parent: P,
  private val request: Job,
) : ResourceCacheUpdater<P, N, R>() {
  override fun ResourceCache<P, N, R>.update() =
    copy(collectionRequests = collectionRequests + (parent to request))
}

fun <P, N : ResourceName, R : Resource<N>> useCollection(
  parent: P,
  cacheSelector: (AppState) -> ResourceCache<P, N, R>,
): ResourceState<List<R>> {
  val collection = useAppSelector { state -> cacheSelector(state).getCollection(parent) }
  val request = useAppSelector { state -> cacheSelector(state).collectionRequests[parent] }
  return ResourceState.Companion.create(collection, request)
}
