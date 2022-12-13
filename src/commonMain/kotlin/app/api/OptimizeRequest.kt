package app.api

import app.data.Item
import app.data.recipe.Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeRequest(
    val recipes: Set<Recipe>,
    val inputs: List<Input>,
    val products: List<Product>,
) {
    @Serializable
    data class Input(
        val item: Item,
        val quantity: Rational,
    )

    @Serializable
    data class Product(
        val item: Item,
        val minimum: Rational,
        val maximum: Rational?,
    )
}
