package util.math.linprog

import util.math.Rational
import util.math.RationalExpression

class RationalConstraint<T> private constructor(
    val expression: RationalExpression<T>,
    val comparison: Comparison,
    val result: Rational,
) {
    enum class Comparison {
        /** Expression must be at least the result; i.e. >= */
        AT_LEAST,

        /** Expression can be at most the result; i.e. <= */
        AT_MOST,

        /** Expression must be exactly the result; i.e. = */
        EQUAL_TO,
    }

    class Builder<T> {
        private val expression = RationalExpression.Builder<T>()

        /** Sets [terms] in this constraint's [RationalExpression]. */
        fun set(vararg terms: Pair<T, Rational>): Builder<T> {
            expression.set(*terms)
            return this
        }

        /** Sets the [Rational] associated with [term] in this constraint's [RationalExpression]. */
        fun set(term: T, value: Rational): Builder<T> {
            expression.set(term, value)
            return this
        }

        fun atLeast(result: Rational) = build(Comparison.AT_LEAST, result)
        fun atMost(result: Rational) = build(Comparison.AT_MOST, result)
        fun equalTo(result: Rational) = build(Comparison.EQUAL_TO, result)

        /** Builds the [RationalConstraint]. */
        private fun build(comparison: Comparison, result: Rational) =
            RationalConstraint(expression.build(), comparison, result)
    }
}