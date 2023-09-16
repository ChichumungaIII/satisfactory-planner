package app.redux.state.resource

import kotlinx.coroutines.Job

sealed interface ResourceState<R> {
  companion object {
    fun <R> create(resource: R?, request: Job?): ResourceState<R> =
      resource?.let { Loaded.create(it, request) }
        ?: request?.let { Loading(it) }
        ?: Empty()
  }

  class Empty<R> : ResourceState<R>

  data class Loading<R>(
    val request: Job,
  ) : ResourceState<R>

  sealed interface Loaded<R> : ResourceState<R> {
    companion object {
      fun <R> create(resource: R, request: Job?): Loaded<R> =
        request?.let { Updating(resource, request) } ?: Stable(resource)
    }

    val resource: R

    data class Stable<R>(override val resource: R) : Loaded<R>

    data class Updating<R>(
      override val resource: R,
      val request: Job,
    ) : Loaded<R>
  }
}
