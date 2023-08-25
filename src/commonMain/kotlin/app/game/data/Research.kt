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
  PROTEIN_INHALER(
    "Protein Inhaler",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_PROTEIN to 2.q,
      Item.BERYL_NUT to 20.q,
      Item.ROTOR to 50.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
  ),
  STRUCTURAL_ANALYSIS(
    "Structural Analysis",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 5.q,
      Item.IRON_ROD to 100.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
  ),
  THE_REBAR_GUN(
    "The Rebar Gun",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ROTOR to 25.q,
      Item.REINFORCED_IRON_PLATE to 50.q,
      Item.SCREW to 500.q,
    ),
    requirement = ResearchCondition(STRUCTURAL_ANALYSIS),
  ),
  INFLATED_POCKET_DIMENSION_ALIEN_ORGANISMS(
    "Inflated Pocket Dimension",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_PROTEIN to 3.q,
      Item.CABLE to 1000.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
  ),
  EXPANDED_TOOLBELT_ALIEN_ORGANISMS(
    "Expanded Toolbelt",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 5.q,
      Item.STEEL_BEAM to 500.q,
    ),
    requirement = ResearchCondition(INFLATED_POCKET_DIMENSION_ALIEN_ORGANISMS),
  ),
  HOSTILE_ORGANISM_DETECTION(
    "Hostile Organism Detection",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 10.q,
      Item.CRYSTAL_OSCILATOR to 5.q,
      Item.HIGH_SPEED_CONNECTOR to 5.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
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
      Item.IRON_REBAR to 10.q,
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
  SUPERCOMPUTER(
    "Supercomputer",
    Category.CATERIUM,
    cost = mapOf(
      Item.AI_LIMITER to 50.q,
      Item.HIGH_SPEED_CONNECTOR to 50.q,
      Item.COMPUTER to 50.q,
    ),
    requirement = all {
      +AI_LIMITER
      +HIGH_SPEED_CONNECTOR
    },
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
      Item.IRON_REBAR to 150.q,
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

  UNKNOWN_CHEMICAL_ELEMENT(
    "Unknown Chemical Element",
    Category.SULFUR,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  SULFUR(
    "Sulfur",
    Category.SULFUR,
    cost = mapOf(Item.SULFUR to 10.q),
    requirement = ResearchCondition(UNKNOWN_CHEMICAL_ELEMENT),
  ),
  BLACK_POWDER(
    "Black Powder",
    Category.SULFUR,
    cost = mapOf(
      Item.SULFUR to 50.q,
      Item.COAL to 25.q,
    ),
    requirement = ResearchCondition(SULFUR),
  ),
  EXPERIMENTAL_POWER_GENERATION(
    "Experimental Power Generation",
    Category.SULFUR,
    cost = mapOf(
      Item.SULFUR to 25.q,
      Item.MODULAR_FRAME to 50.q,
      Item.ROTOR to 100.q,
    ),
    requirement = ResearchCondition(SULFUR),
  ),
  COMPACTED_COAL(
    "Compacted Coal",
    Category.SULFUR,
    cost = mapOf(
      Item.HARD_DRIVE to 1.q,
      Item.SULFUR to 25.q,
      Item.COAL to 25.q,
    ),
    requirement = ResearchCondition(EXPERIMENTAL_POWER_GENERATION),
  ),
  TURBOFUEL(
    "Turbofuel",
    Category.SULFUR,
    cost = mapOf(
      Item.HARD_DRIVE to 1.q,
      Item.COMPACTED_COAL to 15.q,
      Item.PACKAGED_FUEL to 50.q,
    ),
    requirement = ResearchCondition(EXPERIMENTAL_POWER_GENERATION),
  ),
  EXPANDED_TOOLBELT_SULFUR(
    "Expanded Toolbelt",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 100.q,
      Item.ENCASED_INDUSTRIAL_BEAM to 50.q,
    ),
    requirement = ResearchCondition(BLACK_POWDER),
  ),
  THE_NOBELISK_DETONATOR(
    "The Nobelisk Detonator",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 50.q,
      Item.STEEL_PIPE to 100.q,
      Item.CABLE to 200.q,
    ),
    requirement = ResearchCondition(BLACK_POWDER),
  ),
  SMOKELESS_POWDER(
    "Smokeless Powder",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 100.q,
      Item.PLASTIC to 50.q,
    ),
    requirement = ResearchCondition(BLACK_POWDER),
  ),
  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 100.q,
      Item.NOBELISK to 200.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),
  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 200.q,
      Item.IRON_REBAR to 200.q,
      Item.STEEL_BEAM to 200.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),
  THE_RIFLE(
    "The Rifle",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 50.q,
      Item.MOTOR to 100.q,
      Item.RUBBER to 200.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),

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
