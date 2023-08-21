package app.data.common

import app.api.common.Resource
import app.api.common.ResourceName
import app.data.common.LocalData.Debouncing
import app.data.common.LocalData.Stable
import app.data.common.LocalData.Throttled
import app.data.common.LocalData.Updating
import app.util.launchMain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.datetime.Clock
import react.Context
import react.FC
import react.PropsWithChildren
import react.StateSetter
import react.useContext
import react.useState
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

external interface ResourceManagerProps<N : ResourceName, R : Resource<N>> : PropsWithChildren {
  var resource: R
}

class ResourceManager<N : ResourceName, R : Resource<N>> private constructor(
  private val query: suspend (resource: R) -> R,
  private val cache: ResourceCache<N, R>,
  private val data: LocalData<R>,
  private val setData: StateSetter<LocalData<R>>,
  private val state: SaveState,
  private val setState: StateSetter<SaveState>
) {
  companion object {
    private val DEBOUNCE_TIME = 5.seconds

    fun <N : ResourceName, R : Resource<N>, S> createProvider(
      displayName: String,
      context: Context<ResourceManager<N, R>?>,
      serviceContext: Context<S?>,
      query: suspend S.(resource: R) -> R,
      cacheContext: Context<ResourceCache<N, R>?>,
    ) = FC<ResourceManagerProps<N, R>>(displayName) { props ->
      val service = useContext(serviceContext)!!
      val cache = useContext(cacheContext)!!
      val (data, setData) = useState<LocalData<R>>(Stable(props.resource))
      val (state, setState) = useState(SaveState())

      context(ResourceManager({ service.query(it) }, cache, data, setData, state, setState)) {
        +props.children
      }
    }
  }

  fun update(resource: R) {
    check(resource.name == data.resource.name) { "Cannot update unmanaged resource [${resource.name.getResourceName()}]." }

    cache.insert(resource)
    when (data) {
      is Stable -> {
        setData(LocalData.debouncing(resource, newDebouncedSave(resource)))
        setState(state.copy(saving = true))
      }

      is Debouncing -> {
        data.job.cancel()
        setData(LocalData.debouncing(resource, newDebouncedSave(resource)))
      }

      is Updating -> {
        data.job.cancel()
        setData(LocalData.throttled(resource, newThrottledSave(resource, data.job)))
      }

      is Throttled -> {
        data.job.cancel()
        setData(LocalData.throttled(resource, newThrottledSave(resource, data.job)))
      }
    }
  }

  operator fun component1() = data.resource
  operator fun component2() = this

  fun state() = state

  private fun newDebouncedSave(resource: R) = launchMain {
    delay(DEBOUNCE_TIME)
    setData(LocalData.updating(resource, newSave(resource)))
  }

  private fun newSave(resource: R) = launchMain {
    val updated = query(resource)
    delay(2500.milliseconds)

    if (isActive) {
      cache.insert(updated)
      setData(LocalData.stable(updated))
      setState(SaveState(Clock.System.now(), false))
    }
  }

  private fun newThrottledSave(resource: R, previous: Job) = launchMain {
    previous.join()
    if (isActive) {
      setData(LocalData.debouncing(resource, newDebouncedSave(resource)))
    }
  }
}
