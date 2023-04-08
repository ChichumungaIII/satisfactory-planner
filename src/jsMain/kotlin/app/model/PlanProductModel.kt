package app.model

import app.data.Item
import kotlinx.serialization.Serializable
import util.math.Rational
import util.math.q

@Serializable
data class PlanProductModel(
  /** The item being produced. */
  val item: Item,
  /**
   * The minimum amount of `item` that will be produced by this plan. Establishing a plan outcome
   * will fail if this minimum cannot be met. Set by user input.
   */
  val requirement: Rational = 0.q,
  /**
   * The maximum amount of `item` that will be produced by this plan during calculation. Set to
   * `null` to indicate the maximum should remain unbounded. Set by user input.
   */
  val limit: Rational? = null,
  /**
   * The amount of `item` that will be produced by the most recent calculated plan. Set to `null`
   * if a plan has not yet been computed for this product.
   */
  val target: Rational? = null,
  /**
   * The maximum amount of `item` that could be produced from the current inputs while also
   * reaching the minimum requirements for all other products. Set to `null` if a plan has not
   * yet been computed for this product.
   */
  val maximum: Rational? = null,
)
