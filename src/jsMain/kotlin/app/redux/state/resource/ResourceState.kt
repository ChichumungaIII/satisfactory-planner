package app.redux.state.resource

import kotlinx.coroutines.Job

sealed interface ResourceState<N, R> {
  data class Empty<N, R>(
    val name: N,
  ) : ResourceState<N, R>

  data class Loading<N, R>(
    val request: Job,
  ) : ResourceState<N, R>

  sealed interface Loaded<N, R> : ResourceState<N, R> {
    abstract val resource: R

    data class Stable<N, R>(override val resource: R) : Loaded<N, R>

    data class Updating<N, R>(
      override val resource: R,
      val request: Job,
    ) : Loaded<N, R>
  }
}
