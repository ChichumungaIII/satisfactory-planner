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
import react.Dispatch
import react.FC
import react.PropsWithChildren
import react.useContext
import react.useReducer
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

external interface ResourceManagerProps<N : ResourceName, R : Resource<N>> : PropsWithChildren {
  var resource: R
}

class ResourceManager<N : ResourceName, R : Resource<N>> private constructor(
  private val query: suspend (resource: R) -> R,
  private val cache: ResourceCache<N, R>,
  private val managed: ManagedResource<R>,
  private val updateManaged: Dispatch<ResourceManagerAction<R>>,
) {
  companion object {
    private val DEBOUNCE_TIME = 5.seconds

    private sealed interface ResourceManagerAction<R>
    private data class Apply<R>(
      val update: (managed: ManagedResource<R>) -> ManagedResource<R>,
    ) : ResourceManagerAction<R>

    fun <N : ResourceName, R : Resource<N>, S> createProvider(
      displayName: String,
      context: Context<ResourceManager<N, R>?>,
      serviceContext: Context<S?>,
      query: suspend S.(resource: R) -> R,
      cacheContext: Context<ResourceCache<N, R>?>,
    ) = FC<ResourceManagerProps<N, R>>(displayName) { props ->
      val service = useContext(serviceContext)!!
      val cache = useContext(cacheContext)!!
      val (managed, updateManaged) = useReducer({ managed: ManagedResource<R>, action: ResourceManagerAction<R> ->
        when (action) {
          is Apply -> action.update(managed).also { cache.insert(it.data.resource) }
        }
      }, initialState = ManagedResource(Stable(props.resource), SaveState()))

      context(ResourceManager({ service.query(it) }, cache, managed, updateManaged)) {
        +props.children
      }
    }
  }

  fun update(updater: (resource: R) -> R) = updateManaged(Apply { managed ->
    val next = updater(managed.data.resource)
    when (managed.data) {
      is Stable -> ManagedResource(
        LocalData.debouncing(next, newDebouncedSave(next)),
        managed.state.copy(saving = true)
      )

      is Debouncing -> {
        managed.data.job.cancel()
        managed.copy(data = LocalData.debouncing(next, newDebouncedSave(next)))
      }

      is Updating -> {
        managed.data.job.cancel()
        managed.copy(data = LocalData.throttled(next, newThrottledSave(next, managed.data.job)))
      }

      is Throttled -> {
        managed.data.job.cancel()
        managed.copy(data = LocalData.throttled(next, newThrottledSave(next, managed.data.job)))
      }
    }
  })

  operator fun component1() = managed.data.resource
  operator fun component2() = this

  fun state() = managed.state

  private fun newDebouncedSave(resource: R) = launchMain {
    delay(DEBOUNCE_TIME)
    updateManaged(Apply { managed ->
      managed.copy(data = LocalData.updating(resource, newSave(resource)))
    })
  }

  private fun newSave(resource: R) = launchMain {
    val updated = query(resource)
    delay(2500.milliseconds)

    if (isActive) {
      cache.insert(updated)
      updateManaged(Apply { _ ->
        ManagedResource(
          LocalData.stable(updated),
          SaveState(Clock.System.now(), false)
        )
      })
    }
  }

  private fun newThrottledSave(resource: R, previous: Job) = launchMain {
    previous.join()
    if (isActive) {
      updateManaged(Apply { managed ->
        managed.copy(data = LocalData.debouncing(resource, newDebouncedSave(resource)))
      })
    }
  }
}

private data class ManagedResource<R>(
  val data: LocalData<R>,
  val state: SaveState,
)
