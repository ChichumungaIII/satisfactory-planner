package app.v2.data

interface LoadState<I, T> {
    data class Requested<I, T>(override val id: I) : LoadState<I, T>
    data class Loading<I, T>(override val id: I) : LoadState<I, T>
    data class Loaded<I, T>(override val id: I, val data: T) : LoadState<I, T>
    data class Failure<I, T>(override val id: I, val message: String) : LoadState<I, T>

    companion object {
        private val Uninitialized = object : LoadState<Any, Any> {
            override val id: Any? = null
        }

        @Suppress("UNCHECKED_CAST")
        fun <I, T> empty(): LoadState<I, T> = Uninitialized as LoadState<I, T>
        fun <I, T> request(id: I): LoadState<I, T> = Requested(id)
        fun <I, T> loading(id: I): LoadState<I, T> = Loading(id)
        fun <I, T> loaded(id: I, data: T): LoadState<I, T> = Loaded(id, data)
        fun <I, T> failure(id: I, message: String): LoadState<I, T> = Failure(id, message)
    }

    val id: I?
}
