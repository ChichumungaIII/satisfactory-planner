package app.api

import app.data.u5.U5Item
import app.data.u5.U5Recipe
import kotlinx.serialization.Serializable
import util.math.Rational

@Serializable
data class OptimizeRequest(
    val recipes: Set<U5Recipe>,
    val inputs: List<Input>,
    val products: List<Product>
) {
    @Serializable
    data class Input(
        val item: U5Item,
        val quantity: Rational
    )

    @Serializable
    data class Product(
        val item: U5Item,
        val minimum: Rational,
        val maximum: Rational?,
    )
}
