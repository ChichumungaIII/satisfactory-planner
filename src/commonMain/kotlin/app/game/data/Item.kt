package app.game.data

import util.math.Rational
import util.math.q

enum class Item(
  val displayName: String,
  val category: Category,
  val stack: Rational? = null,
  val sink: Rational? = null,
  val energy: Rational? = null,
) {
  /************/
  /** Tier 0 **/
  /************/

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
  SCREWS(
    "Screws",
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

  /************/
  /** Tier 1 **/
  /************/

  /* Field Research */

  OBJECT_SCANNER(
    "Object Scanner",
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

  /************/
  /** Tier 2 **/
  /************/

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

  /************/
  /** Tier 3 **/
  /************/

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
    Category.RESOURCES,
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
    Category.PARTS,
    stack = 50.q,
    sink = 1_176.q,
  ),

  /************/
  /** Tier 4 **/
  /************/

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

  /************/
  /** Tier 5 **/
  /************/

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
    sink = 60.q,
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

  /************/
  /** Tier 6 **/
  /************/

  /* Expanded Power Infrastructure */

  CATERIUM_ORE(
    "Caterium Ore",
    Category.RESOURCES,
    stack = 100.q,
    sink = 7.q,
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

  /************/
  /** Tier 7 **/
  /************/

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
    sink = 3_072.q,
  ),
  RADIO_CONTROL_UNIT(
    "Radio Control Unit",
    Category.PARTS,
    stack = 50.q,
    sink = 19_600.q,
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
  SMOKELESS_POWDER(
    "Smokeless Powder",
    Category.PARTS,
    stack = 100.q,
    sink = 58.q,
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

  /************/
  /** Tier 8 **/
  /************/

  /* Nuclear Power */

  URANIUM(
    "Uranium",
    Category.RESOURCES,
    stack = 100.q,
    sink = 35.q,
  ),
  ENCASED_URANIUM_CELL(
    "Encased Uranium Cell",
    Category.PARTS,
    stack = 200.q,
    sink = 147.q,
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
  ),
  URANIUM_WASTE(
    "Uranium Waste",
    Category.PARTS,
    stack = 500.q,
  ),
  MAGNETIC_FIELD_GENERATOR(
    "Magnetic Field Generator",
    Category.PARTS,
    stack = 50.q,
    sink = 15_650.q,
  ),

  /* Advanced Aluminum Production */

  NITROGEN_GAS(
    "Nitrogen Gas",
    Category.RESOURCES,
  ),
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

  /* Particle Enrichment */

  NITRIC_ACID(
    "Nitric Acid",
    Category.PARTS,
  ),
  PACKAGED_NITRIC_ACID(
    "Packaged Nitric Acid",
    Category.PARTS,
    stack = 100.q,
    sink = 412.q,
  ),
  NON_FISSILE_URANIUM(
    "Non-fissile Uranium",
    Category.PARTS,
    stack = 500.q,
  ),
  PLUTONIUM_PELLET(
    "Plutonium Pellet",
    Category.PARTS,
    stack = 100.q,
  ),
  ENCASED_PLUTONIUM_CELL(
    "Encased Plutonium Cell",
    Category.PARTS,
    stack = 200.q,
  ),
  PLUTONIUM_FUEL_ROD(
    "Plutonium Fuel Rod",
    Category.PARTS,
    stack = 50.q,
    sink = 153_184.q,
    energy = 1_500_000.q,
  ),
  PLUTONIUM_WASTE(
    "Plutonium Waste",
    Category.PARTS,
    stack = 500.q,
  ),
  COPPER_POWDER(
    "Copper Powder",
    Category.PARTS,
    stack = 500.q,
    sink = 72.q,
  ),
  PRESSURE_CONVERSION_CUBE(
    "Pressure Conversion Cube",
    Category.PARTS,
    stack = 50.q,
    sink = 257_312.q,
  ),
  NUCLEAR_PASTA(
    "Nuclear Pasta",
    Category.PARTS,
    stack = 50.q,
    sink = 543_424.q,
  ),

  /***************/
  /** Equipment **/
  /***************/

  /* Transportation */

  BLADE_RUNNERS(
    "Blade Runners",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 4_088.q,
  ),
  PARACHUTE(
    "Parachute",
    Category.EQUIPMENT,
    stack = 50.q,
    sink = 608.q,
  ),
  JETPACK(
    "Jetpack",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 49_580.q,
  ),
  HOVER_PACK(
    "Hover Pack",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 413_920.q,
  ),

  /* Health and Safety */

  MEDICINAL_INHALER(
    "Medicinal Inhaler",
    Category.EQUIPMENT,
    stack = 50.q,
    sink = 125.q,
  ),
  GAS_MASK(
    "Gas Mask",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 55_000.q,
  ),
  GAS_FILTER(
    "Gas Filter",
    Category.CONSUMABLES,
    stack = 50.q,
    sink = 830.q,
  ),
  HAZMAT_SUIT(
    "Hazmat Suit",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 54_100.q,
  ),
  IODINE_INFUSED_FILTER(
    "Iodine Infused Filter",
    Category.CONSUMABLES,
    stack = 50.q,
    sink = 2_718.q,
  ),

  /* Melee Weapons */

  XENO_ZAPPER(
    "Xeno-Zapper",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_880.q,
  ),
  XENO_BASHER(
    "Xeno-Basher",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 18_800.q,
  ),

  /* Rebar Gun */

  REBAR_GUN(
    "Rebar Gun",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 1_968.q,
  ),
  IRON_REBAR(
    "Iron Rebar",
    Category.CONSUMABLES,
    stack = 100.q,
    sink = 8.q,
  ),
  STUN_REBAR(
    "Stun Rebar",
    Category.CONSUMABLES,
    stack = 100.q,
    sink = 186.q,
  ),
  SHATTER_REBAR(
    "Shatter Rebar",
    Category.CONSUMABLES,
    stack = 100.q,
    sink = 332.q,
  ),
  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    Category.CONSUMABLES,
    stack = 100.q,
    sink = 360.q,
  ),

  /* Rifle */

  RIFLE(
    "Rifle",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 9_480.q,
  ),
  RIFLE_AMMO(
    "Rifle Ammo",
    Category.CONSUMABLES,
    stack = 500.q,
    sink = 25.q,
  ),
  HOMING_RIFLE_AMMO(
    "Homing Rifle Ammo",
    Category.CONSUMABLES,
    stack = 500.q,
    sink = 855.q,
  ),
  TURBO_RIFLE_AMMO(
    "Turbo Rifle Ammo",
    Category.CONSUMABLES,
    stack = 500.q,
    sink = 120.q,
  ),

  /* Nobelisk */

  NOBELISK_DETONATOR(
    "Nobelisk Detonator",
    Category.EQUIPMENT,
    stack = 1.q,
    sink = 6_480.q,
  ),
  NOBELISK(
    "Nobelisk",
    Category.CONSUMABLES,
    stack = 50.q,
    sink = 152.q,
  ),
  GAS_NOBELISK(
    "Gas Nobelisk",
    Category.CONSUMABLES,
    stack = 50.q,
    sink = 544.q,
  ),
  PULSE_NOBELISK(
    "Pulse Nobelisk",
    Category.CONSUMABLES,
    stack = 50.q,
    sink = 1_533.q,
  ),
  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    Category.CONSUMABLES,
    stack = 50.q,
    sink = 1_376.q,
  ),
  NUKE_NOBELISK(
    "Nuke Nobelisk",
    Category.CONSUMABLES,
    stack = 50.q,
    sink = 19_600.q,
  ),

  /************/
  /** Nature **/
  /************/

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
  PLASMA_SPITTER_REMAINS(
    "Plasma Spitter Remains",
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

  /* Power Slugs */

  BLUE_POWER_SLUG(
    "Blue Power Slug",
    Category.NATURE,
    stack = 50.q,
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

  /* Specialty */

  HARD_DRIVE(
    "Hard Drive",
    Category.NATURE,
    stack = 100.q,
  ),

  /*******************/
  /** Uncategorized **/
  /*******************/

  /* Uncategorized */

  POWER_SHARD(
    "Power Shard",
    Category.UNCATEGORIZED,
    stack = 100.q,
  ),
  HUB_PARTS(
    "HUB Parts",
    Category.UNCATEGORIZED,
    stack = 1.q,
  ),
  FICSMAS_GIFT(
    "FICSMAS Gift",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  ),
  CANDY_CANE(
    "Candy Cane",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  ),
  FICSMAS_TREE_BRANCH(
    "FICSMAS Tree Branch",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  ),
  FICSMAS_BOW(
    "FICSMAS Bow",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  ),
  ACTUAL_SNOW(
    "Actual Snow",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  ),
  FICSMAS_WONDER_STAR(
    "FICSMAS Wonder Star",
    Category.UNCATEGORIZED,
    stack = 50.q,
    sink = 1.q,
  ),
  RED_FICSMAS_ORNAMENT(
    "Red FICSMAS Ornament",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  ),
  BLUE_FICSMAS_ORNAMENT(
    "Blue FICSMAS Ornament",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  ),
  COPPER_FICSMAS_ORNAMENT(
    "Copper FICSMAS Ornament",
    Category.UNCATEGORIZED,
    stack = 200.q,
    sink = 1.q,
  ),
  IRON_FICSMAS_ORNAMENT(
    "Iron FICSMAS Ornament",
    Category.UNCATEGORIZED,
    stack = 200.q,
    sink = 1.q,
  ),
  FICSMAS_ORNAMENT_BUNDLE(
    "FICSMAS Ornament Bundle",
    Category.UNCATEGORIZED,
    stack = 100.q,
    sink = 1.q,
  ),
  FICSMAS_DECORATION(
    "FICSMAS Decoration",
    Category.UNCATEGORIZED,
    stack = 100.q,
    sink = 1.q,
  ),
  FABRIC(
    "Fabric",
    Category.UNCATEGORIZED,
    stack = 100.q,
    sink = 140.q,
    energy = 15.q,
  ),
  FACTORY_CART(
    "Factory Cart™",
    Category.UNCATEGORIZED,
    stack = 1.q,
    sink = 1_552.q,
  ),
  CANDY_CANE_BASHER(
    "Candy Cane Basher",
    Category.UNCATEGORIZED,
    stack = 1.q,
    sink = 1.q,
  ),
  GOLDEN_FACTORY_CART(
    "Golden Factory Cart™",
    Category.UNCATEGORIZED,
    stack = 1.q,
    sink = 1_852.q,
  ),
  SWEET_FIREWORKS(
    "Sweet Fireworks",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 18.q,
  ),
  FANCY_FIREWORKS(
    "Fancy Fireworks",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 14.q,
  ),
  SPARKLY_FIREWORKS(
    "Sparkly Fireworks",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 10.q,
  ),
  SNOWBALL(
    "Snowball",
    Category.UNCATEGORIZED,
    stack = 500.q,
    sink = 1.q,
  );

  enum class Category(
    val displayName: String,
  ) {
    CONSUMABLES("Consumables"),
    EQUIPMENT("Equipment"),
    FICSMAS("FICSMAS"),
    NATURE("Nature"),
    PARTS("Components"),
    RESOURCES("Resources"),
    SPECIALTY("Specialty"),
    UNCATEGORIZED("Uncategorized"),
  }
}
