package util.math.linprog

import util.math.Rational
import util.math.toRational

fun Rational.toMValue() = RationalMValue(Rational.ZERO, this)
operator fun Rational.plus(other: RationalMValue) = toMValue() + other
operator fun Rational.minus(other: RationalMValue) = toMValue() - other
operator fun Rational.times(other: RationalMValue) = toMValue() * other
operator fun Rational.div(other: RationalMValue) = toMValue() / other
operator fun Rational.compareTo(other: RationalMValue) = toMValue().compareTo(other)

fun Long.toMValue() = RationalMValue(Rational.ZERO, toRational())
operator fun Long.plus(other: RationalMValue) = toMValue() + other
operator fun Long.minus(other: RationalMValue) = toMValue() - other
operator fun Long.times(other: RationalMValue) = toMValue() * other
operator fun Long.div(other: RationalMValue) = toMValue() / other
operator fun Long.compareTo(other: RationalMValue) = toMValue().compareTo(other)

fun Int.toMValue() = RationalMValue(Rational.ZERO, toRational())
operator fun Int.plus(other: RationalMValue) = toMValue() + other
operator fun Int.minus(other: RationalMValue) = toMValue() - other
operator fun Int.times(other: RationalMValue) = toMValue() * other
operator fun Int.div(other: RationalMValue) = toMValue() / other
operator fun Int.compareTo(other: RationalMValue) = toMValue().compareTo(other)
