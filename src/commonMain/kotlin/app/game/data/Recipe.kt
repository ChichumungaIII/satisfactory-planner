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
    unlock = ResearchCondition(Research.IRON_ALLOY_INGOT)
  ),

  IRON_PLATE(
    "Iron Plate",
    time = 6.q,
    inputs = mapOf(Item.IRON_INGOT to 3.q),
    outputs = mapOf(Item.IRON_PLATE to 2.q),
    unlock = ItemCondition(Item.IRON_PLATE),
  ),

  IRON_ROD(
    "Iron Rod",
    time = 4.q,
    inputs = mapOf(Item.IRON_INGOT to 1.q),
    outputs = mapOf(Item.IRON_ROD to 1.q),
    unlock = ItemCondition(Item.IRON_ROD),
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

  COPPER_INGOT(
    "Copper Ingot",
    time = 2.q,
    inputs = mapOf(Item.COPPER_ORE to 1.q),
    outputs = mapOf(Item.COPPER_INGOT to 1.q),
    unlock = ItemCondition(Item.COPPER_INGOT),
  ),

  WIRE(
    "Wire",
    time = 4.q,
    inputs = mapOf(Item.COPPER_INGOT to 1.q),
    outputs = mapOf(Item.WIRE to 2.q),
    unlock = ItemCondition(Item.WIRE),
  ),
  IRON_WIRE(
    "Iron Wire",
    time = 24.q,
    inputs = mapOf(Item.IRON_INGOT to 5.q),
    outputs = mapOf(Item.WIRE to 9.q),
    unlock = ResearchCondition(Research.IRON_WIRE)
  ),

  CABLE(
    "Cable",
    time = 2.q,
    inputs = mapOf(Item.WIRE to 2.q),
    outputs = mapOf(Item.CABLE to 1.q),
    unlock = ItemCondition(Item.CABLE),
  ),

  CONCRETE(
    "Concrete",
    time = 4.q,
    inputs = mapOf(Item.LIMESTONE to 3.q),
    outputs = mapOf(Item.CONCRETE to 1.q),
    unlock = ItemCondition(Item.CONCRETE),
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
    unlock = ResearchCondition(Research.CAST_SCREW)
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
  STITCHED_IRON_PLATE(
    "Stitched Iron Plate",
    time = 32.q,
    inputs = mapOf(
      Item.IRON_PLATE to 10.q,
      Item.WIRE to 20.q,
    ),
    outputs = mapOf(Item.REINFORCED_IRON_PLATE to 3.q),
    unlock = ResearchCondition(Research.STITCHED_IRON_PLATE)
  ),

  BIOMASS_WOOD(
    "Biomass (Wood)",
    time = 4.q,
    inputs = mapOf(Item.WOOD to 4.q),
    outputs = mapOf(Item.BIOMASS to 20.q),
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
    unlock = ResearchCondition(Research.MYCELIA)
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

  COPPER_SHEET(
    "Copper Sheet",
    time = 6.q,
    inputs = mapOf(Item.COPPER_INGOT to 2.q),
    outputs = mapOf(Item.COPPER_SHEET to 1.q),
    unlock = ItemCondition(Item.COPPER_SHEET),
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

  SOLID_BIOFUEL(
    "Solid Biofuel",
    time = 4.q,
    inputs = mapOf(Item.BIOMASS to 8.q),
    outputs = mapOf(Item.SOLID_BIOFUEL to 4.q),
    unlock = ItemCondition(Item.SOLID_BIOFUEL),
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

  COLOR_CARTRIDGE(
    "Color Cartridge",
    time = 6.q,
    inputs = mapOf(Item.FLOWER_PETALS to 5.q),
    outputs = mapOf(Item.COLOR_CARTRIDGE to 10.q),
    unlock = ItemCondition(Item.COLOR_CARTRIDGE),
  ),
}