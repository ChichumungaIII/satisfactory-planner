package util.math.linprog

import util.math.Rational
import util.math.RationalExpression
import util.math.q
import util.math.toRational
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RationalSimplexTest {
    @Test
    fun minimize_brilliant_intro() {
        val c1 = RationalConstraint.Builder<String>().set(
            "c" to Rational.create(1, 10),
            "w" to Rational.create(15, 100),
        ).atLeast(Rational.create(65, 100))
        val c2 = RationalConstraint.Builder<String>().set(
            "c" to Rational.create(75, 100),
            "w" to Rational.create(7, 10),
        ).atLeast(Rational.create(4))
        val c3 = RationalConstraint.Builder<String>().set(
            "c" to Rational.ONE,
            "w" to Rational.ONE,
        ).atMost(Rational.create(7))

        val objective = RationalExpression.Builder<String>().set(
            "c" to Rational.create(40, 100),
            "w" to Rational.create(45, 100),
        ).build()

        val solution = minimize(objective, c1, c2, c3)

        assertEquals(
            mapOf(
                "c" to Rational.create(58, 17),
                "w" to Rational.create(35, 17),
            ),
            solution
        )
        assertEquals(Rational.create(779, 340), objective(solution))
    }

    @Test
    fun maximize_brilliant_simple() {
        val c1 = RationalConstraint.Builder<String>().set(
            "x" to 2.toRational(),
            "y" to 3.toRational(),
        ).atMost(90.toRational())
        val c2 = RationalConstraint.Builder<String>().set(
            "x" to 3.toRational(),
            "y" to 2.toRational(),
        ).atMost(120.toRational())

        val objective = RationalExpression.Builder<String>().set(
            "x" to 7.toRational(),
            "y" to 5.toRational(),
        ).build()

        val solution = maximize(objective, c1, c2)

        assertEquals(
            mapOf(
                "x" to 36.toRational(),
                "y" to 6.toRational()
            ), solution
        )
        assertEquals(282.toRational(), objective(solution))
    }

    @Test
    fun minimize_brilliant_simple() {
        val c1 = RationalConstraint.Builder<String>().set(
            "x" to 4.toRational(),
            "y" to 3.toRational(),
            "z" to 5.toRational(),
        ).atLeast(65.toRational())
        val c2 = RationalConstraint.Builder<String>().set(
            "x" to Rational.ONE,
            "y" to 3.toRational(),
            "z" to 2.toRational(),
        ).atLeast(38.toRational())
        val c3 = RationalConstraint.Builder<String>().set(
            "x" to 2.toRational(),
            "y" to 3.toRational(),
            "z" to 4.toRational(),
        ).atLeast(52.toRational())

        val objective = RationalExpression.Builder<String>().set(
            "x" to 12.toRational(),
            "y" to 3.toRational(),
            "z" to 10.toRational(),
        ).build()

        val solution = minimize(objective, c1, c2, c3)

        assertEquals(
            mapOf(
                "x" to Rational.ZERO,
                "y" to Rational.create(65, 3),
                "z" to Rational.ZERO
            ),
            solution
        )
    }

    @Test
    fun minimize_brilliant_bigM() {
        val c1 = RationalConstraint.Builder<String>().set(
            "x" to -Rational.ONE,
            "y" to 5.toRational(),
        ).atMost(25.toRational())
        val c2 = RationalConstraint.Builder<String>().set(
            "x" to 6.toRational(),
            "y" to 5.toRational(),
        ).atMost(60.toRational())
        val c3 = RationalConstraint.Builder<String>().set(
            "x" to Rational.ONE,
            "y" to Rational.ONE,
        ).atLeast(2.toRational())

        val objective = RationalExpression.Builder<String>().set(
            "x" to Rational.ONE,
            "y" to (-10).toRational(),
        ).build()

        val solution = minimize(objective, c1, c2, c3)

        assertEquals(
            mapOf(
                "x" to 5.toRational(),
                "y" to 6.toRational(),
            ),
            solution
        )
        assertEquals((-55).toRational(), objective(solution))
    }

    @Test
    fun minimize_brilliant_bigM_example() {
        val c1 = RationalConstraint.Builder<String>().set(
            "md" to Rational.ONE,
            "pd" to Rational.ONE,
        ).atMost(40.toRational())
        val c2 = RationalConstraint.Builder<String>().set(
            "ml" to Rational.ONE,
        ).atMost(40.toRational())
        val c3 = RationalConstraint.Builder<String>().set(
            "pd" to Rational.ONE,
        ).atLeast(20.toRational())
        val c4 = RationalConstraint.Builder<String>().set(
            "md" to 5.toRational(),
            "ml" to 4.toRational(),
            "pd" to (-10).toRational(),
        ).equalTo(Rational.ZERO)

        val objective = RationalExpression.Builder<String>().set(
            "md" to 12.toRational(),
            "ml" to 9.toRational(),
            "pd" to 12.toRational(),
        ).build()

        val solution = minimize(objective, c1, c2, c3, c4)

        assertEquals(
            mapOf(
                "md" to 8.toRational(),
                "ml" to 40.toRational(),
                "pd" to 20.toRational(),
            ),
            solution
        )
        assertEquals(696.toRational(), objective(solution))
    }

    @Test
    fun maximize_infeasible() {
        val ingots = RationalConstraint.Builder<String>().set(
            "PLATE" to (-30).q,
            "INGOT" to 30.q,
            "ROD" to (-15).q
        ).atLeast(0.q)
        val ore = RationalConstraint.Builder<String>().set(
            "INGOT" to 30.q
        ).atMost(60.q)
        val plates = RationalConstraint.Builder<String>().set(
            "PLATE" to 20.q
        ).atLeast(30.q)
        val rods = RationalConstraint.Builder<String>().set(
            "ROD" to 15.q
        ).atLeast(0.q)

        val couple = RationalConstraint.equalTo(
            plates.expression - rods.expression,
            0.q,
        )

        val objective = plates.expression

        val failure =
            assertFailsWith<InfeasibleSolutionException> { maximize(objective, ingots, ore, plates, rods, couple) }
        assertContains(failure.message!!, "artificial variable had a value greater than zero")
    }

    @Test
    fun maximize_toLimit() {
        val ingots = RationalConstraint.Builder<String>().set(
            "PLATE" to (-30).q,
            "INGOT" to 30.q,
            "ROD" to (-15).q
        ).atLeast(0.q)
        val ore = RationalConstraint.Builder<String>().set(
            "INGOT" to 30.q
        ).atMost(60.q)
        val plates = RationalConstraint.Builder<String>().set(
            "PLATE" to 20.q
        ).atLeast(24.q)
        val rods = RationalConstraint.Builder<String>().set(
            "ROD" to 15.q
        ).atLeast(24.q)

        val couple = RationalConstraint.equalTo(
            plates.expression - rods.expression,
            0.q,
        )

        val objective = plates.expression

        val solution = maximize(objective, ingots, ore, plates, rods, couple)

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
    fun minimize_unconstrained() {
        val ingots = RationalConstraint.Builder<String>().set(
            "PLATE" to (-30).q,
            "INGOT" to 30.q,
            "ROD" to (-15).q
        ).atLeast(0.q)
        val ore = RationalConstraint.Builder<String>().set(
            "INGOT" to 30.q
        ).atMost(60.q)
        val plates = RationalConstraint.Builder<String>().set(
            "PLATE" to 20.q
        ).atLeast(0.q)
        val rods = RationalConstraint.Builder<String>().set(
            "ROD" to 15.q
        ).atLeast(0.q)

        val objective = ore.expression

        val solution = minimize(objective, ingots, ore, plates, rods)

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