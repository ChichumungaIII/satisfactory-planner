package app.game.data

import app.game.data.RecipeV2.Component.Companion.of
import util.math.Rational
import util.math.q

/** Enumeration of all recipes in the game, including extraction and generation. */
enum class RecipeV2(
  /** The human-readable text representation of the recipe. */
  val displayName: String,
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
  /* Iron Ingot */

  IRON_INGOT(
    "Iron Ingot",
    time = 2,
    inputs = listOf(1 of Item.IRON_ORE),
    product = 1 of Item.IRON_INGOT,
  ),
  IRON_ALLOT_INGOT(
    "Iron Allot Ingot",
    time = 12,
    inputs = listOf(
      8 of Item.IRON_ORE,
      2 of Item.COPPER_ORE,
    ),
    product = 15 of Item.IRON_INGOT,
    alternate = true,
  ),
  BASIC_IRON_INGOT(
    "Basic Iron Ingot",
    time = 12,
    inputs = listOf(
      5 of Item.IRON_ORE,
      8 of Item.LIMESTONE,
    ),
    product = 10 of Item.IRON_INGOT,
    alternate = true,
  ),
  PURE_IRON_INGOT(
    "Pure Iron Ingot",
    time = 12,
    inputs = listOf(
      7 of Item.IRON_ORE,
      4 of Item.WATER,
    ),
    product = 13 of Item.IRON_INGOT,
    alternate = true,
  ),
  LEACHED_IRON_INGOT(
    "Leached Iron Ingot",
    time = 6,
    inputs = listOf(
      5 of Item.IRON_ORE,
      1 of Item.SULFURIC_ACID,
    ),
    product = 10 of Item.IRON_INGOT,
    alternate = true,
  ),

  /* Iron Plate */

  IRON_PLATE(
    "Iron Plate",
    time = 6,
    inputs = listOf(3 of Item.IRON_INGOT),
    product = 2 of Item.IRON_PLATE,
  ),
  STEEL_CAST_PLATE(
    "Steel Cast Plate",
    time = 4,
    inputs = listOf(
      1 of Item.IRON_INGOT,
      1 of Item.STEEL_INGOT,
    ),
    product = 3 of Item.IRON_PLATE,
    alternate = true,
  ),
  COATED_IRON_PLATE(
    "Coated Iron Plate",
    time = 8,
    inputs = listOf(
      5 of Item.IRON_INGOT,
      1 of Item.PLASTIC,
    ),
    product = 10 of Item.IRON_PLATE,
    alternate = true,
  ),

  /* Iron Rod */

  IRON_ROD(
    "Iron Rod",
    time = 4,
    inputs = listOf(1 of Item.IRON_INGOT),
    product = 1 of Item.IRON_ROD,
  ),
  STEEL_ROD(
    "Steel Rod",
    time = 5,
    inputs = listOf(1 of Item.STEEL_INGOT),
    product = 4 of Item.IRON_ROD,
    alternate = true,
  ),
  ALUMINUM_ROD(
    "Aluminum Rod",
    time = 8,
    inputs = listOf(1 of Item.ALUMINUM_INGOT),
    product = 7 of Item.IRON_ROD,
    alternate = true,
  ),

  /* Xeno-Zapper */

  XENO_ZAPPER(
    "Xeno-Zapper",
    time = 40,
    inputs = listOf(
      10 of Item.IRON_ROD,
      2 of Item.REINFORCED_IRON_PLATE,
      15 of Item.CABLE,
      50 of Item.WIRE,
    ),
    product = 1 of Item.XENO_ZAPPER,
  ),

  /* Portable Miner */

  PORTABLE_MINER(
    "Portable Miner",
    time = 40,
    inputs = listOf(
      2 of Item.IRON_PLATE,
      4 of Item.IRON_ROD,
    ),
    product = 1 of Item.PORTABLE_MINER,
  ),
  AUTOMATED_MINER(
    "Automated Miner",
    time = 60,
    inputs = listOf(
      4 of Item.STEEL_PIPE,
      4 of Item.IRON_PLATE,
    ),
    product = 1 of Item.PORTABLE_MINER,
    alternate = true,
  ),

  /* Copper Ingot */

  COPPER_INGOT(
    "Copper Ingot",
    time = 2,
    inputs = listOf(1 of Item.COPPER_ORE),
    product = 1 of Item.COPPER_INGOT,
  ),
  COPPER_ALLOY_INGOT(
    "Copper Alloy Ingot",
    time = 6,
    inputs = listOf(
      5 of Item.COPPER_ORE,
      5 of Item.IRON_ORE,
    ),
    product = 10 of Item.COPPER_INGOT,
    alternate = true,
  ),
  PURE_COPPER_INGOT(
    "Pure Copper Ingot",
    time = 24,
    inputs = listOf(
      6 of Item.COPPER_ORE,
      4 of Item.WATER,
    ),
    product = 15 of Item.COPPER_INGOT,
    alternate = true,
  ),
  TEMPERED_COPPER_INGOT(
    "Tempered Copper Ingot",
    time = 12,
    inputs = listOf(
      5 of Item.COPPER_ORE,
      8 of Item.PETROLEUM_COKE,
    ),
    product = 12 of Item.COPPER_INGOT,
    alternate = true,
  ),
  LEACHED_COPPER_INGOT(
    "Leached Copper Ingot",
    time = 12,
    inputs = listOf(
      9 of Item.COPPER_ORE,
      5 of Item.SULFURIC_ACID,
    ),
    product = 22 of Item.COPPER_INGOT,
    alternate = true,
  ),

  /* Wire */

  WIRE(
    "Wire",
    time = 4,
    inputs = listOf(1 of Item.COPPER_INGOT),
    product = 2 of Item.WIRE,
  ),
  IRON_WIRE(
    "Iron Wire",
    time = 24,
    inputs = listOf(5 of Item.IRON_INGOT),
    product = 9 of Item.WIRE,
    alternate = true,
  ),
  CATERIUM_WIRE(
    "Caterium Wire",
    time = 4,
    inputs = listOf(1 of Item.CATERIUM_INGOT),
    product = 8 of Item.WIRE,
    alternate = true,
  ),
  FUSED_WIRE(
    "Fused Wire",
    time = 20,
    inputs = listOf(
      4 of Item.COPPER_INGOT,
      1 of Item.CATERIUM_INGOT,
    ),
    product = 30 of Item.WIRE,
    alternate = true,
  ),

  /* Cable */

  CABLE(
    "Cable",
    time = 2,
    inputs = listOf(2 of Item.WIRE),
    product = 1 of Item.CABLE,
  ),
  COATED_CABLE(
    "Coated Cable",
    time = 8,
    inputs = listOf(
      5 of Item.WIRE,
      2 of Item.HEAVY_OIL_RESIDUE,
    ),
    product = 9 of Item.CABLE,
    alternate = true,
  ),
  INSULATED_CABLE(
    "INSULATED_CABLE",
    time = 12,
    inputs = listOf(
      9 of Item.WIRE,
      6 of Item.RUBBER,
    ),
    product = 20 of Item.CABLE,
    alternate = true,
  ),
  QUICKWIRE_CABLE(
    "Quickwire Cable",
    time = 24,
    inputs = listOf(
      3 of Item.QUICKWIRE,
      2 of Item.RUBBER,
    ),
    product = 11 of Item.CABLE,
    alternate = true,
  ),

  /* Concrete */

  CONCRETE(
    "Concrete",
    time = 4,
    inputs = listOf(3 of Item.LIMESTONE),
    product = 1 of Item.CONCRETE,
  ),
  RUBBER_CONCRETE(
    "Rubber Concrete",
    time = 6,
    inputs = listOf(
      10 of Item.LIMESTONE,
      2 of Item.RUBBER,
    ),
    product = 9 of Item.CONCRETE,
    alternate = true,
  ),
  WET_CONCRETE(
    "Wet Concrete",
    time = 3,
    inputs = listOf(
      6 of Item.LIMESTONE,
      5 of Item.WATER,
    ),
    product = 4 of Item.CONCRETE,
    alternate = true,
  ),
  FINE_CONCRETE(
    "Fine Concrete",
    time = 12,
    inputs = listOf(
      3 of Item.SILICA,
      12 of Item.LIMESTONE,
    ),
    product = 10 of Item.CONCRETE,
    alternate = true,
  ),

  /* Screws */

  SCREWS(
    "Screws",
    time = 6,
    inputs = listOf(5 of Item.IRON_INGOT),
    product = 4 of Item.SCREWS,
  ),
  CAST_SCREWS(
    "Cast Screws",
    time = 24,
    inputs = listOf(5 of Item.IRON_INGOT),
    product = 20 of Item.SCREWS,
    alternate = true,
  ),
  STEEL_SCREWS(
    "Steel Screws",
    time = 12,
    inputs = listOf(1 of Item.STEEL_BEAM),
    product = 52 of Item.SCREWS,
    alternate = true,
  ),

  /* Reinforced Iron Plate */

  REINFORCED_IRON_PLATE(
    "Reinforced Iron Plate",
    time = 12,
    inputs = listOf(
      6 of Item.IRON_PLATE,
      12 of Item.SCREWS,
    ),
    product = 1 of Item.REINFORCED_IRON_PLATE,
  ),
  BOLTED_IRON_PLATE(
    "Bolted Iron Plate",
    time = 12,
    inputs = listOf(
      18 of Item.IRON_PLATE,
      50 of Item.SCREWS,
    ),
    product = 3 of Item.REINFORCED_IRON_PLATE,
    alternate = true,
  ),
  STITCHED_IRON_PLATE(
    "Stitched Iron Plate",
    time = 32,
    inputs = listOf(
      10 of Item.IRON_PLATE,
      20 of Item.WIRE,
    ),
    product = 3 of Item.REINFORCED_IRON_PLATE,
    alternate = true,
  ),
  ADHERED_IRON_PLATE(
    "Adhered Iron Plate",
    time = 16,
    inputs = listOf(
      3 of Item.IRON_PLATE,
      1 of Item.RUBBER,
    ),
    product = 1 of Item.REINFORCED_IRON_PLATE,
    alternate = true,
  ),

  /* Biomass */

  BIOMASS_LEAVES(
    "Biomass (Leaves)",
    time = 5,
    inputs = listOf(10 of Item.LEAVES),
    product = 5 of Item.BIOMASS,
  ),
  BIOMASS_WOOD(
    "Biomass (Wood)",
    time = 4,
    inputs = listOf(4 of Item.WOOD),
    product = 20 of Item.BIOMASS,
  ),
  BIOMASS_ALIEN_PROTEIN(
    "Biomass (Alien Protein)",
    time = 4,
    inputs = listOf(1 of Item.ALIEN_PROTEIN),
    product = 100 of Item.BIOMASS,
  ),
  BIOMASS_MYCELIA(
    "Biomass (Mycelia)",
    time = 4,
    inputs = listOf(1 of Item.MYCELIA),
    product = 10 of Item.BIOMASS,
  ),

  /* Object Scanner */

  OBJECT_SCANNER(
    "Object Scanner",
    time = 40,
    inputs = listOf(
      4 of Item.REINFORCED_IRON_PLATE,
      20 of Item.WIRE,
      50 of Item.SCREWS,
    ),
    product = 1 of Item.OBJECT_SCANNER,
  ),

  /* Alien Protein */

  HATCHER_PROTEIN(
    "Hatcher Protein",
    time = 3,
    inputs = listOf(1 of Item.HATCHER_REMAINS),
    product = 1 of Item.ALIEN_PROTEIN,
  ),
  HOG_PROTEIN(
    "Hog Protein",
    time = 3,
    inputs = listOf(1 of Item.HOG_REMAINS),
    product = 1 of Item.ALIEN_PROTEIN,
  ),
  SPITTER_PROTEIN(
    "Spitter Protein",
    time = 3,
    inputs = listOf(1 of Item.PLASMA_SPITTER_REMAINS),
    product = 1 of Item.ALIEN_PROTEIN,
  ),
  STINGER_PROTEIN(
    "Stinger Protein",
    time = 3,
    inputs = listOf(1 of Item.STINGER_REMAINS),
    product = 1 of Item.ALIEN_PROTEIN,
  ),

  /* Alien DNA Capsule */

  ALIEN_DNA_CAPSULE(
    "Alien DNA Capsule",
    time = 6,
    inputs = listOf(1 of Item.ALIEN_PROTEIN),
    product = 1 of Item.ALIEN_DNA_CAPSULE,
  ),

  /* Fabric */

  FABRIC(
    "Fabric",
    time = 4,
    inputs = listOf(
      1 of Item.MYCELIA,
      5 of Item.BIOMASS,
    ),
    product = 1 of Item.FABRIC,
  ),
  POLYESTER_FABRIC(
    "Polyester Fabric",
    time = 2,
    inputs = listOf(
      1 of Item.POLYMER_RESIN,
      1 of Item.WATER,
    ),
    product = 1 of Item.FABRIC,
    alternate = true,
  ),

  /* Parachute */

  PARACHUTE(
    "Parachute",
    time = 40,
    inputs = listOf(
      20 of Item.FABRIC,
      10 of Item.CABLE,
    ),
    product = 1 of Item.PARACHUTE,
  ),

  /* Power Shard */

  POWER_SHARD_1(
    "Power Shard (1)",
    time = 8,
    inputs = listOf(1 of Item.BLUE_POWER_SLUG),
    product = 1 of Item.POWER_SHARD,
  ),
  POWER_SHARD_2(
    "Power Shard (2)",
    time = 12,
    inputs = listOf(1 of Item.YELLOW_POWER_SLUG),
    product = 2 of Item.POWER_SHARD,
  ),
  POWER_SHARD_5(
    "Power Shard (5)",
    time = 24,
    inputs = listOf(1 of Item.PURPLE_POWER_SLUG),
    product = 5 of Item.POWER_SHARD,
  ),
  SYNTHETIC_POWER_SHARD(
    "Synthetic Power Shard",
    time = 12,
    inputs = listOf(
      2 of Item.TIME_CRYSTAL,
      2 of Item.DARK_MATTER_CRYSTAL,
      12 of Item.QUARTZ_CRYSTAL,
      12 of Item.EXCITED_PHOTONIC_MATTER,
    ),
    product = 1 of Item.POWER_SHARD,
    byproduct = 12 of Item.DARK_MATTER_RESIDUE,
  ),

  ;

  constructor(
    displayName: String,
    time: Int,
    inputs: List<Component>,
    product: Component,
    byproduct: Component? = null,
    power: ClosedRange<Double>? = null,
    alternate: Boolean = false
  ) : this(displayName, time.q, inputs, product, byproduct, power, alternate)

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
}
