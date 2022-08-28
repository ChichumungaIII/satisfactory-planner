package app.model

import app.model.game.u5.Item
import app.model.game.u5.Recipe
import util.math.Rational
import util.math.RationalExpression
import util.math.linprog.RationalConstraint
import util.math.linprog.maximize
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
    fun addInput(input: PlanInputModel) =
        copy(inputs = inputs + input)

    fun setInput(i: Int, input: PlanInputModel) =
        copy(inputs = inputs.subList(0, i) + input + inputs.subList(i + 1, inputs.size))

    fun removeInput(i: Int) =
        copy(inputs = inputs.subList(0, i) + inputs.subList(i + 1, inputs.size))

    fun addProduct(product: PlanProductModel) =
        copy(products = products + product)

    fun setProduct(i: Int, product: PlanProductModel) =
        copy(products = products.subList(0, i) + product + products.subList(i + 1, products.size))

    fun removeProduct(i: Int) =
        copy(products = products.subList(0, i) + products.subList(i + 1, products.size))

    fun optimize(): PlanModel {
        if (inputs.isEmpty() || products.isEmpty()) {
            return copy(outcome = null)
        }

        // TODO: Replace with a plan and phase-specific recipe set
        val expressions = consider(*Recipe.values())
        check(expressions.keys.containsAll(products.map { it.item }))

        val provisions =
            inputs.groupingBy { it.item }.fold(0.q) { total, input -> total + input.provision }
        val requirements = products.groupingBy { it.item }
            .fold(0.q) { total, product -> total + product.requirement }

        val objective = expressions[products[0].item]!!
        val constraints = expressions.map { (item, expression) ->
            val result = requirements.getOrElse(item) { 0.q } - provisions.getOrElse(item) { 0.q }
            RationalConstraint.atLeast(expression, result)
        } + products.drop(1).map { it.item }.map { item ->
            RationalConstraint.equalTo(
                expressions[item]!! - objective,
                requirements[item]!! - requirements[products[0].item]!!
            )
        }

        val solution = maximize(objective, *constraints.toTypedArray())

        val nextProducts = products.map { product ->
            val target = expressions[product.item]!!(solution).norm()
            product.copy(target = target)
        }
        val nextOutcome = PlanOutcomeModel(solution)
        return copy(products = nextProducts, outcome = nextOutcome)
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

    private fun toConstraint(expression: RationalExpression<Recipe>, result: Rational) =
        if (result < 0.q) RationalConstraint.atMost(-expression, -result)
        else RationalConstraint.atLeast(expression, result)
}
