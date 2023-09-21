package util.math

import kotlinx.serialization.Serializable
import kotlin.math.abs

/**
 * A Rational number is a pair of [Long] values (n, d) representing the ratio of the two, n / d.
 *
 * @property n The numerator of the Rational number.
 * @property d The denominator of the Rational number, which must be positive.
 * @constructor
 */
@Serializable
class Rational private constructor(
  val n: Long,
  val d: Long,
) : Number(), Numeric<Rational> {
  companion object {
    val ZERO = create(0)
    val ONE = create(1)

    val FACTORY = object : Numeric.Factory<Rational> {
      override fun zilch() = ZERO
      override fun unit() = ONE
    }

    private val RATIONAL = Regex("""(-)?\s*(\d+)\s*/\s*(\d+)""")
    private val DECIMAL = Regex("""(-)?(\d+)(\.(\d*)(_(\d*))?)?""")

    /**
     * Parses [text] as a Rational number.
     *
     * [text] may be either a rational number (e.g. "-7 / 4"), or a decimal (e.g. "-1.75").
     */
    fun parse(text: String): Rational? =
      RATIONAL.matchEntire(text.trim())?.let { match ->
        val sign = match.groupValues[1].takeIf { it.isEmpty() }?.let { ONE } ?: -ONE
        val numerator = match.groupValues[2].toLong().toRational()
        val denominator = match.groupValues[3].toLong().toRational()
        (sign * numerator / denominator).norm()
      } ?: DECIMAL.matchEntire(text.trim())?.let { match ->
        val sign = match.groupValues[1].takeIf { it.isEmpty() }?.let { ONE } ?: -ONE
        val integer = match.groupValues[2].toLong().toRational()

        val decimalPart = match.groupValues[4]
        val decimalScale = decimalPart.fold(1.q) { scale, _ -> scale * 10.q }
        val decimal = decimalPart.takeIf { it.isNotEmpty() }?.let { it.toLong() / decimalScale } ?: ZERO

        val repeatedPart = match.groupValues[6]
        val repeatedScale = repeatedPart.fold(ONE) { scale, _ -> scale * 10.q } - ONE
        val repeated =
          repeatedPart.takeIf { it.isNotEmpty() }?.let { it.toLong() / repeatedScale / decimalScale } ?: ZERO

        sign * (integer + decimal + repeated).norm()
      }

    /**
     * Creates a new [Rational] number, n / d.
     *
     * @param n The numerator of the Rational number.
     * @param d The denominator of the Rational number.
     * @throws IllegalStateException if `d` is zero.
     */
    fun create(n: Long, d: Long = 1) =
      if (d < 0) Rational(-n, -d) else Rational(n, d)

    private fun gcd(a: Long, b: Long): Long {
      val pA = abs(a)
      val pB = abs(b)
      return if (pA > pB) gcdImpl(pB, pA) else gcdImpl(pA, pB)
    }

    private fun gcdImpl(a: Long, b: Long): Long =
      if (a == 0L) b else gcdImpl(b % a, a)
  }

  init {
    check(d > 0) { "Rational number [$n/$d] requires a positive denominator." }
  }

  private val gcd by lazy { gcd(n, d) }
  private val normN by lazy { n / gcd }
  private val normD by lazy { d / gcd }

  /**
   * Returns this [Rational] number in reduced form.
   *
   * If the number is already reduced, returns this Rational number.
   */
  fun norm() = if (gcd == 1L) this else Rational(normN, normD)

  fun abs() = if (n < 0) Rational(-n, d) else this

  override fun factory() = FACTORY

  override operator fun unaryMinus() = Rational(-n, d)

  override operator fun plus(other: Rational) =
    Rational(
      n * other.d + other.n * d,
      d * other.d
    ).norm()

  override operator fun minus(other: Rational) =
    Rational(
      n * other.d - other.n * d,
      d * other.d
    ).norm()

  override operator fun times(other: Rational) =
    Rational(
      n * other.n,
      d * other.d
    ).norm()

  override operator fun div(other: Rational) =
    create(
      n * other.d,
      other.n * d
    ).norm()

  override operator fun compareTo(other: Rational) =
    (n * other.d).compareTo(other.n * d)

  /** Returns true if [other] is a [Rational] number numerically equal to this. */
  override fun equals(other: Any?) =
    other is Rational && this.normN == other.normN && this.normD == other.normD

  override fun hashCode() = (normN * 31 + normD).toInt()

  fun exact(): String? {
    if (n < 0) return (-this).exact()?.let { "-$it" }

    val integer = n / d
    var numerator = n % d
    if (numerator == 0L) return "$integer"

    var denominator = d
    var fixedPlaces = 0
    listOf(10L, 5L, 2L).forEach { divisor ->
      while (denominator % divisor == 0L) {
        denominator /= divisor
        numerator *= 10L / divisor
        fixedPlaces++
      }
    }
    val fixed = if (fixedPlaces > 0) "${numerator / denominator}".padStart(fixedPlaces, '0') else ""
    if (denominator == 1L) return "$integer.$fixed"

    var nines = 9L
    while (nines % denominator != 0L) {
      nines = 10L * nines + 9L
      if (nines < 0L) return null
    }
    val repeating = "${(numerator % denominator) * (nines / denominator)}".padStart("$nines".length, '0')
    return "$integer.${fixed}_$repeating"
  }

  override fun toString(): String {
    return exact() ?: run {
      val text = "${n.toDouble() / d.toDouble()}"
      val end = text.lastIndexOf('.')
      return "~${text.substring(0, end + 6)}"
    }
  }

  override fun toDouble() = n.toDouble() / d
  override fun toFloat() = toDouble().toFloat()

  override fun toLong() = n / d
  override fun toInt() = toLong().toInt()
  override fun toShort() = toLong().toShort()
  override fun toByte() = toLong().toByte()
}
