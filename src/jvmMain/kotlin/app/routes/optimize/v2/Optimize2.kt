package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeOutput.Maximization
import app.api.optimize.v2.request.OptimizeOutput.Production
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeConsumption
import app.api.optimize.v2.response.OptimizeProduction
import app.api.optimize.v2.response.OptimizeResponse
import app.game.data.Item
import app.game.data.Recipe
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import util.collections.augment
import util.collections.merge
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.q

typealias OpExpr = Expression<OpVar, BigRational>

fun optimize2(request: OptimizeRequest): OptimizeResponse {
  val inputItems = request.inputs.map { it.item }.toSet()
  val consumptionLimits = request.inputs.index({ it.item }) { it.amount }
  val consumptionMandates = request.inputs.filter { it.required }.index({ it.item }) { it.amount }

  val outputItems = request.outputs.map { it.item }.toSet()
  val productionMandates = request.outputs.filterIsInstance<Production>()
    .index({ it.item }, Requirement::from, Requirement::merge)
  val maximizationWeights = request.outputs.filterIsInstance<Maximization>().index({ it.item }) { it.weight }

  val usableRecipes = getUsableRecipes(request.recipes, inputItems)
  val recipeExpressions = usableRecipes
    .flatMap { it.rates.map { (item, rate) -> item to rate.br * OpVar.create(it) } }
    .fold(mapOf<Item, OpExpr>()) { map, (item, expression) -> map.merge(item, expression, OpExpr::plus) }
  val allItems = recipeExpressions.keys
  val passthroughItems = inputItems intersect outputItems
  val passthroughExpressions = (passthroughItems + consumptionMandates.keys)
    .associateWith { 1.br * OpVar.create(it) }

  val itemExpressions = allItems.associateWith { item ->
    val netProduction = recipeExpressions[item]!!
    val netConsumption = -netProduction
    ItemExpression(
      item = item,
      production = passthroughExpressions[item]?.let { pass -> netProduction + pass } ?: netProduction,
      consumption = passthroughExpressions[item]?.let { pass -> netConsumption + pass } ?: netConsumption,
    )
  }

  val consumptionLimitConstraints =
    consumptionLimits.mapValues { (item, limit) ->
      val consumption = itemExpressions[item]!!.consumption
      Constraint.atMost(consumption, limit)
    }
  val consumptionMandateConstraints =
    consumptionMandates.mapValues { (item, mandate) ->
      val consumption = itemExpressions[item]!!.consumption
      Constraint.atLeast(consumption, mandate)
    }
  val productionMandateConstraints =
    productionMandates.mapValues { (item, requirement) ->
      val production = itemExpressions[item]!!.production
      if (requirement.exact && !maximizationWeights.containsKey(item))
        Constraint.equalTo(production, requirement.amount)
      else Constraint.atLeast(production, requirement.amount)
    }
  val positivityConstraints =
    (allItems - consumptionLimitConstraints.keys - consumptionMandateConstraints.keys - productionMandateConstraints.keys)
      .associateWith { item ->
        val production = itemExpressions[item]!!.production
        Constraint.atLeast(production, 0.br)
      }
  val recipeConstraints = request.limits.filter { usableRecipes.contains(it.key) }
    .map { (recipe, rate) -> Constraint.atMost(1.br * OpVar.create(recipe), rate.br) }
  val fixedConstraints =
    consumptionLimitConstraints.values +
        consumptionMandateConstraints.values +
        positivityConstraints.values +
        recipeConstraints

  /****************/
  /** PLAN RATES **/
  /****************/

  val rates: Map<OpVar, BigRational> = (if (maximizationWeights.isEmpty()) {
    val objective = itemExpressions[outputItems.first()]!!.production
    maximize(objective, fixedConstraints + productionMandateConstraints.values)
  } else {
    val objectives = maximizationWeights.map { (item, weight) ->
      Objective(itemExpressions[item]!!.production, weight, productionMandates[item]?.amount ?: 0.br)
    }
    val primary = objectives.first()
    val balanceConstraints = objectives.drop(1).map { secondary ->
      Constraint.equalTo(
        primary.expression * secondary.weight - secondary.expression * primary.weight,
        secondary.weight * primary.offset - primary.weight * secondary.offset
      )
    }
    maximize(primary.expression, fixedConstraints + productionMandateConstraints.values + balanceConstraints)
  }).filterValues { it != 0.br }
  val planProductConstraints =
    request.outputs.map { it.item }.associateWith { item ->
      val production = itemExpressions[item]!!.production
      Constraint.equalTo(production, production(rates))
    }
  val planConstraints = productionMandateConstraints + planProductConstraints

  /*******************/
  /** INPUT DEMANDS **/
  /*******************/

  val totalConsumed = inputItems.associateWith { item -> itemExpressions[item]!!.consumption(rates) }
  val totalDemand = inputItems.associateWith { item ->
    val consumption = itemExpressions[item]!!.consumption
    consumption(minimize(consumption, fixedConstraints + planConstraints.values))
  }
  val (optimizedConsumption) = request.inputs.augment(totalConsumed) { consumption, (item, amount) ->
    val consumed = minOf(consumption[item] ?: 0.br, amount.br)
    val demand = amount.br + (totalDemand[item] ?: 0.br) - (consumptionLimits[item] ?: 0.br)
    add(OptimizeConsumption(item, amount, consumed.toRational(), demand.toRational()))
    consumption + (item to (consumption[item] ?: 0.br) - consumed)
  }

  /**************************/
  /** PRODUCTION POTENTIAL **/
  /**************************/

  val totalProduced = allItems
    .associateWith { item -> itemExpressions[item]!!.production(rates) }
    .filterValues { it > 0.br }
  val totalPotential = outputItems.associateWith { item ->
    val production = itemExpressions[item]!!.production
    production(maximize(production, fixedConstraints + (planConstraints - item).values))
  }
  val (distributedProduced, byproducts) = request.outputs.augment(totalProduced) { produced, output ->
    val item = output.item
    val amount: BigRational = when (output) {
      is Production -> output.amount.br
      is Maximization -> {
        val surplus = ((totalProduced[item] ?: 0.br) - (productionMandates[item]?.amount ?: 0.br))
        surplus * output.weight.br / maximizationWeights[item]!!
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

tailrec fun getUsableRecipes(allRecipes: Set<Recipe>, items: Set<Item>): Set<Recipe> {
  val usable = allRecipes.filter { items.containsAll(it.inputs.keys) }.toSet()
  val next = items + usable.flatMap { it.outputs.keys }
  return usable.takeIf { items.containsAll(next) } ?: getUsableRecipes(allRecipes, next)
}
