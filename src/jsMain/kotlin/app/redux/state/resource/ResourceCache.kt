package app.redux.state.resource

import app.api.common.ResourceName
import kotlinx.coroutines.Job

data class ResourceCache<N : ResourceName, R>(
  val cache: Map<N, R> = mapOf(),
  val requests: Map<N, Job> = mapOf(),
  val resources: List<N>? = null,
  val resourcesRequest: Job? = null,
) {
  fun contains(name: N) = cache.contains(name)
  operator fun get(name: N) = cache[name]

  fun hasRequest(name: N) = requests.contains(name)
  fun getRequest(name: N) = requests[name]

  fun getResources() = resources?.map { get(it) ?: throw Error("Resource [${it.getResourceName()}] was not loaded.") }
}
