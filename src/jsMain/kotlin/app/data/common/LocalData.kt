package app.data.common

import kotlinx.coroutines.Job

sealed interface LocalData<R> {
  class Stable<R> internal constructor(
    override val resource: R,
  ) : LocalData<R>

  class Debouncing<R> internal constructor(
    override val resource: R,
    val job: Job,
  ) : LocalData<R>

  class Updating<R> internal constructor(
    override val resource: R,
    val job: Job,
  ) : LocalData<R>

  class Throttled<R> internal constructor(
    override val resource: R,
    val job: Job,
  ) : LocalData<R>

  companion object {
    fun <R> stable(resource: R) = Stable(resource)
    fun <R> debouncing(resource: R, job: Job) = Debouncing(resource, job)
    fun <R> updating(resource: R, job: Job) = Updating(resource, job)
    fun <R> throttled(resource: R, job: Job) = Throttled(resource, job)
  }

  val resource: R
}
