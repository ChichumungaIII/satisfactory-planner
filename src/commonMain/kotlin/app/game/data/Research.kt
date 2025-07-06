package app.game.data

import util.math.Rational
import util.math.q

enum class Research(
  val displayName: String,
  val category: Category,
  val cost: Map<Item, Rational>,
) {
  /* Alien Organisms */

  HOG_RESEARCH(
    "Hog Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.HOG_REMAINS to 1.q),
  ),
  HATCHER_RESEARCH(
    "Hatcher Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.HATCHER_REMAINS to 1.q),
  ),
  STINGER_RESEARCH(
    "Stinger Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.STINGER_REMAINS to 1.q),
  ),
  SPITTER_RESEARCH(
    "Spitter Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.PLASMA_SPITTER_REMAINS to 1.q),
  ),
  BIO_ORGANIC_PROPERTIES(
    "Bio-Organic Properties",
    Category.ALIEN_ORGANISMS,
    cost = mapOf()
  ),
  PROTEIN_INHALER(
    "Protein Inhaler",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_PROTEIN to 2.q,
      Item.BERYL_NUT to 20.q,
      Item.ROTOR to 50.q,
    ),
  ),
  STRUCTURAL_ANALYSIS(
    "Structural Analysis",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 5.q,
      Item.IRON_ROD to 100.q,
    ),
  ),
  THE_REBAR_GUN(
    "The Rebar Gun",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ROTOR to 25.q,
      Item.REINFORCED_IRON_PLATE to 50.q,
      Item.SCREWS to 500.q,
    ),
  ),
  INFLATED_POCKET_DIMENSION_ALIEN_ORGANISMS(
    "Inflated Pocket Dimension",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_PROTEIN to 3.q,
      Item.CABLE to 1000.q,
    ),
  ),
  EXPANDED_TOOLBELT_ALIEN_ORGANISMS(
    "Expanded Toolbelt",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 5.q,
      Item.STEEL_BEAM to 500.q,
    ),
  ),
  HOSTILE_ORGANISM_DETECTION(
    "Hostile Organism Detection",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 10.q,
      Item.CRYSTAL_OSCILLATOR to 5.q,
      Item.HIGH_SPEED_CONNECTOR to 5.q,
    ),
  ),

  /* Caterium */

  UNKNOWN_METAL(
    "Unknown Metal",
    Category.CATERIUM,
    cost = mapOf(),
  ),
  CATERIUM(
    "Caterium",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_ORE to 10.q),
  ),
  CATERIUM_INGOTS(
    "Caterium Ingot",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_ORE to 50.q),
  ),
  QUICKWIRE(
    "Quickwire",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_INGOT to 50.q),
  ),
  ZIPLINE(
    "Zipline",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 100.q,
      Item.CABLE to 50.q,
    ),
  ),
  CATERIUM_ELECTRONICS(
    "Caterium Electronics",
    Category.CATERIUM,
    cost = mapOf(Item.QUICKWIRE to 100.q),
  ),
  STUN_REBAR(
    "Stun Rebar",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 50.q,
      Item.IRON_REBAR to 10.q,
    ),
  ),
  AI_LIMITER(
    "AI Limiter",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 200.q,
      Item.COPPER_SHEET to 50.q,
    ),
  ),
  POWER_POLES_MK_2(
    "Power Poles Mk. 2",
    Category.CATERIUM,
    cost = mapOf(Item.QUICKWIRE to 300.q),
  ),
  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 500.q,
      Item.PLASTIC to 50.q,
    ),
  ),
  SMART_SPLITTER(
    "Smart Splitter",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 500.q,
      Item.PLASTIC to 50.q,
    ),
  ),
  POWER_SWITCH(
    "Power Switch",
    Category.CATERIUM,
    cost = mapOf(
      Item.STEEL_BEAM to 100.q,
      Item.AI_LIMITER to 50.q,
    ),
  ),
  SUPERCOMPUTER(
    "Supercomputer",
    Category.CATERIUM,
    cost = mapOf(
      Item.AI_LIMITER to 50.q,
      Item.HIGH_SPEED_CONNECTOR to 50.q,
      Item.COMPUTER to 50.q,
    ),
  ),
  BULLET_GUIDANCE_SYSTEM(
    "Bullet Guidance System",
    Category.CATERIUM,
    cost = mapOf(
      Item.HIGH_SPEED_CONNECTOR to 10.q,
      Item.RIFLE_AMMO to 500.q,
    ),
  ),

  /* Mycelia */

  MYCELIA(
    "Mycelia",
    Category.MYCELIA,
    cost = mapOf(Item.MYCELIA to 5.q),
  ),
  FABRIC(
    "Fabric",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 25.q,
      Item.BIOMASS to 100.q,
    ),
  ),
  TOXIC_CELLULAR_MODIFICATION(
    "Toxic Cellular Modification",
    Category.MYCELIA,
    cost = mapOf(
      Item.NOBELISK to 50.q,
      Item.MYCELIA to 100.q,
      Item.BIOMASS to 200.q,
    ),
  ),
  MEDICAL_PROPERTIES(
    "Medical Properties",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 25.q,
      Item.STATOR to 10.q,
    ),
  ),
  PARACHUTE(
    "Parachute",
    Category.MYCELIA,
    cost = mapOf(
      Item.FABRIC to 10.q,
      Item.CABLE to 50.q,
    ),
  ),
  EXPANDED_TOOLBELT_MYCELIA(
    "Expanded Toolbelt",
    Category.MYCELIA,
    cost = mapOf(
      Item.FABRIC to 50.q,
      Item.ROTOR to 100.q,
    ),
  ),
  SYNTHETIC_POLYESTER_FABRIC(
    "Synthetic Polyester Fabric",
    Category.MYCELIA,
    cost = mapOf(
      Item.FABRIC to 25.q,
      Item.POLYMER_RESIN to 100.q,
    ),
  ),
  VITAMIN_INHALER(
    "Vitamin Inhaler",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 10.q,
      Item.PALEBERRY to 5.q,
    ),
  ),
  THERAPEUTIC_INHALER(
    "Therapeutic Inhaler",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 15.q,
      Item.BACON_AGARIC to 1.q,
      Item.ALIEN_PROTEIN to 1.q,
    ),
  ),

  /* Nutrients */

  BERYL_NUT(
    "Beryl Nut",
    Category.NUTRIENTS,
    cost = mapOf(Item.BERYL_NUT to 5.q),
  ),
  PALEBERRY(
    "Paleberry",
    Category.NUTRIENTS,
    cost = mapOf(Item.PALEBERRY to 2.q),
  ),
  BACON_AGARIC(
    "Bacon Agaric",
    Category.NUTRIENTS,
    cost = mapOf(Item.BACON_AGARIC to 1.q),
  ),
  NUTRITIONAL_PROCESSOR(
    "Nutritional Processor",
    Category.NUTRIENTS,
    cost = mapOf(),
  ),
  NUTRITIONAL_INHALER(
    "Nutritional Inhaler",
    Category.NUTRIENTS,
    cost = mapOf(
      Item.BACON_AGARIC to 2.q,
      Item.PALEBERRY to 4.q,
      Item.BERYL_NUT to 10.q,
    ),
  ),

  /* Power Slugs */

  BLUE_POWER_SLUGS(
    "Blue Power Slugs",
    Category.POWER_SLUGS,
    cost = mapOf(Item.BLUE_POWER_SLUG to 1.q),
  ),
  SLUG_SCANNING(
    "Slug Scanning",
    Category.POWER_SLUGS,
    cost = mapOf(
      Item.IRON_ROD to 50.q,
      Item.WIRE to 100.q,
      Item.SCREWS to 200.q,
    ),
  ),
  YELLOW_POWER_SHARDS(
    "Yellow Power Shards",
    Category.POWER_SLUGS,
    cost = mapOf(
      Item.YELLOW_POWER_SLUG to 1.q,
      Item.ROTOR to 25.q,
      Item.CABLE to 100.q,
    ),
  ),
  OVERCLOCK_PRODUCTION(
    "Overclock Production",
    Category.POWER_SLUGS,
    cost = mapOf(
      Item.POWER_SHARD to 1.q,
      Item.IRON_PLATE to 50.q,
      Item.WIRE to 50.q,
    ),
  ),
  PURPLE_POWER_SHARDS(
    "Purple Power Shards",
    Category.POWER_SLUGS,
    cost = mapOf(
      Item.PURPLE_POWER_SLUG to 1.q,
      Item.MODULAR_FRAME to 25.q,
      Item.COPPER_SHEET to 100.q,
    ),
  ),

  /* Quartz */

  UNKNOWN_CRYSTALLINE_MATERIAL(
    "Unknown Crystalline Material",
    Category.QUARTZ,
    cost = mapOf(),
  ),
  QUARTZ(
    "Quartz",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 10.q),
  ),
  QUARTZ_CRYSTALS(
    "Quartz Crystals",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 20.q),
  ),
  SILICA(
    "Silica",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 20.q),
  ),
  SHATTER_REBAR(
    "Shatter Rebar",
    Category.QUARTZ,
    cost = mapOf(
      Item.QUARTZ_CRYSTAL to 30.q,
      Item.IRON_REBAR to 150.q,
    ),
  ),
  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    Category.QUARTZ,
    cost = mapOf(
      Item.QUARTZ_CRYSTAL to 100.q,
      Item.REINFORCED_IRON_PLATE to 50.q,
    ),
  ),
  BLADE_RUNNERS(
    "Blade Runners",
    Category.QUARTZ,
    cost = mapOf(
      Item.SILICA to 50.q,
      Item.MODULAR_FRAME to 10.q,
    ),
  ),
  INFLATED_POCKET_DIMENSION_QUARTZ(
    "Inflated Pocket Dimension",
    Category.QUARTZ,
    cost = mapOf(Item.SILICA to 200.q),
  ),
  EXPLOSIVE_RESONANCE_APPLICATION(
    "Explosive Resonance Application",
    Category.QUARTZ,
    cost = mapOf(
      Item.CRYSTAL_OSCILLATOR to 5.q,
      Item.NOBELISK to 100.q,
    ),
  ),

  /* Sulfur */

  UNKNOWN_CHEMICAL_ELEMENT(
    "Unknown Chemical Element",
    Category.SULFUR,
    cost = mapOf(),
  ),
  SULFUR(
    "Sulfur",
    Category.SULFUR,
    cost = mapOf(Item.SULFUR to 10.q),
  ),
  BLACK_POWDER(
    "Black Powder",
    Category.SULFUR,
    cost = mapOf(
      Item.SULFUR to 50.q,
      Item.COAL to 25.q,
    ),
  ),
  EXPERIMENTAL_POWER_GENERATION(
    "Experimental Power Generation",
    Category.SULFUR,
    cost = mapOf(
      Item.SULFUR to 25.q,
      Item.MODULAR_FRAME to 50.q,
      Item.ROTOR to 100.q,
    ),
  ),
  COMPACTED_COAL(
    "Compacted Coal",
    Category.SULFUR,
    cost = mapOf(
      Item.HARD_DRIVE to 1.q,
      Item.SULFUR to 25.q,
      Item.COAL to 25.q,
    ),
  ),
  TURBOFUEL(
    "Turbofuel",
    Category.SULFUR,
    cost = mapOf(
      Item.HARD_DRIVE to 1.q,
      Item.COMPACTED_COAL to 15.q,
      Item.PACKAGED_FUEL to 50.q,
    ),
  ),
  EXPANDED_TOOLBELT_SULFUR(
    "Expanded Toolbelt",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 100.q,
      Item.ENCASED_INDUSTRIAL_BEAM to 50.q,
    ),
  ),
  THE_NOBELISK_DETONATOR(
    "The Nobelisk Detonator",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 50.q,
      Item.STEEL_PIPE to 100.q,
      Item.CABLE to 200.q,
    ),
  ),
  SMOKELESS_POWDER(
    "Smokeless Powder",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 100.q,
      Item.PLASTIC to 50.q,
    ),
  ),
  NUCLEAR_DETERRENT_DEVELOPMENT(
    "Nuclear Deterrent Development",
    Category.SULFUR,
    cost = mapOf(
      Item.NOBELISK to 500.q,
      Item.ENCASED_URANIUM_CELL to 10.q,
      Item.AI_LIMITER to 100.q,
    ),
  ),
  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 100.q,
      Item.NOBELISK to 200.q,
    ),
  ),
  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 200.q,
      Item.IRON_REBAR to 200.q,
      Item.STEEL_BEAM to 200.q,
    ),
  ),
  THE_RIFLE(
    "The Rifle",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 50.q,
      Item.MOTOR to 100.q,
      Item.RUBBER to 200.q,
    ),
  ),
  TURBO_RIFLE_AMMO(
    "Turbo Rifle Ammo",
    Category.SULFUR,
    cost = mapOf(
      Item.RIFLE_AMMO to 1_000.q,
      Item.PACKAGED_TURBOFUEL to 50.q,
      Item.ALUMINUM_CASING to 100.q,
    ),
  ),
  INFLATED_POCKET_DIMENSION_SULFUR(
    "Inflated Pocket Dimension",
    Category.SULFUR,
    cost = mapOf(),
  ),

  /* Hard Drive  */

  ADHERED_IRON_PLATE(
    "Adhered Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  ALCLAD_CASING(
    "Alclad Casing",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  AUTOMATED_MINER(
    "Automated Miner",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  AUTOMATED_SPEED_WIRING(
    "Automated Speed Wiring",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  BIOCOAL(
    "Biocoal",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  BOLTED_FRAME(
    "Bolted Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  BOLTED_IRON_PLATE(
    "Bolted Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CAST_SCREW(
    "Cast Screw",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CATERIUM_CIRCUIT_BOARD(
    "Caterium Circuit Board",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CATERIUM_COMPUTER(
    "Caterium Computer",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CATERIUM_WIRE(
    "Caterium Wire",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CHARCOAL(
    "Charcoal",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CHEAP_SILICA(
    "Cheap Silica",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CLASSIC_BATTERY(
    "Classic Battery",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COATED_CABLE(
    "Coated Cable",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COATED_IRON_CANISTER(
    "Coated Iron Canister",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COATED_IRON_PLATE(
    "Coated Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COKE_STEEL_INGOT(
    "Coke Steel Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COMPACTED_STEEL_INGOT(
    "Compacted Steel Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COOLING_DEVICE(
    "Cooling Device",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COPPER_ALLOY_INGOT(
    "Copper Alloy Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  COPPER_ROTOR(
    "Copper Rotor",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CRYSTAL_BEACON(
    "Crystal Beacon",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  CRYSTAL_COMPUTER(
    "Crystal Computer",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  DILUTED_FUEL(
    "Diluted Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  DILUTED_PACKAGED_FUEL(
    "Diluted Packaged Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  ELECTRIC_MOTOR(
    "Electric Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  ELECTRODE_ALUMINUM_SCRAP(
    "Electrode - Aluminum Scrap",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  ELECTRODE_CIRCUIT_BOARD(
    "Electrode Circuit Board",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  ELECTROMAGNETIC_CONNECTION_ROD(
    "Electromagnetic Connection Rod",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  ENCASED_INDUSTRIAL_PIPE(
    "Encased Industrial Pipe",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  FERTILE_URANIUM(
    "Fertile Uranium",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  FINE_BLACK_POWDER(
    "Fine Black Powder",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  FINE_CONCRETE(
    "Fine Concrete",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  FLEXIBLE_FRAMEWORK(
    "Flexible Framework",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  FUSED_QUICKWIRE(
    "Fused Quickwire",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  FUSED_WIRE(
    "Fused Wire",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  HEAT_EXCHANGER(
    "Heat Exchanger",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  HEAT_FUSED_FRAME(
    "Heat-Fused Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  HEAVY_ENCASED_FRAME(
    "Heavy Encased Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  HEAVY_FLEXIBLE_FRAME(
    "Heavy Flexible Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  INFUSED_URANIUM_CELL(
    "Infused Uranium Cell",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  INSTANT_PLUTONIUM_CELL(
    "Instant Plutonium Cell",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  INSTANT_SCRAP(
    "Instant Scrap",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  INSULATED_CABLE(
    "Insulated Cable",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  INSULATED_CRYSTAL_OSCILLATOR(
    "Insulated Crystal Oscillator",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  IRON_ALLOY_INGOT(
    "Iron Alloy Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  IRON_WIRE(
    "Iron Wire",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  OC_SUPERCOMPUTER(
    "OC Supercomputer",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  PLASTIC_SMART_PLATING(
    "Plastic Smart Plating",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  PLUTONIUM_FUEL_UNIT(
    "Plutonium Fuel Unit",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  POLYMER_RESIN(
    "Polymer Resin",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  PURE_ALUMINUM_INGOT(
    "Pure Aluminum Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  PURE_CATERIUM_INGOT(
    "Pure Caterium Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  PURE_COPPER_INGOT(
    "Pure Copper Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  PURE_IRON_INGOT(
    "Pure Iron Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  PURE_QUARTZ_CRYSTAL(
    "Pure Quartz Crystal",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  QUICKWIRE_CABLE(
    "Quickwire Cable",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  QUICKWIRE_STATOR(
    "Quickwire Stator",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  RADIO_CONNECTION_UNIT(
    "Radio Connection Unit",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  RADIO_CONTROL_SYSTEM(
    "Radio Control System",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  RECYCLED_PLASTIC(
    "Recycled Plastic",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  RECYCLED_RUBBER(
    "Recycled Rubber",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  RIGOUR_MOTOR(
    "Rigour Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  RUBBER_CONCRETE(
    "Rubber Concrete",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  SILICON_CIRCUIT_BOARD(
    "Silicon Circuit Board",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  SILICON_HIGH_SPEED_CONNECTOR(
    "Silicon High-Speed Connector",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  SLOPPY_ALUMINA(
    "Sloppy Alumina",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  SOLID_STEEL_INGOT(
    "Solid Steel Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STEAMED_COPPER_SHEET(
    "Steamed Copper Sheet",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STEEL_CANISTER(
    "Steel Canister",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STEEL_COATED_PLATE(
    "Steel Coated Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STEEL_ROD(
    "Steel Rod",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STEEL_ROTOR(
    "Steel Rotor",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STEEL_SCREW(
    "Steel Screw",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STEELED_FRAME(
    "Steeled Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  STITCHED_IRON_PLATE(
    "Stitched Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  SUPER_STATE_COMPUTER(
    "Super-State Computer",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  TURBO_BLEND_FUEL(
    "Turbo Blend Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  TURBO_ELECTRIC_MOTOR(
    "Turbo Electric Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  TURBO_HEAVY_FUEL(
    "Turbo Heavy Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  TURBO_PRESSURE_MOTOR(
    "Turbo Pressure Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  URANIUM_FUEL_UNIT(
    "Uranium Fuel Unit",
    Category.HARD_DRIVE,
    cost = mapOf(),
  ),
  WET_CONCRETE(
    "Wet Concrete",
    Category.HARD_DRIVE,
    cost = mapOf(),
  );

  enum class Category(
    val displayName: String,
  ) {
    HARD_DRIVE("Hard Drive"),
    ALIEN_ORGANISMS("Alien Organisms"),
    CATERIUM("Caterium"),
    MYCELIA("Mycelia"),
    NUTRIENTS("Nutrients"),
    POWER_SLUGS("Power Slugs"),
    QUARTZ("Quartz"),
    SULFUR("Sulfur"),
  }
}
