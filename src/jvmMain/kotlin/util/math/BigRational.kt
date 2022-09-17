package com.chichumunga.satisfactory.util.math

import util.math.Numeric
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

class BigRational constructor(
    private val n: BigInteger,
    private val d: BigInteger,
) : Numeric<BigRational> {
    init {
        check(d > ZERO) { "Rational number [$this] requires a positive denominator." }
    }

    companion object {
        val FACTORY = object : Numeric.Factory<BigRational> {
            override fun zilch() = create(ZERO)
            override fun unit() = create(ONE)
        }

        fun create(n: BigInteger, d: BigInteger = ONE) = if (d < ZERO) BigRational(-n, -d) else BigRational(n, d)
    }

    val norm by lazy {
        val gcd = n.gcd(d)
        if (gcd == ONE) this
        else BigRational(n / gcd, d / gcd)
    }

    override fun factory() = FACTORY

    override operator fun unaryMinus() = BigRational(-n, d)

    override operator fun plus(other: BigRational) = BigRational(n * other.d + other.n * d, d * other.d)
    override operator fun minus(other: BigRational) = BigRational(n * other.d - other.n * d, d * other.d)
    override operator fun times(other: BigRational) = BigRational(n * other.n, d * other.d)
    override operator fun div(other: BigRational) = create(n * other.d, d * other.n)

    override operator fun compareTo(other: BigRational) = (n * other.d).compareTo(other.n * d)

    override fun equals(other: Any?) = other is BigRational && norm.n == other.n && norm.d == other.d
    override fun hashCode() = norm.n.toInt() * 31 + norm.d.toInt()

    override fun toString() = if (d == ONE) "$n" else "$n/$d"
}

inline val BigInteger.br get() = BigRational.create(this)
inline val Long.br get() = bi.br
inline val Int.br get() = bi.br
