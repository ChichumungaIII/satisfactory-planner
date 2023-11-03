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
  ),
  IRON_INGOT(
    "Iron Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 2.q,
  ),
  IRON_PLATE(
    "Iron Plate",
    Category.PARTS,
    stack = 200.q,
    sink = 6.q,
  ),
  IRON_ROD(
    "Iron Rod",
    Category.PARTS,
    stack = 200.q,
    sink = 4.q,
  ),
  XENO_ZAPPER(
    "Xeno-Zapper",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_880.q,
  ),

  /* HUB Upgrade 1 */

  PORTABLE_MINER(
    "Portable Miner",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 56.q,
  ),

  /* HUB Upgrade 2 */

  COPPER_ORE(
    "Copper Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 3.q,
  ),
  COPPER_INGOT(
    "Copper Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 6.q,
  ),
  WIRE(
    "Wire",
    Category.PARTS,
    stack = 500.q,
    sink = 6.q,
  ),
  CABLE(
    "Cable",
    Category.PARTS,
    stack = 200.q,
    sink = 24.q,
  ),

  /* HUB Upgrade 3 */

  LIMESTONE(
    "Limestone",
    Category.RESOURCES,
    stack = 100.q,
    sink = 2.q,
  ),
  CONCRETE(
    "Concrete",
    Category.PARTS,
    stack = 500.q,
    sink = 12.q,
  ),
  SCREW(
    "Screw",
    Category.PARTS,
    stack = 500.q,
    sink = 2.q,
  ),
  REINFORCED_IRON_PLATE(
    "Reinforced Iron Plate",
    Category.PARTS,
    stack = 100.q,
    sink = 120.q,
  ),

  /* HUB Upgrade 6 */

  BIOMASS(
    "Biomass",
    Category.PARTS,
    stack = 200.q,
    sink = 12.q,
    energy = 180.q,
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
  ),
  BEACON(
    "Beacon",
    Category.EQUIPMENT,
    stack = 100.q,
    sink = 320.q,
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
  ),
  ROTOR(
    "Rotor",
    Category.PARTS,
    stack = 100.q,
    sink = 140.q,
  ),
  MODULAR_FRAME(
    "Modular Frame",
    Category.PARTS,
    stack = 50.q,
    sink = 408.q,
  ),
  SMART_PLATING(
    "Smart Plating",
    Category.PARTS,
    stack = 50.q,
    sink = 520.q,
  ),

  /* Obstacle Clearing */

  CHAINSAW(
    "Chainsaw",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 2_760.q,
  ),
  SOLID_BIOFUEL(
    "Solid Biofuel",
    Category.PARTS,
    stack = 200.q,
    sink = 48.q,
    energy = 450.q,
  ),

  /* Resource Sink Bonus Program */

  COLOR_CARTRIDGE(
    "Color Cartridge",
    Category.PARTS,
    stack = 200.q,
    sink = 10.q,
    energy = 900.q,
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
  ),
  WATER(
    "Water",
    category = Category.RESOURCES,
  ),

  /* Basic Steel Production */

  STEEL_INGOT(
    "Steel Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 8.q,
  ),
  STEEL_BEAM(
    "Steel Beam",
    Category.PARTS,
    stack = 200.q,
    sink = 64.q,
  ),
  STEEL_PIPE(
    "Steel Pipe",
    Category.PARTS,
    stack = 200.q,
    sink = 24.q,
  ),
  VERSATILE_FRAMEWORK(
    "Versatile Framework",
    category = Category.PARTS,
    stack = 50.q,
    sink = 1_176.q,
  ),

  /* Improved Melee Combat */

  XENO_BASHER(
    "Xeno-Basher",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 18_800.q,
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
  ),
  STATOR(
    "Stator",
    Category.PARTS,
    stack = 100.q,
    sink = 240.q,
  ),
  MOTOR(
    "Motor",
    Category.PARTS,
    stack = 50.q,
    sink = 1_520.q,
  ),
  AUTOMATED_WIRING(
    "Automated Wiring",
    Category.PARTS,
    stack = 50.q,
    sink = 1_440.q,
  ),
  HEAVY_MODULAR_FRAME(
    "Heavy Modular Frame",
    Category.PARTS,
    stack = 50.q,
    sink = 11_520.q,
  ),

  /* ****** */
  /* TIER 5 */
  /* ****** */

  /* Oil Processing */

  CRUDE_OIL(
    "Crude Oil",
    Category.RESOURCES,
    energy = 320.q,
  ),
  PLASTIC(
    "Plastic",
    Category.PARTS,
    stack = 200.q,
    sink = 75.q,
  ),
  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    Category.PARTS,
    energy = 400.q,
  ),
  RUBBER(
    "Rubber",
    Category.PARTS,
    stack = 200.q,
    sink = 60.q
  ),
  FUEL(
    "Fuel",
    Category.PARTS,
    energy = 750.q,
  ),
  POLYMER_RESIN(
    "Polymer Resin",
    Category.PARTS,
    stack = 200.q,
    sink = 12.q,
  ),
  PETROLEUM_COKE(
    "Petroleum Coke",
    Category.PARTS,
    stack = 200.q,
    sink = 20.q,
    energy = 180.q,
  ),
  CIRCUIT_BOARD(
    "Circuit Board",
    Category.PARTS,
    stack = 200.q,
    sink = 696.q,
  ),

  /* Industrial Manufacturing */

  COMPUTER(
    "Computer",
    Category.PARTS,
    stack = 50.q,
    sink = 17_260.q,
  ),
  MODULAR_ENGINE(
    "Modular Engine",
    Category.PARTS,
    stack = 50.q,
    sink = 9_960.q,
  ),
  ADAPTIVE_CONTROL_UNIT(
    "Adaptive Control Unit",
    Category.PARTS,
    stack = 50.q,
    sink = 86_120.q,
  ),

  /* Alternative Fluid Transport */

  EMPTY_CANISTER(
    "Empty Canister",
    Category.PARTS,
    stack = 100.q,
    sink = 60.q,
  ),
  PACKAGED_WATER(
    "Packaged Water",
    Category.PARTS,
    stack = 100.q,
    sink = 130.q,
  ),
  PACKAGED_OIL(
    "Packaged Oil",
    Category.PARTS,
    stack = 100.q,
    sink = 180.q,
    energy = 320.q,
  ),
  PACKAGED_FUEL(
    "Packaged Fuel",
    Category.PARTS,
    stack = 100.q,
    sink = 270.q,
    energy = 750.q,
  ),
  PACKAGED_HEAVY_OIL_RESIDUE(
    "Packaged Heavy Oil Residue",
    Category.PARTS,
    stack = 100.q,
    sink = 180.q,
    energy = 400.q,
  ),
  PACKAGED_LIQUID_BIOFUEL(
    "Packaged Liquid Biofuel",
    Category.PARTS,
    stack = 100.q,
    sink = 370.q,
    energy = 750.q,
  ),
  LIQUID_BIOFUEL(
    "Liquid Biofuel",
    Category.PARTS,
    energy = 750.q,
  ),

  /* Gas Mask */

  GAS_MASK(
    "Gas Mask",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 55_000.q,
  ),
  GAS_FILTER(
    "Gas Filter",
    Category.PARTS,
    stack = 50.q,
    sink = 830.q,
  ),

  /* ****** */
  /* TIER 6 */
  /* ****** */

  /* Expanded Power Infrastructure */

  CATERIUM_ORE(
    "Caterium Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 7.q
  ),
  CATERIUM_INGOT(
    "Caterium Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 42.q,
  ),
  QUICKWIRE(
    "Quickwire",
    Category.PARTS,
    stack = 500.q,
    sink = 17.q,
  ),
  ZIPLINE(
    "Zipline",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 5_284.q,
  ),
  AI_LIMITER(
    "AI Limiter",
    Category.PARTS,
    stack = 100.q,
    sink = 920.q,
  ),
  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    Category.PARTS,
    stack = 100.q,
    sink = 3_776.q,
  ),

  /* Jetpack */

  JETPACK(
    "Jetpack",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 49_580.q,
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
  ),
  ALUMINA_SOLUTION(
    "Alumina Solution",
    Category.PARTS,
  ),
  PACKAGED_ALUMINA_SOLUTION(
    "Packaged Alumina Solution",
    Category.PARTS,
    stack = 100.q,
    sink = 160.q,
  ),
  ALUMINUM_SCRAP(
    "Aluminum Scrap",
    Category.PARTS,
    stack = 500.q,
    sink = 27.q,
  ),
  ALUMINUM_INGOT(
    "Aluminum Ingot",
    Category.PARTS,
    stack = 100.q,
    sink = 131.q,
  ),
  ALCLAD_ALUMINUM_SHEET(
    "Alclad Aluminum Sheet",
    Category.PARTS,
    stack = 200.q,
    sink = 266.q,
  ),
  ALUMINUM_CASING(
    "Aluminum Casing",
    Category.PARTS,
    stack = 200.q,
    sink = 393.q,
  ),
  RAW_QUARTZ(
    "Raw Quartz",
    Category.RESOURCES,
    stack = 100.q,
    sink = 15.q,
  ),
  QUARTZ_CRYSTAL(
    "Quartz Crystal",
    Category.PARTS,
    stack = 200.q,
    sink = 50.q,
  ),
  SILICA(
    "Silica",
    Category.PARTS,
    stack = 200.q,
    sink = 20.q,
  ),
  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    Category.PARTS,
    stack = 100.q,
    sink = 3_702.q,
  ),
  RADIO_CONTROL_UNIT(
    "Radio Control Unit",
    Category.PARTS,
    stack = 50.q,
    sink = 19_600.q,
  ),

  /* Hazmat Suit */

  HAZMAT_SUIT(
    "Hazmat Suit",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 54_100.q,
  ),
  IODINE_INFUSED_FILTER(
    "Iodine Infused Filter",
    Category.PARTS,
    stack = 50.q,
    sink = 2_718.q,
  ),

  /* Aeronautical Engineering */

  SULFUR(
    "Sulfur",
    Category.RESOURCES,
    stack = 100.q,
    sink = 11.q,
  ),
  BLACK_POWDER(
    "Black Powder",
    Category.PARTS,
    stack = 200.q,
    sink = 14.q,
  ),
  COMPACTED_COAL(
    "Compacted Coal",
    Category.PARTS,
    stack = 100.q,
    sink = 28.q,
    energy = 630.q,
  ),
  TURBOFUEL(
    "Turbofuel",
    Category.PARTS,
    energy = 2_000.q,
  ),
  PACKAGED_TURBOFUEL(
    "Packaged Turbofuel",
    Category.PARTS,
    stack = 100.q,
    sink = 570.q,
    energy = 2_000.q,
  ),
  NOBELISK_DETONATOR(
    "Nobelisk Detonator",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 6_480.q,
  ),
  NOBELISK(
    "Nobelisk",
    Category.PARTS,
    stack = 50.q,
    sink = 152.q,
  ),
  SMOKELESS_POWDER(
    "Smokeless Powder",
    Category.PARTS,
    stack = 100.q,
    sink = 58.q,
  ),
  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    Category.PARTS,
    stack = 50.q,
    sink = 1_376.q,
  ),
  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    Category.PARTS,
    stack = 100.q,
    sink = 360.q,
  ),
  RIFLE(
    "Rifle",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 9_480.q,
  ),
  RIFLE_AMMO(
    "Rifle Ammo",
    Category.PARTS,
    stack = 500.q,
    sink = 25.q,
  ),
  SULFURIC_ACID(
    "Sulfuric Acid",
    Category.PARTS,
  ),
  PACKAGED_SULFURIC_ACID(
    "Packaged Sulfuric Acid",
    Category.PARTS,
    stack = 100.q,
    sink = 152.q,
  ),
  BATTERY(
    "Battery",
    Category.PARTS,
    stack = 200.q,
    sink = 465.q,
    energy = 6_000.q,
  ),
  SUPERCOMPUTER(
    "Supercomputer",
    Category.PARTS,
    stack = 50.q,
    sink = 99_576.q,
  ),
  ASSEMBLY_DIRECTOR_SYSTEM(
    "Assembly Director System",
    Category.PARTS,
    stack = 50.q,
    sink = 543_632.q,
  ),

  /* Hover Pack */

  HOVER_PACK(
    "Hover Pack",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 413_920.q,
  ),

  /* ****** */
  /* TIER 8 */
  /* ****** */

  /* Nuclear Power */

  URANIUM(
    "Uranium",
    Category.RESOURCES,
    stack = 100.q,
    sink = 35.q,
    radiation = 15.q,
  ),
  ENCASED_URANIUM_CELL(
    "Encased Uranium Cell",
    Category.PARTS,
    stack = 200.q,
    sink = 147.q,
    radiation = 1.q / 2.q,
  ),
  ELECTROMAGNETIC_CONTROL_ROD(
    "Electromagnetic Control Rod",
    Category.PARTS,
    stack = 100.q,
    sink = 2_560.q,
  ),
  URANIUM_FUEL_ROD(
    "Uranium Fuel Rod",
    Category.PARTS,
    stack = 50.q,
    sink = 44_092.q,
    energy = 750_000.q,
    radiation = 50.q,
  ),
  MAGNETIC_FIELD_GENERATOR(
    "Magnetic Field Generator",
    Category.PARTS,
    stack = 50.q,
    sink = 15_650.q,
  ),

  /* Advanced Aluminum Production */

  EMPTY_FLUID_TANK(
    "Empty Fluid Tank",
    Category.PARTS,
    stack = 100.q,
    sink = 225.q,
  ),
  PACKAGED_NITROGEN_GAS(
    "Packaged Nitrogen Gas",
    Category.PARTS,
    stack = 100.q,
    sink = 312.q,
  ),
  HEAT_SINK(
    "Heat Sink",
    Category.PARTS,
    stack = 100.q,
    sink = 2_804.q,
  ),
  COOLING_SYSTEM(
    "Cooling System",
    Category.PARTS,
    stack = 100.q,
    sink = 12_006.q,
  ),
  FUSED_MODULAR_FRAME(
    "Fused Modular Frame",
    Category.PARTS,
    stack = 50.q,
    sink = 62_840.q,
  ),

  /* Leading-edge Production */

  TURBO_MOTOR(
    "Turbo Motor",
    Category.PARTS,
    stack = 50.q,
    sink = 242_720.q,
  ),
  THERMAL_PROPULSION_ROCKET(
    "Thermal Propulsion Rocket",
    Category.PARTS,
    stack = 50.q,
    sink = 732_956.q,
  ),

  /* ****** */
  /* NATURE */
  /* ****** */

  /* Milestones */

  WOOD(
    "Wood",
    Category.NATURE,
    stack = 200.q,
    sink = 30.q,
    energy = 100.q,
  ),
  LEAVES(
    "Leaves",
    Category.NATURE,
    stack = 500.q,
    sink = 3.q,
    energy = 15.q,
  ),
  FLOWER_PETALS(
    "Flower Petals",
    Category.NATURE,
    stack = 500.q,
    sink = 10.q,
    energy = 100.q,
  ),

  /* Alien Organisms */

  HOG_REMAINS(
    "Hog Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
  ),
  HATCHER_REMAINS(
    "Hatcher Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
  ),
  STINGER_REMAINS(
    "Stinger Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
  ),
  SPITTER_REMAINS(
    "Spitter Remains",
    Category.NATURE,
    stack = 50.q,
    energy = 250.q,
  ),

  ALIEN_PROTEIN(
    "Alien Protein",
    Category.PARTS,
    stack = 100.q,
  ),
  ALIEN_DNA_CAPSULE(
    "Alien DNA Capsule",
    Category.PARTS,
    stack = 50.q,
    sink = 1_000.q,
  ),

  REBAR_GUN(
    "Rebar Gun",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_968.q,
  ),
  IRON_REBAR(
    "Iron Rebar",
    Category.PARTS,
    stack = 100.q,
    sink = 8.q,
  ),

  /* Mycelia */

  MYCELIA(
    "Mycelia",
    Category.NATURE,
    stack = 200.q,
    sink = 10.q,
    energy = 20.q,
  ),

  /* Nutrients */

  BACON_AGARIC(
    "Bacon Agaric",
    Category.NATURE,
    stack = 50.q,
  ),
  BERYL_NUT(
    "Beryl Nut",
    Category.NATURE,
    stack = 100.q,
  ),
  PALEBERRY(
    "Paleberry",
    Category.NATURE,
    stack = 50.q,
  ),

  MEDICINAL_INHALER(
    "Medicinal Inhaler",
    Category.EQUIPMENT,
    stack = 50.q,
    sink = 125.q,
  ),

  /* Power Slugs */

  BLUE_POWER_SLUG(
    "Blue Power Slug",
    Category.NATURE,
    stack = 50.q
  ),
  YELLOW_POWER_SLUG(
    "Yellow Power Slug",
    Category.NATURE,
    stack = 50.q,
  ),
  PURPLE_POWER_SLUG(
    "Purple Power Slug",
    Category.NATURE,
    stack = 50.q,
  ),

  /* Hard Drives */
  HARD_DRIVE(
    "Hard Drive",
    Category.NATURE,
    stack = 100.q,
  );

  enum class Category(
    val displayName: String,
  ) {
    PARTS("Components"),
    EQUIPMENT("Equipment"),
    NATURE("Nature"),
    RESOURCES("Resources"),
  }

  val unlock by lazy {
    when (this) {
      IRON_ORE -> PhaseCondition(Phase.GAME_START)
      IRON_INGOT -> PhaseCondition(Phase.GAME_START)
      IRON_PLATE -> PhaseCondition(Phase.GAME_START)
      IRON_ROD -> PhaseCondition(Phase.GAME_START)
      XENO_ZAPPER -> PhaseCondition(Phase.GAME_START)
      PORTABLE_MINER -> MilestoneCondition(Milestone.HUB_UPGRADE_1)
      COPPER_ORE -> MilestoneCondition(Milestone.HUB_UPGRADE_2)
      COPPER_INGOT -> MilestoneCondition(Milestone.HUB_UPGRADE_2)
      WIRE -> MilestoneCondition(Milestone.HUB_UPGRADE_2)
      CABLE -> MilestoneCondition(Milestone.HUB_UPGRADE_2)
      LIMESTONE -> MilestoneCondition(Milestone.HUB_UPGRADE_3)
      CONCRETE -> MilestoneCondition(Milestone.HUB_UPGRADE_3)
      SCREW -> MilestoneCondition(Milestone.HUB_UPGRADE_3)
      REINFORCED_IRON_PLATE -> MilestoneCondition(Milestone.HUB_UPGRADE_3)
      BIOMASS -> MilestoneCondition(Milestone.HUB_UPGRADE_6)
      OBJECT_SCANNER -> MilestoneCondition(Milestone.FIELD_RESEARCH)
      BEACON -> MilestoneCondition(Milestone.FIELD_RESEARCH)
      COPPER_SHEET -> MilestoneCondition(Milestone.PART_ASSEMBLY)
      ROTOR -> MilestoneCondition(Milestone.PART_ASSEMBLY)
      MODULAR_FRAME -> MilestoneCondition(Milestone.PART_ASSEMBLY)
      SMART_PLATING -> MilestoneCondition(Milestone.PART_ASSEMBLY)
      CHAINSAW -> MilestoneCondition(Milestone.OBSTACLE_CLEARING)
      SOLID_BIOFUEL -> MilestoneCondition(Milestone.OBSTACLE_CLEARING)
      COLOR_CARTRIDGE -> MilestoneCondition(Milestone.RESOURCE_SINK_BONUS_PROGRAM)
      COAL -> MilestoneCondition(Milestone.COAL_POWER)
      WATER -> MilestoneCondition(Milestone.COAL_POWER)
      STEEL_INGOT -> MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION)
      STEEL_BEAM -> MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION)
      STEEL_PIPE -> MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION)
      VERSATILE_FRAMEWORK -> MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION)
      XENO_BASHER -> MilestoneCondition(Milestone.IMPROVED_MELEE_COMBAT)
      ENCASED_INDUSTRIAL_BEAM -> MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION)
      STATOR -> MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION)
      MOTOR -> MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION)
      AUTOMATED_WIRING -> MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION)
      HEAVY_MODULAR_FRAME -> MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION)
      CRUDE_OIL -> MilestoneCondition(Milestone.OIL_PROCESSING)
      PLASTIC -> MilestoneCondition(Milestone.OIL_PROCESSING)
      HEAVY_OIL_RESIDUE -> MilestoneCondition(Milestone.OIL_PROCESSING)
      RUBBER -> MilestoneCondition(Milestone.OIL_PROCESSING)
      FUEL -> MilestoneCondition(Milestone.OIL_PROCESSING)
      POLYMER_RESIN -> MilestoneCondition(Milestone.OIL_PROCESSING)
      PETROLEUM_COKE -> MilestoneCondition(Milestone.OIL_PROCESSING)
      CIRCUIT_BOARD -> MilestoneCondition(Milestone.OIL_PROCESSING)
      COMPUTER -> MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING)
      MODULAR_ENGINE -> MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING)
      ADAPTIVE_CONTROL_UNIT -> MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING)
      EMPTY_CANISTER -> MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT)
      PACKAGED_WATER -> MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT)
      PACKAGED_OIL -> MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT)
      PACKAGED_FUEL -> MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT)
      PACKAGED_HEAVY_OIL_RESIDUE -> MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT)
      PACKAGED_LIQUID_BIOFUEL -> MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT)
      LIQUID_BIOFUEL -> MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT)
      GAS_MASK -> MilestoneCondition(Milestone.GAS_MASK)
      GAS_FILTER -> MilestoneCondition(Milestone.GAS_MASK)
      CATERIUM_ORE -> any {
        +Milestone.EXPANDED_POWER_INFRASTRUCTURE
        +Research.UNKNOWN_METAL
      }
      CATERIUM_INGOT -> ResearchCondition(Research.CATERIUM_INGOTS)
      QUICKWIRE -> ResearchCondition(Research.QUICKWIRE)
      ZIPLINE -> ResearchCondition(Research.ZIPLINE)
      AI_LIMITER -> ResearchCondition(Research.AI_LIMITER)
      HIGH_SPEED_CONNECTOR -> ResearchCondition(Research.HIGH_SPEED_CONNECTOR)
      JETPACK -> MilestoneCondition(Milestone.JETPACK)
      BAUXITE -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      ALUMINA_SOLUTION -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      PACKAGED_ALUMINA_SOLUTION -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      ALUMINUM_SCRAP -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      ALUMINUM_INGOT -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      ALCLAD_ALUMINUM_SHEET -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      ALUMINUM_CASING -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      RAW_QUARTZ -> any {
        +Milestone.BAUXITE_REFINEMENT
        +Research.UNKNOWN_CRYSTALLINE_MATERIAL
      }
      QUARTZ_CRYSTAL -> ResearchCondition(Research.QUARTZ_CRYSTALS)
      SILICA -> ResearchCondition(Research.SILICA)
      CRYSTAL_OSCILLATOR -> ResearchCondition(Research.CRYSTAL_OSCILLATOR)
      RADIO_CONTROL_UNIT -> MilestoneCondition(Milestone.BAUXITE_REFINEMENT)
      HAZMAT_SUIT -> MilestoneCondition(Milestone.HAZMAT_SUIT)
      IODINE_INFUSED_FILTER -> MilestoneCondition(Milestone.HAZMAT_SUIT)
      SULFUR -> any {
        +Milestone.AERONAUTICAL_ENGINEERING
        +Research.UNKNOWN_CHEMICAL_ELEMENT
      }
      BLACK_POWDER -> ResearchCondition(Research.BLACK_POWDER)
      COMPACTED_COAL -> ResearchCondition(Research.COMPACTED_COAL)
      TURBOFUEL -> ResearchCondition(Research.TURBOFUEL)
      PACKAGED_TURBOFUEL -> ResearchCondition(Research.TURBOFUEL)
      NOBELISK_DETONATOR -> ResearchCondition(Research.THE_NOBELISK_DETONATOR)
      NOBELISK -> ResearchCondition(Research.THE_NOBELISK_DETONATOR)
      SMOKELESS_POWDER -> ResearchCondition(Research.SMOKELESS_POWDER)
      CLUSTER_NOBELISK -> ResearchCondition(Research.CLUSTER_NOBELISK)
      EXPLOSIVE_REBAR -> ResearchCondition(Research.EXPLOSIVE_REBAR)
      RIFLE -> ResearchCondition(Research.THE_RIFLE)
      RIFLE_AMMO -> ResearchCondition(Research.THE_RIFLE)
      SULFURIC_ACID -> MilestoneCondition(Milestone.AERONAUTICAL_ENGINEERING)
      PACKAGED_SULFURIC_ACID -> MilestoneCondition(Milestone.AERONAUTICAL_ENGINEERING)
      BATTERY -> MilestoneCondition(Milestone.AERONAUTICAL_ENGINEERING)
      SUPERCOMPUTER -> any {
        +Milestone.AERONAUTICAL_ENGINEERING
        +Research.SUPERCOMPUTER
      }
      ASSEMBLY_DIRECTOR_SYSTEM -> MilestoneCondition(Milestone.AERONAUTICAL_ENGINEERING)
      HOVER_PACK -> MilestoneCondition(Milestone.HOVER_PACK)
      URANIUM -> MilestoneCondition(Milestone.NUCLEAR_POWER)
      ENCASED_URANIUM_CELL -> MilestoneCondition(Milestone.NUCLEAR_POWER)
      ELECTROMAGNETIC_CONTROL_ROD -> MilestoneCondition(Milestone.NUCLEAR_POWER)
      URANIUM_FUEL_ROD -> MilestoneCondition(Milestone.NUCLEAR_POWER)
      MAGNETIC_FIELD_GENERATOR -> MilestoneCondition(Milestone.NUCLEAR_POWER)
      EMPTY_FLUID_TANK -> MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION)
      PACKAGED_NITROGEN_GAS -> MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION)
      HEAT_SINK -> MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION)
      COOLING_SYSTEM -> MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION)
      FUSED_MODULAR_FRAME -> MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION)
      TURBO_MOTOR -> MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION)
      THERMAL_PROPULSION_ROCKET -> MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION)
      WOOD -> Condition.TRUE
      LEAVES -> Condition.TRUE
      FLOWER_PETALS -> Condition.TRUE
      HOG_REMAINS -> Condition.TRUE
      HATCHER_REMAINS -> Condition.TRUE
      STINGER_REMAINS -> Condition.TRUE
      SPITTER_REMAINS -> Condition.TRUE
      ALIEN_PROTEIN -> any {
        +Research.HOG_RESEARCH
        +Research.HATCHER_RESEARCH
        +Research.STINGER_RESEARCH
        +Research.SPITTER_RESEARCH
      }
      ALIEN_DNA_CAPSULE -> ResearchCondition(Research.BIO_ORGANIC_PROPERTIES)
      REBAR_GUN -> ResearchCondition(Research.THE_REBAR_GUN)
      IRON_REBAR -> ResearchCondition(Research.THE_REBAR_GUN)
      MYCELIA -> Condition.TRUE
      BACON_AGARIC -> Condition.TRUE
      BERYL_NUT -> Condition.TRUE
      PALEBERRY -> Condition.TRUE
      MEDICINAL_INHALER -> any {
        +Research.PROTEIN_INHALER
        // TODO
      }
      BLUE_POWER_SLUG -> Condition.TRUE
      YELLOW_POWER_SLUG -> Condition.TRUE
      PURPLE_POWER_SLUG -> Condition.TRUE
      HARD_DRIVE -> Condition.TRUE
    }
  }
}
