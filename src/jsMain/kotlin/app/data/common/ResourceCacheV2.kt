package app.data.common

import app.api.common.Resource
import react.Context
import react.FC
import react.PropsWithChildren
import react.useReducer

class ResourceCacheV2<N, R : Resource<N>> private constructor(
  private val cache: Map<N, R>,
  private val updateCache: (ResourceCacheAction<N, R>) -> Unit,
) {
  companion object {
    private sealed interface ResourceCacheAction<N, R : Resource<N>>
    private data class Insert<N, R : Resource<N>>(val resource: R) : ResourceCacheAction<N, R>
    private data class InsertAll<N, R : Resource<N>>(val resources: Iterable<R>) : ResourceCacheAction<N, R>

    fun <N, R : Resource<N>> createProvider(
      displayName: String,
      context: Context<ResourceCacheV2<N, R>?>,
    ) = FC<PropsWithChildren>(displayName) {
      val (cache, updateCache) = useReducer({ cache: MutableMap<N, R>, action: ResourceCacheAction<N, R> ->
        when (action) {
          is Insert<N, R> -> cache[action.resource.name] = action.resource
          is InsertAll<N, R> -> cache.putAll(action.resources.associateBy { it.name })
        }
        cache
      }, initialState = mutableMapOf());

      context(ResourceCacheV2(cache, updateCache)) {
        +it.children
      }
    }
  }

  operator fun get(name: N) = cache[name]
  fun insert(resource: R) = updateCache(Insert(resource))
  fun insertAll(resources: Iterable<R>) = updateCache(InsertAll(resources))
}
