package app.redux.state.cache

import app.api.common.Resource
import app.api.common.ResourceName
import kotlinx.coroutines.Deferred

data class AppCache<N : ResourceName, R : Resource<N>>(
  val cache: Map<N, R> = mapOf(),
  val requests: Map<N, Deferred<R>> = mapOf(),
) {
  fun contains(name: N) = cache.contains(name)
  operator fun get(name: N) = cache[name]

  fun getRequest(name: N) = requests[name]
}
