package app.game.data

import app.game.logic.Condition
import app.game.logic.Condition.Companion.all
import app.game.logic.Condition.Companion.any
import app.game.logic.Condition.MilestoneCondition
import app.game.logic.Condition.ResearchCondition
import util.math.Rational
import util.math.q

enum class Research(
  val displayName: String,
  val category: Category,
  val cost: Map<Item, Rational>,
  val requirement: Condition,
) {
  /* Alien Organisms */

  HOG_RESEARCH(
    "Hog Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.HOG_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  HATCHER_RESEARCH(
    "Hatcher Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.HATCHER_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  STINGER_RESEARCH(
    "Stinger Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.STINGER_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  SPITTER_RESEARCH(
    "Spitter Research",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(Item.SPITTER_REMAINS to 1.q),
    requirement = Condition.TRUE,
  ),
  BIO_ORGANIC_PROPERTIES(
    "Bio-Organic Properties",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(),
    requirement = all {
      +HOG_RESEARCH
      +HATCHER_RESEARCH
      +STINGER_RESEARCH
      +SPITTER_RESEARCH
    }
  ),
  PROTEIN_INHALER(
    "Protein Inhaler",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_PROTEIN to 2.q,
      Item.BERYL_NUT to 20.q,
      Item.ROTOR to 50.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
  ),
  STRUCTURAL_ANALYSIS(
    "Structural Analysis",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 5.q,
      Item.IRON_ROD to 100.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
  ),
  THE_REBAR_GUN(
    "The Rebar Gun",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ROTOR to 25.q,
      Item.REINFORCED_IRON_PLATE to 50.q,
      Item.SCREW to 500.q,
    ),
    requirement = ResearchCondition(STRUCTURAL_ANALYSIS),
  ),
  INFLATED_POCKET_DIMENSION_ALIEN_ORGANISMS(
    "Inflated Pocket Dimension",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_PROTEIN to 3.q,
      Item.CABLE to 1000.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
  ),
  EXPANDED_TOOLBELT_ALIEN_ORGANISMS(
    "Expanded Toolbelt",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 5.q,
      Item.STEEL_BEAM to 500.q,
    ),
    requirement = ResearchCondition(INFLATED_POCKET_DIMENSION_ALIEN_ORGANISMS),
  ),
  HOSTILE_ORGANISM_DETECTION(
    "Hostile Organism Detection",
    Category.ALIEN_ORGANISMS,
    cost = mapOf(
      Item.ALIEN_DNA_CAPSULE to 10.q,
      Item.CRYSTAL_OSCILLATOR to 5.q,
      Item.HIGH_SPEED_CONNECTOR to 5.q,
    ),
    requirement = ResearchCondition(BIO_ORGANIC_PROPERTIES),
  ),

  /* Caterium */

  UNKNOWN_METAL(
    "Unknown Metal",
    Category.CATERIUM,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  CATERIUM(
    "Caterium",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_ORE to 10.q),
    requirement = ResearchCondition(UNKNOWN_METAL),
  ),
  CATERIUM_INGOTS(
    "Caterium Ingot",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_ORE to 50.q),
    requirement = ResearchCondition(CATERIUM),
  ),
  QUICKWIRE(
    "Quickwire",
    Category.CATERIUM,
    cost = mapOf(Item.CATERIUM_INGOT to 50.q),
    requirement = ResearchCondition(CATERIUM_INGOTS),
  ),
  ZIPLINE(
    "Zipline",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 100.q,
      Item.CABLE to 50.q,
    ),
    requirement = ResearchCondition(QUICKWIRE),
  ),
  CATERIUM_ELECTRONICS(
    "Caterium Electronics",
    Category.CATERIUM,
    cost = mapOf(Item.QUICKWIRE to 100.q),
    requirement = ResearchCondition(QUICKWIRE),
  ),
  STUN_REBAR(
    "Stun Rebar",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 50.q,
      Item.IRON_REBAR to 10.q,
    ),
    requirement = ResearchCondition(QUICKWIRE),
  ),
  AI_LIMITER(
    "AI Limiter",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 200.q,
      Item.COPPER_SHEET to 50.q,
    ),
    requirement = ResearchCondition(CATERIUM_ELECTRONICS),
  ),
  POWER_POLES_MK_2(
    "Power Poles Mk. 2",
    Category.CATERIUM,
    cost = mapOf(Item.QUICKWIRE to 300.q),
    requirement = ResearchCondition(CATERIUM_ELECTRONICS),
  ),
  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 500.q,
      Item.PLASTIC to 50.q,
    ),
    requirement = ResearchCondition(CATERIUM_ELECTRONICS),
  ),
  SMART_SPLITTER(
    "Smart Splitter",
    Category.CATERIUM,
    cost = mapOf(
      Item.QUICKWIRE to 500.q,
      Item.PLASTIC to 50.q,
    ),
    requirement = ResearchCondition(AI_LIMITER),
  ),
  POWER_SWITCH(
    "Power Switch",
    Category.CATERIUM,
    cost = mapOf(
      Item.STEEL_BEAM to 100.q,
      Item.AI_LIMITER to 50.q,
    ),
    requirement = ResearchCondition(AI_LIMITER),
  ),
  SUPERCOMPUTER(
    "Supercomputer",
    Category.CATERIUM,
    cost = mapOf(
      Item.AI_LIMITER to 50.q,
      Item.HIGH_SPEED_CONNECTOR to 50.q,
      Item.COMPUTER to 50.q,
    ),
    requirement = all {
      +AI_LIMITER
      +HIGH_SPEED_CONNECTOR
    },
  ),
  BULLET_GUIDANCE_SYSTEM(
    "Bullet Guidance System",
    Category.CATERIUM,
    cost = mapOf(
      Item.HIGH_SPEED_CONNECTOR to 10.q,
      Item.RIFLE_AMMO to 500.q,
    ),
    requirement = ResearchCondition(HIGH_SPEED_CONNECTOR),
  ),

  /* Mycelia */

  MYCELIA(
    "Mycelia",
    Category.MYCELIA,
    cost = mapOf(Item.MYCELIA to 5.q),
    requirement = Condition.TRUE,
  ),
  FABRIC(
    "Fabric",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 25.q,
      Item.BIOMASS to 100.q,
    ),
    requirement = ResearchCondition(MYCELIA),
  ),
  TOXIC_CELLULAR_MODIFICATION(
    "Toxic Cellular Modification",
    Category.MYCELIA,
    cost = mapOf(
      Item.NOBELISK to 50.q,
      Item.MYCELIA to 100.q,
      Item.BIOMASS to 200.q,
    ),
    requirement = ResearchCondition(MYCELIA),
  ),
  MEDICAL_PROPERTIES(
    "Medical Properties",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 25.q,
      Item.STATOR to 10.q,
    ),
    requirement = ResearchCondition(MYCELIA),
  ),
  PARACHUTE(
    "Parachute",
    Category.MYCELIA,
    cost = mapOf(
      // TODO: Item.FABRIC to 10.q,
      Item.CABLE to 50.q,
    ),
    requirement = ResearchCondition(FABRIC),
  ),
  EXPANDED_TOOLBELT_MYCELIA(
    "Expanded Toolbelt",
    Category.MYCELIA,
    cost = mapOf(
      // TODO: Item.FABRIC to 50.q,
      Item.ROTOR to 100.q,
    ),
    requirement = ResearchCondition(FABRIC),
  ),
  SYNTHETIC_POLYESTER_FABRIC(
    "Synthetic Polyester Fabric",
    Category.MYCELIA,
    cost = mapOf(
      // TODO: Item.FABRIC to 25.q,
      Item.POLYMER_RESIN to 100.q,
    ),
    requirement = ResearchCondition(FABRIC),
  ),
  VITAMIN_INHALER(
    "Vitamin Inhaler",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 10.q,
      Item.PALEBERRY to 5.q,
    ),
    requirement = ResearchCondition(MEDICAL_PROPERTIES),
  ),
  THERAPEUTIC_INHALER(
    "Therapeutic Inhaler",
    Category.MYCELIA,
    cost = mapOf(
      Item.MYCELIA to 15.q,
      Item.BACON_AGARIC to 1.q,
      Item.ALIEN_PROTEIN to 1.q,
    ),
    requirement = ResearchCondition(MEDICAL_PROPERTIES),
  ),

  /* Nutrients */

  BERYL_NUT(
    "Beryl Nut",
    Category.NUTRIENTS,
    cost = mapOf(Item.BERYL_NUT to 5.q),
    requirement = Condition.TRUE,
  ),
  PALEBERRY(
    "Paleberry",
    Category.NUTRIENTS,
    cost = mapOf(Item.PALEBERRY to 2.q),
    requirement = Condition.TRUE,
  ),
  BACON_AGARIC(
    "Bacon Agaric",
    Category.NUTRIENTS,
    cost = mapOf(Item.BACON_AGARIC to 1.q),
    requirement = Condition.TRUE,
  ),
  NUTRITIONAL_PROCESSOR(
    "Nutritional Processor",
    Category.NUTRIENTS,
    cost = mapOf(),
    requirement = all {
      +BERYL_NUT
      +PALEBERRY
      +BACON_AGARIC
    },
  ),
  NUTRITIONAL_INHALER(
    "Nutritional Inhaler",
    Category.NUTRIENTS,
    cost = mapOf(
      Item.BACON_AGARIC to 2.q,
      Item.PALEBERRY to 4.q,
      Item.BERYL_NUT to 10.q,
    ),
    requirement = ResearchCondition(NUTRITIONAL_PROCESSOR),
  ),

  /* Power Slugs */

  BLUE_POWER_SLUGS(
    "Blue Power Slugs",
    Category.POWER_SLUGS,
    cost = mapOf(Item.BLUE_POWER_SLUG to 1.q),
    requirement = Condition.TRUE,
  ),
  SLUG_SCANNING(
    "Slug Scanning",
    Category.POWER_SLUGS,
    cost = mapOf(
      Item.IRON_ROD to 50.q,
      Item.WIRE to 100.q,
      Item.SCREW to 200.q,
    ),
    requirement = ResearchCondition(BLUE_POWER_SLUGS),
  ),
  YELLOW_POWER_SHARDS(
    "Yellow Power Shards",
    Category.POWER_SLUGS,
    cost = mapOf(
      Item.YELLOW_POWER_SLUG to 1.q,
      Item.ROTOR to 25.q,
      Item.CABLE to 100.q,
    ),
    requirement = ResearchCondition(BLUE_POWER_SLUGS),
  ),
  OVERCLOCK_PRODUCTION(
    "Overclock Production",
    Category.POWER_SLUGS,
    cost = mapOf(
      // TODO: Item.POWER_SHARD to 1.q,
      Item.IRON_PLATE to 50.q,
      Item.WIRE to 50.q,
    ),
    requirement = ResearchCondition(BLUE_POWER_SLUGS),
  ),
  PURPLE_POWER_SHARDS(
    "Purple Power Shards",
    Category.POWER_SLUGS,
    cost = mapOf(
      Item.PURPLE_POWER_SLUG to 1.q,
      Item.MODULAR_FRAME to 25.q,
      Item.COPPER_SHEET to 100.q,
    ),
    requirement = ResearchCondition(YELLOW_POWER_SHARDS),
  ),

  /* Quartz */

  UNKNOWN_CRYSTALLINE_MATERIAL(
    "Unknown Crystalline Material",
    Category.QUARTZ,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  QUARTZ(
    "Quartz",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 10.q),
    requirement = ResearchCondition(UNKNOWN_CRYSTALLINE_MATERIAL),
  ),
  QUARTZ_CRYSTALS(
    "Quartz Crystals",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 20.q),
    requirement = ResearchCondition(QUARTZ),
  ),
  SILICA(
    "Silica",
    Category.QUARTZ,
    cost = mapOf(Item.RAW_QUARTZ to 20.q),
    requirement = ResearchCondition(QUARTZ),
  ),
  SHATTER_REBAR(
    "Shatter Rebar",
    Category.QUARTZ,
    cost = mapOf(
      Item.QUARTZ_CRYSTAL to 30.q,
      Item.IRON_REBAR to 150.q,
    ),
    requirement = ResearchCondition(QUARTZ_CRYSTALS),
  ),
  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    Category.QUARTZ,
    cost = mapOf(
      Item.QUARTZ_CRYSTAL to 100.q,
      Item.REINFORCED_IRON_PLATE to 50.q,
    ),
    requirement = ResearchCondition(QUARTZ_CRYSTALS),
  ),
  BLADE_RUNNERS(
    "Blade Runners",
    Category.QUARTZ,
    cost = mapOf(
      Item.SILICA to 50.q,
      Item.MODULAR_FRAME to 10.q,
    ),
    requirement = ResearchCondition(SILICA),
  ),
  INFLATED_POCKET_DIMENSION_QUARTZ(
    "Inflated Pocket Dimension",
    Category.QUARTZ,
    cost = mapOf(Item.SILICA to 200.q),
    requirement = ResearchCondition(SILICA),
  ),
  EXPLOSIVE_RESONANCE_APPLICATION(
    "Explosive Resonance Application",
    Category.QUARTZ,
    cost = mapOf(
      Item.CRYSTAL_OSCILLATOR to 5.q,
      Item.NOBELISK to 100.q,
    ),
    requirement = ResearchCondition(CRYSTAL_OSCILLATOR),
  ),

  /* Sulfur */

  UNKNOWN_CHEMICAL_ELEMENT(
    "Unknown Chemical Element",
    Category.SULFUR,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  SULFUR(
    "Sulfur",
    Category.SULFUR,
    cost = mapOf(Item.SULFUR to 10.q),
    requirement = ResearchCondition(UNKNOWN_CHEMICAL_ELEMENT),
  ),
  BLACK_POWDER(
    "Black Powder",
    Category.SULFUR,
    cost = mapOf(
      Item.SULFUR to 50.q,
      Item.COAL to 25.q,
    ),
    requirement = ResearchCondition(SULFUR),
  ),
  EXPERIMENTAL_POWER_GENERATION(
    "Experimental Power Generation",
    Category.SULFUR,
    cost = mapOf(
      Item.SULFUR to 25.q,
      Item.MODULAR_FRAME to 50.q,
      Item.ROTOR to 100.q,
    ),
    requirement = ResearchCondition(SULFUR),
  ),
  COMPACTED_COAL(
    "Compacted Coal",
    Category.SULFUR,
    cost = mapOf(
      Item.HARD_DRIVE to 1.q,
      Item.SULFUR to 25.q,
      Item.COAL to 25.q,
    ),
    requirement = ResearchCondition(EXPERIMENTAL_POWER_GENERATION),
  ),
  TURBOFUEL(
    "Turbofuel",
    Category.SULFUR,
    cost = mapOf(
      Item.HARD_DRIVE to 1.q,
      Item.COMPACTED_COAL to 15.q,
      Item.PACKAGED_FUEL to 50.q,
    ),
    requirement = ResearchCondition(EXPERIMENTAL_POWER_GENERATION),
  ),
  EXPANDED_TOOLBELT_SULFUR(
    "Expanded Toolbelt",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 100.q,
      Item.ENCASED_INDUSTRIAL_BEAM to 50.q,
    ),
    requirement = ResearchCondition(BLACK_POWDER),
  ),
  THE_NOBELISK_DETONATOR(
    "The Nobelisk Detonator",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 50.q,
      Item.STEEL_PIPE to 100.q,
      Item.CABLE to 200.q,
    ),
    requirement = ResearchCondition(BLACK_POWDER),
  ),
  SMOKELESS_POWDER(
    "Smokeless Powder",
    Category.SULFUR,
    cost = mapOf(
      Item.BLACK_POWDER to 100.q,
      Item.PLASTIC to 50.q,
    ),
    requirement = ResearchCondition(BLACK_POWDER),
  ),
  NUCLEAR_DETERRENT_DEVELOPMENT(
    "Nuclear Deterrent Development",
    Category.SULFUR,
    cost = mapOf(
      Item.NOBELISK to 500.q,
      Item.ENCASED_URANIUM_CELL to 10.q,
      Item.AI_LIMITER to 100.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),
  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 100.q,
      Item.NOBELISK to 200.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),
  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 200.q,
      Item.IRON_REBAR to 200.q,
      Item.STEEL_BEAM to 200.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),
  THE_RIFLE(
    "The Rifle",
    Category.SULFUR,
    cost = mapOf(
      Item.SMOKELESS_POWDER to 50.q,
      Item.MOTOR to 100.q,
      Item.RUBBER to 200.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),
  TURBO_RIFLE_AMMO(
    "Turbo Rifle Ammo",
    Category.SULFUR,
    cost = mapOf(
      Item.RIFLE_AMMO to 1_000.q,
      Item.PACKAGED_TURBOFUEL to 50.q,
      Item.ALUMINUM_CASING to 100.q,
    ),
    requirement = ResearchCondition(SMOKELESS_POWDER),
  ),
  INFLATED_POCKET_DIMENSION_SULFUR(
    "Inflated Pocket Dimension",
    Category.SULFUR,
    cost = mapOf(),
    requirement = any {
      +NUCLEAR_DETERRENT_DEVELOPMENT
      +CLUSTER_NOBELISK
      +THE_RIFLE
      +TURBO_RIFLE_AMMO
    },
  ),

  /* Hard Drive  */

  ADHERED_IRON_PLATE(
    "Adhered Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  ALCLAD_CASING(
    "Alclad Casing",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  AUTOMATED_MINER(
    "Automated Miner",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING),
  ),
  AUTOMATED_SPEED_WIRING(
    "Automated Speed Wiring",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.ADVANCED_STEEL_PRODUCTION
      +AI_LIMITER
    },
  ),
  BIOCOAL(
    "Biocoal",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.COAL_POWER
      +COMPACTED_COAL
    },
  ),
  BOLTED_FRAME(
    "Bolted Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  BOLTED_IRON_PLATE(
    "Bolted Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  CAST_SCREW(
    "Cast Screw",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  CATERIUM_CIRCUIT_BOARD(
    "Caterium Circuit Board",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.OIL_PROCESSING
      +CATERIUM_INGOTS
    },
  ),
  CATERIUM_COMPUTER(
    "Caterium Computer",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.INDUSTRIAL_MANUFACTURING
      +CATERIUM_INGOTS
    },
  ),
  CATERIUM_WIRE(
    "Caterium Wire",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = ResearchCondition(CATERIUM),
  ),
  CHARCOAL(
    "Charcoal",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.COAL_POWER
      +COMPACTED_COAL
    },
  ),
  CHEAP_SILICA(
    "Cheap Silica",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = ResearchCondition(SILICA),
  ),
  CLASSIC_BATTERY(
    "Classic Battery",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.AERONAUTICAL_ENGINEERING),
  ),
  COATED_CABLE(
    "Coated Cable",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  COATED_IRON_CANISTER(
    "Coated Iron Canister",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  COATED_IRON_PLATE(
    "Coated Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  COKE_STEEL_INGOT(
    "Coke Steel Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  COMPACTED_STEEL_INGOT(
    "Compacted Steel Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +COMPACTED_COAL
      +Milestone.BASIC_STEEL_PRODUCTION
    },
  ),
  COOLING_DEVICE(
    "Cooling Device",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION),
  ),
  COPPER_ALLOY_INGOT(
    "Copper Alloy Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  COPPER_ROTOR(
    "Copper Rotor",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.PART_ASSEMBLY),
  ),
  CRYSTAL_BEACON(
    "Crystal Beacon",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.BASIC_STEEL_PRODUCTION
      +QUARTZ_CRYSTALS
    },
  ),
  CRYSTAL_COMPUTER(
    "Crystal Computer",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.INDUSTRIAL_MANUFACTURING
      +QUARTZ_CRYSTALS
    },
  ),
  DILUTED_FUEL(
    "Diluted Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  DILUTED_PACKAGED_FUEL(
    "Diluted Packaged Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  ELECTRIC_MOTOR(
    "Electric Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.AERONAUTICAL_ENGINEERING),
  ),
  ELECTRODE_ALUMINUM_SCRAP(
    "Electrode - Aluminum Scrap",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  ELECTRODE_CIRCUIT_BOARD(
    "Electrode Circuit Board",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  ELECTROMAGNETIC_CONNECTION_ROD(
    "Electromagnetic Connection Rod",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.NUCLEAR_POWER
      +AI_LIMITER
    },
  ),
  ENCASED_INDUSTRIAL_PIPE(
    "Encased Industrial Pipe",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.ADVANCED_STEEL_PRODUCTION),
  ),
  FERTILE_URANIUM(
    "Fertile Uranium",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.PARTICLE_ENRICHMENT),
  ),
  FINE_BLACK_POWDER(
    "Fine Black Powder",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = ResearchCondition(COMPACTED_COAL),
  ),
  FINE_CONCRETE(
    "Fine Concrete",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = ResearchCondition(QUARTZ),
  ),
  FLEXIBLE_FRAMEWORK(
    "Flexible Framework",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  FUSED_QUICKWIRE(
    "Fused Quickwire",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = ResearchCondition(CATERIUM_INGOTS),
  ),
  FUSED_WIRE(
    "Fused Wire",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = ResearchCondition(CATERIUM_INGOTS),
  ),
  HEAT_EXCHANGER(
    "Heat Exchanger",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.ADVANCED_ALUMINUM_PRODUCTION),
  ),
  HEAT_FUSED_FRAME(
    "Heat-Fused Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.PARTICLE_ENRICHMENT),
  ),
  HEAVY_ENCASED_FRAME(
    "Heavy Encased Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING),
  ),
  HEAVY_FLEXIBLE_FRAME(
    "Heavy Flexible Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.INDUSTRIAL_MANUFACTURING),
  ),
  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  INFUSED_URANIUM_CELL(
    "Infused Uranium Cell",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.NUCLEAR_POWER
      +CATERIUM_INGOTS
      +QUARTZ
      +SULFUR
    },
  ),
  INSTANT_PLUTONIUM_CELL(
    "Instant Plutonium Cell",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.PARTICLE_ENRICHMENT),
  ),
  INSTANT_SCRAP(
    "Instant Scrap",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.BAUXITE_REFINEMENT
      +Milestone.AERONAUTICAL_ENGINEERING
    },
  ),
  INSULATED_CABLE(
    "Insulated Cable",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  INSULATED_CRYSTAL_OSCILLATOR(
    "Insulated Crystal Oscillator",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +CRYSTAL_OSCILLATOR
      +CATERIUM_ELECTRONICS
      +Milestone.OIL_PROCESSING
    },
  ),
  IRON_ALLOY_INGOT(
    "Iron Alloy Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  IRON_WIRE(
    "Iron Wire",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  OC_SUPERCOMPUTER(
    "OC Supercomputer",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.AERONAUTICAL_ENGINEERING
      +Milestone.ADVANCED_ALUMINUM_PRODUCTION
    },
  ),
  PLASTIC_SMART_PLATING(
    "Plastic Smart Plating",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  PLUTONIUM_FUEL_UNIT(
    "Plutonium Fuel Unit",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.PARTICLE_ENRICHMENT),
  ),
  POLYMER_RESIN(
    "Polymer Resin",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  PURE_ALUMINUM_INGOT(
    "Pure Aluminum Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  PURE_CATERIUM_INGOT(
    "Pure Caterium Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +CATERIUM
      +Milestone.COAL_POWER
    },
  ),
  PURE_COPPER_INGOT(
    "Pure Copper Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.COAL_POWER),
  ),
  PURE_IRON_INGOT(
    "Pure Iron Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.COAL_POWER),
  ),
  PURE_QUARTZ_CRYSTAL(
    "Pure Quartz Crystal",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +QUARTZ_CRYSTALS
      +Milestone.COAL_POWER
    },
  ),
  QUICKWIRE_CABLE(
    "Quickwire Cable",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.OIL_PROCESSING
      +CATERIUM_INGOTS
    },
  ),
  QUICKWIRE_STATOR(
    "Quickwire Stator",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.ADVANCED_STEEL_PRODUCTION
      +Research.CATERIUM_INGOTS
    },
  ),
  RADIO_CONNECTION_UNIT(
    "Radio Connection Unit",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.ADVANCED_ALUMINUM_PRODUCTION
      +AI_LIMITER
    },
  ),
  RADIO_CONTROL_SYSTEM(
    "Radio Control System",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  RECYCLED_PLASTIC(
    "Recycled Plastic",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  RECYCLED_RUBBER(
    "Recycled Rubber",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  RIGOUR_MOTOR(
    "Rigour Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.ADVANCED_STEEL_PRODUCTION
      +QUARTZ_CRYSTALS
    },
  ),
  RUBBER_CONCRETE(
    "Rubber Concrete",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  SILICON_CIRCUIT_BOARD(
    "Silicon Circuit Board",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.OIL_PROCESSING
      +QUARTZ
    },
  ),
  SILICON_HIGH_SPEED_CONNECTOR(
    "Silicon High-Speed Connector",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +HIGH_SPEED_CONNECTOR
      +Milestone.OIL_PROCESSING
      +QUARTZ
    },
  ),
  SLOPPY_ALUMINA(
    "Sloppy Alumina",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BAUXITE_REFINEMENT),
  ),
  SOLID_STEEL_INGOT(
    "Solid Steel Ingot",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),
  STEAMED_COPPER_SHEET(
    "Steamed Copper Sheet",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.COAL_POWER),
  ),
  STEEL_CANISTER(
    "Steel Canister",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
  ),
  STEEL_COATED_PLATE(
    "Steel Coated Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.OIL_PROCESSING),
  ),
  STEEL_ROD(
    "Steel Rod",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),
  STEEL_ROTOR(
    "Steel Rotor",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),
  STEEL_SCREW(
    "Steel Screw",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),
  STEELED_FRAME(
    "Steeled Frame",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.BASIC_STEEL_PRODUCTION),
  ),
  STITCHED_IRON_PLATE(
    "Stitched Iron Plate",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = Condition.TRUE,
  ),
  SUPER_STATE_COMPUTER(
    "Super-State Computer",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.NUCLEAR_POWER
      +Milestone.AERONAUTICAL_ENGINEERING
    },
  ),
  TURBO_BLEND_FUEL(
    "Turbo Blend Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.BAUXITE_REFINEMENT
      +SULFUR
    },
  ),
  TURBO_ELECTRIC_MOTOR(
    "Turbo Electric Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.LEADING_EDGE_PRODUCTION
      +Milestone.NUCLEAR_POWER
    },
  ),
  TURBO_HEAVY_FUEL(
    "Turbo Heavy Fuel",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.OIL_PROCESSING
      +COMPACTED_COAL
    },
  ),
  TURBO_PRESSURE_MOTOR(
    "Turbo Pressure Motor",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.PARTICLE_ENRICHMENT),
  ),
  URANIUM_FUEL_UNIT(
    "Uranium Fuel Unit",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = any {
      +Milestone.NUCLEAR_POWER
      +QUARTZ_CRYSTALS
    },
  ),
  WET_CONCRETE(
    "Wet Concrete",
    Category.HARD_DRIVE,
    cost = mapOf(),
    requirement = MilestoneCondition(Milestone.COAL_POWER),
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
