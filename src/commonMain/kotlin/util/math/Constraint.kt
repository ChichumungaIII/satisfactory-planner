package util.math

class Constraint<V, N : Numeric<N>> private constructor(
    val expression: Expression<V, N>,
    val comparison: Comparison,
    val result: N,
) {
    companion object {
        fun <V, N : Numeric<N>> atLeast(expression: Expression<V, N>, result: N) =
            Constraint(expression, Comparison.AT_LEAST, result)

        fun <V, N : Numeric<N>> atMost(expression: Expression<V, N>, result: N) =
            Constraint(expression, Comparison.AT_MOST, result)

        fun <V, N : Numeric<N>> equalTo(expression: Expression<V, N>, result: N) =
            Constraint(expression, Comparison.EQUAL_TO, result)
    }

    enum class Comparison {
        /** Expression must be at least the result; i.e. >= */
        AT_LEAST,

        /** Expression can be at most the result; i.e. <= */
        AT_MOST,

        /** Expression must be exactly the result; i.e. = */
        EQUAL_TO,
    }

    fun test(solution: Map<V, N>) = when (comparison) {
        Comparison.AT_LEAST -> expression(solution) >= result
        Comparison.AT_MOST -> expression(solution) <= result
        Comparison.EQUAL_TO -> expression(solution) == result
    }

    override fun toString() =
        "$expression " + when (comparison) {
            Comparison.AT_LEAST -> ">="
            Comparison.AT_MOST -> "<="
            Comparison.EQUAL_TO -> "="
        } + " $result"
}
