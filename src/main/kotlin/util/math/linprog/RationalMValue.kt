package util.math.linprog

import util.math.Rational
import util.math.q

/**
 * A RationalMValue is a helper type for the linear programming simplex algorithm representing a number of the form
 * `[m] * M + [v]`, where `M` is an arbitrarily large constant.
 *
 * @property m The "large" part of the RationalMValue.
 * @property v The "normal" part of the RationalMValue.
 * @constructor
 */
class RationalMValue(
    val m: Rational,
    val v: Rational,
) : Comparable<RationalMValue> {
    companion object {
        val ZERO = RationalMValue(Rational.ZERO, Rational.ZERO)
        val ONE = RationalMValue(Rational.ZERO, Rational.ONE)
        val M = RationalMValue(Rational.ONE, Rational.ZERO)
    }

    /** This [RationalMValue] with its component parts reduced. */
    private val norm by lazy {
        if (m.norm() === m && v.norm() === v) this
        else RationalMValue(m.norm(), v.norm())
    }

    /**
     * Returns this [RationalMValue] with its component parts reduced.
     *
     * If the parts are already reduced, returns this RationalMValue.
     */
    fun norm() = norm

    /**
     * Returns the "normal" part of this [RationalMValue].
     *
     * @throws IllegalStateException if this [RationalMValue] does not have a zero "M" part.
     */
    fun toRational(): Rational {
        check(m == Rational.ZERO) { "[$this] cannot be converted to Rational." }
        return v
    }

    operator fun unaryPlus() = this
    operator fun unaryMinus() = RationalMValue(-m, -v)

    operator fun plus(other: RationalMValue) =
        RationalMValue(
            m + other.m,
            v + other.v
        )

    operator fun minus(other: RationalMValue) =
        RationalMValue(
            m - other.m,
            v - other.v
        )

    operator fun times(other: RationalMValue): RationalMValue {
        check(m == Rational.ZERO || other.m == Rational.ZERO) {
            "Cannot multiply [($this) * ($other)]: at least one must have a zero `M` part."
        }
        return RationalMValue(
            m * other.v + other.m * v,
            v * other.v
        )
    }

    operator fun div(other: RationalMValue) =
        if (other.m == Rational.ZERO) RationalMValue(m / other.v, v / other.v)
        else RationalMValue(Rational.ZERO, m / other.m)

    override operator fun compareTo(other: RationalMValue) =
        compareBy<RationalMValue> { it.m }.thenBy { it.v }.compare(this, other)

    /** Returns true if [other] is a [RationalMValue] number numerically equal to this. */
    override fun equals(other: Any?) =
        other is RationalMValue && norm.m == other.norm.m && norm.v == other.norm.v

    override fun hashCode() = norm.m.hashCode() * 31 + norm.v.hashCode()

    override fun toString() = when (m) {
        0.q -> "$v"
        else ->
            if (v == 0.q) "$m * M"
            else if (v > 0.q) "$m * M + $v"
            else "$m * M - ${-v}"
    }
}
