package app.data.common

import app.api.common.Resource
import app.api.common.ResourceName
import app.api.plan.v1.Plan
import app.data.common.LocalData.Debouncing
import app.data.common.LocalData.Stable
import app.data.common.LocalData.Throttled
import app.data.common.LocalData.Updating
import app.util.launchMain
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
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

      context(ResourceManager({ service.query(it) }, cache, data, setData)) {
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
    }
  }

  private fun newThrottledSave(resource: R, previous: Job) = launchMain {
    previous.join()
    if (isActive) {
      setData(LocalData.debouncing(resource, newDebouncedSave(resource)))
    }
  }

  private fun R.displayName() = unsafeCast<Plan>().displayName
}
