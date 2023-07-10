package app.data.common

sealed interface RemoteData<N, R> {
  class Empty<N, R> private constructor() : RemoteData<N, R> {
    companion object {
      private val INSTANCE = Empty<Any, Any>()

      @Suppress("UNCHECKED_CAST")
      fun <N, R> instance() = INSTANCE as RemoteData<N, R>
    }

    override val name = null
  }

  class Loading<N, R> internal constructor(override val name: N) : RemoteData<N, R>
  class Loaded<N, R> internal constructor(override val name: N, val data: R) : RemoteData<N, R>
  class Error<N, R> internal constructor(override val name: N, val message: String) : RemoteData<N, R>

  companion object {
    fun <N, R> empty() = Empty.instance<N, R>()
    fun <N, R> loading(name: N) = Loading<N, R>(name)
    fun <N, R> loaded(name: N, data: R) = Loaded(name, data)
    fun <N, R> error(name: N, message: String) = Error<N, R>(name, message)
  }

  val name: N?
}
