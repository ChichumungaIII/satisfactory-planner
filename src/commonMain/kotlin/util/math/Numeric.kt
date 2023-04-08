package util.math

interface Numeric<T : Numeric<T>> : Comparable<T> {
  interface Factory<T : Numeric<T>> {
    fun zilch(): T
    fun unit(): T
  }

  fun factory(): Factory<T>

  operator fun unaryPlus() = this
  operator fun unaryMinus(): T

  operator fun plus(other: T): T
  operator fun minus(other: T): T
  operator fun times(other: T): T
  operator fun div(other: T): T
}
