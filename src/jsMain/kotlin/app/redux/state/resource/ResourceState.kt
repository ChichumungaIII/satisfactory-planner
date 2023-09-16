package app.redux.state.resource

import kotlinx.coroutines.Job

sealed interface ResourceState<R> {
  companion object {
    fun <R> create(resource: R?, request: Job?): ResourceState<R> =
      resource?.let { Loaded.create(it, request) }
        ?: request?.let { Loading(it) }
        ?: Empty()


    class Empty<R> : ResourceState<R> {
      override fun <R2, O> merge(other: ResourceState<R2>, merger: (R, R2) -> O) = Empty<O>()
    }

    data class Loading<R>(
      val request: Job,
    ) : ResourceState<R> {
      override fun <R2, O> merge(other: ResourceState<R2>, merger: (R, R2) -> O) =
        when (other) {
          is Empty -> Empty()
          else -> Loading<O>(request)
        }
    }

    sealed interface Loaded<R> : ResourceState<R> {
      companion object {
        fun <R> create(resource: R, request: Job?): Loaded<R> =
          request?.let { Updating(resource, request) } ?: Stable(resource)
      }

      val resource: R

      data class Stable<R>(override val resource: R) : Loaded<R> {
        override fun <R2, O> merge(other: ResourceState<R2>, merger: (R, R2) -> O) =
          when (other) {
            is Empty -> Empty()
            is Loading -> Loading(other.request)
            is Loaded -> when (other) {
              is Stable -> Stable(merger(resource, other.resource))
              is Updating -> Updating(merger(resource, other.resource), other.request)
            }
          }
      }

      data class Updating<R>(
        override val resource: R,
        val request: Job,
      ) : Loaded<R> {
        override fun <R2, O> merge(other: ResourceState<R2>, merger: (R, R2) -> O) =
          when (other) {
            is Empty -> Empty()
            is Loading -> Loading(other.request)
            is Loaded -> Updating(merger(resource, other.resource), request)
          }
      }
    }
  }

  fun <R2, O> merge(other: ResourceState<R2>, merger: (R, R2) -> O): ResourceState<O>
}
