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
        private var comparison: Comparison? = null
        private var result: Rational? = null

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

        fun atLeast(result: Rational) {
            comparison = Comparison.AT_LEAST
            this.result = result
        }

        fun atMost(result: Rational) {
            comparison = Comparison.AT_MOST
            this.result = result
        }

        fun equalTo(result: Rational) {
            comparison = Comparison.EQUAL_TO
            this.result = result
        }

        /** Builds the [RationalConstraint]. */
        fun build() = RationalConstraint(
            expression.build(),
            checkNotNull(comparison) { "Comparison cannot be null." },
            checkNotNull(result) { "Result cannot be null." }
        )
    }
}