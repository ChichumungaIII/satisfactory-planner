package util.math.linprog

import util.math.Rational

/**
 * An MValue
 */
class MValue<T: Number> private constructor(
    private val m: Rational,
    private val r: Rational,
) {
    companion object {

    }
}