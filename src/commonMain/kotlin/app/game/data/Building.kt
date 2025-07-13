package app.game.data

import app.game.data.Building.RecipeSet

/** Enumeration of all buildings in the game relevant to production planning. */
enum class Building(
  /** The human-readable text representation of the building. */
  val displayName: String,
  /** The subcategory to which the building belongs. */
  val subcategory: Subcategory,
  /** The amount of power the building consumes during operation. A null value indicates power varies by recipe. */
  val power: ClosedRange<Double>?,
  /** The recipes this building supports. */
  val recipes: List<RecipeSet>,
) {
  /* ************* */
  /* Manufacturers */
  /* ************* */

  CONSTRUCTOR("Constructor", Subcategory.MANUFACTURERS, power = 4, {
    within(RecipeV2.Category.STANDARD_PARTS) {
      add(RecipeV2.IRON_PLATE)
      add(RecipeV2.IRON_ROD)
      add(RecipeV2.STEEL_ROD)
      add(RecipeV2.ALUMINUM_ROD)
      add(RecipeV2.SCREWS)
      add(RecipeV2.CAST_SCREWS)
      add(RecipeV2.STEEL_SCREWS)
      add(RecipeV2.COPPER_SHEET)
      add(RecipeV2.STEEL_BEAM)
      add(RecipeV2.ALUMINUM_BEAM)
      add(RecipeV2.STEEL_PIPE)
      add(RecipeV2.IRON_PIPE)
      add(RecipeV2.ALUMINUM_CASING)
      add(RecipeV2.FICSITE_TRIGON)
    }
    within(RecipeV2.Category.ELECTRONICS) {
      add(RecipeV2.WIRE)
      add(RecipeV2.IRON_WIRE)
      add(RecipeV2.CATERIUM_WIRE)
      add(RecipeV2.CABLE)
      add(RecipeV2.QUICKWIRE)
      add(RecipeV2.REANIMATED_SAM)
    }
    within(RecipeV2.Category.COMPOUNDS) {
      add(RecipeV2.CONCRETE)
      add(RecipeV2.QUARTZ_CRYSTAL)
      add(RecipeV2.SILICA)
      add(RecipeV2.COPPER_POWDER)
    }
    within(RecipeV2.Category.BIOMASS) {
      add(RecipeV2.BIOMASS_LEAVES)
      add(RecipeV2.BIOMASS_WOOD)
      add(RecipeV2.BIOMASS_MYCELIA)
      add(RecipeV2.BIOMASS_ALIEN_PROTEIN)
      add(RecipeV2.SOLID_BIOFUEL)
      add(RecipeV2.BIOCOAL)
      add(RecipeV2.CHARCOAL)
    }
    within(RecipeV2.Category.CONTAINERS) {
      add(RecipeV2.EMPTY_CANISTER)
      add(RecipeV2.STEEL_CANISTER)
      add(RecipeV2.EMPTY_FLUID_TANK)
    }
    within(RecipeV2.Category.ALIEN_REMAINS) {
      add(RecipeV2.HOG_PROTEIN)
      add(RecipeV2.HATCHER_PROTEIN)
      add(RecipeV2.STINGER_PROTEIN)
      add(RecipeV2.SPITTER_PROTEIN)
      add(RecipeV2.ALIEN_DNA_CAPSULE)
    }
    within(RecipeV2.Category.POWER_SHARDS) {
      add(RecipeV2.POWER_SHARD_1)
      add(RecipeV2.POWER_SHARD_2)
      add(RecipeV2.POWER_SHARD_5)
    }
    within(RecipeV2.Category.AMMUNITION) {
      add(RecipeV2.IRON_REBAR)
    }
  }),
  ASSEMBLER("Assembler", Subcategory.MANUFACTURERS, power = 15, {
    within(RecipeV2.Category.SPACE_ELEVATOR) {
      add(RecipeV2.SMART_PLATING)
      add(RecipeV2.VERSATILE_FRAMEWORK)
      add(RecipeV2.AUTOMATED_WIRING)
      add(RecipeV2.ASSEMBLY_DIRECTOR_SYSTEM)
      add(RecipeV2.MAGNETIC_FIELD_GENERATOR)
    }
    within(RecipeV2.Category.STANDARD_PARTS) {
      add(RecipeV2.COATED_IRON_PLATE)
      add(RecipeV2.REINFORCED_IRON_PLATE)
      add(RecipeV2.BOLTED_IRON_PLATE)
      add(RecipeV2.STITCHED_IRON_PLATE)
      add(RecipeV2.ADHERED_IRON_PLATE)
      add(RecipeV2.MODULAR_FRAME)
      add(RecipeV2.BOLTED_FRAME)
      add(RecipeV2.STEELED_FRAME)
      add(RecipeV2.ENCASED_INDUSTRIAL_BEAM)
      add(RecipeV2.ENCASED_INDUSTRIAL_PIPE)
      add(RecipeV2.ALCLAD_ALUMINUM_SHEET)
      add(RecipeV2.ALCLAD_CASING)
    }
    within(RecipeV2.Category.ELECTRONICS) {
      add(RecipeV2.FUSED_WIRE)
      add(RecipeV2.INSULATED_CABLE)
      add(RecipeV2.QUICKWIRE_CABLE)
      add(RecipeV2.CIRCUIT_BOARD)
      add(RecipeV2.ELECTRODE_CIRCUIT_BOARD)
      add(RecipeV2.CATERIUM_CIRCUIT_BOARD)
      add(RecipeV2.SILICON_CIRCUIT_BOARD)
      add(RecipeV2.FUSED_QUICKWIRE)
      add(RecipeV2.AI_LIMITER)
      add(RecipeV2.PLASTIC_AI_LIMITER)
      add(RecipeV2.CRYSTAL_COMPUTER)
      add(RecipeV2.OC_SUPERCOMPUTER)
    }
    within(RecipeV2.Category.COMPOUNDS) {
      add(RecipeV2.FINE_CONCRETE)
      add(RecipeV2.RUBBER_CONCRETE)
      add(RecipeV2.COMPACTED_COAL)
      add(RecipeV2.CHEAP_SILICA)
    }
    within(RecipeV2.Category.BIOMASS) {
      add(RecipeV2.FABRIC)
    }
    within(RecipeV2.Category.INDUSTRIAL_PARTS) {
      add(RecipeV2.ROTOR)
      add(RecipeV2.COPPER_ROTOR)
      add(RecipeV2.STEEL_ROTOR)
      add(RecipeV2.STATOR)
      add(RecipeV2.QUICKWIRE_STATOR)
      add(RecipeV2.MOTOR)
      add(RecipeV2.ELECTRIC_MOTOR)
      add(RecipeV2.HEAT_SINK)
      add(RecipeV2.HEAT_EXCHANGER)
    }
    within(RecipeV2.Category.CONTAINERS) {
      add(RecipeV2.COATED_IRON_CANISTER)
      add(RecipeV2.PRESSURE_CONVERSION_CUBE)
    }
    within(RecipeV2.Category.NUCLEAR) {
      add(RecipeV2.ELECTROMAGNETIC_CONTROL_ROD)
      add(RecipeV2.ENCASED_PLUTONIUM_CELL)
      add(RecipeV2.ELECTROMAGNETIC_CONNECTION_ROD)
      add(RecipeV2.PLUTONIUM_FUEL_UNIT)
    }
    within(RecipeV2.Category.TOOLS) {
      add(RecipeV2.AUTOMATED_MINER)
    }
    within(RecipeV2.Category.AMMUNITION) {
      add(RecipeV2.BLACK_POWDER)
      add(RecipeV2.FINE_BLACK_POWDER)
      add(RecipeV2.STUN_REBAR)
      add(RecipeV2.SHATTER_REBAR)
      add(RecipeV2.NOBELISK)
      add(RecipeV2.GAS_NOBELISK)
      add(RecipeV2.CLUSTER_NOBELISK)
      add(RecipeV2.PULSE_NOBELISK)
      add(RecipeV2.RIFLE_AMMO)
      add(RecipeV2.HOMING_RIFLE_AMMO)
    }
  }),
  MANUFACTURER("Manufacturer", Subcategory.MANUFACTURERS, power = 55, {
    within(RecipeV2.Category.SPACE_ELEVATOR) {
      add(RecipeV2.PLASTIC_SMART_PLATING)
      add(RecipeV2.FLEXIBLE_FRAMEWORK)
      add(RecipeV2.AUTOMATED_SPEED_WIRING)
      add(RecipeV2.MODULAR_ENGINE)
      add(RecipeV2.ADAPTIVE_CONTROL_UNIT)
      add(RecipeV2.THERMAL_PROPULSION_ROCKET)
      add(RecipeV2.BALLISTIC_WARP_DRIVE)
    }
    within(RecipeV2.Category.STANDARD_PARTS) {
      add(RecipeV2.HEAVY_MODULAR_FRAME)
      add(RecipeV2.HEAVY_ENCASED_FRAME)
      add(RecipeV2.HEAVY_FLEXIBLE_FRAME)
    }
    within(RecipeV2.Category.ELECTRONICS) {
      add(RecipeV2.HIGH_SPEED_CONNECTOR)
      add(RecipeV2.SILICON_HIGH_SPEED_CONNECTOR)
      add(RecipeV2.SINGULARITY_CELL)
      add(RecipeV2.SAM_FLUCTUATOR)
    }
    within(RecipeV2.Category.INDUSTRIAL_PARTS) {
      add(RecipeV2.RIGOR_MOTOR)
      add(RecipeV2.TURBO_MOTOR)
      add(RecipeV2.TURBO_ELECTRIC_MOTOR)
      add(RecipeV2.TURBO_PRESSURE_MOTOR)
      add(RecipeV2.CLASSIC_BATTERY)
    }
    within(RecipeV2.Category.COMMUNICATIONS) {
      add(RecipeV2.COMPUTER)
      add(RecipeV2.CATERIUM_COMPUTER)
      add(RecipeV2.SUPERCOMPUTER)
      add(RecipeV2.SUPER_STATE_COMPUTER)
      add(RecipeV2.CRYSTAL_OSCILLATOR)
      add(RecipeV2.INSULATED_CRYSTAL_OSCILLATOR)
      add(RecipeV2.RADIO_CONTROL_UNIT)
      add(RecipeV2.RADIO_CONNECTION_UNIT)
      add(RecipeV2.RADIO_CONTROL_SYSTEM)
    }
    within(RecipeV2.Category.NUCLEAR) {
      add(RecipeV2.INFUSED_URANIUM_CELL)
      add(RecipeV2.URANIUM_FUEL_ROD)
      add(RecipeV2.URANIUM_FUEL_UNIT)
      add(RecipeV2.PLUTONIUM_FUEL_ROD)
    }
    within(RecipeV2.Category.CONSUMABLES) {
      add(RecipeV2.GAS_FILTER)
      add(RecipeV2.IODINE_INFUSED_FILTER)
    }
    within(RecipeV2.Category.AMMUNITION) {
      add(RecipeV2.EXPLOSIVE_REBAR)
      add(RecipeV2.NUKE_NOBELISK)
      add(RecipeV2.TURBO_RIFLE_AMMO_MANUFACTURER)
    }
  }),
  PACKAGER("Packager", Subcategory.MANUFACTURERS, power = 10, {
    within(RecipeV2.Category.PACKAGING) {
      add(RecipeV2.PACKAGED_WATER)
      add(RecipeV2.PACKAGED_OIL)
      add(RecipeV2.PACKAGED_HEAVY_OIL_RESIDUE)
      add(RecipeV2.PACKAGED_LIQUID_BIOFUEL)
      add(RecipeV2.PACKAGED_FUEL)
      add(RecipeV2.PACKAGED_TURBOFUEL)
      add(RecipeV2.PACKAGED_ROCKET_FUEL)
      add(RecipeV2.PACKAGED_IONIZED_FUEL)
      add(RecipeV2.PACKAGED_ALUMINA_SOLUTION)
      add(RecipeV2.PACKAGED_SULFURIC_ACID)
      add(RecipeV2.PACKAGED_NITROGEN_GAS)
      add(RecipeV2.PACKAGED_NITRIC_ACID)
    }
    within(RecipeV2.Category.UNPACKAGING) {
      add(RecipeV2.UNPACKAGE_WATER)
      add(RecipeV2.UNPACKAGE_OIL)
      add(RecipeV2.UNPACKAGE_HEAVY_OIL_RESIDUE)
      add(RecipeV2.UNPACKAGE_LIQUID_BIOFUEL)
      add(RecipeV2.UNPACKAGE_FUEL)
      add(RecipeV2.UNPACKAGE_TURBOFUEL)
      add(RecipeV2.UNPACKAGE_ROCKET_FUEL)
      add(RecipeV2.UNPACKAGE_IONIZED_FUEL)
      add(RecipeV2.UNPACKAGE_ALUMINA_SOLUTION)
      add(RecipeV2.UNPACKAGE_SULFURIC_ACID)
      add(RecipeV2.UNPACKAGE_NITROGEN_GAS)
      add(RecipeV2.UNPACKAGE_NITRIC_ACID)
    }
  }),
  REFINERY("Refinery", Subcategory.MANUFACTURERS, power = 30, {
    within(RecipeV2.Category.OIL_PRODUCTS) {
      add(RecipeV2.PLASTIC)
      add(RecipeV2.RESIDUAL_PLASTIC)
      add(RecipeV2.RECYCLED_PLASTIC)
      add(RecipeV2.POLYMER_RESIN)
      add(RecipeV2.RUBBER)
      add(RecipeV2.RESIDUAL_RUBBER)
      add(RecipeV2.RECYCLED_RUBBER)
      add(RecipeV2.HEAVY_OIL_RESIDUE)
      add(RecipeV2.PETROLEUM_COKE)
    }
    within(RecipeV2.Category.ADVANCED_REFINEMENT) {
      add(RecipeV2.ALUMINA_SOLUTION)
      add(RecipeV2.SLOPPY_ALUMINA)
      add(RecipeV2.ALUMINUM_SCRAP)
      add(RecipeV2.ELECTRODE_ALUMINUM_SCRAP)
      add(RecipeV2.SULFURIC_ACID)
    }
    within(RecipeV2.Category.FUEL) {
      add(RecipeV2.FUEL)
      add(RecipeV2.RESIDUAL_FUEL)
      add(RecipeV2.DILUTED_PACKAGED_FUEL)
      add(RecipeV2.LIQUID_BIOFUEL)
      add(RecipeV2.TURBOFUEL)
      add(RecipeV2.TURBO_HEAVY_FUEL)
      add(RecipeV2.IONIZED_FUEL)
    }
    within(RecipeV2.Category.INGOTS) {
      add(RecipeV2.PURE_IRON_INGOT)
      add(RecipeV2.LEACHED_IRON_INGOT)
      add(RecipeV2.PURE_COPPER_INGOT)
      add(RecipeV2.LEACHED_COPPER_INGOT)
      add(RecipeV2.PURE_CATERIUM_INGOT)
      add(RecipeV2.LEACHED_CATERIUM_INGOT)
    }
    within(RecipeV2.Category.COMPOUNDS) {
      add(RecipeV2.WET_CONCRETE)
      add(RecipeV2.PURE_QUARTZ_CRYSTAL)
      add(RecipeV2.QUARTZ_PURIFICATION)
    }
    within(RecipeV2.Category.OTHER) {
      add(RecipeV2.COATED_CABLE)
      add(RecipeV2.STEAMED_COPPER_SHEET)
      add(RecipeV2.POLYESTER_FABRIC)
    }
    within(RecipeV2.Category.AMMUNITION) {
      add(RecipeV2.SMOKELESS_POWDER)
    }
  }),
  BLENDER("Blender", Subcategory.MANUFACTURERS, power = 75, {
    within(RecipeV2.Category.SPACE_ELEVATOR) {
      add(RecipeV2.BIOCHEMICAL_SCULPTOR)
    }
    within(RecipeV2.Category.ADVANCED_REFINEMENT) {
      add(RecipeV2.DISTILLED_SILICA)
      add(RecipeV2.INSTANT_SCRAP)
      add(RecipeV2.NITRIC_ACID)
    }
    within(RecipeV2.Category.FUEL) {
      add(RecipeV2.DILUTED_FUEL)
      add(RecipeV2.TURBO_BLEND_FUEL)
      add(RecipeV2.ROCKET_FUEL)
      add(RecipeV2.NITRO_ROCKET_FUEL)
    }
    within(RecipeV2.Category.INDUSTRIAL_PARTS) {
      add(RecipeV2.COOLING_SYSTEM)
      add(RecipeV2.COOLING_DEVICE)
      add(RecipeV2.FUSED_MODULAR_FRAME)
      add(RecipeV2.HEAT_FUSED_FRAME)
      add(RecipeV2.BATTERY)
    }
    within(RecipeV2.Category.NUCLEAR) {
      add(RecipeV2.ENCASED_URANIUM_CELL)
      add(RecipeV2.NON_FISSILE_URANIUM)
      add(RecipeV2.FERTILE_URANIUM)
    }
    within(RecipeV2.Category.AMMUNITION) {
      add(RecipeV2.TURBO_RIFLE_AMMO_BLENDER)
    }
  }),
  PARTICLE_ACCELERATOR("Particle Accelerator", Subcategory.MANUFACTURERS, recipes = {
    within(RecipeV2.Category.SPACE_ELEVATOR) {
      add(RecipeV2.NUCLEAR_PASTA)
    }
    within(RecipeV2.Category.QUANTUM_TECHNOLOGY) {
      add(RecipeV2.DARK_MATTER_CRYSTAL)
      add(RecipeV2.DARK_MATTER_TRAP)
      add(RecipeV2.DARK_MATTER_CRYSTALLIZATION)
    }
    within(RecipeV2.Category.ADVANCED_REFINEMENT) {
      add(RecipeV2.DIAMONDS)
      add(RecipeV2.CLOUDY_DIAMONDS)
      add(RecipeV2.OIL_BASED_DIAMONDS)
      add(RecipeV2.PETROLEUM_DIAMONDS)
      add(RecipeV2.TURBO_DIAMONDS)
    }
    within(RecipeV2.Category.NUCLEAR) {
      add(RecipeV2.PLUTONIUM_PELLET)
      add(RecipeV2.INSTANT_PLUTONIUM_CELL)
      add(RecipeV2.FICSONIUM)
    }
  }),
  CONVERTER("Converter", Subcategory.MANUFACTURERS, recipes = {
    within(RecipeV2.Category.QUANTUM_TECHNOLOGY) {
      add(RecipeV2.TIME_CRYSTAL)
      add(RecipeV2.EXCITED_PHOTONIC_MATTER)
      add(RecipeV2.DARK_MATTER_RESIDUE)
    }
    within(RecipeV2.Category.ADVANCED_REFINEMENT) {
      add(RecipeV2.PINK_DIAMONDS)
      add(RecipeV2.DARK_ION_FUEL)
    }
    within(RecipeV2.Category.INGOTS) {
      add(RecipeV2.FICSITE_INGOT_IRON)
      add(RecipeV2.FICSITE_INGOT_CATERIUM)
      add(RecipeV2.FICSITE_INGOT_ALUMINUM)
    }
    within(RecipeV2.Category.RAW_RESOURCE_CONVERSION) {
      add(RecipeV2.IRON_ORE_LIMESTONE)
      add(RecipeV2.COPPER_ORE_SULFUR)
      add(RecipeV2.COPPER_ORE_QUARTZ)
      add(RecipeV2.LIMESTONE_SULFUR)
      add(RecipeV2.COAL_LIMESTONE)
      add(RecipeV2.COAL_IRON)
      add(RecipeV2.SULFUR_IRON)
      add(RecipeV2.SULFUR_COAL)
      add(RecipeV2.CATERIUM_ORE_COPPER)
      add(RecipeV2.CATERIUM_ORE_QUARTZ)
      add(RecipeV2.RAW_QUARTZ_COAL)
      add(RecipeV2.RAW_QUARTZ_BAUXITE)
      add(RecipeV2.BAUXITE_COPPER)
      add(RecipeV2.BAUXITE_CATERIUM)
      add(RecipeV2.NITROGEN_GAS_CATERIUM)
      add(RecipeV2.NITROGEN_GAS_BAUXITE)
      add(RecipeV2.URANIUM_ORE_BAUXITE)
    }
  }),
  QUANTUM_ENCODER("Quantum Encoder", Subcategory.MANUFACTURERS, recipes = {
    within(RecipeV2.Category.SPACE_ELEVATOR) {
      add(RecipeV2.AI_EXPANSION_SERVER)
    }
    within(RecipeV2.Category.QUANTUM_TECHNOLOGY) {
      add(RecipeV2.SUPERPOSITION_OSCILLATOR)
      add(RecipeV2.NEURAL_QUANTUM_PROCESSOR)
      add(RecipeV2.ALIEN_POWER_MATRIX)
    }
    within(RecipeV2.Category.NUCLEAR) {
      add(RecipeV2.FICSONIUM_FUEL_ROD)
    }
    within(RecipeV2.Category.POWER_SHARDS) {
      add(RecipeV2.SYNTHETIC_POWER_SHARD)
    }
  }),

  /* ******** */
  /* Smelters */
  /* ******** */

  SMELTER("Smelter", Subcategory.SMELTERS, power = 4, {
    within(RecipeV2.Category.INGOTS) {
      add(RecipeV2.IRON_INGOT)
      add(RecipeV2.COPPER_INGOT)
      add(RecipeV2.CATERIUM_INGOT)
      add(RecipeV2.PURE_ALUMINUM_INGOT)
    }
  }),
  FOUNDRY("Foundry", Subcategory.SMELTERS, power = 16, {
    within(RecipeV2.Category.INGOTS) {
      add(RecipeV2.IRON_ALLOT_INGOT)
      add(RecipeV2.BASIC_IRON_INGOT)
      add(RecipeV2.COPPER_ALLOY_INGOT)
      add(RecipeV2.TEMPERED_COPPER_INGOT)
      add(RecipeV2.STEEL_INGOT)
      add(RecipeV2.SOLID_STEEL_INGOT)
      add(RecipeV2.COKE_STEEL_INGOT)
      add(RecipeV2.COMPACTED_STEEL_INGOT)
      add(RecipeV2.TEMPERED_CATERIUM_INGOT)
      add(RecipeV2.ALUMINUM_INGOT)
    }
    within(RecipeV2.Category.STANDARD_PARTS) {
      add(RecipeV2.STEEL_CAST_PLATE)
      add(RecipeV2.MOLDED_BEAM)
      add(RecipeV2.MOLDED_STEEL_PIPE)
    }
    within(RecipeV2.Category.COMPOUNDS) {
      add(RecipeV2.FUSED_QUARTZ_CRYSTAL)
    }
  }),

  /* ****** */
  /* Miners */
  /* ****** */

  MINER_MK_1("Miner Mk. 1", Subcategory.MINERS, power = 5, {
  }),
  MINER_MK_2("Miner Mk. 2", Subcategory.MINERS, power = 15, {
  }),
  MINER_MK_3("Miner Mk. 3", Subcategory.MINERS, power = 45, {
  }),

  /* **************** */
  /* Fluid Extractors */
  /* **************** */

  WATER_EXTRACTOR("Water Extractor", Subcategory.FLUID_EXTRACTORS, power = 20, {
  }),
  OIL_EXTRACTOR("Oil Extractor", Subcategory.FLUID_EXTRACTORS, power = 40, {
  }),
  RESOURCE_WELL_PRESSURIZER("Resource Well Pressurizer", Subcategory.FLUID_EXTRACTORS, power = 150, {}),
  RESOURCE_WELL_EXTRACTOR("Resource Well Extractor", Subcategory.FLUID_EXTRACTORS, power = 0, {
  }),

  /* ********** */
  /* Generators */
  /* ********** */

  BIOMASS_BURNER("Biomass Burner", Subcategory.GENERATORS, power = 0, {
  }),
  COAL_POWERED_GENERATOR("Coal-Powered Generator", Subcategory.GENERATORS, power = 0, {
  }),
  FUEL_POWERED_GENERATOR("Fuel-Powered Generator", Subcategory.GENERATORS, power = 0, {
  }),
  GEOTHERMAL_GENERATOR("Geothermal Generator", Subcategory.GENERATORS, power = 0, {
  }),
  NUCLEAR_POWER_PLANT("Nuclear Power Plant", Subcategory.GENERATORS, power = 0, {
  }),
  ALIEN_POWER_AUGMENTER("Alien Power Augmenter", Subcategory.GENERATORS, power = 0, {
  });

  constructor(displayName: String, subcategory: Subcategory, power: Int, recipes: MutableList<RecipeSet>.() -> Unit) :
      this(displayName, subcategory, power.toDouble(), recipes)

  constructor(
    displayName: String,
    subcategory: Subcategory,
    power: Double,
    recipes: MutableList<RecipeSet>.() -> Unit
  ) :
      this(displayName, subcategory, power..power, recipes)

  constructor(
    displayName: String,
    subcategory: Subcategory,
    power: ClosedRange<Double>? = null,
    recipes: MutableList<RecipeSet>.() -> Unit
  ) : this(displayName, subcategory, power, buildList { recipes() })

  /** A RecipeSet identifies a correspondence between a building's recipes and their categories. */
  data class RecipeSet(
    /** The category into which [recipes] are grouped. */
    val category: RecipeV2.Category,
    /** The set of recipes for this category. */
    val recipes: List<RecipeV2>,
  )

  /** Enumeration of the broad categories that a Building may belong to. */
  enum class Category(
    /** The human-readable text representation of the category. */
    val displayName: String,
  ) {
    PRODUCTION("Production"),
    POWER("Power"),
  }

  /** Enumeration of the precise category that a Building belongs to. */
  enum class Subcategory(
    /** The human-readable text representation of the subcategory. */
    val displayName: String,
    /** The general category this subcategory is a member of. */
    val category: Category,
  ) {
    MANUFACTURERS("Manufacturers", Category.PRODUCTION),
    SMELTERS("Smelters", Category.PRODUCTION),
    MINERS("Miners", Category.PRODUCTION),
    FLUID_EXTRACTORS("Fluid Extractors", Category.PRODUCTION),
    GENERATORS("Generators", Category.POWER),
  }
}

private fun MutableList<RecipeSet>.within(
  category: RecipeV2.Category,
  builderAction: MutableList<RecipeV2>.() -> Unit
) {
  val recipes = mutableListOf<RecipeV2>()
  recipes.builderAction()
  add(RecipeSet(category, recipes.toList()))
}
