package app.game.data

import app.game.data.RecipeV2.Component.Companion.of
import util.math.Rational
import util.math.q

/** Enumeration of all recipes in the game, including extraction and generation. */
enum class RecipeV2(
  /** The human-readable text representation of the recipe. */
  val displayName: String,
  /** The category dropdown this recipe belongs to in its building's menu. */
  val category: Category,
  /** The amount of time the recipe takes to convert the inputs into products, in seconds. */
  val time: Rational,
  /** The inputs to this recipe. */
  val inputs: List<Component>,
  /** The primary output of this recipe. */
  val product: Component,
  /** The byproduct of this recipe, if one is produced. */
  val byproduct: Component?,
  /** The amount of power this recipe consumes during production, if different from the manufacturer default. */
  val power: ClosedRange<Double>?,
  /** Whether this is an alternate recipe scanned from a hard drive. */
  val alternate: Boolean,
) {
  IRON_INGOT(
    "Iron Ingot",
    category = Category.INGOTS,
    time = 2,
    inputs = listOf(
      1 of Item.IRON_ORE,
    ),
    product = 1 of Item.IRON_INGOT,
  ),

  ;

  constructor(
    displayName: String,
    category: Category,
    time: Int,
    inputs: List<Component>,
    product: Component,
    byproduct: Component? = null,
    power: ClosedRange<Double>? = null,
    alternate: Boolean = false
  ) : this(displayName, category, time.q, inputs, product, byproduct, power, alternate)

  /** A Recipe Component is a pair containing an item and an amount of that item. */
  data class Component(
    /** The item type  */
    val item: Item,
    val amount: Rational,
  ) {
    companion object {
      /** Creates a new [Component] of `this` amount of [item]. */
      infix fun Int.of(item: Item) = this.q of item

      /** Creates a new [Component] of `this` amount of [item]. */
      infix fun Rational.of(item: Item) = Component(item, amount = this)
    }
  }

  /** Enumerates the categories that recipes belong to in their building's menu. */
  enum class Category(
    /** The human-readable text representation of the Category. */
    val displayName: String,
  ) {
    INGOTS("Ingots"),
  }
}
