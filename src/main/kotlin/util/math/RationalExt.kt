package util.math

fun Long.toRational() = Rational.create(this)
operator fun Long.plus(other: Rational) = toRational() + other
operator fun Long.minus(other: Rational) = toRational() - other
operator fun Long.times(other: Rational) = toRational() * other
operator fun Long.div(other: Rational) = toRational() / other
operator fun Long.compareTo(other: Rational) = toRational().compareTo(other)

fun Int.toRational() = toLong().toRational()
operator fun Int.plus(other: Rational) = toRational() + other
operator fun Int.minus(other: Rational) = toRational() - other
operator fun Int.times(other: Rational) = toRational() * other
operator fun Int.div(other: Rational) = toRational() / other
operator fun Int.compareTo(other: Rational) = toRational().compareTo(other)
