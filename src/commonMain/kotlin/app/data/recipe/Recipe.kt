package app.data.recipe

import app.data.Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
sealed interface Recipe {
    val displayName: String

    val time: Rational

    val inputs: Map<Item, Rational>
    val outputs: Map<Item, Rational>
    val components: Map<Item, Rational> get() = inputs.mapValues { (_, count) -> -count } + outputs
}
