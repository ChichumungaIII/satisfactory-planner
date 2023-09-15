package app.redux.state.cache

import app.api.common.Resource
import app.api.common.ResourceName

data class AppCache<N : ResourceName, R : Resource<N>>(
  val cache: Map<N, R> = mapOf(),
) {
  fun contains(name: N) = cache.contains(name)
  operator fun get(name: N) = cache[name]
}
