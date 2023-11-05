package app.game.data

import app.game.logic.Condition
import app.game.logic.Condition.ItemCondition
import app.game.logic.Condition.ResearchCondition
import util.math.Rational
import util.math.q

enum class Recipe(
  val displayName: String,
  val time: Rational,
  val inputs: Map<Item, Rational>,
  val outputs: Map<Item, Rational>,
  val power: ClosedRange<Rational>? = null,
  val unlock: Condition,
  val alternate: Boolean = false,
) {
  IRON_INGOT(
    "Iron Ingot",
    time = 2.q,
    inputs = mapOf(Item.IRON_ORE to 1.q),
    outputs = mapOf(Item.IRON_INGOT to 1.q),
    unlock = ItemCondition(Item.IRON_INGOT),
  ),
  IRON_ALLOY_INGOT(
    "Iron Alloy Ingot",
    time = 6.q,
    inputs = mapOf(
      Item.IRON_ORE to 2.q,
      Item.COPPER_ORE to 2.q,
    ),
    outputs = mapOf(Item.IRON_INGOT to 5.q),
    unlock = ResearchCondition(Research.IRON_ALLOY_INGOT),
    alternate = true,
  ),
  PURE_IRON_INGOT(
    "Pure Iron Ingot",
    time = 12.q,
    inputs = mapOf(
      Item.IRON_ORE to 7.q,
      Item.WATER to 4.q,
    ),
    outputs = mapOf(Item.IRON_INGOT to 13.q),
    unlock = ResearchCondition(Research.PURE_IRON_INGOT),
    alternate = true,
  ),

  IRON_PLATE(
    "Iron Plate",
    time = 6.q,
    inputs = mapOf(Item.IRON_INGOT to 3.q),
    outputs = mapOf(Item.IRON_PLATE to 2.q),
    unlock = ItemCondition(Item.IRON_PLATE),
  ),
  COATED_IRON_PLATE(
    "Coated Iron Plate",
    time = 12.q,
    inputs = mapOf(
      Item.IRON_INGOT to 10.q,
      Item.PLASTIC to 2.q,
    ),
    outputs = mapOf(Item.IRON_PLATE to 15.q),
    unlock = ResearchCondition(Research.COATED_IRON_PLATE),
    alternate = true,
  ),
  STEEL_COATED_PLATE(
    "Steel Coated Plate",
    time = 24.q,
    inputs = mapOf(
      Item.STEEL_INGOT to 3.q,
      Item.PLASTIC to 2.q,
    ),
    outputs = mapOf(Item.IRON_PLATE to 18.q),
    unlock = ResearchCondition(Research.STEEL_COATED_PLATE),
    alternate = true,
  ),

  IRON_ROD(
    "Iron Rod",
    time = 4.q,
    inputs = mapOf(Item.IRON_INGOT to 1.q),
    outputs = mapOf(Item.IRON_ROD to 1.q),
    unlock = ItemCondition(Item.IRON_ROD),
  ),
  STEEL_ROD(
    "Steel Rod",
    time = 5.q,
    inputs = mapOf(Item.STEEL_INGOT to 1.q),
    outputs = mapOf(Item.IRON_ROD to 4.q),
    unlock = ResearchCondition(Research.STEEL_ROD),
    alternate = true,
  ),

  PORTABLE_MINER(
    "Portable Miner",
    time = 40.q,
    inputs = mapOf(
      Item.IRON_PLATE to 2.q,
      Item.IRON_ROD to 4.q,
    ),
    outputs = mapOf(Item.PORTABLE_MINER to 1.q),
    unlock = ItemCondition(Item.PORTABLE_MINER),
  ),
  AUTOMATED_MINER(
    "Automated Miner",
    time = 60.q,
    inputs = mapOf(
      Item.MOTOR to 1.q,
      Item.STEEL_PIPE to 4.q,
      Item.IRON_ROD to 4.q,
      Item.IRON_PLATE to 2.q,
    ),
    outputs = mapOf(Item.PORTABLE_MINER to 1.q),
    unlock = ResearchCondition(Research.AUTOMATED_MINER),
    alternate = true,
  ),

  COPPER_INGOT(
    "Copper Ingot",
    time = 2.q,
    inputs = mapOf(Item.COPPER_ORE to 1.q),
    outputs = mapOf(Item.COPPER_INGOT to 1.q),
    unlock = ItemCondition(Item.COPPER_INGOT),
  ),
  COPPER_ALLOY_INGOT(
    "Copper Alloy Ingot",
    time = 12.q,
    inputs = mapOf(
      Item.COPPER_ORE to 10.q,
      Item.IRON_ORE to 5.q,
    ),
    outputs = mapOf(Item.COPPER_INGOT to 20.q),
    unlock = ResearchCondition(Research.COPPER_ALLOY_INGOT),
    alternate = true,
  ),
  PURE_COPPER_INGOT(
    "Pure Copper Ingot",
    time = 24.q,
    inputs = mapOf(
      Item.COPPER_ORE to 6.q,
      Item.WATER to 4.q,
    ),
    outputs = mapOf(Item.COPPER_INGOT to 15.q),
    unlock = ResearchCondition(Research.PURE_COPPER_INGOT),
    alternate = true,
  ),

  WIRE(
    "Wire",
    time = 4.q,
    inputs = mapOf(Item.COPPER_INGOT to 1.q),
    outputs = mapOf(Item.WIRE to 2.q),
    unlock = ItemCondition(Item.WIRE),
  ),
  CATERIUM_WIRE(
    "Caterium Wire",
    time = 4.q,
    inputs = mapOf(Item.CATERIUM_INGOT to 1.q),
    outputs = mapOf(Item.WIRE to 8.q),
    unlock = ResearchCondition(Research.CATERIUM_WIRE),
    alternate = true,
  ),
  FUSED_WIRE(
    "Fused Wire",
    time = 20.q,
    inputs = mapOf(
      Item.COPPER_INGOT to 4.q,
      Item.CATERIUM_INGOT to 1.q,
    ),
    outputs = mapOf(Item.WIRE to 30.q),
    unlock = ResearchCondition(Research.FUSED_WIRE),
    alternate = true,
  ),
  IRON_WIRE(
    "Iron Wire",
    time = 24.q,
    inputs = mapOf(Item.IRON_INGOT to 5.q),
    outputs = mapOf(Item.WIRE to 9.q),
    unlock = ResearchCondition(Research.IRON_WIRE),
    alternate = true,
  ),

  CABLE(
    "Cable",
    time = 2.q,
    inputs = mapOf(Item.WIRE to 2.q),
    outputs = mapOf(Item.CABLE to 1.q),
    unlock = ItemCondition(Item.CABLE),
  ),
  COATED_CABLE(
    "Coated Cable",
    time = 8.q,
    inputs = mapOf(
      Item.WIRE to 5.q,
      Item.HEAVY_OIL_RESIDUE to 2.q,
    ),
    outputs = mapOf(Item.CABLE to 9.q),
    unlock = ResearchCondition(Research.COATED_CABLE),
    alternate = true,
  ),
  INSULATED_CABLE(
    "Insulated Cable",
    time = 12.q,
    inputs = mapOf(
      Item.WIRE to 9.q,
      Item.RUBBER to 6.q,
    ),
    outputs = mapOf(Item.CABLE to 20.q),
    unlock = ResearchCondition(Research.INSULATED_CABLE),
    alternate = true,
  ),
  QUICKWIRE_CABLE(
    "Quickwire Cable",
    time = 24.q,
    inputs = mapOf(
      Item.QUICKWIRE to 3.q,
      Item.RUBBER to 2.q,
    ),
    outputs = mapOf(Item.CABLE to 11.q),
    unlock = ResearchCondition(Research.QUICKWIRE_CABLE),
    alternate = true,
  ),

  CONCRETE(
    "Concrete",
    time = 4.q,
    inputs = mapOf(Item.LIMESTONE to 3.q),
    outputs = mapOf(Item.CONCRETE to 1.q),
    unlock = ItemCondition(Item.CONCRETE),
  ),
  FINE_CONCRETE(
    "Fine Concrete",
    time = 24.q,
    inputs = mapOf(
      Item.SILICA to 3.q,
      Item.LIMESTONE to 12.q,
    ),
    outputs = mapOf(Item.CONCRETE to 10.q),
    unlock = ResearchCondition(Research.FINE_CONCRETE),
    alternate = true,
  ),
  RUBBER_CONCRETE(
    "Rubber Concrete",
    time = 12.q,
    inputs = mapOf(
      Item.LIMESTONE to 10.q,
      Item.RUBBER to 2.q,
    ),
    outputs = mapOf(Item.CONCRETE to 9.q),
    unlock = ResearchCondition(Research.RUBBER_CONCRETE),
    alternate = true,
  ),
  WET_CONCRETE(
    "Wet Concrete",
    time = 3.q,
    inputs = mapOf(
      Item.LIMESTONE to 6.q,
      Item.WATER to 5.q,
    ),
    outputs = mapOf(Item.CONCRETE to 4.q),
    unlock = ResearchCondition(Research.WET_CONCRETE),
    alternate = true,
  ),

  SCREW(
    "Screw",
    time = 6.q,
    inputs = mapOf(Item.IRON_ROD to 1.q),
    outputs = mapOf(Item.SCREW to 4.q),
    unlock = ItemCondition(Item.SCREW),
  ),
  CAST_SCREW(
    "Cast Screw",
    time = 24.q,
    inputs = mapOf(Item.IRON_INGOT to 5.q),
    outputs = mapOf(Item.SCREW to 20.q),
    unlock = ResearchCondition(Research.CAST_SCREW),
    alternate = true,
  ),
  STEEL_SCREW(
    "Steel Screw",
    time = 12.q,
    inputs = mapOf(Item.STEEL_BEAM to 1.q),
    outputs = mapOf(Item.SCREW to 52.q),
    unlock = ResearchCondition(Research.STEEL_SCREW),
    alternate = true,
  ),

  REINFORCED_IRON_PLATE(
    "Reinforced Iron Plate",
    time = 12.q,
    inputs = mapOf(
      Item.IRON_PLATE to 6.q,
      Item.SCREW to 12.q,
    ),
    outputs = mapOf(Item.REINFORCED_IRON_PLATE to 1.q),
    unlock = ItemCondition(Item.REINFORCED_IRON_PLATE),
  ),
  ADHERED_IRON_PLATE(
    "Adhered Iron Plate",
    time = 16.q,
    inputs = mapOf(
      Item.IRON_PLATE to 3.q,
      Item.RUBBER to 1.q,
    ),
    outputs = mapOf(Item.REINFORCED_IRON_PLATE to 1.q),
    unlock = ResearchCondition(Research.ADHERED_IRON_PLATE),
    alternate = true,
  ),
  BOLTED_IRON_PLATE(
    "Bolted Iron Plate",
    time = 12.q,
    inputs = mapOf(
      Item.IRON_PLATE to 18.q,
      Item.SCREW to 50.q,
    ),
    outputs = mapOf(Item.REINFORCED_IRON_PLATE to 3.q),
    unlock = ResearchCondition(Research.BOLTED_IRON_PLATE),
    alternate = true,
  ),
  STITCHED_IRON_PLATE(
    "Stitched Iron Plate",
    time = 32.q,
    inputs = mapOf(
      Item.IRON_PLATE to 10.q,
      Item.WIRE to 20.q,
    ),
    outputs = mapOf(Item.REINFORCED_IRON_PLATE to 3.q),
    unlock = ResearchCondition(Research.STITCHED_IRON_PLATE),
    alternate = true,
  ),

  BIOMASS_ALIEN_PROTEIN(
    "Biomass (Alien Protein)",
    time = 4.q,
    inputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    outputs = mapOf(Item.BIOMASS to 100.q),
    unlock = ItemCondition(Item.BIOMASS),
  ),
  BIOMASS_LEAVES(
    "Biomass (Leaves)",
    time = 5.q,
    inputs = mapOf(Item.LEAVES to 10.q),
    outputs = mapOf(Item.BIOMASS to 5.q),
    unlock = ItemCondition(Item.BIOMASS),
  ),
  BIOMASS_MYCELIA(
    "Biomass (Mycelia)",
    time = 4.q,
    inputs = mapOf(Item.MYCELIA to 1.q),
    outputs = mapOf(Item.BIOMASS to 10.q),
    unlock = ItemCondition(Item.BIOMASS),
  ),
  BIOMASS_WOOD(
    "Biomass (Wood)",
    time = 4.q,
    inputs = mapOf(Item.WOOD to 4.q),
    outputs = mapOf(Item.BIOMASS to 20.q),
    unlock = ItemCondition(Item.BIOMASS),
  ),

  OBJECT_SCANNER(
    "Object Scanner",
    time = 40.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 4.q,
      Item.WIRE to 20.q,
      Item.SCREW to 50.q,
    ),
    outputs = mapOf(Item.OBJECT_SCANNER to 1.q),
    unlock = ItemCondition(Item.OBJECT_SCANNER),
  ),

  BEACON(
    "Beacon",
    time = 8.q,
    inputs = mapOf(
      Item.IRON_PLATE to 3.q,
      Item.IRON_ROD to 1.q,
      Item.WIRE to 15.q,
      Item.CABLE to 2.q,
    ),
    outputs = mapOf(Item.BEACON to 1.q),
    unlock = ItemCondition(Item.BEACON),
  ),
  CRYSTAL_BEACON(
    "Crystal Beacon",
    time = 120.q,
    inputs = mapOf(
      Item.STEEL_BEAM to 4.q,
      Item.STEEL_PIPE to 16.q,
      Item.CRYSTAL_OSCILLATOR to 1.q,
    ),
    outputs = mapOf(Item.BEACON to 20.q),
    unlock = ResearchCondition(Research.CRYSTAL_BEACON),
    alternate = true,
  ),

  COPPER_SHEET(
    "Copper Sheet",
    time = 6.q,
    inputs = mapOf(Item.COPPER_INGOT to 2.q),
    outputs = mapOf(Item.COPPER_SHEET to 1.q),
    unlock = ItemCondition(Item.COPPER_SHEET),
  ),
  STEAMED_COPPER_SHEET(
    "Steamed Copper Sheet",
    time = 8.q,
    inputs = mapOf(
      Item.COPPER_INGOT to 3.q,
      Item.WATER to 3.q,
    ),
    outputs = mapOf(Item.COPPER_SHEET to 3.q),
    unlock = ResearchCondition(Research.STEAMED_COPPER_SHEET),
    alternate = true,
  ),

  ROTOR(
    "Rotor",
    time = 15.q,
    inputs = mapOf(
      Item.IRON_ROD to 5.q,
      Item.SCREW to 25.q,
    ),
    outputs = mapOf(Item.ROTOR to 1.q),
    unlock = ItemCondition(Item.ROTOR),
  ),
  COPPER_ROTOR(
    "Copper Rotor",
    time = 16.q,
    inputs = mapOf(
      Item.COPPER_SHEET to 6.q,
      Item.SCREW to 52.q,
    ),
    outputs = mapOf(Item.ROTOR to 3.q),
    unlock = ResearchCondition(Research.COPPER_ROTOR),
    alternate = true,
  ),
  STEEL_ROTOR(
    "Steel Rotor",
    time = 12.q,
    inputs = mapOf(
      Item.STEEL_PIPE to 2.q,
      Item.WIRE to 6.q,
    ),
    outputs = mapOf(Item.ROTOR to 1.q),
    unlock = ResearchCondition(Research.STEEL_ROTOR),
    alternate = true,
  ),

  MODULAR_FRAME(
    "Modular Frame",
    time = 60.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 3.q,
      Item.IRON_ROD to 12.q,
    ),
    outputs = mapOf(Item.MODULAR_FRAME to 2.q),
    unlock = ItemCondition(Item.MODULAR_FRAME),
  ),
  BOLTED_FRAME(
    "Bolted Frame",
    time = 24.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 3.q,
      Item.SCREW to 56.q,
    ),
    outputs = mapOf(Item.MODULAR_FRAME to 2.q),
    unlock = ResearchCondition(Research.BOLTED_FRAME),
    alternate = true,
  ),
  STEELED_FRAME(
    "Steeled Frame",
    time = 60.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 2.q,
      Item.STEEL_PIPE to 10.q,
    ),
    outputs = mapOf(Item.MODULAR_FRAME to 3.q),
    unlock = ResearchCondition(Research.STEELED_FRAME),
    alternate = true,
  ),

  SMART_PLATING(
    "Smart Plating",
    time = 30.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 1.q,
      Item.ROTOR to 1.q,
    ),
    outputs = mapOf(Item.SMART_PLATING to 1.q),
    unlock = ItemCondition(Item.SMART_PLATING),
  ),
  PLASTIC_SMART_PLATING(
    "Plastic Smart Plating",
    time = 24.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 1.q,
      Item.ROTOR to 1.q,
      Item.PLASTIC to 3.q,
    ),
    outputs = mapOf(Item.SMART_PLATING to 2.q),
    unlock = ResearchCondition(Research.PLASTIC_SMART_PLATING),
    alternate = true,
  ),

  CHAINSAW(
    "Chainsaw",
    time = 60.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 5.q,
      Item.IRON_ROD to 25.q,
      Item.SCREW to 160.q,
      Item.CABLE to 15.q,
    ),
    outputs = mapOf(Item.CHAINSAW to 1.q),
    unlock = ItemCondition(Item.CHAINSAW),
  ),

  SOLID_BIOFUEL(
    "Solid Biofuel",
    time = 4.q,
    inputs = mapOf(Item.BIOMASS to 8.q),
    outputs = mapOf(Item.SOLID_BIOFUEL to 4.q),
    unlock = ItemCondition(Item.SOLID_BIOFUEL),
  ),

  COLOR_CARTRIDGE(
    "Color Cartridge",
    time = 6.q,
    inputs = mapOf(Item.FLOWER_PETALS to 5.q),
    outputs = mapOf(Item.COLOR_CARTRIDGE to 10.q),
    unlock = ItemCondition(Item.COLOR_CARTRIDGE),
  ),

  BIOCOAL(
    "Biocoal",
    time = 8.q,
    inputs = mapOf(Item.BIOMASS to 5.q),
    outputs = mapOf(Item.COAL to 6.q),
    unlock = ResearchCondition(Research.BIOCOAL),
    alternate = true,
  ),
  CHARCOAL(
    "Charcoal",
    time = 4.q,
    inputs = mapOf(Item.WOOD to 1.q),
    outputs = mapOf(Item.COAL to 10.q),
    unlock = ResearchCondition(Research.CHARCOAL),
    alternate = true,
  ),

  UNPACKAGE_WATER(
    "Unpackage Water",
    time = 1.q,
    inputs = mapOf(Item.PACKAGED_WATER to 2.q),
    outputs = mapOf(
      Item.WATER to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    unlock = ItemCondition(Item.WATER),
  ),

  STEEL_INGOT(
    "Steel Ingot",
    time = 4.q,
    inputs = mapOf(
      Item.IRON_ORE to 3.q,
      Item.COAL to 3.q,
    ),
    outputs = mapOf(Item.STEEL_INGOT to 3.q),
    unlock = ItemCondition(Item.STEEL_INGOT),
  ),
  COKE_STEEL_INGOT(
    "Coke Steel Ingot",
    time = 12.q,
    inputs = mapOf(
      Item.IRON_ORE to 15.q,
      Item.PETROLEUM_COKE to 15.q,
    ),
    outputs = mapOf(Item.STEEL_INGOT to 20.q),
    unlock = ResearchCondition(Research.COKE_STEEL_INGOT),
    alternate = true,
  ),
  COMPACTED_STEEL_INGOT(
    "Compacted Steel Ingot",
    time = 16.q,
    inputs = mapOf(
      Item.IRON_ORE to 6.q,
      Item.COMPACTED_COAL to 3.q,
    ),
    outputs = mapOf(Item.STEEL_INGOT to 10.q),
    unlock = ResearchCondition(Research.COMPACTED_STEEL_INGOT),
    alternate = true,
  ),
  SOLID_STEEL_INGOT(
    "Solid Steel Ingot",
    time = 3.q,
    inputs = mapOf(
      Item.IRON_INGOT to 2.q,
      Item.COAL to 2.q,
    ),
    outputs = mapOf(Item.STEEL_INGOT to 3.q),
    unlock = ResearchCondition(Research.SOLID_STEEL_INGOT),
    alternate = true,
  ),

  STEEL_BEAM(
    "Steel Beam",
    time = 4.q,
    inputs = mapOf(Item.STEEL_INGOT to 4.q),
    outputs = mapOf(Item.STEEL_BEAM to 1.q),
    unlock = ItemCondition(Item.STEEL_BEAM),
  ),

  STEEL_PIPE(
    "Steel Pipe",
    time = 6.q,
    inputs = mapOf(Item.STEEL_INGOT to 3.q),
    outputs = mapOf(Item.STEEL_PIPE to 2.q),
    unlock = ItemCondition(Item.STEEL_PIPE),
  ),

  VERSATILE_FRAMEWORK(
    "Versatile Framework",
    time = 24.q,
    inputs = mapOf(
      Item.MODULAR_FRAME to 1.q,
      Item.STEEL_BEAM to 12.q,
    ),
    outputs = mapOf(Item.VERSATILE_FRAMEWORK to 2.q),
    unlock = ItemCondition(Item.VERSATILE_FRAMEWORK),
  ),
  FLEXIBLE_FRAMEWORK(
    "Flexible Framework",
    time = 16.q,
    inputs = mapOf(
      Item.MODULAR_FRAME to 1.q,
      Item.STEEL_BEAM to 6.q,
      Item.RUBBER to 8.q,
    ),
    outputs = mapOf(Item.VERSATILE_FRAMEWORK to 2.q),
    unlock = ResearchCondition(Research.FLEXIBLE_FRAMEWORK),
    alternate = true,
  ),

  ENCASED_INDUSTRIAL_BEAM(
    "Encased Industrial Beam",
    time = 10.q,
    inputs = mapOf(
      Item.STEEL_BEAM to 4.q,
      Item.CONCRETE to 5.q,
    ),
    outputs = mapOf(Item.ENCASED_INDUSTRIAL_BEAM to 1.q),
    unlock = ItemCondition(Item.ENCASED_INDUSTRIAL_BEAM),
  ),
  ENCASED_INDUSTRIAL_PIPE(
    "Encased Industrial Pipe",
    time = 15.q,
    inputs = mapOf(
      Item.STEEL_PIPE to 7.q,
      Item.CONCRETE to 5.q,
    ),
    outputs = mapOf(Item.ENCASED_INDUSTRIAL_BEAM to 1.q),
    unlock = ResearchCondition(Research.ENCASED_INDUSTRIAL_PIPE),
    alternate = true,
  ),

  STATOR(
    "Stator",
    time = 12.q,
    inputs = mapOf(
      Item.STEEL_PIPE to 3.q,
      Item.WIRE to 8.q,
    ),
    outputs = mapOf(Item.STATOR to 1.q),
    unlock = ItemCondition(Item.STATOR),
  ),
  QUICKWIRE_STATOR(
    "Quickwire Stator",
    time = 15.q,
    inputs = mapOf(
      Item.STEEL_PIPE to 4.q,
      Item.QUICKWIRE to 15.q,
    ),
    outputs = mapOf(Item.STATOR to 2.q),
    unlock = ResearchCondition(Research.QUICKWIRE_STATOR),
    alternate = true,
  ),

  MOTOR(
    "Motor",
    time = 12.q,
    inputs = mapOf(
      Item.ROTOR to 2.q,
      Item.STATOR to 2.q,
    ),
    outputs = mapOf(Item.MOTOR to 1.q),
    unlock = ItemCondition(Item.MOTOR),
  ),
  ELECTRIC_MOTOR(
    "Electric Motor",
    time = 16.q,
    inputs = mapOf(
      Item.ELECTROMAGNETIC_CONTROL_ROD to 1.q,
      Item.ROTOR to 2.q,
    ),
    outputs = mapOf(Item.MOTOR to 2.q),
    unlock = ResearchCondition(Research.ELECTRIC_MOTOR),
    alternate = true,
  ),
  RIGOUR_MOTOR(
    "Rigour Motor",
    time = 48.q,
    inputs = mapOf(
      Item.ROTOR to 3.q,
      Item.STATOR to 3.q,
      Item.CRYSTAL_OSCILLATOR to 1.q,
    ),
    outputs = mapOf(Item.MOTOR to 6.q),
    unlock = ResearchCondition(Research.RIGOUR_MOTOR),
    alternate = true,
  ),

  AUTOMATED_WIRING(
    "Automated Wiring",
    time = 24.q,
    inputs = mapOf(
      Item.STATOR to 1.q,
      Item.CABLE to 20.q,
    ),
    outputs = mapOf(Item.AUTOMATED_WIRING to 1.q),
    unlock = ItemCondition(Item.AUTOMATED_WIRING),
  ),
  AUTOMATED_SPEED_WIRING(
    "Automated Speed Wiring",
    time = 32.q,
    inputs = mapOf(
      Item.STATOR to 2.q,
      Item.WIRE to 40.q,
      Item.HIGH_SPEED_CONNECTOR to 1.q,
    ),
    outputs = mapOf(Item.AUTOMATED_WIRING to 4.q),
    unlock = ResearchCondition(Research.AUTOMATED_SPEED_WIRING),
    alternate = true,
  ),

  HEAVY_MODULAR_FRAME(
    "Heavy Modular Frame",
    time = 30.q,
    inputs = mapOf(
      Item.MODULAR_FRAME to 5.q,
      Item.STEEL_PIPE to 15.q,
      Item.ENCASED_INDUSTRIAL_BEAM to 5.q,
      Item.SCREW to 100.q,
    ),
    outputs = mapOf(Item.HEAVY_MODULAR_FRAME to 1.q),
    unlock = ItemCondition(Item.HEAVY_MODULAR_FRAME),
  ),
  HEAVY_ENCASED_FRAME(
    "Heavy Encased Frame",
    time = 64.q,
    inputs = mapOf(
      Item.MODULAR_FRAME to 8.q,
      Item.ENCASED_INDUSTRIAL_BEAM to 10.q,
      Item.STEEL_PIPE to 36.q,
      Item.CONCRETE to 22.q,
    ),
    outputs = mapOf(Item.HEAVY_MODULAR_FRAME to 3.q),
    unlock = ResearchCondition(Research.HEAVY_ENCASED_FRAME),
    alternate = true,
  ),
  HEAVY_FLEXIBLE_FRAME(
    "Heavy Flexible Frame",
    time = 16.q,
    inputs = mapOf(
      Item.MODULAR_FRAME to 5.q,
      Item.ENCASED_INDUSTRIAL_BEAM to 3.q,
      Item.RUBBER to 20.q,
      Item.SCREW to 104.q,
    ),
    outputs = mapOf(Item.HEAVY_MODULAR_FRAME to 1.q),
    unlock = ResearchCondition(Research.HEAVY_FLEXIBLE_FRAME),
    alternate = true,
  ),

  UNPACKAGE_OIL(
    "Unpackage Oil",
    time = 2.q,
    inputs = mapOf(Item.PACKAGED_OIL to 2.q),
    outputs = mapOf(
      Item.CRUDE_OIL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    unlock = ItemCondition(Item.CRUDE_OIL),
  ),

  PLASTIC(
    "Plastic",
    time = 6.q,
    inputs = mapOf(Item.CRUDE_OIL to 3.q),
    outputs = mapOf(
      Item.PLASTIC to 2.q,
      Item.HEAVY_OIL_RESIDUE to 1.q,
    ),
    unlock = ItemCondition(Item.PLASTIC),
  ),
  RESIDUAL_PLASTIC(
    "Residual Plastic",
    time = 6.q,
    inputs = mapOf(
      Item.POLYMER_RESIN to 6.q,
      Item.WATER to 2.q,
    ),
    outputs = mapOf(Item.PLASTIC to 2.q),
    unlock = ItemCondition(Item.PLASTIC),
  ),
  RECYCLED_PLASTIC(
    "Recycled Plastic",
    time = 12.q,
    inputs = mapOf(
      Item.RUBBER to 6.q,
      Item.FUEL to 6.q,
    ),
    outputs = mapOf(Item.PLASTIC to 12.q),
    unlock = ResearchCondition(Research.RECYCLED_PLASTIC),
    alternate = true,
  ),

  UNPACKAGE_HEAVY_OIL_RESIDUE(
    "Unpackage Heavy Oil Residue",
    time = 6.q,
    inputs = mapOf(Item.PACKAGED_HEAVY_OIL_RESIDUE to 2.q),
    outputs = mapOf(
      Item.HEAVY_OIL_RESIDUE to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    unlock = ItemCondition(Item.HEAVY_OIL_RESIDUE),
  ),
  HEAVY_OIL_RESIDUE(
    "Heavy Oil Residue",
    time = 6.q,
    inputs = mapOf(Item.CRUDE_OIL to 3.q),
    outputs = mapOf(
      Item.HEAVY_OIL_RESIDUE to 4.q,
      Item.POLYMER_RESIN to 2.q,
    ),
    unlock = ResearchCondition(Research.HEAVY_OIL_RESIDUE),
    alternate = true,
  ),

  RESIDUAL_RUBBER(
    "Residual Rubber",
    time = 6.q,
    inputs = mapOf(
      Item.POLYMER_RESIN to 4.q,
      Item.WATER to 4.q,
    ),
    outputs = mapOf(Item.RUBBER to 2.q),
    unlock = ItemCondition(Item.RUBBER),
  ),
  RUBBER(
    "Rubber",
    time = 6.q,
    inputs = mapOf(Item.CRUDE_OIL to 3.q),
    outputs = mapOf(
      Item.RUBBER to 2.q,
      Item.HEAVY_OIL_RESIDUE to 2.q,
    ),
    unlock = ItemCondition(Item.RUBBER),
  ),
  RECYCLED_RUBBER(
    "Recycled Rubber",
    time = 12.q,
    inputs = mapOf(
      Item.PLASTIC to 6.q,
      Item.FUEL to 6.q,
    ),
    outputs = mapOf(Item.RUBBER to 12.q),
    unlock = ResearchCondition(Research.RECYCLED_RUBBER),
    alternate = true,
  ),

  FUEL(
    "Fuel",
    time = 6.q,
    inputs = mapOf(Item.CRUDE_OIL to 6.q),
    outputs = mapOf(
      Item.FUEL to 4.q,
      Item.POLYMER_RESIN to 3.q,
    ),
    unlock = ItemCondition(Item.FUEL),
  ),
  RESIDUAL_FUEL(
    "Residual Fuel",
    time = 6.q,
    inputs = mapOf(Item.HEAVY_OIL_RESIDUE to 6.q),
    outputs = mapOf(Item.FUEL to 4.q),
    unlock = ItemCondition(Item.FUEL),
  ),
  UNPACKAGE_FUEL(
    "Unpackage Fuel",
    time = 2.q,
    inputs = mapOf(Item.PACKAGED_FUEL to 2.q),
    outputs = mapOf(
      Item.FUEL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    unlock = ItemCondition(Item.FUEL),
  ),
  DILUTED_FUEL(
    "Diluted Fuel",
    time = 6.q,
    inputs = mapOf(
      Item.HEAVY_OIL_RESIDUE to 5.q,
      Item.WATER to 10.q,
    ),
    outputs = mapOf(Item.FUEL to 10.q),
    unlock = ResearchCondition(Research.DILUTED_FUEL),
    alternate = true,
  ),

  POLYMER_RESIN(
    "Polymer Resin",
    time = 6.q,
    inputs = mapOf(Item.CRUDE_OIL to 6.q),
    outputs = mapOf(
      Item.POLYMER_RESIN to 13.q,
      Item.HEAVY_OIL_RESIDUE to 2.q,
    ),
    unlock = ResearchCondition(Research.POLYMER_RESIN),
    alternate = true,
  ),

  PETROLEUM_COKE(
    "Petroleum Coke",
    time = 6.q,
    inputs = mapOf(Item.HEAVY_OIL_RESIDUE to 4.q),
    outputs = mapOf(Item.PETROLEUM_COKE to 12.q),
    unlock = ItemCondition(Item.PETROLEUM_COKE),
  ),

  CIRCUIT_BOARD(
    "Circuit Board",
    time = 8.q,
    inputs = mapOf(
      Item.COPPER_SHEET to 2.q,
      Item.PLASTIC to 4.q,
    ),
    outputs = mapOf(Item.CIRCUIT_BOARD to 1.q),
    unlock = ItemCondition(Item.CIRCUIT_BOARD),
  ),
  CATERIUM_CIRCUIT_BOARD(
    "Caterium Circuit Board",
    time = 48.q,
    inputs = mapOf(
      Item.PLASTIC to 10.q,
      Item.QUICKWIRE to 30.q,
    ),
    outputs = mapOf(Item.CIRCUIT_BOARD to 7.q),
    unlock = ResearchCondition(Research.CATERIUM_CIRCUIT_BOARD),
    alternate = true,
  ),
  ELECTRODE_CIRCUIT_BOARD(
    "Electrode Circuit Board",
    time = 12.q,
    inputs = mapOf(
      Item.RUBBER to 6.q,
      Item.PETROLEUM_COKE to 9.q,
    ),
    outputs = mapOf(Item.CIRCUIT_BOARD to 1.q),
    unlock = ResearchCondition(Research.ELECTRODE_CIRCUIT_BOARD),
    alternate = true,
  ),
  SILICON_CIRCUIT_BOARD(
    "Silicon Circuit Board",
    time = 24.q,
    inputs = mapOf(
      Item.COPPER_SHEET to 11.q,
      Item.SILICA to 11.q,
    ),
    outputs = mapOf(Item.CIRCUIT_BOARD to 5.q),
    unlock = ResearchCondition(Research.SILICON_CIRCUIT_BOARD),
    alternate = true,
  ),

  COMPUTER(
    "Computer",
    time = 24.q,
    inputs = mapOf(
      Item.CIRCUIT_BOARD to 10.q,
      Item.CABLE to 9.q,
      Item.PLASTIC to 18.q,
      Item.SCREW to 52.q,
    ),
    outputs = mapOf(Item.COMPUTER to 1.q),
    unlock = ItemCondition(Item.COMPUTER),
  ),
  CATERIUM_COMPUTER(
    "Caterium Computer",
    time = 16.q,
    inputs = mapOf(
      Item.CIRCUIT_BOARD to 7.q,
      Item.QUICKWIRE to 28.q,
      Item.RUBBER to 12.q,
    ),
    outputs = mapOf(Item.COMPUTER to 1.q),
    unlock = ResearchCondition(Research.CATERIUM_COMPUTER),
    alternate = true,
  ),
  CRYSTAL_COMPUTER(
    "Crystal Computer",
    time = 64.q,
    inputs = mapOf(
      Item.CIRCUIT_BOARD to 8.q,
      Item.CRYSTAL_OSCILLATOR to 3.q,
    ),
    outputs = mapOf(Item.COMPUTER to 3.q),
    unlock = ResearchCondition(Research.CRYSTAL_COMPUTER),
    alternate = true,
  ),

  MODULAR_ENGINE(
    "Modular Engine",
    time = 60.q,
    inputs = mapOf(
      Item.MOTOR to 2.q,
      Item.RUBBER to 15.q,
      Item.SMART_PLATING to 2.q,
    ),
    outputs = mapOf(Item.MODULAR_ENGINE to 1.q),
    unlock = ItemCondition(Item.MODULAR_ENGINE),
  ),

  ADAPTIVE_CONTROL_UNIT(
    "Adaptive Control Unit",
    time = 120.q,
    inputs = mapOf(
      Item.AUTOMATED_WIRING to 15.q,
      Item.CIRCUIT_BOARD to 10.q,
      Item.HEAVY_MODULAR_FRAME to 2.q,
      Item.COMPUTER to 2.q,
    ),
    outputs = mapOf(Item.ADAPTIVE_CONTROL_UNIT to 2.q),
    unlock = ItemCondition(Item.ADAPTIVE_CONTROL_UNIT),
  ),

  EMPTY_CANISTER(
    "Empty Canister",
    time = 4.q,
    inputs = mapOf(Item.PLASTIC to 2.q),
    outputs = mapOf(Item.EMPTY_CANISTER to 4.q),
    unlock = ItemCondition(Item.EMPTY_CANISTER),
  ),
  COATED_IRON_CANISTER(
    "Coated Iron Canister",
    time = 4.q,
    inputs = mapOf(
      Item.IRON_PLATE to 2.q,
      Item.COPPER_SHEET to 1.q,
    ),
    outputs = mapOf(Item.EMPTY_CANISTER to 4.q),
    unlock = ResearchCondition(Research.COATED_IRON_CANISTER),
    alternate = true,
  ),
  STEEL_CANISTER(
    "Steel Canister",
    time = 3.q,
    inputs = mapOf(Item.STEEL_INGOT to 3.q),
    outputs = mapOf(Item.EMPTY_CANISTER to 2.q),
    unlock = ResearchCondition(Research.STEEL_CANISTER),
    alternate = true,
  ),

  PACKAGED_WATER(
    "Packaged Water",
    time = 2.q,
    inputs = mapOf(
      Item.WATER to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_WATER to 2.q),
    unlock = ItemCondition(Item.PACKAGED_WATER),
  ),

  PACKAGED_OIL(
    "Packaged Oil",
    time = 4.q,
    inputs = mapOf(
      Item.CRUDE_OIL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_OIL to 2.q),
    unlock = ItemCondition(Item.PACKAGED_OIL),
  ),

  PACKAGED_FUEL(
    "Packaged Fuel",
    time = 3.q,
    inputs = mapOf(
      Item.FUEL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_FUEL to 2.q),
    unlock = ItemCondition(Item.PACKAGED_FUEL),
  ),
  DILUTED_PACKAGED_FUEL(
    "Diluted Packaged Fuel",
    time = 2.q,
    inputs = mapOf(
      Item.HEAVY_OIL_RESIDUE to 1.q,
      Item.PACKAGED_WATER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_FUEL to 2.q),
    unlock = ResearchCondition(Research.DILUTED_PACKAGED_FUEL),
    alternate = true,
  ),

  PACKAGED_HEAVY_OIL_RESIDUE(
    "Packaged Heavy Oil Residue",
    time = 4.q,
    inputs = mapOf(
      Item.HEAVY_OIL_RESIDUE to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_HEAVY_OIL_RESIDUE to 2.q),
    unlock = ItemCondition(Item.PACKAGED_HEAVY_OIL_RESIDUE),
  ),

  PACKAGED_LIQUID_BIOFUEL(
    "Packaged Liquid Biofuel",
    time = 3.q,
    inputs = mapOf(
      Item.LIQUID_BIOFUEL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_LIQUID_BIOFUEL to 2.q),
    unlock = ItemCondition(Item.PACKAGED_LIQUID_BIOFUEL),
  ),

  LIQUID_BIOFUEL(
    "Liquid Biofuel",
    time = 4.q,
    inputs = mapOf(
      Item.SOLID_BIOFUEL to 6.q,
      Item.WATER to 3.q,
    ),
    outputs = mapOf(Item.LIQUID_BIOFUEL to 4.q),
    unlock = ItemCondition(Item.LIQUID_BIOFUEL),
  ),
  UNPACKAGE_LIQUID_BIOFUEL(
    "Unpackage Liquid Biofuel",
    time = 2.q,
    inputs = mapOf(Item.PACKAGED_LIQUID_BIOFUEL to 2.q),
    outputs = mapOf(
      Item.LIQUID_BIOFUEL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    unlock = ItemCondition(Item.LIQUID_BIOFUEL),
  ),

  CATERIUM_INGOT(
    "Caterium Ingot",
    time = 4.q,
    inputs = mapOf(Item.CATERIUM_ORE to 3.q),
    outputs = mapOf(Item.CATERIUM_INGOT to 1.q),
    unlock = ItemCondition(Item.CATERIUM_INGOT),
  ),
  PURE_CATERIUM_INGOT(
    "Pure Caterium Ingot",
    time = 5.q,
    inputs = mapOf(
      Item.CATERIUM_ORE to 2.q,
      Item.WATER to 2.q,
    ),
    outputs = mapOf(Item.CATERIUM_INGOT to 1.q),
    unlock = ResearchCondition(Research.PURE_CATERIUM_INGOT),
    alternate = true,
  ),

  QUICKWIRE(
    "Quickwire",
    time = 5.q,
    inputs = mapOf(Item.CATERIUM_INGOT to 1.q),
    outputs = mapOf(Item.QUICKWIRE to 5.q),
    unlock = ItemCondition(Item.QUICKWIRE),
  ),
  FUSED_QUICKWIRE(
    "Fused Quickwire",
    time = 8.q,
    inputs = mapOf(
      Item.CATERIUM_INGOT to 1.q,
      Item.COPPER_INGOT to 5.q,
    ),
    outputs = mapOf(Item.QUICKWIRE to 12.q),
    unlock = ResearchCondition(Research.FUSED_QUICKWIRE),
    alternate = true,
  ),

  ZIPLINE(
    "Zipline",
    time = 40.q,
    inputs = mapOf(
      Item.XENO_ZAPPER to 1.q,
      Item.QUICKWIRE to 30.q,
      Item.IRON_ROD to 3.q,
      Item.CABLE to 10.q,
    ),
    outputs = mapOf(Item.ZIPLINE to 1.q),
    unlock = ItemCondition(Item.ZIPLINE),
  ),

  AI_LIMITER(
    "AI Limiter",
    time = 12.q,
    inputs = mapOf(
      Item.COPPER_SHEET to 5.q,
      Item.QUICKWIRE to 20.q,
    ),
    outputs = mapOf(Item.AI_LIMITER to 1.q),
    unlock = ItemCondition(Item.AI_LIMITER),
  ),

  HIGH_SPEED_CONNECTOR(
    "High-Speed Connector",
    time = 16.q,
    inputs = mapOf(
      Item.QUICKWIRE to 56.q,
      Item.CABLE to 10.q,
      Item.CIRCUIT_BOARD to 1.q,
    ),
    outputs = mapOf(Item.HIGH_SPEED_CONNECTOR to 1.q),
    unlock = ItemCondition(Item.HIGH_SPEED_CONNECTOR),
  ),
  SILICON_HIGH_SPEED_CONNECTOR(
    "Silicon High-Speed Connector",
    time = 40.q,
    inputs = mapOf(
      Item.QUICKWIRE to 60.q,
      Item.SILICA to 25.q,
      Item.CIRCUIT_BOARD to 2.q,
    ),
    outputs = mapOf(Item.HIGH_SPEED_CONNECTOR to 2.q),
    unlock = ResearchCondition(Research.SILICON_HIGH_SPEED_CONNECTOR),
    alternate = true,
  ),

  ALUMINA_SOLUTION(
    "Alumina Solution",
    time = 6.q,
    inputs = mapOf(
      Item.BAUXITE to 12.q,
      Item.WATER to 18.q,
    ),
    outputs = mapOf(
      Item.ALUMINA_SOLUTION to 12.q,
      Item.SILICA to 5.q,
    ),
    unlock = ItemCondition(Item.ALUMINA_SOLUTION),
  ),
  UNPACKAGE_ALUMINA_SOLUTION(
    "Unpackage Alumina Solution",
    time = 1.q,
    inputs = mapOf(Item.PACKAGED_ALUMINA_SOLUTION to 2.q),
    outputs = mapOf(
      Item.ALUMINA_SOLUTION to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    unlock = ItemCondition(Item.ALUMINA_SOLUTION),
  ),
  SLOPPY_ALUMINA(
    "Sloppy Alumina",
    time = 3.q,
    inputs = mapOf(
      Item.BAUXITE to 10.q,
      Item.WATER to 10.q,
    ),
    outputs = mapOf(Item.ALUMINA_SOLUTION to 12.q),
    unlock = ResearchCondition(Research.SLOPPY_ALUMINA),
    alternate = true,
  ),

  PACKAGED_ALUMINA_SOLUTION(
    "Packaged Alumina Solution",
    time = 1.q,
    inputs = mapOf(
      Item.ALUMINA_SOLUTION to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_ALUMINA_SOLUTION to 2.q),
    unlock = ItemCondition(Item.PACKAGED_ALUMINA_SOLUTION),
  ),

  ALUMINUM_SCRAP(
    "Aluminum Scrap",
    time = 1.q,
    inputs = mapOf(
      Item.ALUMINA_SOLUTION to 4.q,
      Item.COAL to 2.q,
    ),
    outputs = mapOf(
      Item.ALUMINUM_SCRAP to 6.q,
      Item.WATER to 2.q,
    ),
    unlock = ItemCondition(Item.ALUMINUM_SCRAP),
  ),
  ELECTRODE_ALUMINUM_SCRAP(
    "Electrode - Aluminum Scrap",
    time = 4.q,
    inputs = mapOf(
      Item.ALUMINA_SOLUTION to 12.q,
      Item.PETROLEUM_COKE to 4.q,
    ),
    outputs = mapOf(
      Item.ALUMINUM_SCRAP to 20.q,
      Item.WATER to 7.q,
    ),
    unlock = ResearchCondition(Research.ELECTRODE_ALUMINUM_SCRAP),
    alternate = true,
  ),
  INSTANT_SCRAP(
    "Instant Scrap",
    time = 6.q,
    inputs = mapOf(
      Item.BAUXITE to 15.q,
      Item.COAL to 10.q,
      Item.SULFURIC_ACID to 5.q,
      Item.WATER to 6.q,
    ),
    outputs = mapOf(
      Item.ALUMINUM_SCRAP to 30.q,
      Item.WATER to 5.q,
    ),
    unlock = ResearchCondition(Research.INSTANT_SCRAP),
    alternate = true,
  ),

  ALUMINUM_INGOT(
    "Aluminum Ingot",
    time = 4.q,
    inputs = mapOf(
      Item.ALUMINUM_SCRAP to 6.q,
      Item.SILICA to 5.q,
    ),
    outputs = mapOf(Item.ALUMINUM_INGOT to 4.q),
    unlock = ItemCondition(Item.ALUMINUM_INGOT),
  ),
  PURE_ALUMINUM_INGOT(
    "Pure Aluminum Ingot",
    time = 2.q,
    inputs = mapOf(Item.ALUMINUM_SCRAP to 2.q),
    outputs = mapOf(Item.ALUMINUM_INGOT to 1.q),
    unlock = ResearchCondition(Research.PURE_ALUMINUM_INGOT),
    alternate = true,
  ),

  ALCLAD_ALUMINUM_SHEET(
    "Alclad Aluminum Sheet",
    time = 6.q,
    inputs = mapOf(
      Item.ALUMINUM_INGOT to 3.q,
      Item.COPPER_INGOT to 1.q,
    ),
    outputs = mapOf(Item.ALCLAD_ALUMINUM_SHEET to 3.q),
    unlock = ItemCondition(Item.ALCLAD_ALUMINUM_SHEET),
  ),

  ALUMINUM_CASING(
    "Aluminum Casing",
    time = 2.q,
    inputs = mapOf(Item.ALUMINUM_INGOT to 3.q),
    outputs = mapOf(Item.ALUMINUM_CASING to 2.q),
    unlock = ItemCondition(Item.ALUMINUM_CASING),
  ),
  ALCLAD_CASING(
    "Alclad Casing",
    time = 8.q,
    inputs = mapOf(
      Item.ALUMINUM_INGOT to 20.q,
      Item.COPPER_INGOT to 10.q,
    ),
    outputs = mapOf(Item.ALUMINUM_CASING to 15.q),
    unlock = ResearchCondition(Research.ALCLAD_CASING),
    alternate = true,
  ),

  QUARTZ_CRYSTAL(
    "Quartz Crystal",
    time = 8.q,
    inputs = mapOf(Item.RAW_QUARTZ to 5.q),
    outputs = mapOf(Item.QUARTZ_CRYSTAL to 3.q),
    unlock = ItemCondition(Item.QUARTZ_CRYSTAL),
  ),
  PURE_QUARTZ_CRYSTAL(
    "Pure Quartz Crystal",
    time = 8.q,
    inputs = mapOf(
      Item.RAW_QUARTZ to 9.q,
      Item.WATER to 5.q,
    ),
    outputs = mapOf(Item.QUARTZ_CRYSTAL to 7.q),
    unlock = ResearchCondition(Research.PURE_QUARTZ_CRYSTAL),
    alternate = true,
  ),

  SILICA(
    "Silica",
    time = 8.q,
    inputs = mapOf(Item.RAW_QUARTZ to 3.q),
    outputs = mapOf(Item.SILICA to 5.q),
    unlock = ItemCondition(Item.SILICA),
  ),
  CHEAP_SILICA(
    "Cheap Silica",
    time = 16.q,
    inputs = mapOf(
      Item.RAW_QUARTZ to 3.q,
      Item.LIMESTONE to 5.q,
    ),
    outputs = mapOf(Item.SILICA to 7.q),
    unlock = ResearchCondition(Research.CHEAP_SILICA),
    alternate = true,
  ),

  CRYSTAL_OSCILLATOR(
    "Crystal Oscillator",
    time = 120.q,
    inputs = mapOf(
      Item.QUARTZ_CRYSTAL to 36.q,
      Item.CABLE to 28.q,
      Item.REINFORCED_IRON_PLATE to 5.q,
    ),
    outputs = mapOf(Item.CRYSTAL_OSCILLATOR to 2.q),
    unlock = ItemCondition(Item.CRYSTAL_OSCILLATOR),
  ),
  INSULATED_CRYSTAL_OSCILLATOR(
    "Insulated Crystal Oscillator",
    time = 32.q,
    inputs = mapOf(
      Item.QUARTZ_CRYSTAL to 10.q,
      Item.RUBBER to 7.q,
      Item.AI_LIMITER to 1.q,
    ),
    outputs = mapOf(Item.CRYSTAL_OSCILLATOR to 1.q),
    unlock = ResearchCondition(Research.INSULATED_CRYSTAL_OSCILLATOR),
    alternate = true,
  ),

  RADIO_CONTROL_UNIT(
    "Radio Control Unit",
    time = 48.q,
    inputs = mapOf(
      Item.ALUMINUM_CASING to 32.q,
      Item.CRYSTAL_OSCILLATOR to 1.q,
      Item.COMPUTER to 1.q,
    ),
    outputs = mapOf(Item.RADIO_CONTROL_UNIT to 2.q),
    unlock = ItemCondition(Item.RADIO_CONTROL_UNIT),
  ),
  RADIO_CONNECTION_UNIT(
    "Radio Connection Unit",
    time = 16.q,
    inputs = mapOf(
      Item.HEAT_SINK to 4.q,
      Item.HIGH_SPEED_CONNECTOR to 2.q,
      Item.QUARTZ_CRYSTAL to 12.q,
    ),
    outputs = mapOf(Item.RADIO_CONTROL_UNIT to 1.q),
    unlock = ResearchCondition(Research.RADIO_CONNECTION_UNIT),
    alternate = true,
  ),
  RADIO_CONTROL_SYSTEM(
    "Radio Control System",
    time = 40.q,
    inputs = mapOf(
      Item.CRYSTAL_OSCILLATOR to 1.q,
      Item.CIRCUIT_BOARD to 10.q,
      Item.ALUMINUM_CASING to 60.q,
      Item.RUBBER to 30.q,
    ),
    outputs = mapOf(Item.RADIO_CONTROL_UNIT to 3.q),
    unlock = ResearchCondition(Research.RADIO_CONTROL_SYSTEM),
    alternate = true,
  ),

  BLACK_POWDER(
    "Black Powder",
    time = 4.q,
    inputs = mapOf(
      Item.COAL to 1.q,
      Item.SULFUR to 1.q,
    ),
    outputs = mapOf(Item.BLACK_POWDER to 2.q),
    unlock = ItemCondition(Item.BLACK_POWDER),
  ),
  FINE_BLACK_POWDER(
    "Fine Black Powder",
    time = 16.q,
    inputs = mapOf(
      Item.SULFUR to 2.q,
      Item.COMPACTED_COAL to 1.q,
    ),
    outputs = mapOf(Item.BLACK_POWDER to 4.q),
    unlock = ResearchCondition(Research.FINE_BLACK_POWDER),
    alternate = true,
  ),

  COMPACTED_COAL(
    "Compacted Coal",
    time = 12.q,
    inputs = mapOf(
      Item.COAL to 5.q,
      Item.SULFUR to 5.q,
    ),
    outputs = mapOf(Item.COMPACTED_COAL to 5.q),
    unlock = ResearchCondition(Research.COMPACTED_COAL),
    alternate = true,
  ),

  UNPACKAGE_TURBOFUEL(
    "Unpackage Turbofuel",
    time = 6.q,
    inputs = mapOf(Item.PACKAGED_TURBOFUEL to 2.q),
    outputs = mapOf(
      Item.TURBOFUEL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    unlock = ItemCondition(Item.TURBOFUEL),
  ),
  TURBOFUEL(
    "Turbofuel",
    time = 16.q,
    inputs = mapOf(
      Item.FUEL to 6.q,
      Item.COMPACTED_COAL to 4.q,
    ),
    outputs = mapOf(Item.TURBOFUEL to 5.q),
    unlock = ResearchCondition(Research.TURBOFUEL),
    alternate = true,
  ),
  TURBO_BLEND_FUEL(
    "Turbo Blend Fuel",
    time = 8.q,
    inputs = mapOf(
      Item.FUEL to 2.q,
      Item.HEAVY_OIL_RESIDUE to 4.q,
      Item.SULFUR to 3.q,
      Item.PETROLEUM_COKE to 3.q,
    ),
    outputs = mapOf(Item.TURBOFUEL to 6.q),
    unlock = ResearchCondition(Research.TURBO_BLEND_FUEL),
    alternate = true,
  ),
  TURBO_HEAVY_FUEL(
    "Turbo Heavy Fuel",
    time = 8.q,
    inputs = mapOf(
      Item.HEAVY_OIL_RESIDUE to 5.q,
      Item.COMPACTED_COAL to 4.q,
    ),
    outputs = mapOf(Item.TURBOFUEL to 4.q),
    unlock = ResearchCondition(Research.TURBO_HEAVY_FUEL),
    alternate = true,
  ),

  PACKAGED_TURBOFUEL(
    "Packaged Turbofuel",
    time = 6.q,
    inputs = mapOf(
      Item.TURBOFUEL to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_TURBOFUEL to 2.q),
    unlock = ItemCondition(Item.PACKAGED_TURBOFUEL),
  ),

  SMOKELESS_POWDER(
    "Smokeless Powder",
    time = 6.q,
    inputs = mapOf(
      Item.BLACK_POWDER to 2.q,
      Item.HEAVY_OIL_RESIDUE to 1.q,
    ),
    outputs = mapOf(Item.SMOKELESS_POWDER to 2.q),
    unlock = ItemCondition(Item.SMOKELESS_POWDER),
  ),

  SULFURIC_ACID(
    "Sulfuric Acid",
    time = 6.q,
    inputs = mapOf(
      Item.SULFUR to 5.q,
      Item.WATER to 5.q,
    ),
    outputs = mapOf(Item.SULFURIC_ACID to 5.q),
    unlock = ItemCondition(Item.SULFURIC_ACID),
  ),
  UNPACKAGE_SULFURIC_ACID(
    "Unpackage Sulfuric Acid",
    time = 1.q,
    inputs = mapOf(Item.PACKAGED_SULFURIC_ACID to 1.q),
    outputs = mapOf(
      Item.SULFURIC_ACID to 1.q,
      Item.EMPTY_CANISTER to 1.q,
    ),
    unlock = ItemCondition(Item.SULFURIC_ACID),
  ),

  PACKAGED_SULFURIC_ACID(
    "Packaged Sulfuric Acid",
    time = 3.q,
    inputs = mapOf(
      Item.SULFURIC_ACID to 2.q,
      Item.EMPTY_CANISTER to 2.q,
    ),
    outputs = mapOf(Item.PACKAGED_SULFURIC_ACID to 2.q),
    unlock = ItemCondition(Item.PACKAGED_SULFURIC_ACID),
  ),

  BATTERY(
    "Battery",
    time = 3.q,
    inputs = mapOf(
      Item.SULFURIC_ACID to 5.q / 2.q,
      Item.ALUMINA_SOLUTION to 2.q,
      Item.ALUMINUM_CASING to 1.q,
    ),
    outputs = mapOf(
      Item.BATTERY to 1.q,
      Item.WATER to 3.q / 2.q,
    ),
    unlock = ItemCondition(Item.BATTERY),
  ),
  CLASSIC_BATTERY(
    "Classic Battery",
    time = 8.q,
    inputs = mapOf(
      Item.SULFUR to 6.q,
      Item.ALCLAD_ALUMINUM_SHEET to 7.q,
      Item.PLASTIC to 8.q,
      Item.WIRE to 12.q,
    ),
    outputs = mapOf(Item.BATTERY to 4.q),
    unlock = ResearchCondition(Research.CLASSIC_BATTERY),
    alternate = true,
  ),

  SUPERCOMPUTER(
    "Supercomputer",
    time = 32.q,
    inputs = mapOf(
      Item.COMPUTER to 2.q,
      Item.AI_LIMITER to 2.q,
      Item.HIGH_SPEED_CONNECTOR to 3.q,
      Item.PLASTIC to 28.q,
    ),
    outputs = mapOf(Item.SUPERCOMPUTER to 1.q),
    unlock = ItemCondition(Item.SUPERCOMPUTER),
  ),
  OC_SUPERCOMPUTER(
    "OC Supercomputer",
    time = 20.q,
    inputs = mapOf(
      Item.RADIO_CONTROL_UNIT to 3.q,
      Item.COOLING_SYSTEM to 3.q,
    ),
    outputs = mapOf(Item.SUPERCOMPUTER to 1.q),
    unlock = ResearchCondition(Research.OC_SUPERCOMPUTER),
    alternate = true,
  ),
  SUPER_STATE_COMPUTER(
    "Super-State Computer",
    time = 50.q,
    inputs = mapOf(
      Item.COMPUTER to 3.q,
      Item.ELECTROMAGNETIC_CONTROL_ROD to 2.q,
      Item.BATTERY to 20.q,
      Item.WIRE to 45.q,
    ),
    outputs = mapOf(Item.SUPERCOMPUTER to 2.q),
    unlock = ResearchCondition(Research.SUPER_STATE_COMPUTER),
    alternate = true,
  ),

  ASSEMBLY_DIRECTOR_SYSTEM(
    "Assembly Director System",
    time = 80.q,
    inputs = mapOf(
      Item.ADAPTIVE_CONTROL_UNIT to 2.q,
      Item.SUPERCOMPUTER to 1.q,
    ),
    outputs = mapOf(Item.ASSEMBLY_DIRECTOR_SYSTEM to 1.q),
    unlock = ItemCondition(Item.ASSEMBLY_DIRECTOR_SYSTEM),
  ),

  ENCASED_URANIUM_CELL(
    "Encased Uranium Cell",
    time = 12.q,
    inputs = mapOf(
      Item.URANIUM to 10.q,
      Item.CONCRETE to 3.q,
      Item.SULFURIC_ACID to 8.q,
    ),
    outputs = mapOf(
      Item.ENCASED_URANIUM_CELL to 5.q,
      Item.SULFURIC_ACID to 2.q,
    ),
    unlock = ItemCondition(Item.ENCASED_URANIUM_CELL),
  ),
  INFUSED_URANIUM_CELL(
    "Infused Uranium Cell",
    time = 12.q,
    inputs = mapOf(
      Item.URANIUM to 5.q,
      Item.SILICA to 3.q,
      Item.SULFUR to 5.q,
      Item.QUICKWIRE to 15.q,
    ),
    outputs = mapOf(Item.ENCASED_URANIUM_CELL to 4.q),
    unlock = ResearchCondition(Research.INFUSED_URANIUM_CELL),
    alternate = true,
  ),

  ELECTROMAGNETIC_CONTROL_ROD(
    "Electromagnetic Control Rod",
    time = 30.q,
    inputs = mapOf(
      Item.STATOR to 3.q,
      Item.AI_LIMITER to 2.q,
    ),
    outputs = mapOf(Item.ELECTROMAGNETIC_CONTROL_ROD to 2.q),
    unlock = ItemCondition(Item.ELECTROMAGNETIC_CONTROL_ROD),
  ),
  ELECTROMAGNETIC_CONNECTION_ROD(
    "Electromagnetic Connection Rod",
    time = 15.q,
    inputs = mapOf(
      Item.STATOR to 2.q,
      Item.HIGH_SPEED_CONNECTOR to 1.q,
    ),
    outputs = mapOf(Item.ELECTROMAGNETIC_CONTROL_ROD to 2.q),
    unlock = ResearchCondition(Research.ELECTROMAGNETIC_CONNECTION_ROD),
    alternate = true,
  ),

  URANIUM_FUEL_ROD(
    "Uranium Fuel Rod",
    time = 150.q,
    inputs = mapOf(
      Item.ENCASED_URANIUM_CELL to 50.q,
      Item.ENCASED_INDUSTRIAL_BEAM to 3.q,
      Item.ELECTROMAGNETIC_CONTROL_ROD to 5.q,
    ),
    outputs = mapOf(Item.URANIUM_FUEL_ROD to 1.q),
    unlock = ItemCondition(Item.URANIUM_FUEL_ROD),
  ),
  URANIUM_FUEL_UNIT(
    "Uranium Fuel Unit",
    time = 300.q,
    inputs = mapOf(
      Item.ENCASED_URANIUM_CELL to 100.q,
      Item.ELECTROMAGNETIC_CONTROL_ROD to 10.q,
      Item.CRYSTAL_OSCILLATOR to 3.q,
      Item.BEACON to 6.q,
    ),
    outputs = mapOf(Item.URANIUM_FUEL_ROD to 3.q),
    unlock = ResearchCondition(Research.URANIUM_FUEL_UNIT),
    alternate = true,
  ),

  URANIUM_FUEL_ROD_BURNING(
    "Uranium Fuel Rod (burning)",
    time = 300.q,
    inputs = mapOf(
      Item.URANIUM_FUEL_ROD to 1.q,
      Item.WATER to 1_200.q,
    ),
    outputs = mapOf(Item.URANIUM_WASTE to 50.q),
    unlock = ItemCondition(Item.URANIUM_WASTE),
  ),

  MAGNETIC_FIELD_GENERATOR(
    "Magnetic Field Generator",
    time = 120.q,
    inputs = mapOf(
      Item.VERSATILE_FRAMEWORK to 5.q,
      Item.ELECTROMAGNETIC_CONTROL_ROD to 2.q,
      Item.BATTERY to 10.q,
    ),
    outputs = mapOf(Item.MAGNETIC_FIELD_GENERATOR to 2.q),
    unlock = ItemCondition(Item.MAGNETIC_FIELD_GENERATOR),
  ),

  UNPACKAGE_NITROGEN_GAS(
    "Unpackage Nitrogen Gas",
    time = 1.q,
    inputs = mapOf(Item.PACKAGED_NITROGEN_GAS to 1.q),
    outputs = mapOf(
      Item.NITROGEN_GAS to 4.q,
      Item.EMPTY_FLUID_TANK to 1.q,
    ),
    unlock = ItemCondition(Item.NITROGEN_GAS),
  ),

  EMPTY_FLUID_TANK(
    "Empty Fluid Tank",
    time = 1.q,
    inputs = mapOf(Item.ALUMINUM_INGOT to 1.q),
    outputs = mapOf(Item.EMPTY_FLUID_TANK to 1.q),
    unlock = ItemCondition(Item.EMPTY_FLUID_TANK),
  ),

  PACKAGED_NITROGEN_GAS(
    "Packaged Nitrogen Gas",
    time = 1.q,
    inputs = mapOf(
      Item.NITROGEN_GAS to 4.q,
      Item.EMPTY_FLUID_TANK to 1.q,
    ),
    outputs = mapOf(Item.PACKAGED_NITROGEN_GAS to 1.q),
    unlock = ItemCondition(Item.PACKAGED_NITROGEN_GAS),
  ),

  HEAT_SINK(
    "Heat Sink",
    time = 8.q,
    inputs = mapOf(
      Item.ALCLAD_ALUMINUM_SHEET to 5.q,
      Item.COPPER_SHEET to 3.q,
    ),
    outputs = mapOf(Item.HEAT_SINK to 1.q),
    unlock = ItemCondition(Item.HEAT_SINK),
  ),
  HEAT_EXCHANGER(
    "Heat Exchanger",
    time = 6.q,
    inputs = mapOf(
      Item.ALUMINUM_CASING to 3.q,
      Item.RUBBER to 3.q,
    ),
    outputs = mapOf(Item.HEAT_SINK to 1.q),
    unlock = ResearchCondition(Research.HEAT_EXCHANGER),
    alternate = true,
  ),

  COOLING_SYSTEM(
    "Cooling System",
    time = 10.q,
    inputs = mapOf(
      Item.HEAT_SINK to 2.q,
      Item.RUBBER to 2.q,
      Item.WATER to 5.q,
      Item.NITROGEN_GAS to 25.q,
    ),
    outputs = mapOf(Item.COOLING_SYSTEM to 1.q),
    unlock = ItemCondition(Item.COOLING_SYSTEM),
  ),
  COOLING_DEVICE(
    "Cooling Device",
    time = 32.q,
    inputs = mapOf(
      Item.HEAT_SINK to 5.q,
      Item.MOTOR to 1.q,
      Item.NITROGEN_GAS to 24.q,
    ),
    outputs = mapOf(Item.COOLING_SYSTEM to 2.q),
    unlock = ResearchCondition(Research.COOLING_DEVICE),
    alternate = true,
  ),

  FUSED_MODULAR_FRAME(
    "Fused Modular Frame",
    time = 40.q,
    inputs = mapOf(
      Item.HEAVY_MODULAR_FRAME to 1.q,
      Item.ALUMINUM_CASING to 50.q,
      Item.NITROGEN_GAS to 25.q,
    ),
    outputs = mapOf(Item.FUSED_MODULAR_FRAME to 1.q),
    unlock = ItemCondition(Item.FUSED_MODULAR_FRAME),
  ),
  HEAT_FUSED_FRAME(
    "Heat-Fused Frame",
    time = 20.q,
    inputs = mapOf(
      Item.HEAVY_MODULAR_FRAME to 1.q,
      Item.ALUMINUM_INGOT to 50.q,
      Item.NITRIC_ACID to 8.q,
      Item.FUEL to 10.q,
    ),
    outputs = mapOf(Item.FUSED_MODULAR_FRAME to 1.q),
    unlock = ResearchCondition(Research.HEAT_FUSED_FRAME),
    alternate = true,
  ),

  TURBO_MOTOR(
    "Turbo Motor",
    time = 32.q,
    inputs = mapOf(
      Item.COOLING_SYSTEM to 4.q,
      Item.RADIO_CONTROL_UNIT to 2.q,
      Item.MOTOR to 4.q,
      Item.RUBBER to 24.q,
    ),
    outputs = mapOf(Item.TURBO_MOTOR to 1.q),
    unlock = ItemCondition(Item.TURBO_MOTOR),
  ),
  TURBO_ELECTRIC_MOTOR(
    "Turbo Electric Motor",
    time = 64.q,
    inputs = mapOf(
      Item.MOTOR to 7.q,
      Item.RADIO_CONTROL_UNIT to 9.q,
      Item.ELECTROMAGNETIC_CONTROL_ROD to 5.q,
      Item.ROTOR to 7.q,
    ),
    outputs = mapOf(Item.TURBO_MOTOR to 3.q),
    unlock = ResearchCondition(Research.TURBO_ELECTRIC_MOTOR),
    alternate = true,
  ),
  TURBO_PRESSURE_MOTOR(
    "Turbo Pressure Motor",
    time = 32.q,
    inputs = mapOf(
      Item.MOTOR to 4.q,
      Item.PRESSURE_CONVERSION_CUBE to 1.q,
      Item.PACKAGED_NITROGEN_GAS to 24.q,
      Item.STATOR to 8.q,
    ),
    outputs = mapOf(Item.TURBO_MOTOR to 2.q),
    unlock = ResearchCondition(Research.TURBO_PRESSURE_MOTOR),
    alternate = true,
  ),

  THERMAL_PROPULSION_ROCKET(
    "Thermal Propulsion Rocket",
    time = 120.q,
    inputs = mapOf(
      Item.MODULAR_ENGINE to 5.q,
      Item.TURBO_MOTOR to 2.q,
      Item.COOLING_SYSTEM to 6.q,
      Item.FUSED_MODULAR_FRAME to 2.q,
    ),
    outputs = mapOf(Item.THERMAL_PROPULSION_ROCKET to 2.q),
    unlock = ItemCondition(Item.THERMAL_PROPULSION_ROCKET),
  ),

  NITRIC_ACID(
    "Nitric Acid",
    time = 6.q,
    inputs = mapOf(
      Item.NITROGEN_GAS to 12.q,
      Item.WATER to 3.q,
      Item.IRON_PLATE to 1.q,
    ),
    outputs = mapOf(Item.NITRIC_ACID to 3.q),
    unlock = ItemCondition(Item.NITRIC_ACID),
  ),
  UNPACKAGE_NITRIC_ACID(
    "Unpackage Nitric Acid",
    time = 3.q,
    inputs = mapOf(Item.PACKAGED_NITRIC_ACID to 1.q),
    outputs = mapOf(
      Item.NITRIC_ACID to 1.q,
      Item.EMPTY_FLUID_TANK to 1.q,
    ),
    unlock = ItemCondition(Item.NITRIC_ACID),
  ),

  PACKAGED_NITRIC_ACID(
    "Packaged Nitric Acid",
    time = 2.q,
    inputs = mapOf(
      Item.NITRIC_ACID to 1.q,
      Item.EMPTY_FLUID_TANK to 1.q,
    ),
    outputs = mapOf(Item.PACKAGED_NITRIC_ACID to 1.q),
    unlock = ItemCondition(Item.PACKAGED_NITRIC_ACID),
  ),

  NON_FISSILE_URANIUM(
    "Non-fissile Uranium",
    time = 24.q,
    inputs = mapOf(
      Item.URANIUM_WASTE to 15.q,
      Item.SILICA to 10.q,
      Item.NITRIC_ACID to 6.q,
      Item.SULFURIC_ACID to 6.q,
    ),
    outputs = mapOf(
      Item.NON_FISSILE_URANIUM to 20.q,
      Item.WATER to 6.q,
    ),
    unlock = ItemCondition(Item.NON_FISSILE_URANIUM),
  ),
  FERTILE_URANIUM(
    "Fertile Uranium",
    time = 12.q,
    inputs = mapOf(
      Item.URANIUM to 5.q,
      Item.URANIUM_WASTE to 5.q,
      Item.NITRIC_ACID to 3.q,
      Item.SULFURIC_ACID to 5.q,
    ),
    outputs = mapOf(
      Item.NON_FISSILE_URANIUM to 20.q,
      Item.WATER to 8.q,
    ),
    unlock = ResearchCondition(Research.FERTILE_URANIUM),
    alternate = true,
  ),

  PLUTONIUM_PELLET(
    "Plutonium Pellet",
    time = 60.q,
    inputs = mapOf(
      Item.NON_FISSILE_URANIUM to 100.q,
      Item.URANIUM_WASTE to 25.q,
    ),
    outputs = mapOf(Item.PLUTONIUM_PELLET to 30.q),
    power = 250.q..750.q,
    unlock = ItemCondition(Item.PLUTONIUM_PELLET),
  ),

  ENCASED_PLUTONIUM_CELL(
    "Encased Plutonium Cell",
    time = 12.q,
    inputs = mapOf(
      Item.PLUTONIUM_PELLET to 2.q,
      Item.CONCRETE to 4.q,
    ),
    outputs = mapOf(Item.ENCASED_PLUTONIUM_CELL to 1.q),
    unlock = ItemCondition(Item.ENCASED_PLUTONIUM_CELL),
  ),
  INSTANT_PLUTONIUM_CELL(
    "Instant Plutonium Cell",
    time = 120.q,
    inputs = mapOf(
      Item.NON_FISSILE_URANIUM to 150.q,
      Item.ALUMINUM_CASING to 20.q,
    ),
    outputs = mapOf(Item.ENCASED_PLUTONIUM_CELL to 20.q),
    power = 250.q..750.q,
    unlock = ResearchCondition(Research.INSTANT_PLUTONIUM_CELL),
    alternate = true,
  ),

  PLUTONIUM_FUEL_ROD(
    "Plutonium Fuel Rod",
    time = 240.q,
    inputs = mapOf(
      Item.ENCASED_PLUTONIUM_CELL to 30.q,
      Item.STEEL_BEAM to 18.q,
      Item.ELECTROMAGNETIC_CONTROL_ROD to 6.q,
      Item.HEAT_SINK to 10.q,
    ),
    outputs = mapOf(Item.PLUTONIUM_FUEL_ROD to 1.q),
    unlock = ItemCondition(Item.PLUTONIUM_FUEL_ROD),
  ),
  PLUTONIUM_FUEL_UNIT(
    "Plutonium Fuel Unit",
    time = 120.q,
    inputs = mapOf(
      Item.ENCASED_PLUTONIUM_CELL to 20.q,
      Item.PRESSURE_CONVERSION_CUBE to 1.q,
    ),
    outputs = mapOf(Item.PLUTONIUM_FUEL_ROD to 1.q),
    unlock = ResearchCondition(Research.PLUTONIUM_FUEL_UNIT),
    alternate = true,
  ),

  PLUTONIUM_FUEL_ROD_BURNING(
    "Plutonium Fuel Rod (burning)",
    time = 600.q,
    inputs = mapOf(
      Item.PLUTONIUM_FUEL_ROD to 1.q,
      Item.WATER to 2_400.q,
    ),
    outputs = mapOf(Item.PLUTONIUM_WASTE to 10.q),
    unlock = ItemCondition(Item.PLUTONIUM_WASTE),
  ),

  COPPER_POWDER(
    "Copper Powder",
    time = 6.q,
    inputs = mapOf(Item.COPPER_INGOT to 30.q),
    outputs = mapOf(Item.COPPER_POWDER to 5.q),
    unlock = ItemCondition(Item.COPPER_POWDER),
  ),

  PRESSURE_CONVERSION_CUBE(
    "Pressure Conversion Cube",
    time = 60.q,
    inputs = mapOf(
      Item.FUSED_MODULAR_FRAME to 1.q,
      Item.RADIO_CONTROL_UNIT to 2.q,
    ),
    outputs = mapOf(Item.PRESSURE_CONVERSION_CUBE to 1.q),
    unlock = ItemCondition(Item.PRESSURE_CONVERSION_CUBE),
  ),

  NUCLEAR_PASTA(
    "Nuclear Pasta",
    time = 120.q,
    inputs = mapOf(
      Item.COPPER_POWDER to 200.q,
      Item.PRESSURE_CONVERSION_CUBE to 1.q,
    ),
    outputs = mapOf(Item.NUCLEAR_PASTA to 1.q),
    power = 500.q..1500.q,
    unlock = ItemCondition(Item.NUCLEAR_PASTA),
  ),

  BLADE_RUNNERS(
    "Blade Runners",
    time = 60.q,
    inputs = mapOf(
      Item.SILICA to 20.q,
      Item.MODULAR_FRAME to 3.q,
      Item.ROTOR to 3.q,
    ),
    outputs = mapOf(Item.BLADE_RUNNERS to 1.q),
    unlock = ItemCondition(Item.BLADE_RUNNERS),
  ),

  PARACHUTE(
    "Parachute",
    time = 20.q,
    inputs = mapOf(
      Item.FABRIC to 10.q,
      Item.CABLE to 5.q,
    ),
    outputs = mapOf(Item.PARACHUTE to 5.q),
    unlock = ItemCondition(Item.PARACHUTE),
  ),

  JETPACK(
    "Jetpack",
    time = 120.q,
    inputs = mapOf(
      Item.PLASTIC to 50.q,
      Item.RUBBER to 50.q,
      Item.CIRCUIT_BOARD to 15.q,
      Item.MOTOR to 5.q,
    ),
    outputs = mapOf(Item.JETPACK to 1.q),
    unlock = ItemCondition(Item.JETPACK),
  ),

  HOVER_PACK(
    "Hover Pack",
    time = 120.q,
    inputs = mapOf(
      Item.MOTOR to 8.q,
      Item.HEAVY_MODULAR_FRAME to 4.q,
      Item.COMPUTER to 8.q,
      Item.ALCLAD_ALUMINUM_SHEET to 40.q,
    ),
    outputs = mapOf(Item.HOVER_PACK to 1.q),
    unlock = ItemCondition(Item.HOVER_PACK),
  ),

  NUTRITIONAL_INHALER(
    "Nutritional Inhaler",
    time = 20.q,
    inputs = mapOf(
      Item.BACON_AGARIC to 1.q,
      Item.PALEBERRY to 2.q,
      Item.BERYL_NUT to 5.q,
    ),
    outputs = mapOf(Item.MEDICINAL_INHALER to 1.q),
    unlock = ItemCondition(Item.MEDICINAL_INHALER),
  ),
  PROTEIN_INHALER(
    "Protein Inhaler",
    time = 20.q,
    inputs = mapOf(
      Item.ALIEN_PROTEIN to 1.q,
      Item.BERYL_NUT to 10.q,
    ),
    outputs = mapOf(Item.MEDICINAL_INHALER to 1.q),
    unlock = ItemCondition(Item.MEDICINAL_INHALER),
  ),
  THERAPEUTIC_INHALER(
    "Therapeutic Inhaler",
    time = 20.q,
    inputs = mapOf(
      Item.MYCELIA to 15.q,
      Item.ALIEN_PROTEIN to 1.q,
      Item.BACON_AGARIC to 1.q,
    ),
    outputs = mapOf(Item.MEDICINAL_INHALER to 1.q),
    unlock = ItemCondition(Item.MEDICINAL_INHALER),
  ),
  VITAMIN_INHALER(
    "Vitamin Inhaler",
    time = 20.q,
    inputs = mapOf(
      Item.MYCELIA to 10.q,
      Item.PALEBERRY to 5.q,
    ),
    outputs = mapOf(Item.MEDICINAL_INHALER to 1.q),
    unlock = ItemCondition(Item.MEDICINAL_INHALER),
  ),

  GAS_MASK(
    "Gas Mask",
    time = 60.q,
    inputs = mapOf(
      Item.RUBBER to 100.q,
      Item.PLASTIC to 100.q,
      Item.FABRIC to 100.q,
    ),
    outputs = mapOf(Item.GAS_MASK to 1.q),
    unlock = ItemCondition(Item.GAS_MASK),
  ),

  GAS_FILTER(
    "Gas Filter",
    time = 8.q,
    inputs = mapOf(
      Item.COAL to 5.q,
      Item.RUBBER to 2.q,
      Item.FABRIC to 2.q,
    ),
    outputs = mapOf(Item.GAS_FILTER to 1.q),
    unlock = ItemCondition(Item.GAS_FILTER),
  ),

  HAZMAT_SUIT(
    "Hazmat Suit",
    time = 120.q,
    inputs = mapOf(
      Item.RUBBER to 50.q,
      Item.PLASTIC to 50.q,
      Item.ALCLAD_ALUMINUM_SHEET to 50.q,
      Item.FABRIC to 50.q,
    ),
    outputs = mapOf(Item.HAZMAT_SUIT to 1.q),
    unlock = ItemCondition(Item.HAZMAT_SUIT),
  ),

  IODINE_INFUSED_FILTER(
    "Iodine Infused Filter",
    time = 16.q,
    inputs = mapOf(
      Item.GAS_FILTER to 1.q,
      Item.QUICKWIRE to 8.q,
      Item.ALUMINUM_CASING to 1.q,
    ),
    outputs = mapOf(Item.IODINE_INFUSED_FILTER to 1.q),
    unlock = ItemCondition(Item.IODINE_INFUSED_FILTER),
  ),

  XENO_ZAPPER(
    "Xeno-Zapper",
    time = 40.q,
    inputs = mapOf(
      Item.IRON_ROD to 10.q,
      Item.REINFORCED_IRON_PLATE to 2.q,
      Item.CABLE to 15.q,
      Item.WIRE to 50.q,
    ),
    outputs = mapOf(Item.XENO_ZAPPER to 1.q),
    unlock = ItemCondition(Item.XENO_ZAPPER),
  ),

  XENO_BASHER(
    "Xeno-Basher",
    time = 80.q,
    inputs = mapOf(
      Item.MODULAR_FRAME to 5.q,
      Item.XENO_ZAPPER to 2.q,
      Item.CABLE to 25.q,
      Item.WIRE to 500.q,
    ),
    outputs = mapOf(Item.XENO_BASHER to 1.q),
    unlock = ItemCondition(Item.XENO_BASHER),
  ),

  REBAR_GUN(
    "Rebar Gun",
    time = 60.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 6.q,
      Item.IRON_ROD to 16.q,
      Item.SCREW to 100.q,
    ),
    outputs = mapOf(Item.REBAR_GUN to 1.q),
    unlock = ItemCondition(Item.REBAR_GUN),
  ),

  IRON_REBAR(
    "Iron Rebar",
    time = 4.q,
    inputs = mapOf(Item.IRON_ROD to 1.q),
    outputs = mapOf(Item.IRON_REBAR to 1.q),
    unlock = ItemCondition(Item.IRON_REBAR),
  ),

  STUN_REBAR(
    "Stun Rebar",
    time = 6.q,
    inputs = mapOf(
      Item.IRON_REBAR to 1.q,
      Item.QUICKWIRE to 5.q,
    ),
    outputs = mapOf(Item.STUN_REBAR to 1.q),
    unlock = ItemCondition(Item.STUN_REBAR),
  ),

  SHATTER_REBAR(
    "Shatter Rebar",
    time = 12.q,
    inputs = mapOf(
      Item.IRON_REBAR to 2.q,
      Item.QUARTZ_CRYSTAL to 3.q,
    ),
    outputs = mapOf(Item.SHATTER_REBAR to 1.q),
    unlock = ItemCondition(Item.SHATTER_REBAR),
  ),

  EXPLOSIVE_REBAR(
    "Explosive Rebar",
    time = 12.q,
    inputs = mapOf(
      Item.IRON_REBAR to 2.q,
      Item.SMOKELESS_POWDER to 2.q,
      Item.STEEL_PIPE to 2.q,
    ),
    outputs = mapOf(Item.EXPLOSIVE_REBAR to 1.q),
    unlock = ItemCondition(Item.EXPLOSIVE_REBAR),
  ),

  RIFLE(
    "Rifle",
    time = 120.q,
    inputs = mapOf(
      Item.MOTOR to 2.q,
      Item.RUBBER to 10.q,
      Item.STEEL_PIPE to 25.q,
      Item.SCREW to 250.q,
    ),
    outputs = mapOf(Item.RIFLE to 1.q),
    unlock = ItemCondition(Item.RIFLE),
  ),

  RIFLE_AMMO(
    "Rifle Ammo",
    time = 12.q,
    inputs = mapOf(
      Item.COPPER_SHEET to 3.q,
      Item.SMOKELESS_POWDER to 2.q,
    ),
    outputs = mapOf(Item.RIFLE_AMMO to 15.q),
    unlock = ItemCondition(Item.RIFLE_AMMO),
  ),

  HOMING_RIFLE_AMMO(
    "Homing Rifle Ammo",
    time = 24.q,
    inputs = mapOf(
      Item.RIFLE_AMMO to 20.q,
      Item.HIGH_SPEED_CONNECTOR to 1.q,
    ),
    outputs = mapOf(Item.HOMING_RIFLE_AMMO to 10.q),
    unlock = ItemCondition(Item.HOMING_RIFLE_AMMO),
  ),

  TURBO_RIFLE_AMMO(
    "Turbo Rifle Ammo",
    time = 12.q,
    inputs = mapOf(
      Item.RIFLE_AMMO to 25.q,
      Item.ALUMINUM_CASING to 3.q,
      Item.TURBOFUEL to 3.q,
    ),
    outputs = mapOf(Item.TURBO_RIFLE_AMMO to 50.q),
    unlock = ItemCondition(Item.TURBO_RIFLE_AMMO),
  ),

  NOBELISK_DETONATOR(
    "Nobelisk Detonator",
    time = 80.q,
    inputs = mapOf(
      Item.OBJECT_SCANNER to 1.q,
      Item.STEEL_BEAM to 10.q,
      Item.CABLE to 50.q,
    ),
    outputs = mapOf(Item.NOBELISK_DETONATOR to 1.q),
    unlock = ItemCondition(Item.NOBELISK_DETONATOR),
  ),

  NOBELISK(
    "Nobelisk",
    time = 6.q,
    inputs = mapOf(
      Item.BLACK_POWDER to 2.q,
      Item.STEEL_PIPE to 2.q,
    ),
    outputs = mapOf(Item.NOBELISK to 1.q),
    unlock = ItemCondition(Item.NOBELISK),
  ),

  GAS_NOBELISK(
    "Gas Nobelisk",
    time = 12.q,
    inputs = mapOf(
      Item.NOBELISK to 1.q,
      Item.BIOMASS to 10.q,
    ),
    outputs = mapOf(Item.GAS_NOBELISK to 1.q),
    unlock = ItemCondition(Item.GAS_NOBELISK),
  ),

  PULSE_NOBELISK(
    "Pulse Nobelisk",
    time = 60.q,
    inputs = mapOf(
      Item.NOBELISK to 5.q,
      Item.CRYSTAL_OSCILLATOR to 1.q,
    ),
    outputs = mapOf(Item.PULSE_NOBELISK to 5.q),
    unlock = ItemCondition(Item.PULSE_NOBELISK),
  ),

  CLUSTER_NOBELISK(
    "Cluster Nobelisk",
    time = 24.q,
    inputs = mapOf(
      Item.NOBELISK to 3.q,
      Item.SMOKELESS_POWDER to 4.q,
    ),
    outputs = mapOf(Item.CLUSTER_NOBELISK to 1.q),
    unlock = ItemCondition(Item.CLUSTER_NOBELISK),
  ),

  NUKE_NOBELISK(
    "Nuke Nobelisk",
    time = 120.q,
    inputs = mapOf(
      Item.NOBELISK to 5.q,
      Item.ENCASED_URANIUM_CELL to 20.q,
      Item.SMOKELESS_POWDER to 10.q,
      Item.AI_LIMITER to 6.q,
    ),
    outputs = mapOf(Item.NUKE_NOBELISK to 1.q),
    unlock = ItemCondition(Item.NUKE_NOBELISK),
  ),

  HATCHER_PROTEIN(
    "Hatcher Protein",
    time = 3.q,
    inputs = mapOf(Item.HATCHER_REMAINS to 1.q),
    outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    unlock = ItemCondition(Item.ALIEN_PROTEIN),
  ),
  HOG_PROTEIN(
    "Hog Protein",
    time = 3.q,
    inputs = mapOf(Item.HOG_REMAINS to 1.q),
    outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    unlock = ItemCondition(Item.ALIEN_PROTEIN),
  ),
  SPITTER_PROTEIN(
    "Spitter Protein",
    time = 3.q,
    inputs = mapOf(Item.PLASMA_SPITTER_REMAINS to 1.q),
    outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    unlock = ItemCondition(Item.ALIEN_PROTEIN),
  ),
  STINGER_PROTEIN(
    "Stinger Protein",
    time = 3.q,
    inputs = mapOf(Item.STINGER_REMAINS to 1.q),
    outputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    unlock = ItemCondition(Item.ALIEN_PROTEIN),
  ),

  ALIEN_DNA_CAPSULE(
    "Alien DNA Capsule",
    time = 6.q,
    inputs = mapOf(Item.ALIEN_PROTEIN to 1.q),
    outputs = mapOf(Item.ALIEN_DNA_CAPSULE to 1.q),
    unlock = ItemCondition(Item.ALIEN_DNA_CAPSULE),
  ),

  SWEET_FIREWORKS(
    "Sweet Fireworks",
    time = 24.q,
    inputs = mapOf(
      Item.FICSMAS_TREE_BRANCH to 6.q,
      Item.CANDY_CANE to 3.q,
    ),
    outputs = mapOf(Item.SWEET_FIREWORKS to 1.q),
    unlock = ItemCondition(Item.SWEET_FIREWORKS),
  ),

  FANCY_FIREWORKS(
    "Fancy Fireworks",
    time = 24.q,
    inputs = mapOf(
      Item.FICSMAS_TREE_BRANCH to 4.q,
      Item.FICSMAS_BOW to 3.q,
    ),
    outputs = mapOf(Item.FANCY_FIREWORKS to 1.q),
    unlock = ItemCondition(Item.FANCY_FIREWORKS),
  ),

  SPARKLY_FIREWORKS(
    "Sparkly Fireworks",
    time = 24.q,
    inputs = mapOf(
      Item.FICSMAS_TREE_BRANCH to 3.q,
      Item.ACTUAL_SNOW to 2.q,
    ),
    outputs = mapOf(Item.SPARKLY_FIREWORKS to 1.q),
    unlock = ItemCondition(Item.SPARKLY_FIREWORKS),
  ),

  FICSMAS_TREE_BRANCH(
    "FICSMAS Tree Branch",
    time = 6.q,
    inputs = mapOf(Item.FICSMAS_GIFT to 1.q),
    outputs = mapOf(Item.FICSMAS_TREE_BRANCH to 1.q),
    unlock = ItemCondition(Item.FICSMAS_TREE_BRANCH),
  ),

  FABRIC(
    "Fabric",
    time = 4.q,
    inputs = mapOf(
      Item.MYCELIA to 1.q,
      Item.BIOMASS to 5.q,
    ),
    outputs = mapOf(Item.FABRIC to 1.q),
    unlock = ItemCondition(Item.FABRIC),
  ),
  POLYESTER_FABRIC(
    "Polyester Fabric",
    time = 2.q,
    inputs = mapOf(
      Item.POLYMER_RESIN to 1.q,
      Item.WATER to 1.q,
    ),
    outputs = mapOf(Item.FABRIC to 1.q),
    unlock = ResearchCondition(Research.SYNTHETIC_POLYESTER_FABRIC),
    alternate = true,
  ),

  POWER_SHARD_1(
    "Power Shard (1)",
    time = 8.q,
    inputs = mapOf(Item.BLUE_POWER_SLUG to 1.q),
    outputs = mapOf(Item.POWER_SHARD to 1.q),
    unlock = ItemCondition(Item.POWER_SHARD),
  ),
  POWER_SHARD_2(
    "Power Shard (2)",
    time = 12.q,
    inputs = mapOf(Item.YELLOW_POWER_SLUG to 1.q),
    outputs = mapOf(Item.POWER_SHARD to 2.q),
    unlock = ItemCondition(Item.POWER_SHARD),
  ),
  POWER_SHARD_5(
    "Power Shard (5)",
    time = 24.q,
    inputs = mapOf(Item.PURPLE_POWER_SLUG to 1.q),
    outputs = mapOf(Item.POWER_SHARD to 5.q),
    unlock = ItemCondition(Item.POWER_SHARD),
  ),

  CANDY_CANE(
    "Candy Cane",
    time = 12.q,
    inputs = mapOf(Item.FICSMAS_GIFT to 3.q),
    outputs = mapOf(Item.CANDY_CANE to 1.q),
    unlock = ItemCondition(Item.CANDY_CANE),
  ),

  CANDY_CANE_BASHER(
    "Candy Cane Basher",
    time = 80.q,
    inputs = mapOf(
      Item.XENO_ZAPPER to 2.q,
      Item.CANDY_CANE to 25.q,
      Item.FICSMAS_GIFT to 15.q,
    ),
    outputs = mapOf(Item.CANDY_CANE_BASHER to 1.q),
    unlock = ItemCondition(Item.CANDY_CANE_BASHER),
  ),

  FICSMAS_BOW(
    "FICSMAS Bow",
    time = 12.q,
    inputs = mapOf(Item.FICSMAS_GIFT to 2.q),
    outputs = mapOf(Item.FICSMAS_BOW to 1.q),
    unlock = ItemCondition(Item.FICSMAS_BOW),
  ),

  ACTUAL_SNOW(
    "Actual Snow",
    time = 12.q,
    inputs = mapOf(Item.FICSMAS_GIFT to 5.q),
    outputs = mapOf(Item.ACTUAL_SNOW to 2.q),
    unlock = ItemCondition(Item.ACTUAL_SNOW),
  ),

  COPPER_FICSMAS_ORNAMENT(
    "Copper FICSMAS Ornament",
    time = 12.q,
    inputs = mapOf(
      Item.RED_FICSMAS_ORNAMENT to 2.q,
      Item.COPPER_INGOT to 2.q,
    ),
    outputs = mapOf(Item.COPPER_FICSMAS_ORNAMENT to 1.q),
    unlock = ItemCondition(Item.COPPER_FICSMAS_ORNAMENT),
  ),

  IRON_FICSMAS_ORNAMENT(
    "Iron FICSMAS Ornament",
    time = 12.q,
    inputs = mapOf(
      Item.BLUE_FICSMAS_ORNAMENT to 3.q,
      Item.IRON_INGOT to 3.q,
    ),
    outputs = mapOf(Item.IRON_FICSMAS_ORNAMENT to 1.q),
    unlock = ItemCondition(Item.IRON_FICSMAS_ORNAMENT),
  ),

  RED_FICSMAS_ORNAMENT(
    "Red FICSMAS Ornament",
    time = 12.q,
    inputs = mapOf(Item.FICSMAS_GIFT to 1.q),
    outputs = mapOf(Item.RED_FICSMAS_ORNAMENT to 1.q),
    unlock = ItemCondition(Item.RED_FICSMAS_ORNAMENT),
  ),

  BLUE_FICSMAS_ORNAMENT(
    "Blue FICSMAS Ornament",
    time = 12.q,
    inputs = mapOf(Item.FICSMAS_GIFT to 1.q),
    outputs = mapOf(Item.BLUE_FICSMAS_ORNAMENT to 2.q),
    unlock = ItemCondition(Item.BLUE_FICSMAS_ORNAMENT),
  ),

  FICSMAS_ORNAMENT_BUNDLE(
    "FICSMAS Ornament Bundle",
    time = 12.q,
    inputs = mapOf(
      Item.COPPER_FICSMAS_ORNAMENT to 1.q,
      Item.IRON_FICSMAS_ORNAMENT to 1.q,
    ),
    outputs = mapOf(Item.FICSMAS_ORNAMENT_BUNDLE to 1.q),
    unlock = ItemCondition(Item.FICSMAS_ORNAMENT_BUNDLE),
  ),

  FICSMAS_DECORATION(
    "FICSMAS Decoration",
    time = 60.q,
    inputs = mapOf(
      Item.FICSMAS_TREE_BRANCH to 15.q,
      Item.FICSMAS_ORNAMENT_BUNDLE to 6.q,
    ),
    outputs = mapOf(Item.FICSMAS_DECORATION to 2.q),
    unlock = ItemCondition(Item.FICSMAS_DECORATION),
  ),

  FICSMAS_WONDER_STAR(
    "FICSMAS Wonder Star",
    time = 60.q,
    inputs = mapOf(
      Item.FICSMAS_DECORATION to 5.q,
      Item.CANDY_CANE to 20.q,
    ),
    outputs = mapOf(Item.FICSMAS_WONDER_STAR to 1.q),
    unlock = ItemCondition(Item.FICSMAS_WONDER_STAR),
  ),

  SNOWBALL(
    "Snowball",
    time = 12.q,
    inputs = mapOf(Item.ACTUAL_SNOW to 3.q),
    outputs = mapOf(Item.SNOWBALL to 1.q),
    unlock = ItemCondition(Item.SNOWBALL),
  ),

  FACTORY_CART(
    "Factory Cart™",
    time = 20.q,
    inputs = mapOf(
      Item.REINFORCED_IRON_PLATE to 4.q,
      Item.IRON_ROD to 4.q,
      Item.ROTOR to 2.q,
    ),
    outputs = mapOf(Item.FACTORY_CART to 1.q),
    unlock = ItemCondition(Item.FACTORY_CART),
  ),

  GOLDEN_FACTORY_CART(
    "Golden Factory Cart™",
    time = 20.q,
    inputs = mapOf(
      Item.CATERIUM_INGOT to 15.q,
      Item.IRON_ROD to 4.q,
      Item.ROTOR to 2.q,
    ),
    outputs = mapOf(Item.GOLDEN_FACTORY_CART to 1.q),
    unlock = ItemCondition(Item.GOLDEN_FACTORY_CART),
  );

  val rates = (inputs.mapValues { (_, amount) -> -amount } + outputs).mapValues { (_, amount) -> amount * 60.q / time }
}
