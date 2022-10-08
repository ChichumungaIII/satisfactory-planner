package app.api

import app.data.u6.U6Item
import app.data.u6.U6Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeRequest(
    val recipes: Set<U6Recipe>,
    val inputs: List<Input>,
    val products: List<Product>
) {
    @Serializable
    data class Input(
        val item: U6Item,
        val quantity: Rational
    )

    @Serializable
    data class Product(
        val item: U6Item,
        val minimum: Rational,
        val maximum: Rational?,
    )
}
