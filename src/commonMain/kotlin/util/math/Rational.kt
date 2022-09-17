package util.math

import kotlin.math.abs

/**
 * A Rational number is a pair of [Long] values (n, d) representing the ratio of the two, n / d.
 *
 * @property n The numerator of the Rational number.
 * @property d The denominator of the Rational number, which must be positive.
 * @constructor
 */
class Rational private constructor(
    private val n: Long,
    private val d: Long,
) : Number(), Numeric<Rational> {
    companion object {
        val ZERO = create(0)
        val ONE = create(1)

        private val RATIONAL_OR_DECIMAL = Regex("""(-)?(\d+)(\s*/\s*(\d+)|\.(\d+))?""")

        /**
         * Parses [text] as a Rational number.
         *
         * [text] may be either a rational number (e.g. "-7 / 4"), or a decimal (e.g. "-1.75").
         */
        fun parse(text: String): Rational? {
            val match = RATIONAL_OR_DECIMAL.matchEntire(text.trim()) ?: return null
            val sign = if (match.groupValues[1].isEmpty()) ONE else -ONE
            val principal = match.groupValues[2].toLong().toRational()
            val denominator = match.groupValues[4].takeIf { it.isNotEmpty() }?.toLong()?.toRational() ?: ONE
            val decimal = match.groupValues[5].takeIf { it.isNotEmpty() }?.asDecimal() ?: ZERO
            return sign * (principal / denominator + decimal).norm()
        }

        private fun String.asDecimal(): Rational {
            var decimal = toLong().toRational()
            for (i in indices) decimal /= 10.toRational()
            return decimal
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
        check(d > 0) { "Rational number [$this] requires a positive denominator." }
    }

    /** @property norm The reduced form of this Rational number. */
    private val norm by lazy {
        val gcd = gcd(n, d)
        if (gcd == 1L) this else Rational(n / gcd, d / gcd)
    }

    /**
     * Returns this [Rational] number in reduced form.
     *
     * If the number is already reduced, returns this Rational number.
     */
    fun norm() = norm

    override fun factory() = object : Numeric.Factory<Rational> {
        override fun zilch() = ZERO
        override fun unit() = ONE
    }

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
        other is Rational && this.norm.n == other.norm.n && this.norm.d == other.norm.d

    override fun hashCode() = (norm.n * 31 + norm.d).toInt()

    override fun toString() = if (d == 1L) "$n" else "$n/$d"

    fun toDecimal(places: Int): String = toDouble().toString()

    override fun toDouble() = n.toDouble() / d
    override fun toFloat() = toDouble().toFloat()

    override fun toLong() = n / d
    override fun toInt() = toLong().toInt()
    override fun toShort() = toLong().toShort()
    override fun toByte() = toLong().toByte()

    override fun toChar() = toInt().toChar()
}