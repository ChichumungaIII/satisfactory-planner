package app.redux.state.cache

import app.api.common.Resource
import app.api.common.ResourceName
import kotlinx.coroutines.Job

sealed interface ResourceState<N : ResourceName, R : Resource<N>> {
  data class Loading<N : ResourceName, R : Resource<N>>(
    val name: N,
    val request: Job,
  ) : ResourceState<N, R>

  data class Loaded<N : ResourceName, R : Resource<N>>(
    val resource: R
  ) : ResourceState<N, R>

  data class Updating<N : ResourceName, R : Resource<N>>(
    val resource: R,
    val request: Job,
  ) : ResourceState<N, R>
}
