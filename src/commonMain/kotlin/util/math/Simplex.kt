package util.math

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import util.math.Constraint.Comparison

/** Companion method to [maximize]. Computes the minimum solution to [objective]. */
fun <V, N : Numeric<N>> minimize(
  objective: Expression<V, N>,
  constraints: List<Constraint<V, N>>,
  numbers: Numeric.Factory<N>,
) = maximize(-objective, constraints, numbers)

/**
 * Implementation of the Big-M simplex method to optimize [objective] given a set of [rawConstraints].
 *
 * See https://brilliant.org/wiki/linear-programming/ for greater details on how the algorithm works.
 *
 * @param objective The expression to maximize.
 * @param rawConstraints Restrictions that the solution cannot violate.
 * @return A Map containing the values to which each term in the objective and constraints should be set in order to
 *         maximize the objective function.
 */
fun <V, N : Numeric<N>> maximize(
  objective: Expression<V, N>,
  rawConstraints: List<Constraint<V, N>>,
  numbers: Numeric.Factory<N>,
): Map<V, N> {
  val constraints = rawConstraints.map { it.normalize(numbers) }

  // Terms are the variables in the expressions. Each one can be manipulated to maximize the objective function, and
  // each one needs a corresponding output value in the resulting map.
  val terms = (objective.terms + constraints.flatMap { it.expression.terms }.toSet()).toList()

  // Each row corresponds with a constraint function, except the last row, which corresponds with the objective.
  // Each column corresponds with a term, except the last column, which contain the constraint restriction values.
  val matrix = MutableMatrix.create(constraints.size + 1, terms.size + 1) { row, column ->
    if (column < terms.size) {
      val term = terms[column]
      if (row < constraints.size) MValue.from(constraints[row].expression.get(term), numbers)
      // Negate the objective terms. We introduce variable `z` to represent the value of the objective function, which
      // gives "z = <objective>" --> "z - objective = 0" --> we insert "-objective = 0" into the matrix.
      else MValue.from(-objective.get(term), numbers)
    } else {
      if (row < constraints.size) MValue.from(constraints[row].result, numbers)
      else MValue.zilch(numbers)
    }
  }

  // Introduce slack variables. Slack variables transform inequality constraints into equality constraints.
  for ((row, constraint) in constraints.withIndex()) {
    val slack = when (constraint.comparison) {
      Comparison.AT_LEAST -> MValue.from(-numbers.unit(), numbers)
      Comparison.AT_MOST -> MValue.from(numbers.unit(), numbers)
      Comparison.EQUAL_TO -> continue // Don't add a slack variable
    }

    val column = matrix.columns() - 1
    matrix.addColumn(column, MValue.zilch(numbers))
    matrix.set(row, column, slack)
  }

  // Introduce artificial variables, which move our basic solution into the feasible region.
  val artificialColumns = mutableListOf<Int>()
  for ((row, constraint) in constraints.withIndex()) {
    if (constraint.comparison == Comparison.AT_LEAST || constraint.comparison == Comparison.EQUAL_TO) {
      val column = matrix.columns() - 1
      matrix.addColumn(column, MValue.zilch(numbers))
      matrix.set(row, column, MValue.from(numbers.unit(), numbers))
      matrix.set(matrix.rows() - 1, column, MValue.ofM(numbers.unit(), numbers))
      artificialColumns.add(column)

      // Pivot on the new artificial variable to bring the basic solution into the feasible region. This is safe
      // to do prior to completing matrix setup because at this stage, pivoting on artificial variables cannot
      // affect other artificial variables due to the structure of the matrix.
      matrix.pivot(row, column)
    }
  }

  // Find the solution: pivot on the optimal cell until none of the objective values are negative, which implies any
  // additional pivots would change to a worse solution.
  while (matrix.row(matrix.rows() - 1).take(matrix.columns() - 1).any { it < MValue.zilch(numbers) }) {
    // The ideal column is the one with the "most negative" coefficient.
    val column = matrix.row(matrix.rows() - 1).withIndex().take(matrix.columns() - 1).minBy { it.value }.index

    // Ensure coefficients are always positive.
    matrix.column(matrix.columns() - 1).withIndex().take(matrix.rows() - 1)
      .filter { (_, coefficient) -> coefficient < MValue.zilch(numbers) }
      .forEach { (row, _) -> matrix.update(row, matrix.columns() - 1) { -it } }

    // The ideal row minimizes (right-hand side) / (positive coefficient) in that column.
    val row =
      matrix.column(column).withIndex().take(matrix.rows() - 1)
        .filter { (_, cell) -> cell > MValue.zilch(numbers) }
        .minByOrNull { (row, cell) -> matrix.get(row, matrix.columns() - 1) / cell }?.index
        ?: error("Could not find a suitable pivot in column [$column].")

    matrix.pivot(row, column)
  }

  // If an artificial variable is nonzero in the solution, the solution is infeasible.
  val artificialInfeasible = artificialColumns.any { column ->
    matrix.column(column).withIndex().filterNot { it.value == MValue.zilch(numbers) }
      .map { (row, cell) -> matrix.get(row, matrix.columns() - 1) / cell }.singleOrNull()
      ?.takeUnless { it == MValue.zilch(numbers) } != null
  }
  if (artificialInfeasible) {
    throw InfeasibleSolutionException("An artificial variable had a value greater than zero.")
  }

  // Extract the solution from the matrix. Terms are in the solution if their corresponding column is all zeros except
  // for a single one, in which case their value is the right-hand side.
  return terms.withIndex().associate { (column, term) ->
    term to (matrix.column(column).withIndex().filterNot { it.value == MValue.zilch(numbers) }
      .singleOrNull()
      ?.takeIf { (_, cell) -> cell.toNumeric() == numbers.unit() }
      ?.let { (row, _) -> matrix.get(row, matrix.columns() - 1) }
      ?.toNumeric()
      ?: numbers.zilch())
  }
}

class InfeasibleSolutionException(message: String) : Exception(message)

private fun <V, N : Numeric<N>> Constraint<V, N>.normalize(numbers: Numeric.Factory<N>) =
  if (result > numbers.zilch()) this
  else if (result == numbers.zilch() && comparison == Comparison.AT_LEAST)
    Constraint.atMost(-expression, -result)
  else when (comparison) {
    Comparison.AT_LEAST -> Constraint.atMost(-expression, -result)
    Comparison.AT_MOST -> Constraint.atLeast(-expression, -result)
    Comparison.EQUAL_TO -> Constraint.equalTo(-expression, -result)
  }

private fun <N : Numeric<N>> MutableMatrix<MValue<N>>.pivot(pivotRow: Int, pivotColumn: Int) {
  // Normalize the rows of variables in the solution so that the variables in the solution can be identified as
  // the only 1 in a column that's otherwise 0s.
  val divisor = get(pivotRow, pivotColumn)
  for (column in 0 until columns()) {
    update(pivotRow, column) { (it / divisor) }
  }

  (0 until rows()).filterNot { it == pivotRow }.map { row ->
    // Standard matrix row operation. Subtract the pivot row from the current row so that (row, column) is 0.
    val scalar = get(row, pivotColumn) / get(pivotRow, pivotColumn)
    for (column in 0 until columns()) {
      update(row, column) { (it - scalar * get(pivotRow, column)) }
    }
  }
}

// Simplex solver to help with bugs: https://cbom.atozmath.com/CBOM/Simplex.aspx
private fun <T> MutableMatrix<T>.debug(pad: Int = 10) {
  val cells = mutableListOf<MutableList<String>>()
  for (row in 0 until rows()) {
    cells.add(mutableListOf())
    row(row).forEach { value -> cells[row].add(value.toString()) }
  }
  val debug = cells.joinToString("\n") { row ->
    row.joinToString(" | ") { it.padStart(pad, ' ') }
  }
  println(".\n\n$debug\n")
}

