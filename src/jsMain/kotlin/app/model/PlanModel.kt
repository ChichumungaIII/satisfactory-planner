package app.model

import app.api.OptimizeResponse
import app.data.u5.Item
import app.data.u5.Recipe
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import util.math.Expression
import util.math.Expression.Companion.times
import util.math.Rational
import util.math.q
import kotlin.random.Random

@Serializable
data class PlanModel(
    /** The title of this Plan. Set by user input. */
    val title: String = "Production Plan",
    /** A random ID assigned to this Plan, for React tracking. */
    val id: Int = Random.Default.nextInt(),
    /** True when a new plan is being computed. */
    @Transient val loading: Boolean = false,
    /** The inputs provided to this Plan. */
    val inputs: List<PlanInputModel> = listOf(),
    /** The products produced by this Plan. */
    val products: List<PlanProductModel> = listOf(),
    /** The calculated outcome of this plan. */
    val outcome: PlanOutcomeModel? = null,
) {
    fun setLoading(loading: Boolean) = copy(loading = loading)

    fun addInput(input: PlanInputModel) = copy(inputs = inputs + input)

    fun setInput(i: Int, input: PlanInputModel) =
        copy(inputs = inputs.subList(0, i) + input + inputs.subList(i + 1, inputs.size))

    fun removeInput(i: Int) = copy(inputs = inputs.subList(0, i) + inputs.subList(i + 1, inputs.size))

    fun addProduct(product: PlanProductModel) = copy(products = products + product)

    fun setProduct(i: Int, product: PlanProductModel) =
        copy(products = products.subList(0, i) + product + products.subList(i + 1, products.size))

    fun removeProduct(i: Int) = copy(products = products.subList(0, i) + products.subList(i + 1, products.size))

    fun update(result: OptimizeResponse): PlanModel {
        val (outcome, minimums, maximums) = result
        val expressions = consider(outcome.keys)
        return copy(
            loading = false,
            inputs = inputs.map {
                it.copy(
                    target = -expressions[it.item]!!(outcome).norm(),
                    minimum = maxOf(minimums[it.item]!!.norm(), 0.q)
                )
            },
            products = products.map {
                it.copy(
                    target = expressions[it.item]!!(outcome).norm(),
                    maximum = maximums[it.item]!!.norm()
                )
            },
            outcome = PlanOutcomeModel(outcome)
        )
    }

    private fun consider(recipes: Iterable<Recipe>): Map<Item, Expression<Recipe, Rational>> {
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
