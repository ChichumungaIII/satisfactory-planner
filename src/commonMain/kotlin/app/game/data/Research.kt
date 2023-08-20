package app.game.data

import app.game.logic.Condition
import app.game.logic.Condition.Companion.all
import app.game.logic.Condition.ResearchCondition
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

  UNKNOWN_METAL(
    "Unknown Metal",
    Category.CATERIUM,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  CATERIUM(
    "Caterium",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_ORE to 10.q),
    requirement = ResearchCondition(UNKNOWN_METAL),
  ),
  CATERIUM_INGOT(
    "Caterium Ingot",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_ORE to 50.q),
    requirement = ResearchCondition(CATERIUM),
  ),
  QUICKWIRE(
    "Quickwire",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_INGOT to 50.q),
    requirement = ResearchCondition(CATERIUM_INGOT),
  ),
  ZIPLINE(
    "Zipline",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 100.q,
      Item.CABLE to 50.q,
    ),
    requirement = ResearchCondition(QUICKWIRE),
  ),
  CATERIUM_ELECTRONICS(
    "Caterium Electronics",
    Category.CATERIUM,
    cost = mapOf(Item.QUICKWIRE to 100.q),
    requirement = ResearchCondition(QUICKWIRE),
  ),
  STUN_REBAR(
    "Stun Rebar",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 50.q,
      // TODO: Item.IRON_REBAR to 10.q,
    ),
    requirement = ResearchCondition(QUICKWIRE),
  ),
  AI_LIMITER(
    "AI Limiter",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 200.q,
      Item.COPPER_SHEET to 50.q,
    ),
    requirement = ResearchCondition(CATERIUM_ELECTRONICS),
  ),
  POWER_POLES_MK_2(
    "Power Poles Mk. 2",
    Category.CATERIUM,
    cost = mapOf(Item.QUICKWIRE to 300.q),
    requirement = ResearchCondition(CATERIUM_ELECTRONICS),
  ),
  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 500.q,
      Item.PLASTIC to 50.q,
    ),
    requirement = ResearchCondition(CATERIUM_ELECTRONICS),
  ),

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

  UNKNOWN_CRYSTALLINE_MATERIAL(
    "Unknown Crystalline Material",
    Category.QUARTZ,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  QUARTZ(
    "Quartz",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 10.q),
    requirement = ResearchCondition(UNKNOWN_CRYSTALLINE_MATERIAL),
  ),
  QUARTZ_CRYSTALS(
    "Quartz Crystals",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 20.q),
    requirement = ResearchCondition(QUARTZ),
  ),
  SILICA(
    "Silica",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 20.q),
    requirement = ResearchCondition(QUARTZ),
  ),
  SHATTER_REBAR(
    "Shatter Rebar",
    Category.QUARTZ,
    cost = mapOf(
      Item.QUARTZ_CRYSTAL to 30.q,
      // TODO: Item.IRON_REBAR to 150.q,
    ),
    requirement = ResearchCondition(QUARTZ_CRYSTALS),
  ),
  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    Category.QUARTZ,
    cost = mapOf(
      Item.QUARTZ_CRYSTAL to 100.q,
      Item.REINFORCED_IRON_PLATE to 50.q,
    ),
    requirement = ResearchCondition(QUARTZ_CRYSTALS),
  ),

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
