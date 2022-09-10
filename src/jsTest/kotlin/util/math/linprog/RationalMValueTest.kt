package util.math.linprog

import util.math.Rational
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame
import kotlin.test.assertTrue

class RationalMValueTest {
    @Test
    fun norm_returnsNormalizedValue() {
        val m = Rational.create(4, 8)
        val v = Rational.create(3, 9)
        assertEquals("1/2 * M + 1/3", RationalMValue(m, v).norm().toString())
    }

    @Test
    fun norm_alreadyReduced_returnsSameInstance() {
        val value = RationalMValue(Rational.create(1, 2), Rational.create(1, 3))
        assertSame(value, value.norm())
    }

    @Test
    fun unaryPlus_returnsSameInstance() {
        val value = RationalMValue(Rational.ZERO, Rational.ONE)
        assertSame(value, +value)
    }

    @Test
    fun unaryMinus_returnsNegation() {
        val value = RationalMValue(Rational.create(3, 4), Rational.create(-2))
        assertEquals(RationalMValue(Rational.create(-3, 4), Rational.create(2)), -value)
    }

    @Test
    fun plus_returnsSum() {
        assertEquals(
            RationalMValue(Rational.create(1), Rational.create(2, 3)),
            RationalMValue(Rational.create(3, 4), Rational.create(1, 3)) +
                    RationalMValue(Rational.create(1, 4), Rational.create(1, 3))
        )
    }

    @Test
    fun minus_returnsDifference() {
        assertEquals(
            RationalMValue(Rational.create(3, 4), Rational.create(1, 3)),
            RationalMValue(Rational.create(1), Rational.create(2, 3)) -
                    RationalMValue(Rational.create(1, 4), Rational.create(1, 3))
        )
    }

    @Test
    fun times_returnsProduct() {
        assertEquals(
            RationalMValue(Rational.create(4), Rational.create(2, 3)),
            RationalMValue(Rational.create(2), Rational.create(1, 3)) *
                    RationalMValue(Rational.ZERO, Rational.create(2))
        )
    }

    @Test
    fun times_twoNonzeroMValues_throws() {
        val expected = assertFailsWith<IllegalStateException> {
            RationalMValue(Rational.ONE, Rational.ZERO) *
                    RationalMValue(Rational.ONE, Rational.ZERO)
        }
        assertEquals(
            "Cannot multiply [(1 * M + 0) * (1 * M + 0)]: at least one must have a zero `M` part.",
            expected.message
        )
    }

    @Test
    fun div_returnsQuotient() {
        assertEquals(
            RationalMValue(Rational.create(2), Rational.create(1, 3)),
            RationalMValue(Rational.create(4), Rational.create(2, 3)) /
                    RationalMValue(Rational.ZERO, Rational.create(2))
        )
    }

    @Test
    fun div_withMValue_cancelsValuePart() {
        assertEquals(
            RationalMValue(Rational.ZERO, Rational.create(2)),
            RationalMValue(Rational.create(4), Rational.create(3)) /
                    RationalMValue(Rational.create(2), Rational.create(5))
        )
    }

    @Test
    fun compareTo_comparesByMFirst() {
        assertTrue {
            RationalMValue(Rational.create(3), Rational.create(1)) >
                    RationalMValue(Rational.create(2), Rational.create(2))
        }
    }
}