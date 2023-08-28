package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequest.Objective
import app.game.data.Item
import app.game.data.Recipe
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import util.math.Constraint
import util.math.Expression
import util.math.maximize

class Optimizer(
  private val expressions: Map<Item, Expression<Recipe, BigRational>>,
  private val constraints: List<Constraint<Recipe, BigRational>>,
  private val productConstraints: Map<Item, Constraint<Recipe, BigRational>>,
) {
  suspend fun computePrincipalSolution(
    objectives: List<Objective>,
    demands: Map<Item, BigRational>,
  ) = objectives.takeIf { it.isNotEmpty() }
    ?.let { computeOptimizedSolution(it, demands) }
    ?: computeExplicitSolution()

  private suspend fun computeOptimizedSolution(
    objectives: List<Objective>,
    demands: Map<Item, BigRational>,
  ): Map<Recipe, BigRational> {
    val principalObjective = objectives[0]
    val principalOffset = demands[principalObjective.item] ?: 0.br
    val balanceConstraints = objectives.subList(1, objectives.size).map { secondaryObjective ->
      val secondaryOffset = demands[secondaryObjective.item] ?: 0.br
      Constraint.equalTo(
        expressions[principalObjective.item]!! * secondaryObjective.weight.br -
            expressions[secondaryObjective.item]!! * principalObjective.weight.br,
        secondaryObjective.weight.br * principalOffset -
            principalObjective.weight.br * secondaryOffset
      )
    }
    val objective = expressions[principalObjective.item]!!
    return maximize(
      objective,
      constraints + productConstraints.values + balanceConstraints,
      BigRational.FACTORY
    )
  }

  private suspend fun computeExplicitSolution() =
    maximize(
      expressions[productConstraints.keys.first()]!!,
      constraints + productConstraints.values,
      BigRational.FACTORY
    )
}
