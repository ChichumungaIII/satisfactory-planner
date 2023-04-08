package util.math

class MValue<N : Numeric<N>> private constructor(
  val m: N,
  val v: N,
  private val numbers: Numeric.Factory<N>,
) : Comparable<MValue<N>> {
  companion object {
    fun <N : Numeric<N>> zilch(numbers: Numeric.Factory<N>) = create(numbers.zilch(), numbers.zilch(), numbers)
    fun <N : Numeric<N>> ofM(m: N, numbers: Numeric.Factory<N>) = create(m, numbers.zilch(), numbers)
    fun <N : Numeric<N>> from(v: N, numbers: Numeric.Factory<N>) = create(numbers.zilch(), v, numbers)
    fun <N : Numeric<N>> create(m: N, v: N, numbers: Numeric.Factory<N>) = MValue(m, v, numbers)
  }

  operator fun unaryPlus() = this
  operator fun unaryMinus() = MValue(-m, -v, numbers)

  operator fun plus(other: MValue<N>) = MValue(m + other.m, v + other.v, numbers)
  operator fun minus(other: MValue<N>) = MValue(m - other.m, v - other.v, numbers)

  operator fun times(other: MValue<N>): MValue<N> {
    check(m == numbers.zilch() || other.m == numbers.zilch()) { "Cannot multiply [($this) * ($other)]." }
    return MValue(m * other.v + other.m * v, v * other.v, numbers)
  }

  operator fun div(other: MValue<N>) =
    if (other.m == numbers.zilch()) MValue(m / other.v, v / other.v, numbers)
    else MValue(numbers.zilch(), m / other.m, numbers)

  override operator fun compareTo(other: MValue<N>) =
    compareBy<MValue<N>> { it.m }.thenBy { it.v }.compare(this, other)

  override fun equals(other: Any?) = other is MValue<*> && m == other.m && v == other.v
  override fun hashCode() = 31 * m.hashCode() + v.hashCode()

  fun toNumeric(): N {
    check(m == numbers.zilch()) { "[$this] cannot be converted to Numeric." }
    return v
  }

  override fun toString() = when (m) {
    numbers.zilch() -> "$v"
    else ->
      if (v == numbers.zilch()) "$m * M"
      else if (v > numbers.zilch()) "$m * M + $v"
      else "$m * M - ${-v}"
  }
}
