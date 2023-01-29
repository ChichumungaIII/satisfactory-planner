package app.v2.data

sealed interface Loadable<out T>
data class Loaded<T>(val value: T) : Loadable<T>
data class Failure<T>(val message: String) : Loadable<T>
data class Loading<T>(val loading: Boolean) : Loadable<T>
