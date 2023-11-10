package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeOutput
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeConsumption
import app.api.optimize.v2.response.OptimizeProduction
import app.api.optimize.v2.response.OptimizeResponse
import app.game.data.Item
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import util.collections.augment
import util.collections.join
import util.collections.merge
import util.math.Constraint
import util.math.Expression.Companion.times
import util.math.q

fun optimize3(request: OptimizeRequest): OptimizeResponse {
  val availability = request.inputs.index({ it.item }) { it.amount }
  val inputItems = availability.keys
  val requirements = request.outputs.filterIsInstance<OptimizeOutput.Production>()
    .index({ it.item }, Requirement::from, Requirement::merge)
  val weights = request.outputs.filterIsInstance<OptimizeOutput.Maximization>().index({ it.item }) { it.weight }

  val supplyExpressions = inputItems.associateWith { item -> 1.br * OpVar.create(item) }
  val usableRecipes = getUsableRecipes(request.recipes, inputItems)
  val itemExpressions = usableRecipes.flatMap { it.rates.map { (item, rate) -> item to rate.br * OpVar.create(it) } }
    .fold(mapOf<Item, OpExpr>()) { map, (item, expression) -> map.merge(item, expression, OpExpr::plus) }
    .join(supplyExpressions, OpExpr::plus)

  val requirementConstraints =
    requirements.mapValues { (item, requirement) ->
      val production = itemExpressions[item]!!
      if (requirement.exact && !weights.containsKey(item))
        Constraint.equalTo(production, requirement.amount)
      else Constraint.atLeast(production, requirement.amount)
    }
  val fixedConstraints =
    availability.map { (item, amount) -> Constraint.atMost(supplyExpressions[item]!!, amount) } +
        (itemExpressions.keys - requirementConstraints.keys).map { item ->
          Constraint.atLeast(itemExpressions[item]!!, 0.br)
        } + request.limits.filter { usableRecipes.contains(it.key) }
      .map { (recipe, rate) -> Constraint.atMost(1.br * OpVar.create(recipe), rate.br) }

  /****************/
  /** PLAN RATES **/
  /****************/

  val rates: Map<OpVar, BigRational> = (if (weights.isEmpty()) {
    val objective = itemExpressions[requirements.keys.first()]!!
    maximize(objective, fixedConstraints + requirementConstraints.values)
  } else {
    val objectives = weights.map { (item, weight) ->
      Objective(itemExpressions[item]!!, weight, requirements[item]?.amount ?: 0.br)
    }
    val primary = objectives.first()
    val balanceConstraints = objectives.drop(1).map { secondary ->
      Constraint.equalTo(
        primary.expression * secondary.weight - secondary.expression * primary.weight,
        secondary.weight * primary.offset - primary.weight * secondary.offset
      )
    }
    maximize(primary.expression, fixedConstraints + requirementConstraints.values + balanceConstraints)
  }).filterValues { it != 0.br }
  val planConstraints = requirementConstraints +
      request.outputs.map { it.item }.associateWith { item ->
        val production = itemExpressions[item]!!
        Constraint.equalTo(production, production(rates))
      }

  /*******************/
  /** INPUT DEMANDS **/
  /*******************/

  val totalConsumed = inputItems.associateWith { item -> supplyExpressions[item]!!(rates) }
  val totalDemand = inputItems.associateWith { item ->
    val consumption = supplyExpressions[item]!!
    consumption(minimize(consumption, fixedConstraints + planConstraints.values))
  }
  val (optimizedConsumption) = request.inputs.augment(totalConsumed) { consumption, (item, amount) ->
    val consumed = minOf(consumption[item] ?: 0.br, amount.br)
    val demand = amount.br + (totalDemand[item] ?: 0.br) - (availability[item] ?: 0.br)
    add(OptimizeConsumption(item, amount, consumed.toRational(), demand.toRational()))
    consumption + (item to (consumption[item] ?: 0.br) - consumed)
  }

  /**************************/
  /** PRODUCTION POTENTIAL **/
  /**************************/

  val totalProduced = itemExpressions.keys
    .associateWith { item -> itemExpressions[item]!!(rates) }
    .filterValues { it > 0.br }
  val totalPotential = request.outputs.map { it.item }.associateWith { item ->
    val production = itemExpressions[item]!!
    production(maximize(production, fixedConstraints + (planConstraints - item).values))
  }
  val (distributedProduced, byproducts) = request.outputs.augment(totalProduced) { produced, output ->
    val item = output.item
    val amount: BigRational = when (output) {
      is OptimizeOutput.Production -> output.amount.br
      is OptimizeOutput.Maximization -> {
        val surplus = ((totalProduced[item] ?: 0.br) - (requirements[item]?.amount ?: 0.br))
        surplus * output.weight.br / weights[item]!!
      }
    }
    add(OptimizeProduction(item, amount.toRational(), 0.q))
    produced + (item to (produced[item] ?: 0.br) - amount)
  }
  val optimizeProduced = distributedProduced.map { (item, amount) ->
    val potential = amount.br + (totalPotential[item] ?: 0.br) - (totalProduced[item] ?: 0.br)
    OptimizeProduction(item, amount, potential.toRational())
  }

  /**************/
  /** RESPONSE **/
  /**************/

  return OptimizeResponse(
    consumed = optimizedConsumption,
    produced = optimizeProduced,
    byproducts = byproducts.filterValues { it != 0.br }.mapValues { (_, amount) -> amount.toRational() },
    rates = rates.mapNotNull { (v, rate) -> (v as? RecipeVar)?.let { it.recipe to rate.toRational() } }.toMap(),
  )
}
