package app.game.data

import app.game.logic.Condition
import app.game.logic.Condition.Companion.any
import app.game.logic.Condition.MilestoneCondition
import app.game.logic.Condition.PhaseCondition
import app.game.logic.Condition.ResearchCondition
import util.math.Rational
import util.math.q

enum class Item(
  val displayName: String,
  val category: Category,
  val stack: Rational? = null,
  val sink: Rational? = null,
  val energy: Rational? = null,
  val radiation: Rational? = null,
  val unlock: Condition,
) {
  /* ****** */
  /* Tier 0 */
  /* ****** */

  /* Game Start */

  IRON_ORE(
    "Iron Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 1.q,
    unlock = PhaseCondition(Phase.GAME_START),
  ),
  IRON_INGOT(
    "Iron Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 2.q,
    unlock = PhaseCondition(Phase.GAME_START),
  ),
  IRON_PLATE(
    "Iron Plate",
    Category.PARTS,
    stack = 200.q,
    sink = 6.q,
    unlock = PhaseCondition(Phase.GAME_START),
  ),
  IRON_ROD(
    "Iron Rod",
    Category.PARTS,
    stack = 200.q,
    sink = 4.q,
    unlock = PhaseCondition(Phase.GAME_START),
  ),
  XENO_ZAPPER(
    "Xeno-Zapper",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_880.q,
    unlock = PhaseCondition(Phase.GAME_START),
  ),

  /* HUB Upgrade 1 */

  PORTABLE_MINER(
    "Portable Miner",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 56.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_1),
  ),

  /* HUB Upgrade 2 */

  COPPER_ORE(
    "Copper Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 3.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),
  COPPER_INGOT(
    "Copper Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 6.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),
  WIRE(
    "Wire",
    Category.PARTS,
    stack = 500.q,
    sink = 6.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),
  CABLE(
    "Cable",
    Category.PARTS,
    stack = 200.q,
    sink = 24.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_2),
  ),

  /* HUB Upgrade 3 */

  LIMESTONE(
    "Limestone",
    Category.RESOURCES,
    stack = 100.q,
    sink = 2.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),
  CONCRETE(
    "Concrete",
    Category.PARTS,
    stack = 500.q,
    sink = 12.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),
  SCREW(
    "Screw",
    Category.PARTS,
    stack = 500.q,
    sink = 2.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),
  REINFORCED_IRON_PLATE(
    "Reinforced Iron Plate",
    Category.PARTS,
    stack = 100.q,
    sink = 120.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_3),
  ),

  /* HUB Upgrade 6 */

  BIOMASS(
    "Biomass",
    Category.PARTS,
    stack = 200.q,
    sink = 12.q,
    energy = 180.q,
    unlock = MilestoneCondition(Milestone.HUB_UPGRADE_6),
  ),

  /* ****** */
  /* TIER 1 */
  /* ****** */

  /* Field Research */

  OBJECT_SCANNER(
    "Object scanner",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_400.q,
    unlock = MilestoneCondition(Milestone.FIELD_RESEARCH),
  ),
  BEACON(
    "Beacon",
    Category.EQUIPMENT,
    stack = 100.q,
    sink = 320.q,
    unlock = MilestoneCondition(Milestone.FIELD_RESEARCH),
  ),

  /* ****** */
  /* TIER 2 */
  /* ****** */

  /* Part Assembly */

  COPPER_SHEET(
    "Copper Sheet",
    Category.PARTS,
    stack = 200.q,
    sink = 24.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  ROTOR(
    "Rotor",
    Category.PARTS,
    stack = 100.q,
    sink = 140.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  MODULAR_FRAME(
    "Modular Frame",
    Category.PARTS,
    stack = 50.q,
    sink = 408.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  SMART_PLATING(
    "Smart Plating",
    Category.PARTS,
    stack = 50.q,
    sink = 520.q,
    unlock = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),

  /* Obstacle Clearing */

  CHAINSAW(
    "Chainsaw",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 2760.q,
    unlock = MilestoneCondition(Milestone.OBSTACLE_CLEARING),
  ),
  SOLID_BIOFUEL(
    "Solid Biofuel",
    Category.PARTS,
    stack = 200.q,
    sink = 48.q,
    energy = 450.q,
    unlock = MilestoneCondition(Milestone.OBSTACLE_CLEARING),
  ),

  /* Resource Sink Bonus Program */

  COLOR_CARTRIDGE(
    "Color Cartridge",
    Category.PARTS,
    stack = 200.q,
    sink = 10.q,
    energy = 900.q,
    unlock = MilestoneCondition(Milestone.RESOURCE_SINK_BONUS_PROGRAM),
  ),

  /* ****** */
  /* TIER 3 */
  /* ****** */

  /* Coal Power */

  COAL(
    "Coal",
    Category.RESOURCES,
    stack = 100.q,
    sink = 3.q,
    energy = 300.q,
    unlock = MilestoneCondition(Milestone.COAL_POWER),
  ),

  /* Basic Steel Production */

  STEEL_INGOT(
    "Steel Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 8.q,
    unlock = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),
  STEEL_BEAM(
    "Steel Beam",
    Category.PARTS,
    stack = 200.q,
    sink = 64.q,
    unlock = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),
  STEEL_PIPE(
    "Steel Pipe",
    Category.PARTS,
    stack = 200.q,
    sink = 24.q,
    unlock = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),

  /* Improved Melee Combat */

  XENO_BASHER(
    "Xeno-Basher",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 18_800.q,
    unlock = MilestoneCondition(Milestone.IMPROVED_MELEE_COMBAT),
  ),

  /* ****** */
  /* TIER 4 */
  /* ****** */

  /* Advanced Steel Production */

  ENCASED_INDUSTRIAL_BEAM(
    "Encased Industrial Beam",
    Category.PARTS,
    stack = 100.q,
    sink = 632.q,
    unlock = MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION),
  ),
  STATOR(
    "Stator",
    Category.PARTS,
    stack = 100.q,
    sink = 240.q,
    unlock = MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION),
  ),
  MOTOR(
    "Motor",
    Category.PARTS,
    stack = 50.q,
    sink = 1_520.q,
    unlock = MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION),
  ),
  AUTOMATED_WIRING(
    "Automated Wiring",
    Category.PARTS,
    stack = 50.q,
    sink = 1_440.q,
    unlock = MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION),
  ),
  HEAVY_MODULAR_FRAME(
    "Heavy Modular Frame",
    Category.PARTS,
    stack = 50.q,
    sink = 11_520.q,
    unlock = MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION),
  ),

  /* ****** */
  /* TIER 5 */
  /* ****** */

  /* Oil Processing */

  CRUDE_OIL(
    "Crude Oil",
    Category.RESOURCES,
    energy = 320.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  PLASTIC(
    "Plastic",
    Category.PARTS,
    stack = 200.q,
    sink = 75.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    Category.PARTS,
    energy = 400.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  RUBBER(
    "Rubber",
    Category.PARTS,
    stack = 200.q,
    sink = 60.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING)
  ),
  FUEL(
    "Fuel",
    Category.PARTS,
    energy = 750.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  POLYMER_RESIN(
    "Polymer Resin",
    Category.PARTS,
    stack = 200.q,
    sink = 12.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  PETROLEUM_COKE(
    "Petroleum Coke",
    Category.PARTS,
    stack = 200.q,
    sink = 20.q,
    energy = 180.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  CIRCUIT_BOARD(
    "Circuit Board",
    Category.PARTS,
    stack = 200.q,
    sink = 696.q,
    unlock = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),

  /* Industrial Manufacturing */

  COMPUTER(
    "Computer",
    Category.PARTS,
    stack = 50.q,
    sink = 17_260.q,
    unlock = MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING),
  ),
  MODULAR_ENGINE(
    "Modular Engine",
    Category.PARTS,
    stack = 50.q,
    sink = 9_960.q,
    unlock = MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING),
  ),
  ADAPTIVE_CONTROL_UNIT(
    "Adaptive Control Unit",
    Category.PARTS,
    stack = 50.q,
    sink = 86_120.q,
    unlock = MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING),
  ),

  /* Alternative Fluid Transport */

  EMPTY_CANISTER(
    "Empty Canister",
    Category.PARTS,
    stack = 100.q,
    sink = 60.q,
    unlock = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  PACKAGED_WATER(
    "Packaged Water",
    Category.PARTS,
    stack = 100.q,
    sink = 130.q,
    unlock = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  PACKAGED_OIL(
    "Packaged Oil",
    Category.PARTS,
    stack = 100.q,
    sink = 180.q,
    energy = 320.q,
    unlock = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  PACKAGED_FUEL(
    "Packaged Fuel",
    Category.PARTS,
    stack = 100.q,
    sink = 270.q,
    energy = 750.q,
    unlock = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  PACKAGED_HEAVY_OIL_RESIDUE(
    "Packaged Heavy Oil Residue",
    Category.PARTS,
    stack = 100.q,
    sink = 180.q,
    energy = 400.q,
    unlock = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  PACKAGED_LIQUID_BIOFUEL(
    "Packaged Liquid Biofuel",
    Category.PARTS,
    stack = 100.q,
    sink = 370.q,
    energy = 750.q,
    unlock = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  LIQUID_BIOFUEL(
    "Liquid Biofuel",
    Category.PARTS,
    energy = 750.q,
    unlock = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),

  /* Gas Mask */

  GAS_MASK(
    "Gas Mask",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 55_000.q,
    unlock = MilestoneCondition(Milestone.GAS_MASK),
  ),
  GAS_FILTER(
    "Gas Filter",
    Category.PARTS,
    stack = 50.q,
    sink = 830.q,
    unlock = MilestoneCondition(Milestone.GAS_MASK),
  ),

  /* ****** */
  /* TIER 6 */
  /* ****** */

  /* Expanded Power Infrastructure */

  CATERIUM_ORE(
    "Caterium Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 7.q,
    unlock = any {
      +Milestone.EXPANDED_POWER_INFRASTRUCTURE
      +Research.UNKNOWN_METAL
    }
  ),
  CATERIUM_INGOT(
    "Caterium Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 42.q,
    unlock = ResearchCondition(Research.CATERIUM_INGOT),
  ),
  QUICKWIRE(
    "Quickwire",
    Category.PARTS,
    stack = 500.q,
    sink = 17.q,
    unlock = ResearchCondition(Research.QUICKWIRE),
  ),
  ZIPLINE(
    "Zipline",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 5_284.q,
    unlock = ResearchCondition(Research.ZIPLINE),
  ),
  AI_LIMITER(
    "AI Limiter",
    Category.PARTS,
    stack = 100.q,
    sink = 920.q,
    unlock = ResearchCondition(Research.AI_LIMITER),
  ),
  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    Category.PARTS,
    stack = 100.q,
    sink = 3_776.q,
    unlock = ResearchCondition(Research.HIGH_SPEED_CONNECTOR),
  ),

  /* Jetpack */

  JETPACK(
    "Jetpack",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 49_580.q,
    unlock = MilestoneCondition(Milestone.JETPACK),
  ),

  /* ****** */
  /* TIER 7 */
  /* ****** */

  /* Bauxite Refinement */

  BAUXITE(
    "Bauxite",
    Category.RESOURCES,
    stack = 100.q,
    sink = 8.q,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  ALUMINA_SOLUTION(
    "Alumina Solution",
    Category.PARTS,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  PACKAGED_ALUMINA_SOLUTION(
    "Packaged Alumina Solution",
    Category.PARTS,
    stack = 100.q,
    sink = 160.q,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  ALUMINUM_SCRAP(
    "Aluminum Scrap",
    Category.PARTS,
    stack = 500.q,
    sink = 27.q,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  ALUMINUM_INGOT(
    "Aluminum Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 131.q,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  ALCLAD_ALUMINUM_SHEET(
    "Alclad Aluminum Sheet",
    Category.PARTS,
    stack = 200.q,
    sink = 266.q,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  ALUMINUM_CASING(
    "Aluminum Casing",
    Category.PARTS,
    stack = 200.q,
    sink = 393.q,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  RAW_QUARTZ(
    "Raw Quartz",
    Category.RESOURCES,
    stack = 100.q,
    sink = 15.q,
    unlock = any {
      +Milestone.BAUXITE_REFINEMENT
      +Research.UNKNOWN_CRYSTALLINE_MATERIAL
    },
  ),
  QUARTZ_CRYSTAL(
    "Quartz Crystal",
    Category.PARTS,
    stack = 200.q,
    sink = 50.q,
    unlock = ResearchCondition(Research.QUARTZ_CRYSTALS),
  ),
  SILICA(
    "Silica",
    Category.PARTS,
    stack = 200.q,
    sink = 20.q,
    unlock = ResearchCondition(Research.SILICA),
  ),
  CRYSTAL_OSCILATOR(
    "Crystal Oscilator",
    Category.PARTS,
    stack = 100.q,
    sink = 3_702.q,
    unlock = ResearchCondition(Research.CRYSTAL_OSCILLATOR),
  ),
  RADIO_CONTROL_UNIT(
    "Radio Control Unit",
    Category.PARTS,
    stack = 50.q,
    sink = 19_600.q,
    unlock = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),

  /* Hazmat Suit */

  HAZMAT_SUIT(
    "Hazmat Suit",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 54_100.q,
    unlock = MilestoneCondition(Milestone.HAZMAT_SUIT),
  ),
  IODINE_INFUSED_FILTER(
    "Iodine Infused Filter",
    Category.PARTS,
    stack = 50.q,
    sink = 2_718.q,
    unlock = MilestoneCondition(Milestone.HAZMAT_SUIT),
  ),

  /* Aeronautical Engineering */

  // TODO: Sulfur

  /* ****** */
  /* NATURE */
  /* ****** */

  BACON_AGARIC(
    "Bacon Agaric",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  ),
  BERYL_NUT(
    "Beryl Nut",
    Category.NATURE,
    stack = 100.q,
    unlock = Condition.TRUE,
  ),
  PALEBERRY(
    "Paleberry",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  ),

  FLOWER_PETALS(
    "Flower Petals",
    Category.NATURE,
    stack = 500.q,
    sink = 10.q,
    energy = 100.q,
    unlock = Condition.TRUE,
  ),
  LEAVES(
    "Leaves",
    Category.NATURE,
    stack = 500.q,
    sink = 3.q,
    energy = 15.q,
    unlock = Condition.TRUE,
  ),
  MYCELIA(
    "Mycelia",
    Category.NATURE,
    stack = 200.q,
    sink = 10.q,
    energy = 20.q,
    unlock = Condition.TRUE,
  ),
  WOOD(
    "Wood",
    Category.NATURE,
    stack = 200.q,
    sink = 30.q,
    energy = 100.q,
    unlock = Condition.TRUE,
  ),

  HATCHER_REMAINS(
    "Hatcher Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),
  HOG_REMAINS(
    "Hog Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),
  SPITTER_REMAINS(
    "Spitter Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),
  STINGER_REMAINS(
    "Stinger Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
    unlock = Condition.TRUE,
  ),

  BLUE_POWER_SLUG(
    "Blue Power Slug",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE
  ),
  YELLOW_POWER_SLUG(
    "Yellow Power Slug",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  ),
  PURPLE_POWER_SLUG(
    "Purple Power Slug",
    Category.NATURE,
    stack = 50.q,
    unlock = Condition.TRUE,
  );

  enum class Category(
    val displayName: String,
  ) {
    PARTS("Components"),
    EQUIPMENT("Equipment"),
    NATURE("Nature"),
    RESOURCES("Resources"),
  }
}
