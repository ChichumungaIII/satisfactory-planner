package app.model

import app.data.u5.Item
import app.data.u5.Recipe
import util.math.Constraint
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.InfeasibleSolutionException
import util.math.Rational
import util.math.maximize
import util.math.minimize
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
        val expressions = consider(Recipe.values())
        check(expressions.keys.containsAll(inputs.map { it.item }))
        check(expressions.keys.containsAll(products.map { it.item }))

        val provisions = inputs.associate { it.item to it.provision }
        val requirements = products.associate { it.item to it.requirement }
        val limits = products.filterNot { it.limit == null }.associate { it.item to it.limit!! }

        val planConstraints =
            expressions.map { (item, expression) ->
                val result = requirements.getOrElse(item) { 0.q } - provisions.getOrElse(item) { 0.q }
                Constraint.atLeast(expression, result)
            }

        /* PRIMARY PLAN */

        val unlimited = products.filter { it.limit == null }.map { it.item }
        val unrealized = products.filterNot { it.limit == null }.mapTo(mutableSetOf()) { it.item }
        val realized = mutableSetOf<Item>()

        var solution: Map<Recipe, Rational>
        do {
            val principal = (unlimited + unrealized).first()
            val objective = expressions[principal]!!

            val limitConstraints =
                unrealized.map { item -> Constraint.atMost(expressions[item]!!, limits[item]!!) }
            val realizedConstraints =
                realized.map { item -> Constraint.equalTo(expressions[item]!!, limits[item]!!) }
            val balanceConstraints = (unlimited + unrealized).filterNot { it == principal }.map { item ->
                Constraint.equalTo(
                    expressions[item]!! - objective,
                    requirements[item]!! - requirements[principal]!!
                )
            }

            try {
                val constraints = planConstraints + limitConstraints + realizedConstraints + balanceConstraints
                solution = maximize(objective, constraints, Rational.FACTORY)
            } catch (e: InfeasibleSolutionException) {
                val nextInputs = inputs.map { input -> input.copy(target = null, minimum = null) }
                val nextProducts = products.map { product -> product.copy(target = null, maximum = null) }
                return copy(inputs = nextInputs, products = nextProducts, outcome = PlanOutcomeModel())
            }

            val newlyRealized = unrealized.filter { item -> expressions[item]!!(solution) == limits[item]!! }.toSet()
            unrealized -= newlyRealized
            realized += newlyRealized
        } while (newlyRealized.isNotEmpty())

        /* MINIMUMS FOR INPUTS */

        val inputMinimums = inputs.map { it.item }
            .associateWith { item ->
                (-expressions[item]!!).let {
                    it(minimize(it, planConstraints, Rational.FACTORY))
                }
            }

        /* MAXIMUMS FOR PRODUCTS */

        val productMaximums = products.map { it.item }
            .associateWith { item -> (expressions[item]!!).let { it(maximize(it, planConstraints, Rational.FACTORY)) } }

        /* FINAL COMPILATION */

        val nextInputs = inputs.map { input ->
            input.copy(
                target = -expressions[input.item]!!(solution).norm(),
                minimum = maxOf(inputMinimums[input.item]!!.norm(), 0.q),
            )
        }
        val nextProducts = products.map { product ->
            product.copy(
                target = expressions[product.item]!!(solution).norm(),
                maximum = productMaximums[product.item]!!.norm()
            )
        }
        return copy(
            inputs = nextInputs,
            products = nextProducts,
            outcome = PlanOutcomeModel(solution),
        )
    }

    private fun consider(recipes: Array<Recipe>): Map<Item, Expression<Recipe, Rational>> {
        val expressions = mutableMapOf<Item, Expression<Recipe, Rational>>()
        for (recipe in recipes) {
            for (component in recipe.products) {
                val item = component.item
                val expression = (component.quantity * 60.q / recipe.time) * recipe
                expressions[item] = expressions[item]?.let { it + expression } ?: expression
            }
            for (component in recipe.inputs) {
                val item = component.item
                val expression = -(component.quantity * 60.q / recipe.time) * recipe
                expressions[item] = expressions[item]?.let { it + expression } ?: expression
            }
        }
        return expressions
    }
}
