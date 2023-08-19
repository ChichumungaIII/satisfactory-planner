package app.data.common

import app.api.common.Resource
import app.api.common.ResourceName
import react.Context
import react.FC
import react.PropsWithChildren
import react.useReducer

class ResourceCache<N : ResourceName, R : Resource<N>> private constructor(
  private val cache: Map<N, R>,
  private val updateCache: (ResourceCacheAction<N, R>) -> Unit,
) {
  companion object {
    private sealed interface ResourceCacheAction<N : ResourceName, R : Resource<N>>

    private data class Insert<N : ResourceName, R : Resource<N>>(
      val resource: R,
    ) : ResourceCacheAction<N, R>

    private data class InsertAll<N : ResourceName, R : Resource<N>>(
      val resources: Iterable<R>,
    ) : ResourceCacheAction<N, R>

    fun <N : ResourceName, R : Resource<N>> createProvider(
      displayName: String,
      context: Context<ResourceCache<N, R>?>,
    ) = FC<PropsWithChildren>(displayName) {
      val (cache, updateCache) = useReducer({ cache: MutableMap<N, R>, action: ResourceCacheAction<N, R> ->
        when (action) {
          is Insert<N, R> -> cache[action.resource.name] = action.resource
          is InsertAll<N, R> -> cache.putAll(action.resources.associateBy { it.name })
        }
        cache
      }, initialState = mutableMapOf());
      context(ResourceCache(cache, updateCache)) { +it.children }
    }
  }

  fun contains(name: N) = cache.contains(name)
  operator fun get(name: N) = cache[name]
  fun insert(resource: R) = updateCache(Insert(resource))
  fun insertAll(resources: Iterable<R>) = updateCache(InsertAll(resources))
}
