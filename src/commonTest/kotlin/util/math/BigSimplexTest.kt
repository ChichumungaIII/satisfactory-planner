package util.math

import kotlinx.coroutines.async
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import util.math.Expression.Companion.times
import kotlin.test.Test
import kotlin.test.assertEquals

class BigSimplexTest {
    @Test
    fun profile_largeOptimization() = runBlocking {
        val count = 400

        var objective = 1.q * "x1"
        var constraints = mutableListOf(Constraint.atMost(1.q * "x1", 1.q))
        for (i in 2..count) {
            val expression = 1.q * "x$i"
            objective += expression
            constraints.add(Constraint.atMost(expression, i.q))
        }

        val solution = async(newFixedThreadPoolContext(12, "MyPool")) {
            maximize(objective, constraints, Rational.FACTORY)
        }.await()

        assertEquals(((count * count + count) / 2).q, objective(solution))
    }
}
