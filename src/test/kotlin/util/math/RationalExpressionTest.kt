package util.math

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class RationalExpressionTest {
    enum class Term {
        X, Y, Z
    }

    @Test
    fun terms_returnsAllTerms() {
        val expression = RationalExpression.Builder<Term>().set(
            Term.X to Rational.ZERO,
            Term.Z to Rational.ONE
        ).build()
        assertEquals(setOf(Term.X, Term.Z), expression.terms())
    }

    @Test
    fun get_returnsCorrespondingValue() {
        val expression = RationalExpression.Builder<Term>().set(Term.Y, Rational.create(2, 3)).build()
        assertEquals(Rational.create(2, 3), expression.get(Term.Y))
    }

    @Test
    fun get_returnsZeroDefault() {
        val expression = RationalExpression.Builder<Term>().build()
        assertEquals(Rational.ZERO, expression.get(Term.Y))
    }

    @Test
    fun unaryPlus_returnsSameInstance() {
        val expression = RationalExpression.Builder<Term>().build()
        assertSame(expression, +expression)
    }

    @Test
    fun unaryMinus_returnsNegation() {
        val expression = RationalExpression.Builder<Term>().set(
            Term.X to Rational.create(1, 3),
            Term.Z to Rational.create(3, 4)
        ).build()
        val expected = RationalExpression.Builder<Term>().set(
            Term.X to Rational.create(-1, 3),
            Term.Z to Rational.create(-3, 4)
        ).build()
        assertEquals(expected, -expression)
    }

    @Test
    fun plus_addsCorrespondingTerms() {
        val expression1 = RationalExpression.Builder<Term>().set(
            Term.X to Rational.create(1, 2),
            Term.Y to Rational.create(2, 3),
        ).build()
        val expression2 = RationalExpression.Builder<Term>().set(
            Term.Y to Rational.create(1, 3),
            Term.Z to Rational.create(3, 5),
        ).build()
        val expected = RationalExpression.Builder<Term>().set(
            Term.X to Rational.create(1, 2),
            Term.Y to Rational.create(3, 3),
            Term.Z to Rational.create(3, 5),
        ).build()
        assertEquals(expected, expression1 + expression2)
    }

    @Test
    fun minus_subtractsCorrespondingTerms() {
        val expression1 = RationalExpression.Builder<Term>().set(
            Term.X to Rational.create(1, 2),
            Term.Y to Rational.create(2, 3),
        ).build()
        val expression2 = RationalExpression.Builder<Term>().set(
            Term.Y to Rational.create(1, 3),
            Term.Z to Rational.create(3, 5),
        ).build()
        val expected = RationalExpression.Builder<Term>().set(
            Term.X to Rational.create(1, 2),
            Term.Y to Rational.create(1, 3),
            Term.Z to Rational.create(-3, 5),
        ).build()
        assertEquals(expected, expression1 - expression2)
    }

    @Test
    fun invoke_evaluatesExpressionWithData() {
        val expression = RationalExpression.Builder<Term>().set(
            Term.X to Rational.create(2),
            Term.Y to Rational.create(5),
        ).build()
        val data = mapOf(
            Term.X to Rational.create(4),
            Term.Z to Rational.create(9)
        )
        assertEquals(Rational.create(8), expression(data))
    }
}