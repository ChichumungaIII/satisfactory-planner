package app.data.common

import app.api.common.Resource
import app.api.common.ResourceName
import app.redux.state.AppState
import app.redux.state.cache.AppCache
import app.redux.useAppDispatch
import app.redux.useAppSelector
import app.util.launchMain
import kotlinx.coroutines.delay
import react.Context
import react.FC
import react.PropsWithChildren
import react.StateSetter
import react.useState
import redux.RAction
import redux.WrapperAction
import kotlin.time.Duration.Companion.milliseconds

class ResourceLoader<N : ResourceName, R : Resource<N>> private constructor(
  private val query: suspend (name: N) -> R,
  private val cache: AppCache<N, R>,
  private val dispatch: (RAction) -> WrapperAction,
  private val createInsert: (R) -> RAction,
  private val data: RemoteData<N, N>,
  private val setData: StateSetter<RemoteData<N, N>>
) {
  companion object {
    fun <N : ResourceName, R : Resource<N>> createProvider(
      displayName: String,
      context: Context<ResourceLoader<N, R>?>,
      query: suspend (name: N) -> R,
      selector: (AppState) -> AppCache<N, R>,
      createInsert: (R) -> RAction,
    ) = FC<PropsWithChildren>(displayName) {
      val cache = useAppSelector(selector)
      val dispatch = useAppDispatch()

      val (data, setData) = useState<RemoteData<N, N>>(RemoteData.empty())

      context(ResourceLoader(query, cache, dispatch, createInsert, data, setData)) { +it.children }
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
      dispatch(createInsert(resource))
      setData(RemoteData.loaded(name, name))
    }
    setData(RemoteData.loading(name))
  }

  operator fun component1() = data.map { cache[it]!! }
  operator fun component2() = this
}
