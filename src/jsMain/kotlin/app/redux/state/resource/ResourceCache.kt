package app.redux.state.resource

import app.api.common.ResourceName
import kotlinx.coroutines.Job

data class ResourceCache<P, N : ResourceName, R>(
  val cache: Map<N, R> = mapOf(),
  val requests: Map<N, Job> = mapOf(),
  val collections: Map<P, List<N>> = mapOf(),
  val collectionRequests: Map<P, Job> = mapOf(),
) {
  fun contains(name: N) = cache.contains(name)
  operator fun get(name: N) = cache[name]

  fun hasRequest(name: N) = requests.contains(name)
  fun getRequest(name: N) = requests[name]

  fun getCollection(parent: P) = collections[parent]?.map {
    get(it) ?: throw Error("Resource [${it.getResourceName()}] was not loaded.")
  }
}
