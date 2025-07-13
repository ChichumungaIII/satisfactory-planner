package app.game.data

import util.math.Rational
import util.math.q

/** Enumeration of all items in the game and their relevant properties. */
enum class Item(
  /** The human-readable text representation of the item. */
  val displayName: String,
  /** A categorization of the item, for display purposes. */
  val category: Category,
  /** How many instances of the item stack into an inventory slot. A zero value indicates the item cannot be held. */
  val stack: Rational,
  /** If set, represents the amount of energy (in MW) the item has when used as fuel. */
  val energy: Rational? = null,
) {
  /* ****** */
  /* Nature */
  /* ****** */

  /* Biomass */

  LEAVES(
    "Leaves",
    category = Category.NATURE,
    stack = 500,
    energy = 15,
  ),
  WOOD(
    "Wood",
    category = Category.NATURE,
    stack = 200,
    energy = 100,
  ),

  /* Alien Megafauna */

  HOG_REMAINS(
    "Hog Remains",
    category = Category.NATURE,
    stack = 50,
    energy = 250,
  ),
  HATCHER_REMAINS(
    "Hatcher Remains",
    category = Category.NATURE,
    stack = 50,
    energy = 250,
  ),
  STINGER_REMAINS(
    "Stinger Remains",
    category = Category.NATURE,
    stack = 50,
    energy = 250,
  ),
  PLASMA_SPITTER_REMAINS(
    "Plasma Spitter Remains",
    category = Category.NATURE,
    stack = 50,
    energy = 250,
  ),

  /* Alien Technology */

  MERCER_SPHERE(
    "Mercer Sphere",
    category = Category.NATURE,
    stack = 50,
  ),
  SOMERSLOOP(
    "Somersloop",
    category = Category.NATURE,
    stack = 50,
  ),

  /* Mycelia */

  MYCELIA(
    "Mycelia",
    category = Category.NATURE,
    stack = 200,
    energy = 20,
  ),

  /* Nutrients */

  BERYL_NUT(
    "Beryl Nut",
    category = Category.NATURE,
    stack = 100,
  ),
  PALEBERRY(
    "Paleberry",
    category = Category.NATURE,
    stack = 50,
  ),
  BACON_AGARIC(
    "Bacon Agaric",
    category = Category.NATURE,
    stack = 50,
  ),

  /* Power Slugs */

  BLUE_POWER_SLUG(
    "Blue Power Slug",
    category = Category.NATURE,
    stack = 50,
  ),
  YELLOW_POWER_SLUG(
    "Yellow Power Slug",
    category = Category.NATURE,
    stack = 50,
  ),
  PURPLE_POWER_SLUG(
    "Purple Power Slug",
    category = Category.NATURE,
    stack = 50,
  ),

  /* ****** */
  /* Tier 0 */
  /* ****** */

  /* Onboarding */

  IRON_ORE(
    "Iron Ore",
    category = Category.RESOURCES,
    stack = 100,
  ),
  IRON_INGOT(
    "Iron Ingot",
    category = Category.PARTS,
    stack = 100,
  ),
  IRON_PLATE(
    "Iron Plate",
    category = Category.PARTS,
    stack = 200,
  ),
  IRON_ROD(
    "Iron Rod",
    category = Category.PARTS,
    stack = 200,
  ),
  XENO_ZAPPER(
    "Xeno-Zapper",
    category = Category.EQUIPMENT,
    stack = 1,
  ),

  /* HUB Upgrade 1 */

  PORTABLE_MINER(
    "Portable Miner",
    category = Category.EQUIPMENT,
    stack = 50,
  ),

  /* HUB Upgrade 2 */

  COPPER_ORE(
    "Copper Ore",
    category = Category.RESOURCES,
    stack = 100,
  ),
  COPPER_INGOT(
    "Copper Ingot",
    category = Category.PARTS,
    stack = 100,
  ),
  WIRE(
    "Wire",
    category = Category.PARTS,
    stack = 500,
  ),
  CABLE(
    "Cable",
    category = Category.PARTS,
    stack = 200,
  ),

  /* HUB Upgrade 3 */

  LIMESTONE(
    "Limestone",
    category = Category.RESOURCES,
    stack = 100,
  ),
  CONCRETE(
    "Concrete",
    category = Category.PARTS,
    stack = 500,
  ),
  SCREWS(
    "Screws",
    category = Category.PARTS,
    stack = 500,
  ),
  REINFORCED_IRON_PLATE(
    "Reinforced Iron Plate",
    category = Category.PARTS,
    stack = 100,
  ),

  /* HUB Upgrade 6 */

  BIOMASS(
    "Biomass",
    category = Category.PARTS,
    stack = 200,
    energy = 180,
  ),

  /* ****** */
  /* Tier 1 */
  /* ****** */

  /* Field Research */

  OBJECT_SCANNER(
    "Object Scanner",
    category = Category.EQUIPMENT,
    stack = 1,
  ),

  /* Tier 1 Research */

  ALIEN_PROTEIN(
    "Alien Protein",
    category = Category.PARTS,
    stack = 100,
  ),
  ALIEN_DNA_CAPSULE(
    "Alien DNA Capsule",
    category = Category.PARTS,
    stack = 50,
  ),
  FABRIC(
    "Fabric",
    category = Category.PARTS,
    stack = 100,
  ),
  PARACHUTE(
    "Parachute",
    category = Category.EQUIPMENT,
    stack = 1,
  ),
  POWER_SHARD(
    "Power Shard",
    category = Category.PARTS,
    stack = 100,
  ),

  /* ****** */
  /* Tier 2 */
  /* ****** */

  /* Part Assembly */

  COPPER_SHEET(
    "Copper Sheet",
    category = Category.PARTS,
    stack = 200,
  ),
  ROTOR(
    "Rotor",
    category = Category.PARTS,
    stack = 100,
  ),
  MODULAR_FRAME(
    "Modular Frame",
    category = Category.PARTS,
    stack = 50,
  ),
  SMART_PLATING(
    "Smart Plating",
    category = Category.PARTS,
    stack = 100,
  ),

  /* Obstacle Clearing */

  SOLID_BIOFUEL(
    "Solid Biofuel",
    category = Category.PARTS,
    stack = 200,
    energy = 450,
  ),
  CHAINSAW(
    "Chainsaw",
    category = Category.EQUIPMENT,
    stack = 1,
  ),

  /* Tier 2 Research */

  MEDICINAL_INHALER(
    "Medicinal Inhaler",
    category = Category.CONSUMABLES,
    stack = 50,
  ),
  REBAR_GUN(
    "Rebar Gun",
    category = Category.EQUIPMENT,
    stack = 1,
  ),
  IRON_REBAR(
    "Iron Rebar",
    category = Category.CONSUMABLES,
    stack = 100,
  ),

  /* ****** */
  /* Tier 3 */
  /* ****** */

  /* Coal Power */

  COAL(
    "Coal",
    category = Category.RESOURCES,
    stack = 100,
    energy = 300,
  ),
  WATER(
    "Water",
    category = Category.RESOURCES,
    stack = 0,
  ),

  /* Basic Steel Production */

  STEEL_INGOT(
    "Steel Ingot",
    category = Category.PARTS,
    stack = 100,
  ),
  STEEL_BEAM(
    "Steel Beam",
    category = Category.PARTS,
    stack = 200,
  ),
  STEEL_PIPE(
    "Steel Pipe",
    category = Category.PARTS,
    stack = 200,
  ),
  VERSATILE_FRAMEWORK(
    "Versatile Framework",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Enhanced Asset Security */

  XENO_BASHER(
    "Xeno-Basher",
    category = Category.EQUIPMENT,
    stack = 1,
  ),

  /* Tier 3 Research */

  GAS_MASK(
    "Gas Mask",
    category = Category.EQUIPMENT,
    stack = 1,
  ),
  GAS_FILTER(
    "Gas Filter",
    category = Category.CONSUMABLES,
    stack = 50,
  ),

  /* ****** */
  /* Tier 4 */
  /* ****** */

  /* Advanced Steel Production */

  ENCASED_INDUSTRIAL_BEAM(
    "Encased Industrial Beam",
    category = Category.PARTS,
    stack = 100,
  ),
  STATOR(
    "Stator",
    category = Category.PARTS,
    stack = 100,
  ),
  MOTOR(
    "Motor",
    category = Category.PARTS,
    stack = 50,
  ),
  AUTOMATED_WIRING(
    "Automated Wiring",
    category = Category.PARTS,
    stack = 50,
  ),

  /* ****** */
  /* Tier 5 */
  /* ****** */

  /* Jetpack */

  JETPACK(
    "Jetpack",
    category = Category.EQUIPMENT,
    stack = 1,
  ),

  /* Oil Processing */

  CRUDE_OIL(
    "Crude Oil",
    category = Category.RESOURCES,
    stack = 0,
    energy = 320,
  ),
  PLASTIC(
    "Plastic",
    category = Category.PARTS,
    stack = 200,
  ),
  RUBBER(
    "Rubber",
    category = Category.PARTS,
    stack = 200,
  ),
  FUEL(
    "Fuel",
    category = Category.PARTS,
    stack = 0,
    energy = 750,
  ),
  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    category = Category.PARTS,
    stack = 0,
    energy = 400,
  ),
  POLYMER_RESIN(
    "Polymer Resin",
    category = Category.PARTS,
    stack = 200,
  ),
  PETROLEUM_COKE(
    "Petroleum Coke",
    category = Category.PARTS,
    stack = 200,
    energy = 180,
  ),
  CIRCUIT_BOARD(
    "Circuit Board",
    category = Category.PARTS,
    stack = 200,
  ),

  /* Fluid Packaging */

  LIQUID_BIOFUEL(
    "Liquid Biofuel",
    category = Category.PARTS,
    stack = 0,
    energy = 750,
  ),
  EMPTY_CANISTER(
    "Empty Canister",
    category = Category.PARTS,
    stack = 100,
  ),
  PACKAGED_WATER(
    "Packaged Water",
    category = Category.PARTS,
    stack = 100,
  ),
  PACKAGED_OIL(
    "Packaged Oil",
    category = Category.PARTS,
    stack = 100,
    energy = 320,
  ),
  PACKAGED_FUEL(
    "Packaged Fuel",
    category = Category.PARTS,
    stack = 100,
    energy = 750,
  ),
  PACKAGED_HEAVY_OIL_RESIDUE(
    "Packaged Heavy Oil Residue",
    category = Category.PARTS,
    stack = 100,
    energy = 400,
  ),
  PACKAGED_LIQUID_BIOFUEL(
    "Packaged Liquid Biofuel",
    category = Category.PARTS,
    stack = 100,
    energy = 750,
  ),

  /* Petroleum Power */

  CATERIUM_ORE(
    "Caterium ore",
    category = Category.RESOURCES,
    stack = 100,
  ),

  /* Tier 5 Research */

  CATERIUM_INGOT(
    "Caterium Ingot",
    category = Category.PARTS,
    stack = 100,
  ),
  QUICKWIRE(
    "Quickwire",
    category = Category.PARTS,
    stack = 500,
  ),
  ZIPLINE(
    "Zipline",
    category = Category.EQUIPMENT,
    stack = 1,
  ),
  STUN_REBAR(
    "Stun Rebar",
    category = Category.PARTS,
    stack = 100,
  ),
  AI_LIMITER(
    "AI Limiter",
    category = Category.PARTS,
    stack = 100,
  ),
  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    category = Category.PARTS,
    stack = 100,
  ),

  /* ****** */
  /* Tier 6 */
  /* ****** */

  /* Industrial Manufacturing */

  COMPUTER(
    "Computer",
    category = Category.PARTS,
    stack = 50,
  ),
  HEAVY_MODULAR_FRAME(
    "Heavy Modular Frame",
    category = Category.PARTS,
    stack = 50,
  ),
  MODULAR_ENGINE(
    "Modular Engine",
    category = Category.PARTS,
    stack = 50,
  ),
  ADAPTIVE_CONTROL_UNIT(
    "Adaptive Control Unit",
    category = Category.PARTS,
    stack = 50,
  ),

  /* ****** */
  /* Tier 7 */
  /* ****** */

  /* Bauxite Refinement */

  RAW_QUARTZ(
    "Raw Quartz",
    category = Category.RESOURCES,
    stack = 100,
  ),

  /* Quartz Research */
  QUARTZ_CRYSTAL(
    "Quartz Crystal",
    category = Category.PARTS,
    stack = 200,
  ),
  SILICA(
    "Silica",
    category = Category.PARTS,
    stack = 500,
  ),
  SHATTER_REBAR(
    "Shatter Rebar",
    category = Category.CONSUMABLES,
    stack = 100,
  ),
  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    category = Category.PARTS,
    stack = 100,
  ),
  BLADE_RUNNERS(
    "Blade Runners",
    category = Category.EQUIPMENT,
    stack = 1,
  ),

  BAUXITE(
    "Bauxite",
    category = Category.RESOURCES,
    stack = 100,
  ),
  ALUMINA_SOLUTION(
    "Alumina Solution",
    category = Category.PARTS,
    stack = 0,
  ),
  PACKAGED_ALUMINA_SOLUTION(
    "Packaged Alumina Solution",
    category = Category.PARTS,
    stack = 100,
  ),
  ALUMINUM_SCRAP(
    "Aluminum Scrap",
    category = Category.PARTS,
    stack = 500,
  ),
  ALUMINUM_INGOT(
    "Aluminum Ingot",
    category = Category.PARTS,
    stack = 100,
  ),
  ALCLAD_ALUMINUM_SHEET(
    "Alclad Aluminum Sheet",
    category = Category.PARTS,
    stack = 200,
  ),
  ALUMINUM_CASING(
    "Aluminum Casing",
    category = Category.PARTS,
    stack = 200,
  ),

  /* Hoverpack */

  HOVERPACK(
    "Hoverpack",
    category = Category.EQUIPMENT,
    stack = 1,
  ),

  /* Hazmat Suit */

  HAZMAT_SUIT(
    "Hazmat Suit",
    category = Category.EQUIPMENT,
    stack = 1,
  ),
  IODINE_INFUSED_FILTER(
    "Iodine Infused Filter",
    category = Category.CONSUMABLES,
    stack = 50,
  ),

  /* Control System Development */

  SULFUR(
    "Sulfur",
    category = Category.RESOURCES,
    stack = 100,
  ),
  SULFURIC_ACID(
    "Sulfuric Acid",
    category = Category.PARTS,
    stack = 0,
  ),
  PACKAGED_SULFURIC_ACID(
    "Packaged Sulfuric Acid",
    category = Category.PARTS,
    stack = 100,
  ),
  BATTERY(
    "Battery",
    category = Category.PARTS,
    stack = 200,
    energy = 6_000,
  ),
  RADIO_CONTROL_UNIT(
    "Radio Control Unit",
    category = Category.PARTS,
    stack = 50,
  ),
  SUPERCOMPUTER(
    "Supercomputer",
    category = Category.PARTS,
    stack = 50,
  ),
  ASSEMBLY_DIRECTOR_SYSTEM(
    "Assembly Director System",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Tier 7 Research */

  BLACK_POWDER(
    "Black Powder",
    category = Category.PARTS,
    stack = 200,
  ),
  COMPACTED_COAL(
    "Compacted Coal",
    category = Category.PARTS,
    stack = 100,
    energy = 630,
  ),
  TURBOFUEL(
    "Turbofuel",
    category = Category.PARTS,
    stack = 0,
    energy = 2_000,
  ),
  PACKAGED_TURBOFUEL(
    "Packaged Turbofuel",
    category = Category.PARTS,
    stack = 100,
    energy = 2_000,
  ),
  ROCKET_FUEL(
    "Rocket Fuel",
    category = Category.PARTS,
    stack = 0,
    energy = 3_600,
  ),
  PACKAGED_ROCKET_FUEL(
    "Packaged Rocket Fuel",
    category = Category.PARTS,
    stack = 100,
    energy = 3_600,
  ),
  SMOKELESS_POWDER(
    "Smokeless Powder",
    category = Category.PARTS,
    stack = 100,
  ),
  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    category = Category.CONSUMABLES,
    stack = 100,
  ),
  NOBELISK_DETONATOR(
    "Nobelisk Detonator",
    category = Category.EQUIPMENT,
    stack = 1,
  ),
  NOBELISK(
    "Nobelisk",
    category = Category.CONSUMABLES,
    stack = 50,
  ),
  GAS_NOBELISK(
    "Gas Nobelisk",
    category = Category.CONSUMABLES,
    stack = 50,
  ),
  PULSE_NOBELISK(
    "Pulse Nobelisk",
    category = Category.CONSUMABLES,
    stack = 50,
  ),
  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    category = Category.CONSUMABLES,
    stack = 50,
  ),
  RIFLE(
    "Rifle",
    category = Category.EQUIPMENT,
    stack = 1,
  ),
  RIFLE_AMMO(
    "Rifle Ammo",
    category = Category.CONSUMABLES,
    stack = 500,
  ),
  HOMING_RIFLE_AMMO(
    "Homing Rifle Ammo",
    category = Category.CONSUMABLES,
    stack = 500,
  ),
  TURBO_RIFLE_AMMO(
    "Turbo Rifle Ammo",
    category = Category.CONSUMABLES,
    stack = 500,
  ),

  /* ****** */
  /* Tier 8 */
  /* ****** */

  /* Nuclear Power */

  URANIUM(
    "Uranium",
    category = Category.RESOURCES,
    stack = 100,
  ),
  ENCASED_URANIUM_CELL(
    "Encased Uranium Cell",
    category = Category.PARTS,
    stack = 200,
  ),
  ELECTROMAGNETIC_CONTROL_ROD(
    "Electromagnetic Control Rod",
    category = Category.PARTS,
    stack = 100,
  ),
  URANIUM_FUEL_ROD(
    "Uranium Fuel Rod",
    category = Category.PARTS,
    stack = 50,
    energy = 750_000,
  ),
  URANIUM_WASTE(
    "Uranium Waste",
    category = Category.PARTS,
    stack = 500,
  ),
  MAGNETIC_FIELD_GENERATOR(
    "Magnetic Field Generator",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Advanced Aluminum Production */

  NITROGEN_GAS(
    "Nitrogen Gas",
    category = Category.RESOURCES,
    stack = 0,
  ),
  EMPTY_FLUID_TANK(
    "Empty Fluid Tank",
    category = Category.PARTS,
    stack = 100,
  ),
  PACKAGED_NITROGEN_GAS(
    "Packaged Nitrogen Gas",
    category = Category.PARTS,
    stack = 100,
  ),
  HEAT_SINK(
    "Heat Sink",
    category = Category.PARTS,
    stack = 100,
  ),
  COOLING_SYSTEM(
    "Cooling System",
    category = Category.PARTS,
    stack = 100,
  ),
  FUSED_MODULAR_FRAME(
    "Fused Modular Frame",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Leading-edge Production */

  TURBO_MOTOR(
    "Turbo Motor",
    category = Category.PARTS,
    stack = 50,
  ),
  THERMAL_PROPULSION_ROCKET(
    "Thermal Propulsion Rocket",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Particle Enrichment */

  NITRIC_ACID(
    "Nitric Acid",
    category = Category.PARTS,
    stack = 0,
  ),
  PACKAGED_NITRIC_ACID(
    "Packaged Nitric Acid",
    category = Category.PARTS,
    stack = 100,
  ),
  DISSOLVED_SILICA(
    "Dissolved Silica",
    category = Category.PARTS,
    stack = 0,
  ),
  NON_FISSILE_URANIUM(
    "Non-Fissile Uranium",
    category = Category.PARTS,
    stack = 500,
  ),
  PLUTONIUM_PELLET(
    "Plutonium Pellet",
    category = Category.PARTS,
    stack = 100,
  ),
  ENCASED_PLUTONIUM_CELL(
    "Encased Plutonium Cell",
    category = Category.PARTS,
    stack = 200,
  ),
  PLUTONIUM_FUEL_ROD(
    "Plutonium Fuel Rod",
    category = Category.PARTS,
    stack = 50,
    energy = 1_500_000,
  ),
  PLUTONIUM_WASTE(
    "Plutonium Waste",
    category = Category.PARTS,
    stack = 500,
  ),
  COPPER_POWDER(
    "Copper Powder",
    category = Category.PARTS,
    stack = 500,
  ),
  PRESSURE_CONVERSION_CUBE(
    "Pressure Conversion Cube",
    category = Category.PARTS,
    stack = 50,
  ),
  NUCLEAR_PASTA(
    "Nuclear Pasta",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Tier 8 Research */

  NUKE_NOBELISK(
    "Nuke Nobelisk",
    category = Category.CONSUMABLES,
    stack = 50,
  ),

  /* ****** */
  /* Tier 9 */
  /* ****** */

  /* Matter Conversion */

  SAM(
    "SAM",
    category = Category.RESOURCES,
    stack = 100,
  ),
  DIAMONDS(
    "Diamonds",
    category = Category.PARTS,
    stack = 200,
  ),
  TIME_CRYSTAL(
    "Time Crystal",
    category = Category.PARTS,
    stack = 200,
  ),
  REANIMATED_SAM(
    "Reanimated SAM",
    category = Category.PARTS,
    stack = 100,
  ),
  FICSITE_INGOT(
    "Ficsite Ingot",
    category = Category.PARTS,
    stack = 100,
  ),
  FICSITE_TRIGON(
    "Ficsite Trigon",
    category = Category.PARTS,
    stack = 200,
  ),
  SAM_FLUCTUATOR(
    "SAM Fluctuator",
    category = Category.PARTS,
    stack = 100,
  ),
  BIOCHEMICAL_SCULPTOR(
    "Biochemical Sculptor",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Quantum Encoding */

  EXCITED_PHOTONIC_MATTER(
    "Excited Photonic Matter",
    category = Category.PARTS,
    stack = 0,
  ),
  DARK_MATTER_RESIDUE(
    "Dark Matter Residue",
    category = Category.PARTS,
    stack = 0,
  ),
  DARK_MATTER_CRYSTAL(
    "Dark Matter Crystal",
    category = Category.PARTS,
    stack = 200,
  ),
  SUPERPOSITION_OSCILLATOR(
    "Superposition Oscillator",
    category = Category.PARTS,
    stack = 100,
  ),
  NEURAL_QUANTUM_PROCESSOR(
    "Neural-Quantum Processor",
    category = Category.PARTS,
    stack = 100,
  ),
  AI_EXPANSION_SERVER(
    "AI Expansion Server",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Spatial Energy Regulation */

  SINGULARITY_CELL(
    "Singularity Cell",
    category = Category.PARTS,
    stack = 50,
  ),
  BALLISTIC_WARP_DRIVE(
    "Ballistic Warp Drive",
    category = Category.PARTS,
    stack = 50,
  ),

  /* Peak Efficiency */

  FICSONIUM(
    "Ficsonium",
    category = Category.PARTS,
    stack = 100,
  ),
  FICSONIUM_FUEL_ROD(
    "Ficsonium Fuel Rod",
    category = Category.PARTS,
    stack = 50,
    energy = 150_000,
  ),

  /* Tier 9 Research */

  ALIEN_POWER_MATRIX(
    "Alien Power Matrix",
    category = Category.PARTS,
    stack = 50,
  );

  constructor(displayName: String, category: Category, stack: Int, energy: Int? = null) :
      this(displayName, category, stack.q, energy?.q)

  /** Enumerates the sorts of items that exist. */
  enum class Category(
    /** The human-readable text representation of the Category. */
    val displayName: String,
  ) {
    RESOURCES("Resources"),
    PARTS("Parts"),
    EQUIPMENT("Equipment"),
    NATURE("Nature"),
    CONSUMABLES("Consumables"),
  }
}
