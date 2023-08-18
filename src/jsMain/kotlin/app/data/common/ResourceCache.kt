package app.data.common

import app.api.common.Resource
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useReducer

class ResourceCache<N, R : Resource<N>> {
  sealed interface ResourceCacheAction<R>
  data class Insert<R>(val resource: R) : ResourceCacheAction<R>
  data class InsertAll<R>(val resources: Collection<R>) : ResourceCacheAction<R>

  val Context = createContext<ReducerInstance<ResourceCache<N, R>, ResourceCacheAction<R>>>()
  val Provider = FC<PropsWithChildren> { props ->
    val reducer =
      useReducer<ResourceCache<N, R>, ResourceCacheAction<R>>({ cache, action ->
        when (action) {
          is Insert<R> -> cache.insert(action.resource)
          is InsertAll<R> -> cache.insertAll(action.resources)
        }
        cache
      }, initialState = this@ResourceCache)

    Context.Provider {
      value = reducer
      +props.children
    }
  }

  private val cache = mutableMapOf<N, R>()

  operator fun get(name: N) = cache[name]

  private fun insert(resource: R) {
    cache[resource.name] = resource
  }

  private fun insertAll(resources: Collection<R>) {
    resources.forEach { insert(it) }
  }
}
