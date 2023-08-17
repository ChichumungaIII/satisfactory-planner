package app.game.data

import app.game.logic.Condition
import app.game.logic.Condition.Companion.all
import util.math.Rational
import util.math.q

enum class Research(
  val displayName: String,
  val category: Category,
  val cost: Map<Item, Rational>,
  val requirement: Condition,
) {
  /* Hard Drive  */

  CAST_SCREW(
    "Cast Screw",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  IRON_ALLOY_INGOT(
    "Iron Alloy Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  IRON_WIRE(
    "Iron Wire",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  STITCHED_IRON_PLATE(
    "Stitched Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),

  /* Alien Organisms */

  HOG_RESEARCH(
    "Hog Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.HOG_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  HATCHER_RESEARCH(
    "Hatcher Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.HATCHER_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  STINGER_RESEARCH(
    "Stinger Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.STINGER_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  SPITTER_RESEARCH(
    "Spitter Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.SPITTER_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  BIO_ORGANIC_PROPERTIES(
    "Bio-Organic Properties",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(),
    requirement = all {
      +HOG_RESEARCH
      +HATCHER_RESEARCH
      +STINGER_RESEARCH
      +SPITTER_RESEARCH
    }
  ),

  /* Caterium */


  /* Mycelia */

  MYCELIA(
    "Mycelia",
    Category.MYCELIA,
    cost = mapOf(Item.MYCELIA to 5.q),
    requirement = Condition.TRUE,
  ),

  /* Nutrients */


  /* Power Slugs */


  /* Quartz */


  /* Sulfur */


  ;

  enum class Category(
    val displayName: String,
  ) {
    HARD_DRIVE("Hard Drive"),
    ALIEN_ORGANISMS("Alien Organisms"),
    CATERIUM("Caterium"),
    MYCELIA("Mycelia"),
    NUTRIENTS("Nutrients"),
    POWER_SLUGS("Power Slugs"),
    QUARTZ("Quartz"),
    SULFUR("Sulfur"),
  }
}
