package app.redux.state.resource

import app.api.common.Resource
import app.api.common.ResourceName
import kotlinx.coroutines.Job

data class ResourceCache<P, N : ResourceName, R : Resource<N>>(
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

  fun updateCollection(parent: P, updater: (collection: List<N>?) -> List<N>?) =
    updater(collections[parent])
      ?.let { collections + (parent to it) }
      ?: (collections - parent)
}

abstract class ResourceCacheUpdater<P, N : ResourceName, R : Resource<N>> {
  abstract fun ResourceCache<P, N, R>.update(): ResourceCache<P, N, R>
}

class InsertResource<P, N : ResourceName, R : Resource<N>>(parent: P, resource: R) :
  InsertAllResources<P, N, R>(parent, listOf(resource))

open class InsertAllResources<P, N : ResourceName, R : Resource<N>>(
  private val parent: P,
  private val resources: Iterable<R>,
) : ResourceCacheUpdater<P, N, R>() {
  override fun ResourceCache<P, N, R>.update(): ResourceCache<P, N, R> {
    val cache = cache + resources.associateBy { it.name }
    val collections = updateCollection(parent) {
      it?.let { it + resources.map { r -> r.name } }?.distinct()
    }
    return copy(
      cache = cache,
      collections = collections,
    )
  }
}

class DeleteResource<P, N : ResourceName, R : Resource<N>>(
  private val parent: P,
  private val name: N,
) : ResourceCacheUpdater<P, N, R>() {
  override fun ResourceCache<P, N, R>.update() = copy(
    cache = cache - name,
    requests = requests - name,
    collections = updateCollection(parent) { it?.let { it - name } }
  )
}

class InsertCollection<P, N : ResourceName, R : Resource<N>>(
  private val parent: P,
  private val collection: List<R>,
) : ResourceCacheUpdater<P, N, R>() {
  override fun ResourceCache<P, N, R>.update() =
    with(InsertAllResources(parent, collection)) {
      update().copy(collections = collections + (parent to collection.map { it.name }))
    }
}

