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
    private val title: String = "Production Plan",
    private val id: Int = Random.Default.nextInt(),
    private val inputs: List<PlanInputModel> = listOf(),
    private val products: List<PlanProductModel> = listOf(),
    private val outcome: PlanOutcomeModel? = null,
) {
    fun title() = title
    fun id() = id
    fun inputs() = inputs
    fun products() = products
    fun outcome() = outcome

    fun setTitle(title: String) =
        copy(title = title)

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
        if (inputs().isEmpty() || products().isEmpty()) {
            return copy(outcome = null)
        }

        // TODO: Replace with a plan and phase-specific recipe set
        val expressions = consider(*Recipe.values())
        check(expressions.keys.containsAll(products().map { it.item() }))

        val provisions = inputs().groupingBy { it.item() }.fold(0.q) { total, input -> total + input.target() }
        val requirements = products().groupingBy { it.item() }.fold(0.q) { total, product -> total + product.target() }

        val objective = expressions[products[0].item()]!!
        val constraints = expressions.map { (item, expression) ->
            toConstraint(expression, requirements.getOrElse(item) { 0.q } - provisions.getOrElse(item) { 0.q })
        } + products().drop(1).map { it.item() }.map { item ->
            RationalConstraint.equalTo(expressions[item]!! - objective, 0.q)
        }

        val solution = maximize(objective, *constraints.toTypedArray())

        val products = products().map { product ->
            val maximum = expressions[product.item()]!!(solution)
            product.copy(
                target = maximum,
                maximum = maximum,
            )
        }
        val outcome = PlanOutcomeModel(solution)
        return copy(products = products, outcome = outcome)
    }

    private fun consider(vararg recipes: Recipe): Map<Item, RationalExpression<Recipe>> {
        val expressions = mutableMapOf<Item, RationalExpression.Builder<Recipe>>()
        for (recipe in recipes) {
            for (component in recipe.components()) {
                expressions.getOrPut(component.item()) { RationalExpression.Builder() }
                    .set(recipe, component.quantity() * 60.q / recipe.time())
            }
        }
        return expressions.mapValues { (_, expression) -> expression.build() }
    }

    private fun toConstraint(expression: RationalExpression<Recipe>, result: Rational) =
        if (result < 0.q) RationalConstraint.atMost(-expression, -result)
        else RationalConstraint.atLeast(expression, result)
}
