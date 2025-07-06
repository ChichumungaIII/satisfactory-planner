package app.data.recipe

import app.game.data.Item
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

fun Recipe.inputRate(item: Item, clock: Rational) = -componentRate(item, clock)
fun Recipe.outputRate(item: Item, clock: Rational) = componentRate(item, clock)
fun Recipe.componentRate(item: Item, clock: Rational) =
  components.getOrElse(item) { 0.q } * clock * 60.q / time
