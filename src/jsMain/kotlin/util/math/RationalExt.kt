package util.math

inline val Long.q: Rational get() = toRational()
fun Long.toRational() = Rational.create(this)
operator fun Long.plus(other: Rational) = toRational() + other
operator fun Long.minus(other: Rational) = toRational() - other
operator fun Long.times(other: Rational) = toRational() * other
operator fun Long.div(other: Rational) = toRational() / other
operator fun Long.compareTo(other: Rational) = toRational().compareTo(other)

inline val Int.q: Rational get() = toRational()
fun Int.toRational() = toLong().toRational()
operator fun Int.plus(other: Rational) = toRational() + other
operator fun Int.minus(other: Rational) = toRational() - other
operator fun Int.times(other: Rational) = toRational() * other
operator fun Int.div(other: Rational) = toRational() / other
operator fun Int.compareTo(other: Rational) = toRational().compareTo(other)
