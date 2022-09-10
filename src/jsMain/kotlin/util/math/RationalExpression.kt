package util.math

/**
 * A RationalExpression represents the mathematical form `A * x_0 + B * x_1 + C * x_2 + ...`, where terms `x_n` are
 * paired with [Rational] numbers `A`, `B`, `C`, and so forth.
 *
 * Terms may be identified through any arbitrary type.
 *
 * @property terms
 * @constructor
 */
class RationalExpression<T> private constructor(
    private val terms: Map<T, Rational>,
) {
    /** Builder for [RationalExpression]. */
    class Builder<T> {
        private val terms = mutableMapOf<T, Rational>()

        /** Sets [terms] in this expression. */
        fun set(vararg terms: Pair<T, Rational>): Builder<T> {
            terms.forEach { (term, value) -> set(term, value) }
            return this
        }

        /** Sets the [Rational] associated with [term] in this expression. */
        fun set(term: T, value: Rational): Builder<T> {
            terms[term] = value
            return this
        }

        /** Builds the [RationalExpression]. */
        fun build() = RationalExpression(terms)
    }

    /** Enumerates the explicitly-defined terms contained in this [RationalExpression]. */
    fun terms() = terms.keys

    /**
     * Gets the constant [Rational] associated with [term] in this [RationalExpression], or [Rational.ZERO] if this
     * expression does not contain an association for [term].
     */
    fun get(term: T) = terms[term] ?: Rational.ZERO

    operator fun unaryPlus() = this
    operator fun unaryMinus() = RationalExpression(terms.mapValues { (_, v) -> -v }.toMap())

    operator fun plus(other: RationalExpression<T>) = join(other, Rational::plus)
    operator fun minus(other: RationalExpression<T>) = join(other, Rational::minus)

    private fun join(other: RationalExpression<T>, merge: (Rational, Rational) -> Rational) =
        RationalExpression((terms() + other.terms()).associateWith { term -> merge(get(term), other.get(term)) })

    operator fun invoke(data: Map<T, Rational>) =
        terms.map { (term, value) -> value * (data[term] ?: Rational.ZERO) }.reduceOrNull(Rational::plus)
            ?: Rational.ZERO

    override fun equals(other: Any?) =
        other is RationalExpression<*> && terms == other.terms

    override fun hashCode() =
        terms.entries.map(Map.Entry<T, Rational>::hashCode)
            .reduceOrNull { l, r -> l * 31 + r } ?: 0

    override fun toString() =
        terms.entries.joinToString(" + ") { (term, value) -> "$value * $term" }
}