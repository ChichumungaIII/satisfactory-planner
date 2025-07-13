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
    within(app.game.data.RecipeV2.Category.AMMUNITION) {
      add(app.game.data.RecipeV2.IRON_REBAR)
    }
  }),
  ASSEMBLER("Assembler", Subcategory.MANUFACTURERS, power = 15, {
  }),
  MANUFACTURER("Manufacturer", Subcategory.MANUFACTURERS, power = 55, {
  }),
  PACKAGER("Packager", Subcategory.MANUFACTURERS, power = 10, {
  }),
  REFINERY("Refinery", Subcategory.MANUFACTURERS, power = 30, {
  }),
  BLENDER("Blender", Subcategory.MANUFACTURERS, power = 75, {
  }),
  PARTICLE_ACCELERATOR("Particle Accelerator", Subcategory.MANUFACTURERS, recipes = {
  }),
  CONVERTER("Converter", Subcategory.MANUFACTURERS, recipes = {
  }),
  QUANTUM_ENCODER("Quantum Encoder", Subcategory.MANUFACTURERS, recipes = {
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
