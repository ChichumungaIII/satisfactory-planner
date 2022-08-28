package app.model

import app.model.game.u5.Item
import app.model.game.u5.Recipe
import util.math.RationalExpression
import util.math.linprog.RationalConstraint
import util.math.linprog.maximize
import util.math.linprog.minimize
import util.math.q
import kotlin.random.Random

data class PlanModel(
    /** The title of this Plan. Set by user input. */
    val title: String = "Production Plan",
    /** A random ID assigned to this Plan, for React tracking. */
    val id: Int = Random.Default.nextInt(),
    /** The inputs provided to this Plan. */
    val inputs: List<PlanInputModel> = listOf(),
    /** The products produced by this Plan. */
    val products: List<PlanProductModel> = listOf(),
    /** The calculated outcome of this plan. */
    val outcome: PlanOutcomeModel? = null,
) {
    fun addInput(input: PlanInputModel) = copy(inputs = inputs + input)

    fun setInput(i: Int, input: PlanInputModel) =
        copy(inputs = inputs.subList(0, i) + input + inputs.subList(i + 1, inputs.size))

    fun removeInput(i: Int) = copy(inputs = inputs.subList(0, i) + inputs.subList(i + 1, inputs.size))

    fun addProduct(product: PlanProductModel) = copy(products = products + product)

    fun setProduct(i: Int, product: PlanProductModel) =
        copy(products = products.subList(0, i) + product + products.subList(i + 1, products.size))

    fun removeProduct(i: Int) = copy(products = products.subList(0, i) + products.subList(i + 1, products.size))

    fun optimize(): PlanModel {
        if (inputs.isEmpty() || products.isEmpty()) {
            return copy(outcome = null)
        }

        // TODO: Replace with a plan and phase-specific recipe set
        val expressions = consider(*Recipe.values())
        check(expressions.keys.containsAll(inputs.map { it.item }))
        check(expressions.keys.containsAll(products.map { it.item }))

        val provisions = inputs.associate { it.item to it.provision }
        val requirements = products.associate { it.item to it.requirement }

        val constraints = expressions.map { (item, expression) ->
            val result = requirements.getOrElse(item) { 0.q } - provisions.getOrElse(item) { 0.q }
            RationalConstraint.atLeast(expression, result)
        }

        /* PRIMARY PLAN */

        val primaryObjective = expressions[products[0].item]!!
        val primaryConstraints = constraints + products.drop(1).map { it.item }.map { item ->
            RationalConstraint.equalTo(
                expressions[item]!! - primaryObjective, requirements[item]!! - requirements[products[0].item]!!
            )
        } + products.associate { it.item to it.limit }.filterValues { it != null }.map { (item, limit) ->
            RationalConstraint.atMost(expressions[item]!!, limit!!)
        }
        val nextOutcome = PlanOutcomeModel(maximize(primaryObjective, *primaryConstraints.toTypedArray()))

        /* MINIMUMS FOR INPUTS */

        val inputMinimums = inputs.map { it.item }
            .associateWith { item -> (-expressions[item]!!).let { it(minimize(it, *constraints.toTypedArray())) } }

        /* MAXIMUMS FOR PRODUCTS */

        val productMaximums = products.map { it.item }
            .associateWith { item -> (expressions[item]!!).let { it(maximize(it, *constraints.toTypedArray())) } }

        /* FINAL COMPILATION */

        val nextInputs = inputs.map { input ->
            input.copy(
                target = -expressions[input.item]!!(nextOutcome.recipes).norm(),
                minimum = maxOf(inputMinimums[input.item]!!.norm(), 0.q),
            )
        }
        val nextProducts = products.map { product ->
            product.copy(
                target = expressions[product.item]!!(nextOutcome.recipes).norm(),
                maximum = productMaximums[product.item]!!.norm()
            )
        }
        return copy(
            inputs = nextInputs,
            products = nextProducts,
            outcome = nextOutcome,
        )
    }

    private fun consider(vararg recipes: Recipe): Map<Item, RationalExpression<Recipe>> {
        val expressions = mutableMapOf<Item, RationalExpression.Builder<Recipe>>()
        for (recipe in recipes) {
            for (component in recipe.components()) {
                expressions.getOrPut(component.item()) { RationalExpression.Builder() }
                    .set(recipe, (component.quantity() * 60.q / recipe.time()).norm())
            }
        }
        return expressions.mapValues { (_, expression) -> expression.build() }
    }
}
