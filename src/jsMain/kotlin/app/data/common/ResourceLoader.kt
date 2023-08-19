package app.data.common

import app.api.common.Resource
import app.api.common.ResourceName
import app.util.launchMain
import kotlinx.coroutines.delay
import react.Context
import react.FC
import react.PropsWithChildren
import react.StateSetter
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds

class ResourceLoader<N : ResourceName, R : Resource<N>> private constructor(
  private val query: suspend (name: N) -> R,
  private val cache: ResourceCache<N, R>,
  private val data: RemoteData<N, N>,
  private val setData: StateSetter<RemoteData<N, N>>,
) {
  companion object {
    fun <N : ResourceName, R : Resource<N>, S> createProvider(
      displayName: String,
      context: Context<ResourceLoader<N, R>?>,
      serviceContext: Context<S?>,
      query: suspend S.(name: N) -> R,
      cacheContext: Context<ResourceCache<N, R>?>,
    ) = FC<PropsWithChildren>(displayName) {
      val service = useContext(serviceContext)!!
      val cache = useContext(cacheContext)!!
      val (data, setData) = useState<RemoteData<N, N>>(RemoteData.empty())
      context(ResourceLoader({ service.query(it) }, cache, data, setData)) { +it.children }
    }
  }

  fun load(name: N) {
    if (data.name == name) return

    if (cache.contains(name)) {
      setData(RemoteData.loaded(name, name))
      return
    }

    launchMain {
      delay(1500.milliseconds)
      val resource = query(name)
      cache.insert(resource)
      setData(RemoteData.loaded(name, name))
    }
    setData(RemoteData.loading(name))
  }

  operator fun component1() = data.map { cache[it]!! }
  operator fun component2() = this
}
