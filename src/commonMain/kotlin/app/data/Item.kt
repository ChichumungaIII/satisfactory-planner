package app.data

import util.math.Rational
import util.math.q

/**
 * Enumeration of all items in the game and their relevant properties.
 */
enum class Item(
  /** The human-readable text representation of the item. */
  val displayName: String,
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
    stack = 500,
    energy = 15,
  ),
  WOOD(
    "Wood",
    stack = 200,
    energy = 100,
  ),

  /* Alien Megafauna */

  HOG_REMAINS(
    "Hog Remains",
    stack = 50,
    energy = 250,
  ),
  HATCHER_REMAINS(
    "Hatcher Remains",
    stack = 50,
    energy = 250,
  ),
  STINGER_REMAINS(
    "Stinger Remains",
    stack = 50,
    energy = 250,
  ),
  PLASMA_SPITTER_REMAINS(
    "Plasma Spitter Remains",
    stack = 50,
    energy = 250,
  ),

  /* Alien Technology */

  MERCER_SPHERE(
    "Mercer Sphere",
    stack = 50,
  ),
  SOMERSLOOP(
    "Somersloop",
    stack = 50,
  ),

  /* Mycelia */

  MYCELIA(
    "Mycelia",
    stack = 200,
    energy = 20,
  ),

  /* Nutrients */

  BERYL_NUT(
    "Beryl Nut",
    stack = 100,
  ),
  PALEBERRY(
    "Paleberry",
    stack = 50,
  ),
  BACON_AGARIC(
    "Bacon Agaric",
    stack = 50,
  ),

  /* Power Slugs */

  BLUE_POWER_SLUG(
    "Blue Power Slug",
    stack = 50,
  ),
  YELLOW_POWER_SLUG(
    "Yellow Power Slug",
    stack = 50,
  ),
  PURPLE_POWER_SLUG(
    "Purple Power Slug",
    stack = 50,
  ),

  /* ****** */
  /* Tier 0 */
  /* ****** */

  /* Onboarding */

  IRON_ORE(
    "Iron Ore",
    stack = 100,
  ),
  IRON_INGOT(
    "Iron Ingot",
    stack = 100,
  ),
  IRON_PLATE(
    "Iron Plate",
    stack = 200,
  ),
  IRON_ROD(
    "Iron Rod",
    stack = 200,
  ),
  XENO_ZAPPER(
    "Xeno-Zapper",
    stack = 1,
  ),

  /* HUB Upgrade 1 */

  PORTABLE_MINER(
    "Portable Miner",
    stack = 50,
  ),

  /* HUB Upgrade 2 */

  COPPER_ORE(
    "Copper Ore",
    stack = 100,
  ),
  COPPER_INGOT(
    "Copper Ingot",
    stack = 100,
  ),
  WIRE(
    "Wire",
    stack = 500,
  ),
  CABLE(
    "Cable",
    stack = 200,
  ),

  /* HUB Upgrade 3 */

  LIMESTONE(
    "Limestone",
    stack = 100,
  ),
  CONCRETE(
    "Concrete",
    stack = 500,
  ),
  SCREWS(
    "Screws",
    stack = 500,
  ),
  REINFORCED_IRON_PLATE(
    "Reinforced Iron Plate",
    stack = 100,
  ),

  /* HUB Upgrade 6 */

  BIOMASS(
    "Biomass",
    stack = 200,
    energy = 180,
  ),

  /* ****** */
  /* Tier 1 */
  /* ****** */

  /* Field Research */

  OBJECT_SCANNER(
    "Object Scanner",
    stack = 1,
  ),

  /* Tier 1 Research */

  ALIEN_PROTEIN(
    "Alien Protein",
    stack = 100,
  ),
  ALIEN_DNA_CAPSULE(
    "Alien DNA Capsule",
    stack = 50,
  ),
  FABRIC(
    "Fabric",
    stack = 100,
  ),
  PARACHUTE(
    "Parachute",
    stack = 1,
  ),
  POWER_SHARD(
    "Power Shard",
    stack = 100,
  ),

  /* ****** */
  /* Tier 2 */
  /* ****** */

  /* Part Assembly */

  COPPER_SHEET(
    "Copper Sheet",
    stack = 200,
  ),
  ROTOR(
    "Rotor",
    stack = 100,
  ),
  MODULAR_FRAME(
    "Modular Frame",
    stack = 50,
  ),
  SMART_PLATING(
    "Smart Plating",
    stack = 100,
  ),

  /* Obstacle Clearing */

  SOLID_BIOFUEL(
    "Solid Biofuel",
    stack = 200,
    energy = 450,
  ),
  CHAINSAW(
    "Chainsaw",
    stack = 1,
  ),

  /* Tier 2 Research */

  MEDICINAL_INHALER(
    "Medicinal Inhaler",
    stack = 50,
  ),
  REBAR_GUN(
    "Rebar Gun",
    stack = 1,
  ),
  IRON_REBAR(
    "Iron Rebar",
    stack = 100,
  ),

  /* ****** */
  /* Tier 3 */
  /* ****** */

  /* Coal Power */

  COAL(
    "Coal",
    stack = 100,
    energy = 300,
  ),
  WATER(
    "Water",
    stack = 0,
  ),

  /* Basic Steel Production */

  STEEL_INGOT(
    "Steel Ingot",
    stack = 100,
  ),
  STEEL_BEAM(
    "Steel Beam",
    stack = 200,
  ),
  STEEL_PIPE(
    "Steel Pipe",
    stack = 200,
  ),
  VERSATILE_FRAMEWORK(
    "Versatile Framework",
    stack = 50,
  ),

  /* Enhanced Asset Security */

  XENO_BASHER(
    "Xeno-Basher",
    stack = 1,
  ),

  /* Tier 3 Research */

  GAS_MASK(
    "Gas Mask",
    stack = 1,
  ),
  GAS_FILTER(
    "Gas Filter",
    stack = 50,
  ),

  /* ****** */
  /* Tier 4 */
  /* ****** */

  /* Advanced Steel Production */

  ENCASED_INDUSTRIAL_BEAM(
    "Encased Industrial Beam",
    stack = 100,
  ),
  STATOR(
    "Stator",
    stack = 100,
  ),
  MOTOR(
    "Motor",
    stack = 50,
  ),
  AUTOMATED_WIRING(
    "Automated Wiring",
    stack = 50,
  ),

  /* ****** */
  /* Tier 5 */
  /* ****** */

  /* Jetpack */

  JETPACK(
    "Jetpack",
    stack = 1,
  ),

  /* Oil Processing */

  CRUDE_OIL(
    "Crude Oil",
    stack = 0,
    energy = 320,
  ),
  PLASTIC(
    "Plastic",
    stack = 200,
  ),
  RUBBER(
    "Rubber",
    stack = 200,
  ),
  FUEL(
    "Fuel",
    stack = 0,
    energy = 750,
  ),
  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    stack = 0,
    energy = 400,
  ),
  POLYMER_RESIN(
    "Polymer Resin",
    stack = 200,
  ),
  PETROLEUM_COKE(
    "Petroleum Coke",
    stack = 200,
    energy = 180,
  ),
  CIRCUIT_BOARD(
    "Circuit Board",
    stack = 200,
  ),

  /* Fluid Packaging */

  EMPTY_CANISTER(
    "Empty Canister",
    stack = 100,
  ),
  PACKAGED_WATER(
    "Packaged Water",
    stack = 100,
  ),
  PACKAGED_OIL(
    "Packaged Oil",
    stack = 100,
    energy = 320,
  ),
  PACKAGED_FUEL(
    "Packaged Fuel",
    stack = 100,
    energy = 750,
  ),
  PACKAGED_HEAVY_OIL_RESIDUE(
    "Packaged Heavy Oil Residue",
    stack = 100,
    energy = 400,
  ),
  PACKAGED_LIQUID_BIOFUEL(
    "Packaged Liquid Biofuel",
    stack = 100,
    energy = 750,
  ),
  LIQUID_BIOFUEL(
    "Liquid Biofuel",
    stack = 0,
    energy = 750,
  ),

  /* Petroleum Power */

  CATERIUM_ORE(
    "Caterium ore",
    stack = 100,
  ),

  /* Tier 5 Research */

  CATERIUM_INGOT(
    "Caterium Ingot",
    stack = 100,
  ),
  QUICKWIRE(
    "Quickwire",
    stack = 500,
  ),
  ZIPLINE(
    "Zipline",
    stack = 1,
  ),
  STUN_REBAR(
    "Stun Rebar",
    stack = 100,
  ),
  AI_LIMITER(
    "AI Limiter",
    stack = 100,
  ),
  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    stack = 100,
  ),

  /* ****** */
  /* Tier 6 */
  /* ****** */

  /* Industrial Manufacturing */

  COMPUTER(
    "Computer",
    stack = 50,
  ),
  HEAVY_MODULAR_FRAME(
    "Heavy Modular Frame",
    stack = 50,
  ),
  MODULAR_ENGINE(
    "Modular Engine",
    stack = 50,
  ),
  ADAPTIVE_CONTROL_UNIT(
    "Adaptive Control Unit",
    stack = 50,
  ),

  /* ****** */
  /* Tier 7 */
  /* ****** */

  /* Bauxite Refinement */

  RAW_QUARTZ(
    "Raw Quartz",
    stack = 100,
  ),

  /* Quartz Research */
  QUARTZ_CRYSTAL(
    "Quartz Crystal",
    stack = 200,
  ),
  SILICA(
    "Silica",
    stack = 500,
  ),
  SHATTER_REBAR(
    "Shatter Rebar",
    stack = 100,
  ),
  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    stack = 100,
  ),
  BLADE_RUNNERS(
    "Blade Runners",
    stack = 1,
  ),

  BAUXITE(
    "Bauxite",
    stack = 100,
  ),
  ALUMINA_SOLUTION(
    "Alumina Solution",
    stack = 0,
  ),
  PACKAGED_ALUMINA_SOLUTION(
    "Packaged Alumina Solution",
    stack = 100,
  ),
  ALUMINUM_SCRAP(
    "Aluminum Scrap",
    stack = 500,
  ),
  ALUMINUM_INGOT(
    "Aluminum Ingot",
    stack = 100,
  ),
  ALCLAD_ALUMINUM_SHEET(
    "Alclad Aluminum Sheet",
    stack = 200,
  ),
  ALUMINUM_CASING(
    "Aluminum Casing",
    stack = 200,
  ),

  /* Hoverpack */

  HOVERPACK(
    "Hoverpack",
    stack = 1,
  ),

  /* Hazmat Suit */

  IODINE_INFUSED_FILTER(
    "Iodine Infused Filter",
    stack = 50,
  ),
  HAZMAT_SUIT(
    "Hazmat Suit",
    stack = 1,
  ),

  /* Control System Development */

  SULFUR(
    "Sulfur",
    stack = 100,
  ),
  SULFURIC_ACID(
    "Sulfuric Acid",
    stack = 0,
  ),
  PACKAGED_SULFURIC_ACID(
    "Packaged Sulfuric Acid",
    stack = 100,
  ),
  BATTERY(
    "Battery",
    stack = 200,
    energy = 6_000,
  ),
  RADIO_CONTROL_UNIT(
    "Radio Control Unit",
    stack = 50,
  ),
  SUPERCOMPUTER(
    "Supercomputer",
    stack = 50,
  ),
  ASSEMBLY_DIRECTOR_SYSTEM(
    "Assembly Director System",
    stack = 50,
  ),

  /* Tier 7 Research */

  BLACK_POWDER(
    "Black Powder",
    stack = 200,
  ),
  COMPACTED_COAL(
    "Compacted Coal",
    stack = 100,
    energy = 630,
  ),
  TURBOFUEL(
    "Turbofuel",
    stack = 0,
    energy = 2_000,
  ),
  PACKAGED_TURBOFUEL(
    "Packaged Turbofuel",
    stack = 100,
    energy = 2_000,
  ),
  ROCKET_FUEL(
    "Rocket Fuel",
    stack = 0,
    energy = 3_600,
  ),
  PACKAGED_ROCKET_FUEL(
    "Packaged Rocket Fuel",
    stack = 100,
    energy = 3_600,
  ),
  SMOKELESS_POWDER(
    "Smokeless Powder",
    stack = 100,
  ),
  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    stack = 100,
  ),
  NOBELISK_DETONATOR(
    "Nobelisk Detonator",
    stack = 1,
  ),
  NOBELISK(
    "Nobelisk",
    stack = 50,
  ),
  GAS_NOBELISK(
    "Gas Nobelisk",
    stack = 50,
  ),
  PULSE_NOBELISK(
    "Pulse Nobelisk",
    stack = 50,
  ),
  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    stack = 50,
  ),
  RIFLE(
    "Rifle",
    stack = 1,
  ),
  RIFLE_AMMO(
    "Rifle Ammo",
    stack = 500,
  ),
  HOMING_RIFLE_AMMO(
    "Homing Rifle Ammo",
    stack = 500,
  ),
  TURBO_RIFLE_AMMO(
    "Turbo Rifle Ammo",
    stack = 500,
  ),

  /* ****** */
  /* Tier 8 */
  /* ****** */

  /* Nuclear Power */

  URANIUM(
    "Uranium",
    stack = 100,
  ),
  ENCASED_URANIUM_CELL(
    "Encased Uranium Cell",
    stack = 200,
  ),
  ELECTROMAGNETIC_CONTROL_ROD(
    "Electromagnetic Control Rod",
    stack = 100,
  ),
  URANIUM_FUEL_ROD(
    "Uranium Fuel Rod",
    stack = 50,
    energy = 750_000,
  ),
  URANIUM_WASTE(
    "Uranium Waste",
    stack = 500,
  ),
  MAGNETIC_FIELD_GENERATOR(
    "Magnetic Field Generator",
    stack = 50,
  ),

  /* Advanced Aluminum Production */

  NITROGEN_GAS(
    "Nitrogen Gas",
    stack = 0,
  ),
  EMPTY_FLUID_TANK(
    "Empty Fluid Tank",
    stack = 100,
  ),
  PACKAGED_NITROGEN_GAS(
    "Packaged Nitrogen Gas",
    stack = 100,
  ),
  HEAT_SINK(
    "Heat Sink",
    stack = 100,
  ),
  COOLING_SYSTEM(
    "Cooling System",
    stack = 100,
  ),
  FUSED_MODULAR_FRAME(
    "Fused Modular Frame",
    stack = 50,
  ),

  /* Leading-edge Production */

  TURBO_MOTOR(
    "Turbo Motor",
    stack = 50,
  ),
  THERMAL_PROPULSION_ROCKET(
    "Thermal Propulsion Rocket",
    stack = 50,
  ),

  /* Particle Enrichment */

  NITRIC_ACID(
    "Nitric Acid",
    stack = 0,
  ),
  PACKAGED_NITRIC_ACID(
    "Packaged Nitric Acid",
    stack = 100,
  ),
  NON_FISSILE_URANIUM(
    "Non-Fissile Uranium",
    stack = 500,
  ),
  PLUTONIUM_PELLET(
    "Plutonium Pellet",
    stack = 100,
  ),
  ENCASED_PLUTONIUM_CELL(
    "Encased Plutonium Cell",
    stack = 200,
  ),
  PLUTONIUM_FUEL_ROD(
    "Plutonium Fuel Rod",
    stack = 50,
    energy = 1_500_000,
  ),
  PLUTONIUM_WASTE(
    "Plutonium Waste",
    stack = 500,
  ),
  COPPER_POWDER(
    "Copper Powder",
    stack = 500,
  ),
  PRESSURE_CONVERSION_CUBE(
    "Pressure Conversion Cube",
    stack = 50,
  ),
  NUCLEAR_PASTA(
    "Nuclear Pasta",
    stack = 50,
  ),

  /* Tier 8 Research */

  NUKE_NOBELISK(
    "Nuke Nobelisk",
    stack = 50,
  ),

  /* ****** */
  /* Tier 9 */
  /* ****** */

  /* Matter Conversion */

  SAM(
    "SAM",
    stack = 100,
  ),
  DIAMONDS(
    "Diamonds",
    stack = 200,
  ),
  TIME_CRYSTAL(
    "Time Crystal",
    stack = 200,
  ),
  FICSITE_INGOT(
    "Ficsite Ingot",
    stack = 100,
  ),
  FICSITE_TRIGON(
    "Ficsite Trigon",
    stack = 200,
  ),
  REANIMATED_SAM(
    "Reanimated SAM",
    stack = 100,
  ),
  SAM_FLUCTUATOR(
    "SAM Fluctuator",
    stack = 100,
  ),
  BIOCHEMICAL_SCULPTOR(
    "Biochemical Sculptor",
    stack = 50,
  ),

  /* Quantum Encoding */

  EXCITED_PHOTONIC_MATTER(
    "Excited Photonic Matter",
    stack = 0,
  ),
  DARK_MATTER_RESIDUE(
    "Dark Matter Residue",
    stack = 0,
  ),
  DARK_MATTER_CRYSTAL(
    "Dark Matter Crystal",
    stack = 200,
  ),
  SUPERPOSITION_OSCILLATOR(
    "Superposition Oscillator",
    stack = 100,
  ),
  NEURAL_QUANTUM_PROCESSOR(
    "Neural-Quantum Processor",
    stack = 100,
  ),
  AI_EXPANSION_SERVER(
    "AI Expansion Server",
    stack = 50,
  ),

  /* Spatial Energy Regulation */

  SINGULARITY_CELL(
    "Singularity Cell",
    stack = 50,
  ),
  BALLISTIC_WARP_DRIVE(
    "Ballistic Warp Drive",
    stack = 50,
  ),

  /* Peak Efficiency */

  FICSONIUM(
    "Ficsonium",
    stack = 100,
  ),
  FICSONIUM_FUEL_ROD(
    "Ficsonium Fuel Rod",
    stack = 50,
    energy = 150_000,
  ),

  /* Tier 9 Research */

  ALIEN_POWER_MATRIX(
    "Alien Power Matrix",
    stack = 50,
  );

  constructor(displayName: String, stack: Int, energy: Int? = null) :
      this(displayName, stack.q, energy?.q)
}
