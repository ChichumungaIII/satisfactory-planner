package app.game.data

/** Enumeration of all buildings in the game relevant to production planning. */
enum class Building(
  /** The human-readable text representation of the building. */
  val displayName: String,
  /** The subcategory to which the building belongs. */
  val subcategory: Subcategory,
  /** The amount of power the building consumes during operation. A null value indicates power varies by recipe. */
  val power: ClosedRange<Double>? = null,
  /** The recipes this building supports. */
  val recipes: List<Recipe>,
) {
  /* ************* */
  /* Manufacturers */
  /* ************* */

  CONSTRUCTOR("Constructor", Subcategory.MANUFACTURERS, power = 4, {
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
  ALIEN_POWER_AUGMENTER("Alien Power Augmenter", Subcategory.GENERATORS, power = 0, {});

  constructor(displayName: String, subcategory: Subcategory, power: Int, recipes: MutableList<Recipe>.() -> Unit) :
      this(displayName, subcategory, power.toDouble(), recipes)

  constructor(displayName: String, subcategory: Subcategory, power: Double, recipes: MutableList<Recipe>.() -> Unit) :
      this(displayName, subcategory, power..power, recipes)

  constructor(
    displayName: String,
    subcategory: Subcategory,
    power: ClosedRange<Double>? = null,
    recipes: MutableList<Recipe>.() -> Unit
  ) : this(displayName, subcategory, power, buildList { recipes() })


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
