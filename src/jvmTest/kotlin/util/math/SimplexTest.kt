package util.math

import kotlinx.coroutines.runBlocking
import util.math.Expression.Companion.times
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SimplexTest {
    @Test
    fun minimize_brilliant_intro() = runBlocking {
        val c1 = Constraint.atLeast((1.q / 10.q) * "c" + (15.q / 100.q) * "w", (65.q / 100.q))
        val c2 = Constraint.atLeast((75.q / 100.q) * "c" + (7.q / 10.q) * "w", 4.q)
        val c3 = Constraint.atMost(1.q * "c" + 1.q * "w", 7.q)

        val objective = (40.q / 100.q) * "c" + (45.q / 100.q) * "w"

        val solution = minimize(objective, listOf(c1, c2, c3), Rational.FACTORY)

        assertEquals(
            mapOf(
                "c" to 58.q / 17.q,
                "w" to 35.q / 17.q,
            ), solution
        )
        assertEquals((779.q / 340.q), objective(solution))
    }

    @Test
    fun maximize_brilliant_simple() = runBlocking {
        val c1 = Constraint.atMost(2.q * "x" + 3.q * "y", 90.q)
        val c2 = Constraint.atMost(3.q * "x" + 2.q * "y", 120.q)

        val objective = 7.q * "x" + 5.q * "y"

        val solution = maximize(objective, listOf(c1, c2), Rational.FACTORY)

        assertEquals(
            mapOf(
                "x" to 36.q,
                "y" to 6.q,
            ), solution
        )
        assertEquals(282.q, objective(solution))
    }

    @Test
    fun minimize_brilliant_simple() = runBlocking {
        val c1 = Constraint.atLeast(4.q * "x" + 3.q * "y" + 5.q * "z", 65.q)
        val c2 = Constraint.atLeast(1.q * "x" + 3.q * "y" + 2.q * "z", 38.q)
        val c3 = Constraint.atLeast(2.q * "x" + 3.q * "y" + 4.q * "z", 52.q)

        val objective = 12.q * "x" + 3.q * "y" + 10.q * "z"

        val solution = minimize(objective, listOf(c1, c2, c3), Rational.FACTORY)

        assertEquals(
            mapOf(
                "x" to 0.q,
                "y" to (65.q / 3.q),
                "z" to 0.q,
            ), solution
        )
    }

    @Test
    fun minimize_brilliant_bigM() = runBlocking {
        val c1 = Constraint.atMost((-1).q * "x" + 5.q * "y", 25.q)
        val c2 = Constraint.atMost(6.q * "x" + 5.q * "y", 60.q)
        val c3 = Constraint.atLeast(1.q * "x" + 1.q * "y", 2.q)

        val objective = 1.q * "x" + (-10).q * "y"

        val solution = minimize(objective, listOf(c1, c2, c3), Rational.FACTORY)

        assertEquals(
            mapOf(
                "x" to 5.q,
                "y" to 6.q,
            ), solution
        )
        assertEquals((-55).q, objective(solution))
    }

    @Test
    fun minimize_brilliant_bigM_example() = runBlocking {
        val c1 = Constraint.atMost(1.q * "md" + 1.q * "pd", 40.q)
        val c2 = Constraint.atMost(1.q * "ml", 40.q)
        val c3 = Constraint.atLeast(1.q * "pd", 20.q)
        val c4 = Constraint.equalTo(5.q * "md" + 4.q * "ml" + (-10).q * "pd", 0.q)

        val objective = 12.q * "md" + 9.q * "ml" + 12.q * "pd"

        val solution = minimize(objective, listOf(c1, c2, c3, c4), Rational.FACTORY)

        assertEquals(
            mapOf(
                "md" to 8.q,
                "ml" to 40.q,
                "pd" to 20.q,
            ), solution
        )
        assertEquals(696.q, objective(solution))
    }

    @Test
    fun maximize_infeasible() = runBlocking {
        val ingots = Constraint.atLeast((-30).q * "PLATE" + 30.q * "INGOT" + (-15).q * "ROD", 0.q)
        val ore = Constraint.atLeast((-30).q * "INGOT", (-60).q)
        val plates = Constraint.atLeast(20.q * "PLATE", 30.q)
        val rods = Constraint.atLeast(15.q * "ROD", 0.q)
        val couple = Constraint.equalTo(plates.expression - rods.expression, 0.q)

        val objective = plates.expression

        val failure =
            assertFailsWith<InfeasibleSolutionException> {
                maximize(
                    objective,
                    listOf(ingots, ore, plates, rods, couple),
                    Rational.FACTORY
                )
            }
        assertContains(failure.message!!, "artificial variable had a value greater than zero")
    }

    @Test
    fun maximize_toLimit() = runBlocking {
        val ingots = Constraint.atLeast((-30).q * "PLATE" + 30.q * "INGOT" + (-15).q * "ROD", 0.q)
        val ore = Constraint.atLeast((-30).q * "INGOT", (-60).q)
        val plates = Constraint.atLeast(20.q * "PLATE", 24.q)
        val rods = Constraint.atLeast(15.q * "ROD", 24.q)
        val couple = Constraint.equalTo(plates.expression - rods.expression, 0.q)

        val objective = plates.expression

        val solution = maximize(objective, listOf(ingots, ore, plates, rods, couple), Rational.FACTORY)

        assertEquals(
            mapOf(
                "PLATE" to 6.q / 5.q,
                "INGOT" to 2.q,
                "ROD" to 8.q / 5.q,
            ),
            solution,
        )
        assertEquals(24.q, plates.expression(solution))
        assertEquals(24.q, rods.expression(solution))
    }

    @Test
    fun minimize_unconstrained() = runBlocking {
        val ingots = Constraint.atLeast((-30).q * "PLATE" + 30.q * "INGOT" + (-15).q * "ROD", 0.q)
        val ore = Constraint.atLeast((-30).q * "INGOT", (-60).q)
        val plates = Constraint.atLeast(20.q * "PLATE", 0.q)
        val rods = Constraint.atLeast(15.q * "ROD", 0.q)

        val objective = ore.expression

        val solution = minimize(-objective, listOf(ingots, ore, plates, rods), Rational.FACTORY)

        assertEquals(
            mapOf(
                "PLATE" to 0.q,
                "INGOT" to 0.q,
                "ROD" to 0.q,
            ),
            solution,
        )
    }
}