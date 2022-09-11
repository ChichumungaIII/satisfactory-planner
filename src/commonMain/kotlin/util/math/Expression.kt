package util.math

class Expression<T, N : Numeric<N>> private constructor(
    private val coefficients: Map<T, N>,
    private val numbers: Numeric.Factory<N>,
) {
    companion object {
        operator fun <V, N : Numeric<N>> N.times(v: V) = Expression(mapOf(v to this), this.factory())
    }

    val terms = coefficients.keys
    fun get(term: T) = coefficients[term] ?: numbers.zilch()

    operator fun unaryPlus() = this
    operator fun unaryMinus() = Expression(coefficients.mapValues { (_, n) -> -n }.toMap(), numbers)

    operator fun plus(other: Expression<T, N>) = join(other, Numeric<N>::plus)
    operator fun minus(other: Expression<T, N>) = join(other, Numeric<N>::minus)
    private fun join(other: Expression<T, N>, merge: (N, N) -> N) =
        Expression((terms + other.terms).associateWith { term -> merge(get(term), other.get(term)) }, numbers)

    operator fun invoke(values: Map<T, N>) =
        coefficients.map { (term, c) -> c * (values[term] ?: numbers.zilch()) }.reduce(Numeric<N>::plus)

    override fun equals(other: Any?) = other is Expression<*, *> && coefficients == other.coefficients
    override fun hashCode() = coefficients.map { it.hashCode() }.reduce { acc, i -> acc * 31 + i }
    override fun toString() = coefficients.entries.joinToString(" + ") { (term, value) -> "$value * $term" }
}
