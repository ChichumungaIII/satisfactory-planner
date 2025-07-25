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
  val power: ClosedRange<Double>? = null,
  /** Whether this is an alternate recipe scanned from a hard drive. */
  val alternate: Boolean,
) {
  /* Iron Ore */

  IRON_ORE_LIMESTONE(
    "Iron Ore (Limestone)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      24 of Item.LIMESTONE,
    ),
    product = 12 of Item.IRON_ORE,
    power = 100..400,
  ),


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

  /* Copper Ore */

  COPPER_ORE_QUARTZ(
    "Copper Ore (Quartz)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      10 of Item.RAW_QUARTZ,
    ),
    product = 12 of Item.COPPER_ORE,
    power = 100..400,
  ),
  COPPER_ORE_SULFUR(
    "Copper Ore (Sulfur)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      12 of Item.SULFUR,
    ),
    product = 12 of Item.COPPER_ORE,
    power = 100..400,
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

  /* Limestone */

  LIMESTONE_SULFUR(
    "Limestone (Sulfur)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      2 of Item.SULFUR,
    ),
    product = 12 of Item.LIMESTONE,
    power = 100..400
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
    power = 0..2_000
  ),

  /* Copper Sheet */

  COPPER_SHEET(
    "Copper Sheet",
    time = 6,
    inputs = listOf(2 of Item.COPPER_INGOT),
    product = 1 of Item.COPPER_SHEET,
  ),
  STEAMED_COPPER_SHEET(
    "Steamed Copper Sheet",
    time = 8,
    inputs = listOf(
      3 of Item.COPPER_INGOT,
      3 of Item.WATER,
    ),
    product = 3 of Item.COPPER_SHEET,
    alternate = true,
  ),

  /* Rotor */

  ROTOR(
    "Rotor",
    time = 15,
    inputs = listOf(
      5 of Item.IRON_ROD,
      25 of Item.SCREWS,
    ),
    product = 1 of Item.ROTOR,
  ),
  COPPER_ROTOR(
    "Copper Rotor",
    time = 16,
    inputs = listOf(
      6 of Item.COPPER_SHEET,
      52 of Item.ROTOR,
    ),
    product = 3 of Item.ROTOR,
    alternate = true,
  ),
  STEEL_ROTOR(
    "Steel Rotor",
    time = 12,
    inputs = listOf(
      2 of Item.STEEL_PIPE,
      6 of Item.WIRE,
    ),
    product = 1 of Item.ROTOR,
    alternate = true,
  ),

  /* Modular Frame */

  MODULAR_FRAME(
    "Modular Frame",
    time = 60,
    inputs = listOf(
      3 of Item.REINFORCED_IRON_PLATE,
      12 of Item.IRON_ROD,
    ),
    product = 2 of Item.MODULAR_FRAME,
  ),
  BOLTED_FRAME(
    "Bolted Frame",
    time = 24,
    inputs = listOf(
      3 of Item.REINFORCED_IRON_PLATE,
      56 of Item.SCREWS,
    ),
    product = 2 of Item.MODULAR_FRAME,
    alternate = true,
  ),
  STEELED_FRAME(
    "Steeled Frame",
    time = 60,
    inputs = listOf(
      2 of Item.REINFORCED_IRON_PLATE,
      10 of Item.STEEL_PIPE,
    ),
    product = 3 of Item.MODULAR_FRAME,
    alternate = true,
  ),

  /* Smart Plating */

  SMART_PLATING(
    "Smart Plating",
    time = 30,
    inputs = listOf(
      1 of Item.REINFORCED_IRON_PLATE,
      1 of Item.ROTOR,
    ),
    product = 1 of Item.SMART_PLATING,
  ),
  PLASTIC_SMART_PLATING(
    "Plastic Smart Plating",
    time = 24,
    inputs = listOf(
      1 of Item.REINFORCED_IRON_PLATE,
      1 of Item.ROTOR,
      3 of Item.PLASTIC,
    ),
    product = 2 of Item.SMART_PLATING,
    alternate = true,
  ),

  /* Solid Biofuel */

  SOLID_BIOFUEL(
    "Solid Biofuel",
    time = 4,
    inputs = listOf(8 of Item.BIOMASS),
    product = 4 of Item.SOLID_BIOFUEL,
  ),

  /* Chainsaw */

  CHAINSAW(
    "Chainsaw",
    time = 60,
    inputs = listOf(
      5 of Item.REINFORCED_IRON_PLATE,
      25 of Item.IRON_ROD,
      160 of Item.SCREWS,
      15 of Item.CABLE,
    ),
    product = 1 of Item.CHAINSAW,
  ),

  /* Medicinal Inhaler */

  NUTRITIONAL_INHALER(
    "Nutritional Inhaler",
    time = 20,
    inputs = listOf(
      1 of Item.BACON_AGARIC,
      2 of Item.PALEBERRY,
      5 of Item.BERYL_NUT,
    ),
    product = 1 of Item.MEDICINAL_INHALER,
  ),
  PROTEIN_INHALER(
    "Protein Inhaler",
    time = 20,
    inputs = listOf(
      1 of Item.ALIEN_PROTEIN,
      10 of Item.BERYL_NUT,
    ),
    product = 1 of Item.MEDICINAL_INHALER,
  ),
  THERAPEUTIC_INHALER(
    "Therapeutic Inhaler",
    time = 20,
    inputs = listOf(
      15 of Item.MYCELIA,
      1 of Item.ALIEN_PROTEIN,
      1 of Item.BACON_AGARIC,
    ),
    product = 1 of Item.MEDICINAL_INHALER,
  ),
  VITAMIN_INHALER(
    "Vitamin Inhaler",
    time = 20,
    inputs = listOf(
      10 of Item.MYCELIA,
      5 of Item.PALEBERRY,
    ),
    product = 1 of Item.MEDICINAL_INHALER,
  ),

  /* Rebar Gun */

  REBAR_GUN(
    "Rebar Gun",
    time = 60,
    inputs = listOf(
      6 of Item.REINFORCED_IRON_PLATE,
      16 of Item.IRON_ROD,
      100 of Item.SCREWS,
    ),
    product = 1 of Item.REBAR_GUN,
  ),

  /* Iron Rebar */

  IRON_REBAR(
    "Iron Rebar",
    time = 4,
    inputs = listOf(1 of Item.IRON_ROD),
    product = 1 of Item.IRON_REBAR,
  ),

  /* Coal */

  COAL_IRON(
    "Coal (Iron)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      18 of Item.IRON_ORE,
    ),
    product = 12 of Item.COAL,
    power = 100..400,
  ),
  COAL_LIMESTONE(
    "Coal (Limestone)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      36 of Item.LIMESTONE,
    ),
    product = 12 of Item.COAL,
    power = 100..400,
  ),
  BIOCOAL(
    "Biocoal",
    time = 8,
    inputs = listOf(5 of Item.BIOMASS),
    product = 6 of Item.COAL,
    alternate = true,
  ),
  CHARCOAL(
    "Charcoal",
    time = 4,
    inputs = listOf(1 of Item.WOOD),
    product = 10 of Item.COAL,
    alternate = true,
  ),

  /* Steel Ingot */

  STEEL_INGOT(
    "Steel Ingot",
    time = 4,
    inputs = listOf(
      3 of Item.IRON_ORE,
      3 of Item.COAL,
    ),
    product = 3 of Item.STEEL_INGOT,
  ),
  SOLID_STEEL_INGOT(
    "Solid Steel Ingot",
    time = 3,
    inputs = listOf(
      2 of Item.IRON_INGOT,
      2 of Item.COAL,
    ),
    product = 3 of Item.STEEL_INGOT,
    alternate = true,
  ),
  COKE_STEEL_INGOT(
    "Coke Steel Ingot",
    time = 12,
    inputs = listOf(
      15 of Item.IRON_ORE,
      15 of Item.PETROLEUM_COKE,
    ),
    product = 20 of Item.STEEL_INGOT,
    alternate = true,
  ),
  COMPACTED_STEEL_INGOT(
    "Compacted Steel Ingot",
    time = 24,
    inputs = listOf(
      2 of Item.IRON_ORE,
      1 of Item.COMPACTED_COAL,
    ),
    product = 4 of Item.STEEL_INGOT,
    alternate = true,
  ),

  /* Steel Beam */

  STEEL_BEAM(
    "Steel Beam",
    time = 4,
    inputs = listOf(4 of Item.STEEL_INGOT),
    product = 1 of Item.STEEL_BEAM,
  ),
  MOLDED_BEAM(
    "Molded Beam",
    time = 12,
    inputs = listOf(
      24 of Item.STEEL_INGOT,
      16 of Item.CONCRETE,
    ),
    product = 9 of Item.STEEL_BEAM,
    alternate = true,
  ),
  ALUMINUM_BEAM(
    "Aluminum Beam",
    time = 8,
    inputs = listOf(3 of Item.ALUMINUM_INGOT),
    product = 3 of Item.STEEL_BEAM,
    alternate = true,
  ),

  /* Steel Pipe */

  STEEL_PIPE(
    "Steel Pipe",
    time = 6,
    inputs = listOf(3 of Item.STEEL_INGOT),
    product = 2 of Item.STEEL_PIPE,
  ),
  IRON_PIPE(
    "Iron Pipe",
    time = 12,
    inputs = listOf(20 of Item.IRON_INGOT),
    product = 4 of Item.STEEL_PIPE,
    alternate = true,
  ),
  MOLDED_STEEL_PIPE(
    "Molded Steel Pipe",
    time = 6,
    inputs = listOf(
      5 of Item.STEEL_INGOT,
      3 of Item.CONCRETE,
    ),
    product = 5 of Item.STEEL_PIPE,
    alternate = true,
  ),

  /* Versatile Framework */

  VERSATILE_FRAMEWORK(
    "Versatile Framework",
    time = 24,
    inputs = listOf(
      1 of Item.MODULAR_FRAME,
      12 of Item.STEEL_BEAM,
    ),
    product = 2 of Item.VERSATILE_FRAMEWORK,
  ),
  FLEXIBLE_FRAMEWORK(
    "Flexible Framework",
    time = 16,
    inputs = listOf(
      1 of Item.MODULAR_FRAME,
      6 of Item.STEEL_BEAM,
      8 of Item.RUBBER,
    ),
    product = 2 of Item.VERSATILE_FRAMEWORK,
    alternate = true,
  ),

  /* Xeno-Basher */

  XENO_BASHER(
    "Xeno-Basher",
    time = 80,
    inputs = listOf(
      2 of Item.XENO_ZAPPER,
      5 of Item.MODULAR_FRAME,
      25 of Item.IRON_ROD,
      500 of Item.WIRE,
    ),
    product = 1 of Item.XENO_BASHER,
  ),

  /* Gas Mask */

  GAS_MASK(
    "Gas Mask",
    time = 60,
    inputs = listOf(
      50 of Item.FABRIC,
      10 of Item.COPPER_SHEET,
      10 of Item.STEEL_PIPE,
    ),
    product = 1 of Item.GAS_MASK,
  ),

  /* Gas Filter */

  GAS_FILTER(
    "Gas Filter",
    time = 8,
    inputs = listOf(
      2 of Item.FABRIC,
      4 of Item.COAL,
      2 of Item.IRON_PLATE,
    ),
    product = 1 of Item.GAS_FILTER,
  ),

  /* Encased Industrial Beam */

  ENCASED_INDUSTRIAL_BEAM(
    "Encased Industrial Beam",
    time = 10,
    inputs = listOf(
      3 of Item.STEEL_BEAM,
      6 of Item.CONCRETE,
    ),
    product = 1 of Item.ENCASED_INDUSTRIAL_BEAM,
  ),
  ENCASED_INDUSTRIAL_PIPE(
    "Encased Industrial Pipe",
    time = 15,
    inputs = listOf(
      6 of Item.STEEL_PIPE,
      5 of Item.CONCRETE,
    ),
    product = 1 of Item.ENCASED_INDUSTRIAL_BEAM,
    alternate = true,
  ),

  /* Stator */

  STATOR(
    "Stator",
    time = 12,
    inputs = listOf(
      3 of Item.STEEL_PIPE,
      8 of Item.WIRE,
    ),
    product = 1 of Item.STATOR,
  ),
  QUICKWIRE_STATOR(
    "Quickwire Stator",
    time = 15,
    inputs = listOf(
      4 of Item.STEEL_PIPE,
      15 of Item.QUICKWIRE,
    ),
    product = 2 of Item.STATOR,
    alternate = true,
  ),

  /* Motor */

  MOTOR(
    "Motor",
    time = 12,
    inputs = listOf(
      2 of Item.ROTOR,
      2 of Item.STATOR,
    ),
    product = 1 of Item.MOTOR,
  ),
  RIGOR_MOTOR(
    "Rigor Motor",
    time = 48,
    inputs = listOf(
      3 of Item.ROTOR,
      3 of Item.STATOR,
      1 of Item.CRYSTAL_OSCILLATOR,
    ),
    product = 6 of Item.MOTOR,
    alternate = true,
  ),
  ELECTRIC_MOTOR(
    "Electric Motor",
    time = 16,
    inputs = listOf(
      1 of Item.ELECTROMAGNETIC_CONTROL_ROD,
      2 of Item.ROTOR,
    ),
    product = 2 of Item.MOTOR,
    alternate = true,
  ),

  /* Automated Wiring */

  AUTOMATED_WIRING(
    "Automated Wiring",
    time = 24,
    inputs = listOf(
      1 of Item.STATOR,
      20 of Item.CABLE,
    ),
    product = 1 of Item.AUTOMATED_WIRING,
  ),
  AUTOMATED_SPEED_WIRING(
    "Automated Speed Wiring",
    time = 32,
    inputs = listOf(
      2 of Item.STATOR,
      40 of Item.WIRE,
      1 of Item.HIGH_SPEED_CONNECTOR,
    ),
    product = 4 of Item.AUTOMATED_WIRING,
    alternate = true,
  ),

  /* Jetpack */

  JETPACK(
    "Jetpack",
    time = 60,
    inputs = listOf(
      5 of Item.MOTOR,
      10 of Item.STEEL_PIPE,
      25 of Item.IRON_PLATE,
      50 of Item.WIRE,
    ),
    product = 1 of Item.JETPACK,
  ),

  /* Plastic */

  PLASTIC(
    "Plastic",
    time = 6,
    inputs = listOf(3 of Item.CRUDE_OIL),
    product = 2 of Item.PLASTIC,
    byproduct = 1 of Item.HEAVY_OIL_RESIDUE,
  ),
  RESIDUAL_PLASTIC(
    "Residual Plastic",
    time = 6,
    inputs = listOf(
      6 of Item.POLYMER_RESIN,
      2 of Item.WATER,
    ),
    product = 2 of Item.PLASTIC,
  ),
  RECYCLED_PLASTIC(
    "Recycled Plastic",
    time = 12,
    inputs = listOf(
      6 of Item.RUBBER,
      6 of Item.FUEL,
    ),
    product = 12 of Item.PLASTIC,
    alternate = true,
  ),

  /* Rubber */

  RUBBER(
    "Rubber",
    time = 6,
    inputs = listOf(2 of Item.CRUDE_OIL),
    product = 2 of Item.RUBBER,
    byproduct = 2 of Item.HEAVY_OIL_RESIDUE,
  ),
  RESIDUAL_RUBBER(
    "Residual Rubber",
    time = 6,
    inputs = listOf(
      4 of Item.POLYMER_RESIN,
      4 of Item.WATER,
    ),
    product = 2 of Item.RUBBER,
  ),
  RECYCLED_RUBBER(
    "Recycled Rubber",
    time = 12,
    inputs = listOf(
      6 of Item.PLASTIC,
      6 of Item.FUEL,
    ),
    product = 12 of Item.RUBBER,
    alternate = true,
  ),

  /* Fuel */

  FUEL(
    "Fuel",
    time = 6,
    inputs = listOf(6 of Item.CRUDE_OIL),
    product = 4 of Item.FUEL,
    byproduct = 3 of Item.POLYMER_RESIN,
  ),
  RESIDUAL_FUEL(
    "Residual Fuel",
    time = 6,
    inputs = listOf(6 of Item.HEAVY_OIL_RESIDUE),
    product = 4 of Item.FUEL,
  ),
  DILUTED_FUEL(
    "Diluted Fuel",
    time = 6,
    inputs = listOf(
      5 of Item.HEAVY_OIL_RESIDUE,
      10 of Item.WATER,
    ),
    product = 10 of Item.FUEL,
    alternate = true,
  ),

  /* Heavy Oil Residue */

  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    time = 6,
    inputs = listOf(3 of Item.CRUDE_OIL),
    product = 4 of Item.HEAVY_OIL_RESIDUE,
    byproduct = 2 of Item.POLYMER_RESIN,
    alternate = true,
  ),

  /* Polymer Resin */

  POLYMER_RESIN(
    "Polymer Resin",
    time = 6,
    inputs = listOf(6 of Item.CRUDE_OIL),
    product = 13 of Item.POLYMER_RESIN,
    byproduct = 2 of Item.HEAVY_OIL_RESIDUE,
    alternate = true,
  ),

  /* Petroleum Coke */

  PETROLEUM_COKE(
    "Petroleum Coke",
    time = 6,
    inputs = listOf(4 of Item.HEAVY_OIL_RESIDUE),
    product = 12 of Item.PETROLEUM_COKE,
  ),

  /* Circuit Board */

  CIRCUIT_BOARD(
    "Circuit Board",
    time = 8,
    inputs = listOf(
      2 of Item.CIRCUIT_BOARD,
      4 of Item.PLASTIC,
    ),
    product = 1 of Item.CIRCUIT_BOARD,
  ),
  ELECTRODE_CIRCUIT_BOARD(
    "Electrode Circuit Board",
    time = 12,
    inputs = listOf(
      4 of Item.RUBBER,
      8 of Item.PETROLEUM_COKE,
    ),
    product = 1 of Item.CIRCUIT_BOARD,
    alternate = true,
  ),
  CATERIUM_CIRCUIT_BOARD(
    "Caterium Circuit Board",
    time = 48,
    inputs = listOf(
      10 of Item.PLASTIC,
      30 of Item.QUICKWIRE,
    ),
    product = 7 of Item.CIRCUIT_BOARD,
    alternate = true,
  ),
  SILICON_CIRCUIT_BOARD(
    "Silicon Circuit Board",
    time = 24,
    inputs = listOf(
      11 of Item.COPPER_SHEET,
      11 of Item.SILICA,
    ),
    product = 5 of Item.CIRCUIT_BOARD,
    alternate = true,
  ),

  /* Liquid Biofuel */

  LIQUID_BIOFUEL(
    "Liquid Biofuel",
    time = 4,
    inputs = listOf(
      6 of Item.SOLID_BIOFUEL,
      3 of Item.WATER,
    ),
    product = 4 of Item.LIQUID_BIOFUEL,
  ),

  /* Empty Canister */

  EMPTY_CANISTER(
    "Empty Canister",
    time = 4,
    inputs = listOf(2 of Item.PLASTIC),
    product = 4 of Item.EMPTY_CANISTER,
  ),
  COATED_IRON_CANISTER(
    "Coated Iron Canister",
    time = 4,
    inputs = listOf(
      2 of Item.IRON_PLATE,
      1 of Item.COPPER_SHEET,
    ),
    product = 4 of Item.EMPTY_CANISTER,
    alternate = true,
  ),
  STEEL_CANISTER(
    "Steel Canister",
    time = 6,
    inputs = listOf(4 of Item.STEEL_INGOT),
    product = 4 of Item.EMPTY_CANISTER,
    alternate = true,
  ),

  /* Packaged Water */

  PACKAGED_WATER(
    "Packaged Water",
    time = 2,
    inputs = listOf(
      2 of Item.WATER,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_WATER,
  ),
  UNPACKAGE_WATER(
    "Unpackage Water",
    time = 1,
    inputs = listOf(2 of Item.PACKAGED_WATER),
    product = 2 of Item.WATER,
    byproduct = 2 of Item.EMPTY_CANISTER,
  ),

  /* Packaged Oil */

  PACKAGED_OIL(
    "Packaged Oil",
    time = 4,
    inputs = listOf(
      2 of Item.CRUDE_OIL,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_OIL,
  ),
  UNPACKAGE_OIL(
    "Unpackage Oil",
    time = 2,
    inputs = listOf(2 of Item.PACKAGED_OIL),
    product = 2 of Item.CRUDE_OIL,
    byproduct = 2 of Item.EMPTY_CANISTER,
  ),

  /* Packaged Fuel */

  PACKAGED_FUEL(
    "Packaged Fuel",
    time = 3,
    inputs = listOf(
      2 of Item.FUEL,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_FUEL,
  ),
  DILUTED_PACKAGED_FUEL(
    "Diluted Packaged Fuel",
    time = 2,
    inputs = listOf(
      1 of Item.HEAVY_OIL_RESIDUE,
      2 of Item.PACKAGED_WATER,
    ),
    product = 2 of Item.PACKAGED_FUEL,
    alternate = true,
  ),
  UNPACKAGE_FUEL(
    "Unpackage Fuel",
    time = 2,
    inputs = listOf(2 of Item.PACKAGED_FUEL),
    product = 2 of Item.FUEL,
    byproduct = 2 of Item.EMPTY_CANISTER,
  ),

  /* Packaged Heavy Oil Residue */

  PACKAGED_HEAVY_OIL_RESIDUE(
    "Packaged Heavy Oil Residue",
    time = 4,
    inputs = listOf(
      2 of Item.HEAVY_OIL_RESIDUE,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_HEAVY_OIL_RESIDUE,
  ),
  UNPACKAGE_HEAVY_OIL_RESIDUE(
    "Unpackage Heavy Oil Residue",
    time = 6,
    inputs = listOf(2 of Item.PACKAGED_HEAVY_OIL_RESIDUE),
    product = 2 of Item.HEAVY_OIL_RESIDUE,
    byproduct = 2 of Item.EMPTY_CANISTER,
  ),

  /* Packaged Liquid Biofuel */

  PACKAGED_LIQUID_BIOFUEL(
    "Packaged Liquid Biofuel",
    time = 3,
    inputs = listOf(
      2 of Item.LIQUID_BIOFUEL,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_LIQUID_BIOFUEL,
  ),
  UNPACKAGE_LIQUID_BIOFUEL(
    "Unpackage Liquid Biofuel",
    time = 2,
    inputs = listOf(2 of Item.PACKAGED_LIQUID_BIOFUEL),
    product = 2 of Item.LIQUID_BIOFUEL,
    byproduct = 2 of Item.EMPTY_CANISTER,
  ),

  /* Caterium Ore */

  CATERIUM_ORE_COPPER(
    "Caterium Ore (Copper)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      15 of Item.COPPER_ORE,
    ),
    product = 12 of Item.CATERIUM_ORE,
    power = 100..400,
  ),
  CATERIUM_ORE_QUARTZ(
    "Caterium Ore (Quartz)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      12 of Item.RAW_QUARTZ,
    ),
    product = 12 of Item.CATERIUM_ORE,
    power = 100..400,
  ),

  /* Caterium Ingot */

  CATERIUM_INGOT(
    "Caterium Ingot",
    time = 4,
    inputs = listOf(3 of Item.CATERIUM_ORE),
    product = 1 of Item.CATERIUM_INGOT,
  ),
  PURE_CATERIUM_INGOT(
    "Pure Caterium Ingot",
    time = 5,
    inputs = listOf(
      2 of Item.CATERIUM_ORE,
      2 of Item.WATER,
    ),
    product = 1 of Item.CATERIUM_INGOT,
    alternate = true,
  ),
  TEMPERED_CATERIUM_INGOT(
    "Tempered Caterium Ingot",
    time = 8,
    inputs = listOf(
      6 of Item.CATERIUM_ORE,
      2 of Item.PETROLEUM_COKE,
    ),
    product = 3 of Item.CATERIUM_INGOT,
    alternate = true,
  ),
  LEACHED_CATERIUM_INGOT(
    "Leached Caterium ingot",
    time = 10,
    inputs = listOf(
      9 of Item.CATERIUM_ORE,
      5 of Item.SULFURIC_ACID,
    ),
    product = 6 of Item.CATERIUM_INGOT,
    alternate = true,
  ),

  /* Quickwire */

  QUICKWIRE(
    "Quickwire",
    time = 5,
    inputs = listOf(1 of Item.CATERIUM_INGOT),
    product = 5 of Item.QUICKWIRE,
  ),
  FUSED_QUICKWIRE(
    "Fused Quickwire",
    time = 8,
    inputs = listOf(
      1 of Item.CATERIUM_INGOT,
      5 of Item.COPPER_INGOT,
    ),
    product = 12 of Item.QUICKWIRE,
    alternate = true,
  ),

  /* Zipline */

  ZIPLINE(
    "Zipline",
    time = 40,
    inputs = listOf(
      1 of Item.XENO_ZAPPER,
      30 of Item.QUICKWIRE,
      3 of Item.IRON_ROD,
      10 of Item.CABLE,
    ),
    product = 1 of Item.ZIPLINE,
  ),

  /* Stun Rebar */

  STUN_REBAR(
    "Stun Rebar",
    time = 6,
    inputs = listOf(
      1 of Item.IRON_REBAR,
      5 of Item.QUICKWIRE,
    ),
    product = 1 of Item.STUN_REBAR,
  ),

  /* AI Limiter */

  AI_LIMITER(
    "AI Limiter",
    time = 12,
    inputs = listOf(
      5 of Item.COPPER_SHEET,
      20 of Item.QUICKWIRE,
    ),
    product = 1 of Item.AI_LIMITER,
  ),
  PLASTIC_AI_LIMITER(
    "Plastic AI Limiter",
    time = 15,
    inputs = listOf(
      30 of Item.QUICKWIRE,
      7 of Item.PLASTIC,
    ),
    product = 2 of Item.AI_LIMITER,
    alternate = true,
  ),

  /* High-Speed Connector */

  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    time = 16,
    inputs = listOf(
      56 of Item.QUICKWIRE,
      10 of Item.CABLE,
      1 of Item.CIRCUIT_BOARD,
    ),
    product = 1 of Item.HIGH_SPEED_CONNECTOR,
  ),
  SILICON_HIGH_SPEED_CONNECTOR(
    "Silicon High-Speed Connector",
    time = 40,
    inputs = listOf(
      60 of Item.QUICKWIRE,
      25 of Item.SILICA,
      2 of Item.CIRCUIT_BOARD,
    ),
    product = 2 of Item.HIGH_SPEED_CONNECTOR,
    alternate = true,
  ),

  /* Computer */

  COMPUTER(
    "Computer",
    time = 24,
    inputs = listOf(
      4 of Item.CIRCUIT_BOARD,
      8 of Item.CABLE,
      16 of Item.PLASTIC,
    ),
    product = 1 of Item.COMPUTER,
  ),
  CATERIUM_COMPUTER(
    "Caterium Computer",
    time = 16,
    inputs = listOf(
      4 of Item.CIRCUIT_BOARD,
      14 of Item.QUICKWIRE,
      6 of Item.RUBBER,
    ),
    product = 1 of Item.COMPUTER,
    alternate = true,
  ),
  CRYSTAL_COMPUTER(
    "Crystal Computer",
    time = 36,
    inputs = listOf(
      3 of Item.CIRCUIT_BOARD,
      1 of Item.CRYSTAL_OSCILLATOR,
    ),
    product = 2 of Item.COMPUTER,
    alternate = true,
  ),

  /* Heavy Modular Frame */

  HEAVY_MODULAR_FRAME(
    "Heavy Modular Frame",
    time = 30,
    inputs = listOf(
      5 of Item.MODULAR_FRAME,
      20 of Item.STEEL_PIPE,
      5 of Item.ENCASED_INDUSTRIAL_BEAM,
      120 of Item.SCREWS,
    ),
    product = 1 of Item.HEAVY_MODULAR_FRAME,
  ),
  HEAVY_ENCASED_FRAME(
    "Heavy Encased Frame",
    time = 64,
    inputs = listOf(
      8 of Item.MODULAR_FRAME,
      10 of Item.ENCASED_INDUSTRIAL_BEAM,
      36 of Item.STEEL_PIPE,
      22 of Item.CONCRETE,
    ),
    product = 3 of Item.HEAVY_MODULAR_FRAME,
    alternate = true,
  ),
  HEAVY_FLEXIBLE_FRAME(
    "Heavy Flexible Frame",
    time = 16,
    inputs = listOf(
      5 of Item.MODULAR_FRAME,
      3 of Item.ENCASED_INDUSTRIAL_BEAM,
      20 of Item.RUBBER,
      104 of Item.SCREWS,
    ),
    product = 1 of Item.HEAVY_MODULAR_FRAME,
    alternate = true,
  ),

  /* Modular Engine */

  MODULAR_ENGINE(
    "Modular Engine",
    time = 60,
    inputs = listOf(
      2 of Item.MOTOR,
      15 of Item.RUBBER,
      2 of Item.SMART_PLATING,
    ),
    product = 1 of Item.MODULAR_ENGINE,
  ),

  /* Adaptive Control Unit */

  ADAPTIVE_CONTROL_UNIT(
    "Adaptive Control Unit",
    time = 60,
    inputs = listOf(
      5 of Item.AUTOMATED_WIRING,
      5 of Item.CIRCUIT_BOARD,
      1 of Item.HEAVY_MODULAR_FRAME,
      2 of Item.COMPUTER,
    ),
    product = 1 of Item.ADAPTIVE_CONTROL_UNIT,
  ),

  /* Raw Quartz */

  RAW_QUARTZ_COAL(
    "Raw Quartz (Coal)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      24 of Item.COAL,
    ),
    product = 12 of Item.RAW_QUARTZ,
    power = 100..400,
  ),
  RAW_QUARTZ_BAUXITE(
    "Raw Quartz (Bauxite)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      10 of Item.BAUXITE,
    ),
    product = 12 of Item.RAW_QUARTZ,
    power = 100..400,
  ),

  /* Quartz Crystal */

  QUARTZ_CRYSTAL(
    "Quartz Crystal",
    time = 8,
    inputs = listOf(5 of Item.RAW_QUARTZ),
    product = 3 of Item.QUARTZ_CRYSTAL,
  ),
  FUSED_QUARTZ_CRYSTAL(
    "Fused Quartz Crystal",
    time = 20,
    inputs = listOf(
      25 of Item.RAW_QUARTZ,
      12 of Item.COAL,
    ),
    product = 18 of Item.QUARTZ_CRYSTAL,
    alternate = true,
  ),
  PURE_QUARTZ_CRYSTAL(
    "Pure Quartz Crystal",
    time = 8,
    inputs = listOf(
      9 of Item.RAW_QUARTZ,
      5 of Item.WATER,
    ),
    product = 7 of Item.QUARTZ_CRYSTAL,
    alternate = true,
  ),
  QUARTZ_PURIFICATION(
    "Quartz Purification",
    time = 12,
    inputs = listOf(),
    product = 15 of Item.QUARTZ_CRYSTAL,
    byproduct = 2 of Item.DISSOLVED_SILICA,
    alternate = true,
  ),

  /* Silica */

  SILICA(
    "Silica",
    time = 8,
    inputs = listOf(2 of Item.RAW_QUARTZ),
    product = 5 of Item.SILICA,
  ),
  CHEAP_SILICA(
    "Cheap Silica",
    time = 8,
    inputs = listOf(
      3 of Item.RAW_QUARTZ,
      5 of Item.LIMESTONE,
    ),
    product = 7 of Item.SILICA,
    alternate = true,
  ),
  DISTILLED_SILICA(
    "Distilled Silica",
    time = 6,
    inputs = listOf(
      12 of Item.DISSOLVED_SILICA,
      5 of Item.LIMESTONE,
      10 of Item.WATER,
    ),
    product = 27 of Item.SILICA,
    byproduct = 8 of Item.WATER,
    alternate = true,
  ),

  /* Shatter Rebar */

  SHATTER_REBAR(
    "Shatter Rebar",
    time = 12,
    inputs = listOf(
      2 of Item.IRON_REBAR,
      3 of Item.QUARTZ_CRYSTAL,
    ),
    product = 1 of Item.SHATTER_REBAR,
  ),

  /* Crystal Oscillator */

  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    time = 120,
    inputs = listOf(
      36 of Item.QUARTZ_CRYSTAL,
      28 of Item.CABLE,
      5 of Item.REINFORCED_IRON_PLATE,
    ),
    product = 2 of Item.CRYSTAL_OSCILLATOR,
  ),
  INSULATED_CRYSTAL_OSCILLATOR(
    "Insulated Crystal Oscillator",
    time = 32,
    inputs = listOf(
      10 of Item.QUARTZ_CRYSTAL,
      7 of Item.RUBBER,
      1 of Item.AI_LIMITER,
    ),
    product = 1 of Item.CRYSTAL_OSCILLATOR,
    alternate = true,
  ),

  /* Blade Runners */

  BLADE_RUNNERS(
    "Blade Runners",
    time = 60,
    inputs = listOf(
      20 of Item.SILICA,
      3 of Item.MODULAR_FRAME,
      3 of Item.ROTOR,
    ),
    product = 1 of Item.BLADE_RUNNERS,
  ),

  /* Bauxite */

  BAUXITE_COPPER(
    "Bauxite (Copper)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      18 of Item.COPPER_ORE,
    ),
    product = 12 of Item.BAUXITE,
    power = 100..400,
  ),
  BAUXITE_CATERIUM(
    "Bauxite (Caterium)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      15 of Item.CATERIUM_ORE,
    ),
    product = 12 of Item.BAUXITE,
    power = 100..400,
  ),

  /* Alumina Solution */

  ALUMINA_SOLUTION(
    "Alumina Solution",
    time = 6,
    inputs = listOf(
      12 of Item.BAUXITE,
      18 of Item.WATER,
    ),
    product = 12 of Item.ALUMINA_SOLUTION,
    byproduct = 5 of Item.SILICA,
  ),
  SLOPPY_ALUMINA(
    "Sloppy Alumina",
    time = 3,
    inputs = listOf(
      10 of Item.BAUXITE,
      10 of Item.WATER,
    ),
    product = 12 of Item.ALUMINA_SOLUTION,
    alternate = true,
  ),

  /* Packaged Alumina Solution */

  PACKAGED_ALUMINA_SOLUTION(
    "Packaged Alumina Solution",
    time = 1,
    inputs = listOf(
      2 of Item.ALUMINA_SOLUTION,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_ALUMINA_SOLUTION,
  ),
  UNPACKAGE_ALUMINA_SOLUTION(
    "Unpackage Alumina Solution",
    time = 1,
    inputs = listOf(2 of Item.PACKAGED_ALUMINA_SOLUTION),
    product = 2 of Item.ALUMINA_SOLUTION,
    byproduct = 2 of Item.EMPTY_CANISTER,
  ),

  /* Aluminum Scrap */

  ALUMINUM_SCRAP(
    "Aluminum Scrap",
    time = 1,
    inputs = listOf(
      4 of Item.ALUMINA_SOLUTION,
      2 of Item.COAL,
    ),
    product = 6 of Item.ALUMINUM_SCRAP,
    byproduct = 2 of Item.WATER,
  ),
  ELECTRODE_ALUMINUM_SCRAP(
    "Electrode Aluminum Scrap",
    time = 4,
    inputs = listOf(
      12 of Item.ALUMINA_SOLUTION,
      4 of Item.PETROLEUM_COKE,
    ),
    product = 20 of Item.ALUMINA_SOLUTION,
    byproduct = 7 of Item.WATER,
    alternate = true,
  ),
  INSTANT_SCRAP(
    "Instant Scrap",
    time = 6,
    inputs = listOf(
      15 of Item.BAUXITE,
      10 of Item.COAL,
      5 of Item.SULFURIC_ACID,
      6 of Item.WATER,
    ),
    product = 30 of Item.ALUMINUM_SCRAP,
    byproduct = 5 of Item.WATER,
    alternate = true,
  ),

  /* Aluminum Ingot */

  ALUMINUM_INGOT(
    "Aluminum Ingot",
    time = 4,
    inputs = listOf(
      6 of Item.ALUMINUM_SCRAP,
      5 of Item.SILICA,
    ),
    product = 4 of Item.ALUMINUM_INGOT,
  ),
  PURE_ALUMINUM_INGOT(
    "Pure Aluminum Ingot",
    time = 2,
    inputs = listOf(2 of Item.ALUMINUM_SCRAP),
    product = 1 of Item.ALUMINUM_INGOT,
    alternate = true,
  ),

  /* Alclad Aluminum Sheet */

  ALCLAD_ALUMINUM_SHEET(
    "Alclad Aluminum Sheet",
    time = 6,
    inputs = listOf(
      3 of Item.ALUMINUM_INGOT,
      1 of Item.COPPER_INGOT,
    ),
    product = 3 of Item.ALCLAD_ALUMINUM_SHEET,
  ),

  /* Aluminum Casing */

  ALUMINUM_CASING(
    "Aluminum Casing",
    time = 2,
    inputs = listOf(3 of Item.ALUMINUM_INGOT),
    product = 2 of Item.ALUMINUM_CASING,
  ),
  ALCLAD_CASING(
    "Alclad Casing",
    time = 8,
    inputs = listOf(
      20 of Item.ALUMINUM_INGOT,
      10 of Item.COPPER_SHEET,
    ),
    product = 15 of Item.ALUMINUM_CASING,
    alternate = true,
  ),

  /* Hoverpack */

  HOVERPACK(
    "Hoverpack",
    time = 120,
    inputs = listOf(
      8 of Item.MOTOR,
      4 of Item.HEAVY_MODULAR_FRAME,
      8 of Item.COMPUTER,
      40 of Item.ALCLAD_ALUMINUM_SHEET,
    ),
    product = 1 of Item.HOVERPACK,
  ),

  /* Hazmat Suit */

  HAZMAT_SUIT(
    "Hazmat Suit",
    time = 120,
    inputs = listOf(
      50 of Item.RUBBER,
      50 of Item.PLASTIC,
      50 of Item.ALCLAD_ALUMINUM_SHEET,
      50 of Item.FABRIC,
    ),
    product = 1 of Item.HAZMAT_SUIT,
  ),

  /* Iodine Infused Filter */

  IODINE_INFUSED_FILTER(
    "Iodine Infused Filter",
    time = 16,
    inputs = listOf(
      1 of Item.GAS_FILTER,
      8 of Item.QUICKWIRE,
      1 of Item.ALUMINUM_CASING,
    ),
    product = 1 of Item.IODINE_INFUSED_FILTER,
  ),

  /* Sulfur */

  SULFUR_IRON(
    "Sulfur (Iron)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      30 of Item.IRON_ORE,
    ),
    product = 12 of Item.SULFUR,
    power = 100..400,
  ),
  SULFUR_COAL(
    "Sulfur (Coal)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      20 of Item.COAL,
    ),
    product = 12 of Item.SULFUR,
    power = 100..400,
  ),

  /* Sulfuric Acid */

  SULFURIC_ACID(
    "Sulfuric Acid",
    time = 6,
    inputs = listOf(
      5 of Item.SULFUR,
      5 of Item.WATER,
    ),
    product = 5 of Item.SULFURIC_ACID,
  ),

  /* Packaged Sulfuric Acid */

  PACKAGED_SULFURIC_ACID(
    "Packaged Sulfuric Acid",
    time = 3,
    inputs = listOf(
      2 of Item.SULFURIC_ACID,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_SULFURIC_ACID,
  ),
  UNPACKAGE_SULFURIC_ACID(
    "Unpackage Sulfuric Acid",
    time = 1,
    inputs = listOf(1 of Item.PACKAGED_SULFURIC_ACID),
    product = 1 of Item.SULFURIC_ACID,
    byproduct = 1 of Item.EMPTY_CANISTER,
  ),

  /* Battery */

  BATTERY(
    "Battery",
    time = 3,
    inputs = listOf(
      (5.q / 2.q) of Item.SULFURIC_ACID,
      2 of Item.ALUMINA_SOLUTION,
      1 of Item.ALUMINUM_CASING,
    ),
    product = 1 of Item.BATTERY,
    byproduct = (3.q / 2.q) of Item.WATER,
  ),
  CLASSIC_BATTERY(
    "Classic Battery",
    time = 8,
    inputs = listOf(
      6 of Item.SULFUR,
      7 of Item.ALCLAD_ALUMINUM_SHEET,
      8 of Item.PLASTIC,
      12 of Item.WIRE,
    ),
    product = 4 of Item.BATTERY,
    alternate = true,
  ),

  /* Radio Control Unit */

  RADIO_CONTROL_UNIT(
    "Radio Control Unit",
    time = 48,
    inputs = listOf(
      32 of Item.ALUMINUM_CASING,
      1 of Item.CRYSTAL_OSCILLATOR,
      2 of Item.COMPUTER,
    ),
    product = 2 of Item.RADIO_CONTROL_UNIT,
  ),
  RADIO_CONTROL_SYSTEM(
    "Radio Control System",
    time = 40,
    inputs = listOf(
      1 of Item.CRYSTAL_OSCILLATOR,
      10 of Item.CIRCUIT_BOARD,
      60 of Item.ALUMINUM_CASING,
      30 of Item.RUBBER,
    ),
    product = 3 of Item.RADIO_CONTROL_UNIT,
    alternate = true,
  ),
  RADIO_CONNECTION_UNIT(
    "Radio Connection Unit",
    time = 16,
    inputs = listOf(
      4 of Item.HEAT_SINK,
      2 of Item.HIGH_SPEED_CONNECTOR,
      12 of Item.QUARTZ_CRYSTAL,
    ),
    product = 1 of Item.RADIO_CONTROL_UNIT,
    alternate = true,
  ),

  /* Supercomputer */

  SUPERCOMPUTER(
    "Supercomputer",
    time = 32,
    inputs = listOf(
      4 of Item.COMPUTER,
      2 of Item.AI_LIMITER,
      3 of Item.HIGH_SPEED_CONNECTOR,
      28 of Item.PLASTIC,
    ),
    product = 1 of Item.SUPERCOMPUTER,
  ),
  SUPER_STATE_COMPUTER(
    "Super-State Computer",
    time = 25,
    inputs = listOf(
      3 of Item.COMPUTER,
      1 of Item.ELECTROMAGNETIC_CONTROL_ROD,
      10 of Item.BATTERY,
      25 of Item.WIRE,
    ),
    product = 1 of Item.SUPERCOMPUTER,
    alternate = true,
  ),
  OC_SUPERCOMPUTER(
    "OC Supercomputer",
    time = 20,
    inputs = listOf(
      2 of Item.RADIO_CONTROL_UNIT,
      2 of Item.COOLING_SYSTEM,
    ),
    product = 1 of Item.SUPERCOMPUTER,
    alternate = true,
  ),

  /* Assembly Director System */

  ASSEMBLY_DIRECTOR_SYSTEM(
    "Assembly Director System",
    time = 80,
    inputs = listOf(
      2 of Item.ADAPTIVE_CONTROL_UNIT,
      1 of Item.SUPERCOMPUTER,
    ),
    product = 1 of Item.ASSEMBLY_DIRECTOR_SYSTEM,
  ),

  /* Black Powder */

  BLACK_POWDER(
    "Black Powder",
    time = 4,
    inputs = listOf(
      1 of Item.COAL,
      1 of Item.SULFUR,
    ),
    product = 2 of Item.BLACK_POWDER,
  ),
  FINE_BLACK_POWDER(
    "Fine Black Powder",
    time = 8,
    inputs = listOf(
      1 of Item.SULFUR,
      2 of Item.COMPACTED_COAL,
    ),
    product = 6 of Item.BLACK_POWDER,
    alternate = true,
  ),

  /* Compacted Coal */

  COMPACTED_COAL(
    "Compacted Coal",
    time = 12,
    inputs = listOf(
      5 of Item.COAL,
      5 of Item.SULFUR,
    ),
    product = 5 of Item.COMPACTED_COAL,
  ),

  /* Turbofuel */

  TURBOFUEL(
    "Turbofuel",
    time = 16,
    inputs = listOf(
      6 of Item.FUEL,
      4 of Item.COMPACTED_COAL,
    ),
    product = 5 of Item.TURBOFUEL,
  ),
  TURBO_HEAVY_FUEL(
    "Turbo Heavy Fuel",
    time = 8,
    inputs = listOf(
      5 of Item.HEAVY_OIL_RESIDUE,
      4 of Item.COMPACTED_COAL,
    ),
    product = 4 of Item.TURBOFUEL,
    alternate = true,
  ),
  TURBO_BLEND_FUEL(
    "Turbo Blend Fuel",
    time = 8,
    inputs = listOf(
      2 of Item.FUEL,
      4 of Item.HEAVY_OIL_RESIDUE,
      3 of Item.SULFUR,
      3 of Item.PETROLEUM_COKE,
    ),
    product = 6 of Item.TURBOFUEL,
    alternate = true,
  ),

  /* Packaged Turbofuel */

  PACKAGED_TURBOFUEL(
    "Packaged Turbofuel",
    time = 6,
    inputs = listOf(
      2 of Item.TURBOFUEL,
      2 of Item.EMPTY_CANISTER,
    ),
    product = 2 of Item.PACKAGED_TURBOFUEL,
  ),
  UNPACKAGE_TURBOFUEL(
    "Unpackage Turbofuel",
    time = 6,
    inputs = listOf(2 of Item.PACKAGED_TURBOFUEL),
    product = 2 of Item.TURBOFUEL,
    byproduct = 2 of Item.EMPTY_CANISTER,
  ),

  /* Rocket Fuel */

  ROCKET_FUEL(
    "Rocket Fuel",
    time = 6,
    inputs = listOf(
      6 of Item.TURBOFUEL,
      1 of Item.NITRIC_ACID,
    ),
    product = 10 of Item.ROCKET_FUEL,
    byproduct = 1 of Item.COMPACTED_COAL,
  ),
  NITRO_ROCKET_FUEL(
    "Nitro Rocket Fuel",
    time = 12.q / 5.q,
    inputs = listOf(
      4 of Item.FUEL,
      3 of Item.NITROGEN_GAS,
      4 of Item.SULFUR,
      2 of Item.COAL,
    ),
    product = 6 of Item.ROCKET_FUEL,
    byproduct = 1 of Item.COMPACTED_COAL,
    alternate = true,
  ),

  /* Packaged Rocket Fuel */

  PACKAGED_ROCKET_FUEL(
    "Packaged Rocket Fuel",
    time = 1,
    inputs = listOf(
      2 of Item.ROCKET_FUEL,
      1 of Item.EMPTY_FLUID_TANK,
    ),
    product = 1 of Item.PACKAGED_ROCKET_FUEL,
  ),
  UNPACKAGE_ROCKET_FUEL(
    "Unpackage Rocket Fuel",
    time = 1,
    inputs = listOf(1 of Item.PACKAGED_ROCKET_FUEL),
    product = 2 of Item.ROCKET_FUEL,
    byproduct = 1 of Item.EMPTY_FLUID_TANK,
  ),

  /* Smokeless Powder */

  SMOKELESS_POWDER(
    "Smokeless Powder",
    time = 6,
    inputs = listOf(
      2 of Item.BLACK_POWDER,
      1 of Item.HEAVY_OIL_RESIDUE,
    ),
    product = 2 of Item.SMOKELESS_POWDER,
  ),

  /* Explosive Rebar */

  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    time = 12,
    inputs = listOf(
      2 of Item.IRON_REBAR,
      2 of Item.SMOKELESS_POWDER,
      2 of Item.STEEL_PIPE,
    ),
    product = 1 of Item.EXPLOSIVE_REBAR,
  ),

  /* Nobelisk Detonator */

  NOBELISK_DETONATOR(
    "Nobelisk Detonator",
    time = 80,
    inputs = listOf(
      1 of Item.OBJECT_SCANNER,
      10 of Item.STEEL_BEAM,
      50 of Item.CABLE,
    ),
    product = 1 of Item.NOBELISK_DETONATOR,
  ),

  /* Nobelisk */

  NOBELISK(
    "Nobelisk",
    time = 6,
    inputs = listOf(
      2 of Item.BLACK_POWDER,
      2 of Item.STEEL_PIPE,
    ),
    product = 1 of Item.NOBELISK,
  ),

  /* Gas Nobelisk */

  GAS_NOBELISK(
    "Gas Nobelisk",
    time = 12,
    inputs = listOf(
      1 of Item.NOBELISK,
      10 of Item.BIOMASS,
    ),
    product = 1 of Item.GAS_NOBELISK,
  ),

  /* Pulse Nobelisk */

  PULSE_NOBELISK(
    "Pulse Nobelisk",
    time = 60,
    inputs = listOf(
      5 of Item.NOBELISK,
      1 of Item.CRYSTAL_OSCILLATOR,
    ),
    product = 5 of Item.PULSE_NOBELISK,
  ),

  /* Cluster Nobelisk */

  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    time = 24,
    inputs = listOf(
      3 of Item.NOBELISK,
      4 of Item.SMOKELESS_POWDER,
    ),
    product = 1 of Item.CLUSTER_NOBELISK,
  ),

  /* Rifle */

  RIFLE(
    "Rifle",
    time = 120,
    inputs = listOf(
      2 of Item.MOTOR,
      10 of Item.RUBBER,
      25 of Item.STEEL_PIPE,
      250 of Item.SCREWS,
    ),
    product = 1 of Item.RIFLE,
  ),

  /* Rifle Ammo */

  RIFLE_AMMO(
    "Rifle Ammo",
    time = 12,
    inputs = listOf(
      3 of Item.COPPER_SHEET,
      2 of Item.SMOKELESS_POWDER,
    ),
    product = 15 of Item.RIFLE_AMMO,
  ),

  /* Homing Rifle Ammo */

  HOMING_RIFLE_AMMO(
    "Homing Rifle Ammo",
    time = 24,
    inputs = listOf(
      20 of Item.RIFLE_AMMO,
      1 of Item.HIGH_SPEED_CONNECTOR,
    ),
    product = 10 of Item.HOMING_RIFLE_AMMO,
  ),

  /* Turbo Rifle Ammo */

  TURBO_RIFLE_AMMO_BLENDER(
    "Turbo Rifle Ammo",
    time = 12,
    inputs = listOf(
      25 of Item.RIFLE_AMMO,
      3 of Item.ALUMINUM_CASING,
      3 of Item.TURBOFUEL,
    ),
    product = 50 of Item.TURBO_RIFLE_AMMO,
  ),
  TURBO_RIFLE_AMMO_MANUFACTURER(
    "Turbo Rifle Ammo",
    time = 12,
    inputs = listOf(
      25 of Item.RIFLE_AMMO,
      3 of Item.ALUMINUM_CASING,
      3 of Item.PACKAGED_TURBOFUEL,
    ),
    product = 50 of Item.TURBO_RIFLE_AMMO,
  ),

  /* Uranium */

  URANIUM_ORE_BAUXITE(
    "Uranium Ore (Bauxite)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      48 of Item.BAUXITE,
    ),
    product = 12 of Item.URANIUM,
    power = 100..400,
  ),

  /* Encased Uranium Cell */

  ENCASED_URANIUM_CELL(
    "Encased Uranium Cell",
    time = 12,
    inputs = listOf(
      10 of Item.URANIUM,
      3 of Item.CONCRETE,
      8 of Item.SULFURIC_ACID,
    ),
    product = 5 of Item.ENCASED_URANIUM_CELL,
    byproduct = 2 of Item.SULFURIC_ACID,
  ),
  INFUSED_URANIUM_CELL(
    "Infused Uranium Cell",
    time = 12,
    inputs = listOf(
      5 of Item.URANIUM,
      3 of Item.SILICA,
      5 of Item.SULFUR,
      15 of Item.QUICKWIRE,
    ),
    product = 4 of Item.ENCASED_URANIUM_CELL,
    alternate = true,
  ),

  /* Electromagnetic Control Rod */

  ELECTROMAGNETIC_CONTROL_ROD(
    "Electromagnetic Control Rod",
    time = 30,
    inputs = listOf(
      3 of Item.STATOR,
      2 of Item.AI_LIMITER,
    ),
    product = 2 of Item.ELECTROMAGNETIC_CONTROL_ROD,
  ),
  ELECTROMAGNETIC_CONNECTION_ROD(
    "Electromagnetic Connection Rod",
    time = 15,
    inputs = listOf(
      2 of Item.STATOR,
      1 of Item.HIGH_SPEED_CONNECTOR,
    ),
    product = 2 of Item.ELECTROMAGNETIC_CONTROL_ROD,
    alternate = true,
  ),

  /* Uranium Fuel Rod */

  URANIUM_FUEL_ROD(
    "Uranium Fuel Rod",
    time = 150,
    inputs = listOf(
      50 of Item.ENCASED_URANIUM_CELL,
      3 of Item.ENCASED_INDUSTRIAL_BEAM,
      5 of Item.ELECTROMAGNETIC_CONTROL_ROD,
    ),
    product = 1 of Item.URANIUM_FUEL_ROD,
  ),
  URANIUM_FUEL_UNIT(
    "Uranium Fuel Unit",
    time = 300,
    inputs = listOf(
      100 of Item.ENCASED_URANIUM_CELL,
      10 of Item.ELECTROMAGNETIC_CONTROL_ROD,
      3 of Item.CRYSTAL_OSCILLATOR,
      10 of Item.ROTOR,
    ),
    product = 3 of Item.URANIUM_FUEL_ROD,
    alternate = true,
  ),

  /* Uranium Waste */

  BURN_URANIUM_FUEL_ROD(
    "Uranium Fuel Rod",
    time = 300,
    inputs = listOf(
      1 of Item.URANIUM_FUEL_ROD,
      1_200 of Item.WATER,
    ),
    product = 50 of Item.URANIUM_WASTE,
  ),

  /* Magnetic Field Generator */

  MAGNETIC_FIELD_GENERATOR(
    "Magnetic Field Generator",
    time = 120,
    inputs = listOf(
      5 of Item.VERSATILE_FRAMEWORK,
      2 of Item.ELECTROMAGNETIC_CONTROL_ROD,
    ),
    product = 2 of Item.MAGNETIC_FIELD_GENERATOR,
  ),

  /* Nitrogen Gas */

  NITROGEN_GAS_CATERIUM(
    "Nitrogen Gas (Caterium)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      12 of Item.CATERIUM_ORE,
    ),
    product = 12 of Item.NITROGEN_GAS,
    power = 100..400,
  ),
  NITROGEN_GAS_BAUXITE(
    "Nitrogen Gas (Bauxite)",
    time = 6,
    inputs = listOf(
      1 of Item.REANIMATED_SAM,
      10 of Item.BAUXITE,
    ),
    product = 12 of Item.NITROGEN_GAS,
  ),

  /* Empty Fluid Tank */

  EMPTY_FLUID_TANK(
    "Empty Fluid Tank",
    time = 1,
    inputs = listOf(1 of Item.ALUMINUM_INGOT),
    product = 1 of Item.EMPTY_FLUID_TANK,
  ),

  /* Packaged Nitrogen Gas */

  PACKAGED_NITROGEN_GAS(
    "Packaged Nitrogen Gas",
    time = 1,
    inputs = listOf(
      4 of Item.NITROGEN_GAS,
      1 of Item.EMPTY_FLUID_TANK,
    ),
    product = 1 of Item.PACKAGED_NITROGEN_GAS,
  ),
  UNPACKAGE_NITROGEN_GAS(
    "Unpackage Nitrogen Gas",
    time = 1,
    inputs = listOf(1 of Item.PACKAGED_NITROGEN_GAS),
    product = 4 of Item.NITROGEN_GAS,
    byproduct = 1 of Item.EMPTY_FLUID_TANK,
  ),

  /* Heat Sink */

  HEAT_SINK(
    "Heat Sink",
    time = 8,
    inputs = listOf(
      5 of Item.ALCLAD_ALUMINUM_SHEET,
      3 of Item.COPPER_SHEET,
    ),
    product = 1 of Item.HEAT_SINK,
  ),
  HEAT_EXCHANGER(
    "Heat Exchanger",
    time = 6,
    inputs = listOf(
      3 of Item.ALUMINUM_CASING,
      3 of Item.RUBBER,
    ),
    product = 1 of Item.HEAT_SINK,
    alternate = true,
  ),

  /* Cooling System */

  COOLING_SYSTEM(
    "Cooling System",
    time = 24,
    inputs = listOf(
      2 of Item.HEAT_SINK,
      2 of Item.RUBBER,
      5 of Item.WATER,
      25 of Item.NITROGEN_GAS,
    ),
    product = 2 of Item.COOLING_SYSTEM,
  ),
  COOLING_DEVICE(
    "Cooling Device",
    time = 24,
    inputs = listOf(
      4 of Item.HEAT_SINK,
      1 of Item.MOTOR,
      24 of Item.NITROGEN_GAS,
    ),
    product = 2 of Item.COOLING_SYSTEM,
    alternate = true,
  ),

  /* Fused Modular Frame */

  FUSED_MODULAR_FRAME(
    "Fused Modular Frame",
    time = 40,
    inputs = listOf(
      1 of Item.HEAVY_MODULAR_FRAME,
      50 of Item.ALUMINUM_CASING,
      25 of Item.NITROGEN_GAS,
    ),
    product = 1 of Item.FUSED_MODULAR_FRAME,
  ),
  HEAT_FUSED_FRAME(
    "Heat-Fused Frame",
    time = 20,
    inputs = listOf(
      1 of Item.HEAVY_MODULAR_FRAME,
      50 of Item.ALUMINUM_INGOT,
      8 of Item.NITRIC_ACID,
      10 of Item.FUEL,
    ),
    product = 1 of Item.FUSED_MODULAR_FRAME,
    alternate = true,
  ),

  /* Turbo Motor */

  TURBO_MOTOR(
    "Turbo Motor",
    time = 32,
    inputs = listOf(
      4 of Item.COOLING_SYSTEM,
      2 of Item.RADIO_CONTROL_UNIT,
      4 of Item.MOTOR,
      24 of Item.RUBBER,
    ),
    product = 1 of Item.TURBO_MOTOR,
  ),
  TURBO_ELECTRIC_MOTOR(
    "Turbo Electric Motor",
    time = 64,
    inputs = listOf(
      7 of Item.MOTOR,
      9 of Item.RADIO_CONTROL_UNIT,
      5 of Item.ELECTROMAGNETIC_CONTROL_ROD,
      7 of Item.ROTOR,
    ),
    product = 3 of Item.TURBO_MOTOR,
    alternate = true,
  ),
  TURBO_PRESSURE_MOTOR(
    "Turbo Pressure Motor",
    time = 32,
    inputs = listOf(
      4 of Item.MOTOR,
      1 of Item.PRESSURE_CONVERSION_CUBE,
      24 of Item.PACKAGED_NITROGEN_GAS,
      8 of Item.STATOR,
    ),
    product = 2 of Item.TURBO_MOTOR,
    alternate = true,
  ),

  /* Thermal Propulsion Rocket */

  THERMAL_PROPULSION_ROCKET(
    "Thermal Propulsion Rocket",
    time = 120,
    inputs = listOf(
      5 of Item.MODULAR_ENGINE,
      2 of Item.TURBO_MOTOR,
      6 of Item.COOLING_SYSTEM,
      2 of Item.FUSED_MODULAR_FRAME,
    ),
    product = 2 of Item.THERMAL_PROPULSION_ROCKET,
  ),

  /* Nitric Acid */

  NITRIC_ACID(
    "Nitric Acid",
    time = 6,
    inputs = listOf(
      12 of Item.NITROGEN_GAS,
      3 of Item.WATER,
      1 of Item.IRON_PLATE,
    ),
    product = 3 of Item.NITRIC_ACID,
  ),

  /* Packaged Nitric Acid */

  PACKAGED_NITRIC_ACID(
    "Packaged Nitric Acid",
    time = 2,
    inputs = listOf(
      1 of Item.NITRIC_ACID,
      1 of Item.EMPTY_FLUID_TANK,
    ),
    product = 1 of Item.PACKAGED_NITRIC_ACID,
  ),
  UNPACKAGE_NITRIC_ACID(
    "Unpackage Nitric Acid",
    time = 3,
    inputs = listOf(1 of Item.PACKAGED_NITRIC_ACID),
    product = 1 of Item.NITRIC_ACID,
    byproduct = 1 of Item.EMPTY_FLUID_TANK,
  ),

  /* Non-Fissile Uranium */

  NON_FISSILE_URANIUM(
    "Non-Fissile Uranium",
    time = 24,
    inputs = listOf(
      15 of Item.URANIUM_WASTE,
      10 of Item.SILICA,
      6 of Item.NITRIC_ACID,
      6 of Item.SULFURIC_ACID,
    ),
    product = 20 of Item.NON_FISSILE_URANIUM,
    byproduct = 6 of Item.WATER,
  ),
  FERTILE_URANIUM(
    "Fertile Uranium",
    time = 12,
    inputs = listOf(
      5 of Item.URANIUM,
      5 of Item.URANIUM_WASTE,
      3 of Item.NITRIC_ACID,
      5 of Item.SULFURIC_ACID,
    ),
    product = 20 of Item.NON_FISSILE_URANIUM,
    byproduct = 8 of Item.WATER,
    alternate = true,
  ),

  /* Plutonium Pellet */

  PLUTONIUM_PELLET(
    "Plutonium Pellet",
    time = 60,
    inputs = listOf(
      100 of Item.NON_FISSILE_URANIUM,
      25 of Item.URANIUM_WASTE,
    ),
    product = 30 of Item.PLUTONIUM_PELLET,
    power = 250..750,
  ),

  /* Encased Plutonium Cell */

  ENCASED_PLUTONIUM_CELL(
    "Encased Plutonium Cell",
    time = 12,
    inputs = listOf(
      2 of Item.PLUTONIUM_PELLET,
      4 of Item.CONCRETE,
    ),
    product = 1 of Item.ENCASED_PLUTONIUM_CELL,
  ),
  INSTANT_PLUTONIUM_CELL(
    "Instant Plutonium Cell",
    time = 120,
    inputs = listOf(
      150 of Item.NON_FISSILE_URANIUM,
      20 of Item.ALUMINUM_CASING,
    ),
    product = 20 of Item.ENCASED_PLUTONIUM_CELL,
    power = 250..750,
    alternate = true,
  ),

  /* Plutonium Fuel Rod */

  PLUTONIUM_FUEL_ROD(
    "Plutonium Fuel Rod",
    time = 240,
    inputs = listOf(
      30 of Item.ENCASED_PLUTONIUM_CELL,
      18 of Item.STEEL_BEAM,
      6 of Item.ELECTROMAGNETIC_CONTROL_ROD,
      10 of Item.HEAT_SINK,
    ),
    product = 1 of Item.PLUTONIUM_FUEL_ROD,
  ),
  PLUTONIUM_FUEL_UNIT(
    "Plutonium Fuel Unit",
    time = 120,
    inputs = listOf(
      20 of Item.ENCASED_PLUTONIUM_CELL,
      1 of Item.PRESSURE_CONVERSION_CUBE,
    ),
    product = 1 of Item.PLUTONIUM_FUEL_ROD,
    alternate = true,
  ),

  /* Plutonium Waste */

  BURN_PLUTONIUM_FUEL_ROD(
    "Plutonium Fuel Rod",
    time = 600,
    inputs = listOf(
      1 of Item.PLUTONIUM_FUEL_ROD,
      2_400 of Item.WATER,
    ),
    product = 10 of Item.PLUTONIUM_WASTE,
  ),

  /* Copper Powder */

  COPPER_POWDER(
    "Copper Powder",
    time = 6,
    inputs = listOf(30 of Item.COPPER_INGOT),
    product = 5 of Item.COPPER_POWDER,
  ),

  /* Pressure Conversion Cube */

  PRESSURE_CONVERSION_CUBE(
    "Pressure Conversion Cube",
    time = 60,
    inputs = listOf(
      1 of Item.FUSED_MODULAR_FRAME,
      2 of Item.RADIO_CONTROL_UNIT,
    ),
    product = 1 of Item.PRESSURE_CONVERSION_CUBE,
  ),

  /* Nuclear Pasta */

  NUCLEAR_PASTA(
    "Nuclear Pasta",
    time = 120,
    inputs = listOf(
      200 of Item.COPPER_POWDER,
      1 of Item.PRESSURE_CONVERSION_CUBE,
    ),
    product = 1 of Item.NUCLEAR_PASTA,
    power = 500..1_500,
  ),

  /* Nuke Nobelisk */

  NUKE_NOBELISK(
    "Nuke Nobelisk",
    time = 120,
    inputs = listOf(
      5 of Item.NOBELISK,
      20 of Item.ENCASED_URANIUM_CELL,
      10 of Item.SMOKELESS_POWDER,
      6 of Item.AI_LIMITER,
    ),
    product = 1 of Item.NUKE_NOBELISK,
  ),

  /* Diamonds */

  DIAMONDS(
    "Diamonds",
    time = 2,
    inputs = listOf(20 of Item.COAL),
    product = 1 of Item.DIAMONDS,
    power = 250..750,
  ),
  CLOUDY_DIAMONDS(
    "Cloudy Diamonds",
    time = 3,
    inputs = listOf(
      12 of Item.COAL,
      24 of Item.LIMESTONE,
    ),
    product = 1 of Item.DIAMONDS,
    power = 250..750,
    alternate = true,
  ),
  OIL_BASED_DIAMONDS(
    "Oil-Based Diamonds",
    time = 3,
    inputs = listOf(10 of Item.CRUDE_OIL),
    product = 2 of Item.DIAMONDS,
    power = 250..750,
    alternate = true,
  ),
  PETROLEUM_DIAMONDS(
    "Petroleum Diamonds",
    time = 2,
    inputs = listOf(24 of Item.PETROLEUM_COKE),
    product = 1 of Item.DIAMONDS,
    power = 250..750,
    alternate = true,
  ),
  PINK_DIAMONDS(
    "Pink Diamonds",
    time = 4,
    inputs = listOf(
      8 of Item.COAL,
      3 of Item.QUARTZ_CRYSTAL,
    ),
    product = 1 of Item.DIAMONDS,
    power = 100..400,
    alternate = true,
  ),
  TURBO_DIAMONDS(
    "Turbo Diamonds",
    time = 3,
    inputs = listOf(
      30 of Item.COAL,
      2 of Item.PACKAGED_TURBOFUEL,
    ),
    product = 3 of Item.DIAMONDS,
    power = 250..750,
    alternate = true,
  ),

  /* Time Crystal */

  TIME_CRYSTAL(
    "Time Crystal",
    time = 10,
    inputs = listOf(2 of Item.DIAMONDS),
    product = 1 of Item.TIME_CRYSTAL,
  ),

  /* Reanimated SAM */

  REANIMATED_SAM(
    "Reanimated SAM",
    time = 2,
    inputs = listOf(4 of Item.SAM),
    product = 1 of Item.REANIMATED_SAM,
  ),

  /* Ficsite Ingot */

  FICSITE_INGOT_IRON(
    "Ficsite Ingot (Iron)",
    time = 6,
    inputs = listOf(
      4 of Item.REANIMATED_SAM,
      24 of Item.IRON_INGOT,
    ),
    product = 1 of Item.FICSITE_INGOT,
    power = 100..400,
  ),
  FICSITE_INGOT_CATERIUM(
    "Ficsite Ingot (Caterium)",
    time = 4,
    inputs = listOf(
      3 of Item.REANIMATED_SAM,
      4 of Item.CATERIUM_INGOT,
    ),
    product = 1 of Item.FICSITE_INGOT,
    power = 100..400,
  ),
  FICSITE_INGOT_ALUMINUM(
    "Ficsite Ingot (Aluminum)",
    time = 2,
    inputs = listOf(
      2 of Item.REANIMATED_SAM,
      4 of Item.ALUMINUM_INGOT,
    ),
    product = 1 of Item.FICSITE_INGOT,
    power = 100..400
  ),

  /* Ficsite Trigon */

  FICSITE_TRIGON(
    "Ficsite Trigon",
    time = 6,
    inputs = listOf(1 of Item.FICSITE_INGOT),
    product = 3 of Item.FICSITE_TRIGON,
  ),

  /* SAM Fluctuator */

  SAM_FLUCTUATOR(
    "SAM Fluctuator",
    time = 6,
    inputs = listOf(
      6 of Item.REANIMATED_SAM,
      5 of Item.WIRE,
      3 of Item.STEEL_PIPE,
    ),
    product = 1 of Item.SAM_FLUCTUATOR,
  ),

  /* Biochemical Sculptor */

  BIOCHEMICAL_SCULPTOR(
    "Biochemical Sculptor",
    time = 120,
    inputs = listOf(
      1 of Item.ASSEMBLY_DIRECTOR_SYSTEM,
      80 of Item.FICSITE_TRIGON,
      20 of Item.WATER,
    ),
    product = 4 of Item.BIOCHEMICAL_SCULPTOR,
  ),

  /* Excited Photonic Matter */

  EXCITED_PHOTONIC_MATTER(
    "Excited Photonic Matter",
    time = 3,
    inputs = listOf(),
    product = 10 of Item.EXCITED_PHOTONIC_MATTER,
    power = 100..400,
  ),

  /* Dark Matter Residue */

  DARK_MATTER_RESIDUE(
    "Dark Matter Residue",
    time = 6,
    inputs = listOf(5 of Item.REANIMATED_SAM),
    product = 10 of Item.DARK_MATTER_RESIDUE,
  ),

  /* Dark Matter Crystal */

  DARK_MATTER_CRYSTAL(
    "Dark Matter Crystal",
    time = 2,
    inputs = listOf(
      1 of Item.DIAMONDS,
      5 of Item.DARK_MATTER_RESIDUE,
    ),
    product = 1 of Item.DARK_MATTER_CRYSTAL,
    power = -500..1_500,
  ),
  DARK_MATTER_CRYSTALLIZATION(
    "Dark Matter Crystallization",
    time = 3,
    inputs = listOf(10 of Item.DARK_MATTER_RESIDUE),
    product = 1 of Item.DARK_MATTER_CRYSTAL,
    power = 500..1_500,
    alternate = true,
  ),
  DARK_MATTER_TRAP(
    "Dark Matter Trap",
    time = 2,
    inputs = listOf(
      1 of Item.TIME_CRYSTAL,
      5 of Item.DARK_MATTER_RESIDUE,
    ),
    product = 2 of Item.DARK_MATTER_CRYSTAL,
    power = 500..1_500,
    alternate = true,
  ),

  /* Superposition Oscillator */

  SUPERPOSITION_OSCILLATOR(
    "Superposition Oscillator",
    time = 12,
    inputs = listOf(
      6 of Item.DARK_MATTER_CRYSTAL,
      1 of Item.CRYSTAL_OSCILLATOR,
      9 of Item.ALCLAD_ALUMINUM_SHEET,
      25 of Item.EXCITED_PHOTONIC_MATTER,
    ),
    product = 1 of Item.SUPERPOSITION_OSCILLATOR,
    byproduct = 25 of Item.DARK_MATTER_RESIDUE,
    power = 0..2_000,
  ),

  /* Neural-Quantum Processor */

  NEURAL_QUANTUM_PROCESSOR(
    "Neural-Quantum Processor",
    time = 20,
    inputs = listOf(
      5 of Item.TIME_CRYSTAL,
      1 of Item.SUPERCOMPUTER,
      15 of Item.FICSITE_TRIGON,
      25 of Item.EXCITED_PHOTONIC_MATTER,
    ),
    product = 1 of Item.NEURAL_QUANTUM_PROCESSOR,
    byproduct = 25 of Item.DARK_MATTER_RESIDUE,
    power = 0..2_000,
  ),

  /* AI Expansion Server */

  AI_EXPANSION_SERVER(
    "AI Expansion Server",
    time = 15,
    inputs = listOf(
      1 of Item.MAGNETIC_FIELD_GENERATOR,
      1 of Item.NEURAL_QUANTUM_PROCESSOR,
      1 of Item.SUPERPOSITION_OSCILLATOR,
      25 of Item.EXCITED_PHOTONIC_MATTER,
    ),
    product = 1 of Item.AI_EXPANSION_SERVER,
    byproduct = 25 of Item.DARK_MATTER_RESIDUE,
    power = 0..2_000,
  ),

  /* Singularity Cell */

  SINGULARITY_CELL(
    "Singularity Cell",
    time = 60,
    inputs = listOf(
      1 of Item.NUCLEAR_PASTA,
      20 of Item.DARK_MATTER_CRYSTAL,
      100 of Item.IRON_PLATE,
      200 of Item.CONCRETE,
    ),
    product = 10 of Item.SINGULARITY_CELL,
  ),

  /* Ballistic Warp Drive */

  BALLISTIC_WARP_DRIVE(
    "Ballistic Warp Drive",
    time = 60,
    inputs = listOf(
      1 of Item.THERMAL_PROPULSION_ROCKET,
      5 of Item.SINGULARITY_CELL,
      2 of Item.SUPERPOSITION_OSCILLATOR,
      40 of Item.DARK_MATTER_CRYSTAL,
    ),
    product = 1 of Item.BALLISTIC_WARP_DRIVE,
  ),

  /* Ficsonium */

  FICSONIUM(
    "Ficsonium",
    time = 6,
    inputs = listOf(
      1 of Item.PLUTONIUM_WASTE,
      1 of Item.SINGULARITY_CELL,
      20 of Item.DARK_MATTER_CRYSTAL,
    ),
    product = 1 of Item.FICSONIUM,
    power = 500..1_500,
  ),

  /* Ficsonium Fuel Rod  */

  FICSONIUM_FUEL_ROD(
    "Ficsonium Fuel Rod",
    time = 24,
    inputs = listOf(
      2 of Item.FICSONIUM,
      2 of Item.ELECTROMAGNETIC_CONTROL_ROD,
      40 of Item.FICSITE_TRIGON,
      20 of Item.EXCITED_PHOTONIC_MATTER,
    ),
    product = 1 of Item.FICSONIUM_FUEL_ROD,
    byproduct = 20 of Item.DARK_MATTER_RESIDUE,
    power = 0..2_000,
  ),

  /* Alien Power Matrix */

  ALIEN_POWER_MATRIX(
    "Alien Power Matrix",
    time = 24,
    inputs = listOf(
      5 of Item.SAM_FLUCTUATOR,
      3 of Item.POWER_SHARD,
      3 of Item.SUPERPOSITION_OSCILLATOR,
      24 of Item.EXCITED_PHOTONIC_MATTER,
    ),
    product = 1 of Item.ALIEN_POWER_MATRIX,
    byproduct = 24 of Item.DARK_MATTER_RESIDUE,
    power = 0..2_000,
  ),

  /* Ionized Fuel */

  IONIZED_FUEL(
    "Ionized Fuel",
    time = 24,
    inputs = listOf(
      16 of Item.ROCKET_FUEL,
      1 of Item.POWER_SHARD,
    ),
    product = 16 of Item.IONIZED_FUEL,
    byproduct = 2 of Item.COMPACTED_COAL,
  ),
  DARK_ION_FUEL(
    "Dark-Ion Fuel",
    time = 3,
    inputs = listOf(
      12 of Item.PACKAGED_ROCKET_FUEL,
      4 of Item.DARK_MATTER_CRYSTAL,
    ),
    product = 10 of Item.IONIZED_FUEL,
    byproduct = 2 of Item.COMPACTED_COAL,
    alternate = true,
  ),

  /* Packaged Ionized Fuel */

  PACKAGED_IONIZED_FUEL(
    "Packaged Ionized Fuel",
    time = 3,
    inputs = listOf(
      4 of Item.IONIZED_FUEL,
      2 of Item.EMPTY_FLUID_TANK,
    ),
    product = 2 of Item.PACKAGED_IONIZED_FUEL,
  ),
  UNPACKAGE_IONIZED_FUEL(
    "Unpackage Ionized Fuel",
    time = 3,
    inputs = listOf(2 of Item.PACKAGED_IONIZED_FUEL),
    product = 4 of Item.IONIZED_FUEL,
    byproduct = 2 of Item.EMPTY_FLUID_TANK,
  );

  constructor(
    displayName: String,
    time: Int,
    inputs: List<Component>,
    product: Component,
    byproduct: Component? = null,
    power: ClosedRange<Int>? = null,
    alternate: Boolean = false
  ) : this(displayName, time.q, inputs, product, byproduct, power?.map(Int::toDouble), alternate)

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

  /** Enumerates the categories recipes fall into within their [Building]'s menus. */
  enum class Category(
    /** The human-readable text representation of the Category. */
    val displayName: String,
  ) {
    ADVANCED_REFINEMENT("Advanced Refinement"),
    ALIEN_REMAINS("Alien Remains"),
    AMMUNITION("Ammunition"),
    BIOMASS("Biomass"),
    COMMUNICATIONS("Communications"),
    COMPOUNDS("Compounds"),
    CONSUMABLES("Consumables"),
    CONTAINERS("Containers"),
    ELECTRONICS("Electronics"),
    FUEL("Fuel"),
    INDUSTRIAL_PARTS("Industrial Parts"),
    INGOTS("Ingots"),
    NUCLEAR("Nuclear"),
    OIL_PRODUCTS("Oil Products"),
    OTHER("Other"),
    PACKAGING("Packaging"),
    POWER_SHARDS("Power Shards"),
    QUANTUM_TECHNOLOGY("Quantum Technology"),
    RAW_RESOURCE_CONVERSION("Raw Resource Conversion"),
    SPACE_ELEVATOR("Space Elevator"),
    STANDARD_PARTS("Standard Parts"),
    TOOLS("Tools"),
    UNPACKAGING("Unpackaging"),
  }

  companion object {
    private fun <T : Comparable<T>, R : Comparable<R>> ClosedRange<T>.map(mapper: (T) -> R) =
      mapper(start)..mapper(endInclusive)
  }
}
