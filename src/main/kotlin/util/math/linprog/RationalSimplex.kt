package util.math.linprog

import util.math.MutableMatrix
import util.math.Rational
import util.math.RationalExpression
import util.math.linprog.RationalConstraint.Comparison

/** Companion method to [maximize]. Computes the minimum solution to [objective]. */
fun <T> minimize(objective: RationalExpression<T>, vararg constraints: RationalConstraint<T>) =
    maximize(-objective, *constraints)

/**
 * Implementation of the Big-M simplex method to optimize [objective] given a set of [constraints].
 *
 * See https://brilliant.org/wiki/linear-programming/ for greater details on how the algorithm works.
 *
 * @param objective The expression to maximize.
 * @param constraints Restrictions that the solution cannot violate.
 * @return A Map containing the values to which each term in the objective and constraints should be set in order to
 *         maximize the objective function.
 */
fun <T> maximize(
    objective: RationalExpression<T>,
    vararg constraints: RationalConstraint<T>,
): Map<T, Rational> {
    // Terms are the variables in the expressions. Each one can be manipulated to maximize the objective function, and
    // each one needs a corresponding output value in the resulting map.
    val terms = (objective.terms() + constraints.flatMap { it.expression.terms() }.toSet()).toList()

    // Each row corresponds with a constraint function, except the last row, which corresponds with the objective.
    // Each column corresponds with a term, except the last column, which contain the constraint restriction values.
    val matrix = MutableMatrix.create(constraints.size + 1, terms.size + 1) { row, column ->
        if (column < terms.size) {
            val term = terms[column]
            if (row < constraints.size) constraints[row].expression.get(term).toMValue()
            // Negate the objective terms. We introduce variable `z` to represent the value of the objective function, which
            // gives "z = <objective>" --> "z - objective = 0" --> we insert "-objective = 0" into the matrix.
            else -objective.get(term).toMValue()
        } else {
            if (row < constraints.size) constraints[row].result.toMValue()
            else RationalMValue.ZERO
        }
    }

    // Introduce slack variables. Slack variables transform inequality constraints into equality constraints.
    for ((row, constraint) in constraints.withIndex()) {
        val slack = when (constraint.comparison) {
            Comparison.AT_LEAST -> -RationalMValue.ONE
            Comparison.AT_MOST -> RationalMValue.ONE
            Comparison.EQUAL_TO -> continue // Don't add a slack variable
        }

        val column = matrix.columns() - 1
        matrix.addColumn(column, RationalMValue.ZERO)
        matrix.set(row, column, slack)
    }

    // Introduce artificial variables, which move our basic solution into the feasible region.
    for ((row, constraint) in constraints.withIndex()) {
        if (constraint.comparison == Comparison.AT_LEAST || constraint.comparison == Comparison.EQUAL_TO) {
            val column = matrix.columns() - 1
            matrix.addColumn(column, RationalMValue.ZERO)
            matrix.set(row, column, RationalMValue.ONE)
            matrix.set(matrix.rows() - 1, column, RationalMValue.M)

            // Pivot on the new artificial variable to bring the basic solution into the feasible region. This is safe
            // to do prior to completing matrix setup because at this stage, pivoting on artificial variables cannot
            // affect other artificial variables due to the structure of the matrix.
            matrix.pivot(row, column)
        }
    }

    // Find the solution: pivot on the optimal cell until none of the objective values are negative, which implies any
    // additional pivots would change to a worse solution.
    while (matrix.row(matrix.rows() - 1).take(matrix.columns() - 1).any { it < RationalMValue.ZERO }) {
        // The ideal column is the one with the "most negative" coefficient.
        val column = matrix.row(matrix.rows() - 1).withIndex()
            .take(matrix.columns() - 1)
            .minBy { it.value }
            .index

        // Ensure coefficients are always positive.
        matrix.column(matrix.columns() - 1).withIndex()
            .take(matrix.rows() - 1)
            .filter { (_, coefficient) -> coefficient < RationalMValue.ZERO }
            .forEach { (row, _) -> matrix.update(row, matrix.columns() - 1) { -it } }

        // The ideal row minimizes (right-hand side) / (positive coefficient) in that column.
        val row = matrix.column(column).withIndex()
            .take(matrix.rows() - 1)
            .filter { (_, cell) -> cell > RationalMValue.ZERO }
            .minByOrNull { (row, cell) -> matrix.get(row, matrix.columns() - 1) / cell }
            ?.index
            ?: error("Could not find a suitable pivot in column [$column].")

        matrix.pivot(row, column)
    }

    // Extract the solution from the matrix. Terms are in the solution with a nonzero value if their corresponding
    // column is all zeros except for a single row, in which case their value is (right-hand side) / (that coefficient).
    return terms.withIndex().associate { (column, term) ->
        term to (matrix.column(column).withIndex()
            .filterNot { it.value == RationalMValue.ZERO }
            .map { (row, cell) -> matrix.get(row, matrix.columns() - 1) / cell }
            .singleOrNull()
            ?.toRational()
            ?: Rational.ZERO)
    }
}

private fun MutableMatrix<RationalMValue>.pivot(pivotRow: Int, pivotColumn: Int) {
    for (row in 0 until rows()) {
        if (row == pivotRow) {
            // This isn't necessary, but I find it tidy to normalize the rows of variables in the solution.
            val divisor = get(row, pivotColumn)
            for (column in 0 until columns()) {
                update(row, column) { (it / divisor).norm() }
            }
        } else {
            // Standard matrix row operation. Subtract the pivot row from the current row so that (row, column) is 0.
            val scalar = get(row, pivotColumn) / get(pivotRow, pivotColumn)
            for (column in 0 until columns()) {
                update(row, column) { (it - scalar * get(pivotRow, column)).norm() }
            }
        }
    }
}
