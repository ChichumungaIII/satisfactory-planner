package util.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class RationalTest {
    @Test
    fun parse_matchesRationalNumber() {
        assertEquals(Rational.create(-7, 4), Rational.parse(" -7 / 4 "))
    }

    @Test
    fun parse_matchesWholeNumber() {
        assertEquals(Rational.create(5), Rational.parse("5"))
    }

    @Test
    fun parse_matchesDecimalNumber() {
        assertEquals(Rational.create(-7, 4), Rational.parse("-1.75"))
    }

    @Test
    fun parse_handlesLeadingZeros() {
        assertEquals(Rational.create(101, 100), Rational.parse("01.01"))
    }

    @Test
    fun parse_returnsNullOtherwise() {
        assertNull(Rational.parse("Unknown"))
    }

    @Test
    fun create_normalizesSign() {
        assertEquals(Rational.create(-2, 3), Rational.create(2, -3))
    }

    @Test
    fun create_zeroDenominator_throws() {
        val expected = assertFailsWith<IllegalStateException> { Rational.create(5, 0) }
        assertEquals("Rational number [5/0] requires a positive denominator.", expected.message)
    }

    @Test
    fun norm_returnsReducedValue() {
        assertEquals("3/4", Rational.create(9, 12).norm().toString())
    }

    @Test
    fun norm_alreadyReduced_returnsSameInstance() {
        val value = Rational.create(7, 13)
        assertSame(value, value.norm())
    }

    @Test
    fun unaryPlus_returnsSameInstance() {
        val value = Rational.create(4, 8)
        assertSame(value, +value)
    }

    @Test
    fun unaryMinus_returnsNegation() {
        val value = Rational.create(1, 2)
        assertEquals(Rational.create(-1, 2), -value)
    }

    @Test
    fun plus_returnsSum() {
        assertEquals(
            Rational.create(7, 12),
            Rational.create(1, 3) + Rational.create(1, 4)
        )
    }

    @Test
    fun minus_returnsDifference() {
        assertEquals(
            Rational.create(1, 3),
            Rational.create(7, 12) - Rational.create(1, 4)
        )
    }

    @Test
    fun times_returnsProduct() {
        assertEquals(
            Rational.create(2, 5),
            Rational.create(3, 5) * Rational.create(2, 3)
        )
    }

    @Test
    fun div_returnsQuotient() {
        assertEquals(
            Rational.create(3, 5),
            Rational.create(2, 5) / Rational.create(2, 3)
        )
    }

    @Test
    fun compareTo_returnsLessThan() {
        assertTrue { Rational.create(1, 3) < Rational.create(1, 2) }
    }

    @Test
    fun compareTo_returnsEquals() {
        assertEquals(0, Rational.create(1, 2).compareTo(Rational.create(2, 4)))
    }

    @Test
    fun compareTo_returnsGreaterThan() {
        assertTrue { Rational.create(1, 2) > Rational.create(1, 3) }
    }
}