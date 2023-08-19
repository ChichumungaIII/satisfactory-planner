package app.data.common

import app.api.common.Resource
import react.Context
import react.FC
import react.PropsWithChildren
import react.useEffectOnce
import react.useReducer

class ResourceCache<N, R : Resource<N>> private constructor(
  private val cache: MutableMap<N, R>,
  private var render: () -> Unit = { throw Error("ResourceCache#render") },
) {
  companion object {
    fun <N, R : Resource<N>> createProvider(
      displayName: String,
      context: Context<ResourceCache<N, R>?>,
    ) = FC<PropsWithChildren>(displayName) { provider ->
      val (cache, render) = useReducer({ cache: ResourceCache<N, R>, _: Unit -> cache }, Unit) {
        ResourceCache(mutableMapOf())
      }
      useEffectOnce { cache.render = { render(Unit) } }
      context(cache) { +provider.children }
    }
  }

  fun contains(name: N) = cache.contains(name)

  operator fun get(name: N) = cache[name]

  fun insert(resource: R) {
    cache[resource.name] = resource
    render()
  }

  fun insertAll(resources: Iterable<R>) {
    cache.putAll(resources.associateBy { it.name })
    render()
  }
}
