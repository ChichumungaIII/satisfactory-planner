package com.chichumunga.satisfactory.scripts.conversion

import app.game.data.Item
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.item.MajorItemGroup
import com.chichumunga.satisfactory.scripts.item.MinorItemGroup

data class ItemSpec(
  val enumName: String,
  val majorItemGroup: MajorItemGroup,
  val minorItemGroup: MinorItemGroup,
  val category: Item.Category,
)

val ITEM_SPECS = listOf(
  ItemSpec("IRON_ORE", MajorItemGroup.TIER_0, MinorItemGroup.GAME_START, Item.Category.RESOURCES),
  ItemSpec("IRON_INGOT", MajorItemGroup.TIER_0, MinorItemGroup.GAME_START, Item.Category.PARTS),
  ItemSpec("IRON_PLATE", MajorItemGroup.TIER_0, MinorItemGroup.GAME_START, Item.Category.PARTS),
  ItemSpec("IRON_ROD", MajorItemGroup.TIER_0, MinorItemGroup.GAME_START, Item.Category.PARTS),
  ItemSpec("XENO_ZAPPER", MajorItemGroup.TIER_0, MinorItemGroup.GAME_START, Item.Category.EQUIPMENT),

  ItemSpec("PORTABLE_MINER", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_1, Item.Category.EQUIPMENT),

  ItemSpec("COPPER_ORE", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_2, Item.Category.RESOURCES),
  ItemSpec("COPPER_INGOT", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_2, Item.Category.PARTS),
  ItemSpec("WIRE", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_2, Item.Category.PARTS),
  ItemSpec("CABLE", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_2, Item.Category.PARTS),

  ItemSpec("LIMESTONE", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_3, Item.Category.RESOURCES),
  ItemSpec("CONCRETE", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_3, Item.Category.PARTS),
  ItemSpec("SCREW", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_3, Item.Category.PARTS),
  ItemSpec("REINFORCED_IRON_PLATE", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_3, Item.Category.PARTS),

  ItemSpec("BIOMASS", MajorItemGroup.TIER_0, MinorItemGroup.HUB_UPGRADE_6, Item.Category.PARTS),


  ItemSpec("OBJECT_SCANNER", MajorItemGroup.TIER_1, MinorItemGroup.FIELD_RESEARCH, Item.Category.EQUIPMENT),
  ItemSpec("BEACON", MajorItemGroup.TIER_1, MinorItemGroup.FIELD_RESEARCH, Item.Category.EQUIPMENT),

  ItemSpec("COPPER_SHEET", MajorItemGroup.TIER_2, MinorItemGroup.PART_ASSEMBLY, Item.Category.PARTS),
  ItemSpec("ROTOR", MajorItemGroup.TIER_2, MinorItemGroup.PART_ASSEMBLY, Item.Category.PARTS),
  ItemSpec("MODULAR_FRAME", MajorItemGroup.TIER_2, MinorItemGroup.PART_ASSEMBLY, Item.Category.PARTS),
  ItemSpec("SMART_PLATING", MajorItemGroup.TIER_2, MinorItemGroup.PART_ASSEMBLY, Item.Category.PARTS),

  ItemSpec("CHAINSAW", MajorItemGroup.TIER_2, MinorItemGroup.OBSTACLE_CLEARING, Item.Category.EQUIPMENT),
  ItemSpec("SOLID_BIOFUEL", MajorItemGroup.TIER_2, MinorItemGroup.OBSTACLE_CLEARING, Item.Category.PARTS),

  ItemSpec("COLOR_CARTRIDGE", MajorItemGroup.TIER_2, MinorItemGroup.RESOURCE_SINK_BONUS_PROGRAM, Item.Category.PARTS),


  ItemSpec("COAL", MajorItemGroup.TIER_3, MinorItemGroup.COAL_POWER, Item.Category.RESOURCES),
  ItemSpec("WATER", MajorItemGroup.TIER_3, MinorItemGroup.COAL_POWER, Item.Category.RESOURCES),

  ItemSpec("STEEL_INGOT", MajorItemGroup.TIER_3, MinorItemGroup.BASIC_STEEL_PRODUCTION, Item.Category.PARTS),
  ItemSpec("STEEL_BEAM", MajorItemGroup.TIER_3, MinorItemGroup.BASIC_STEEL_PRODUCTION, Item.Category.PARTS),
  ItemSpec("STEEL_PIPE", MajorItemGroup.TIER_3, MinorItemGroup.BASIC_STEEL_PRODUCTION, Item.Category.PARTS),
  ItemSpec("VERSATILE_FRAMEWORK", MajorItemGroup.TIER_3, MinorItemGroup.BASIC_STEEL_PRODUCTION, Item.Category.PARTS),

  ItemSpec("XENO_BASHER", MajorItemGroup.TIER_3, MinorItemGroup.IMPROVED_MELEE_COMBAT, Item.Category.EQUIPMENT),


  ItemSpec(
    "ENCASED_INDUSTRIAL_BEAM",
    MajorItemGroup.TIER_4,
    MinorItemGroup.ADVANCED_STEEL_PRODUCTION,
    Item.Category.PARTS
  ),
  ItemSpec("STATOR", MajorItemGroup.TIER_4, MinorItemGroup.ADVANCED_STEEL_PRODUCTION, Item.Category.PARTS),
  ItemSpec("MOTOR", MajorItemGroup.TIER_4, MinorItemGroup.ADVANCED_STEEL_PRODUCTION, Item.Category.PARTS),
  ItemSpec("AUTOMATED_WIRING", MajorItemGroup.TIER_4, MinorItemGroup.ADVANCED_STEEL_PRODUCTION, Item.Category.PARTS),
  ItemSpec("HEAVY_MODULAR_FRAME", MajorItemGroup.TIER_4, MinorItemGroup.ADVANCED_STEEL_PRODUCTION, Item.Category.PARTS),


  ItemSpec("CRUDE_OIL", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.RESOURCES),
  ItemSpec("PLASTIC", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.PARTS),
  ItemSpec("HEAVY_OIL_RESIDUE", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.PARTS),
  ItemSpec("RUBBER", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.PARTS),
  ItemSpec("FUEL", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.PARTS),
  ItemSpec("POLYMER_RESIN", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.PARTS),
  ItemSpec("PETROLEUM_COKE", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.PARTS),
  ItemSpec("CIRCUIT_BOARD", MajorItemGroup.TIER_5, MinorItemGroup.OIL_PROCESSING, Item.Category.PARTS),

  ItemSpec("COMPUTER", MajorItemGroup.TIER_5, MinorItemGroup.INDUSTRIAL_MANUFACTURING, Item.Category.PARTS),
  ItemSpec("MODULAR_ENGINE", MajorItemGroup.TIER_5, MinorItemGroup.INDUSTRIAL_MANUFACTURING, Item.Category.PARTS),
  ItemSpec(
    "ADAPTIVE_CONTROL_UNIT",
    MajorItemGroup.TIER_5,
    MinorItemGroup.INDUSTRIAL_MANUFACTURING,
    Item.Category.PARTS
  ),

  ItemSpec("EMPTY_CANISTER", MajorItemGroup.TIER_5, MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT, Item.Category.PARTS),
  ItemSpec("PACKAGED_WATER", MajorItemGroup.TIER_5, MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT, Item.Category.PARTS),
  ItemSpec("PACKAGED_OIL", MajorItemGroup.TIER_5, MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT, Item.Category.PARTS),
  ItemSpec("PACKAGED_FUEL", MajorItemGroup.TIER_5, MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT, Item.Category.PARTS),
  ItemSpec(
    "PACKAGED_FUEL_EXPERIMENTAL",
    MajorItemGroup.TIER_5,
    MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT,
    Item.Category.PARTS
  ),
  ItemSpec(
    "PACKAGED_HEAVY_OIL_RESIDUE",
    MajorItemGroup.TIER_5,
    MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT,
    Item.Category.PARTS
  ),
  ItemSpec(
    "PACKAGED_LIQUID_BIOFUEL",
    MajorItemGroup.TIER_5,
    MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT,
    Item.Category.PARTS
  ),
  ItemSpec(
    "PACKAGED_LIQUID_BIOFUEL_EXPERIMENTAL",
    MajorItemGroup.TIER_5,
    MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT,
    Item.Category.PARTS
  ),
  ItemSpec("LIQUID_BIOFUEL", MajorItemGroup.TIER_5, MinorItemGroup.ALTERNATIVE_FLUID_TRANSPORT, Item.Category.PARTS),

  ItemSpec("GAS_MASK", MajorItemGroup.TIER_5, MinorItemGroup.GAS_MASK, Item.Category.EQUIPMENT),
  ItemSpec("GAS_FILTER", MajorItemGroup.TIER_5, MinorItemGroup.GAS_MASK, Item.Category.PARTS),


  ItemSpec(
    "CATERIUM_ORE",
    MajorItemGroup.TIER_6,
    MinorItemGroup.EXPANDED_POWER_INFRASTRUCTURE,
    Item.Category.RESOURCES
  ),
  ItemSpec("CATERIUM_INGOT", MajorItemGroup.TIER_6, MinorItemGroup.EXPANDED_POWER_INFRASTRUCTURE, Item.Category.PARTS),
  ItemSpec("QUICKWIRE", MajorItemGroup.TIER_6, MinorItemGroup.EXPANDED_POWER_INFRASTRUCTURE, Item.Category.PARTS),
  ItemSpec("ZIPLINE", MajorItemGroup.TIER_6, MinorItemGroup.EXPANDED_POWER_INFRASTRUCTURE, Item.Category.EQUIPMENT),
  ItemSpec("AI_LIMITER", MajorItemGroup.TIER_6, MinorItemGroup.EXPANDED_POWER_INFRASTRUCTURE, Item.Category.PARTS),
  ItemSpec(
    "HIGH_SPEED_CONNECTOR",
    MajorItemGroup.TIER_6,
    MinorItemGroup.EXPANDED_POWER_INFRASTRUCTURE,
    Item.Category.PARTS
  ),

  ItemSpec("JETPACK", MajorItemGroup.TIER_6, MinorItemGroup.JETPACK, Item.Category.EQUIPMENT),
  ItemSpec("JETPACK_EXPERIMENTAL", MajorItemGroup.TIER_6, MinorItemGroup.JETPACK, Item.Category.EQUIPMENT),


  ItemSpec("BAUXITE", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.RESOURCES),
  ItemSpec("ALUMINA_SOLUTION", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("PACKAGED_ALUMINA_SOLUTION", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("ALUMINUM_SCRAP", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("ALUMINUM_INGOT", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("ALCLAD_ALUMINUM_SHEET", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("ALUMINUM_CASING", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("RAW_QUARTZ", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.RESOURCES),
  ItemSpec("QUARTZ_CRYSTAL", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("SILICA", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("CRYSTAL_OSCILLATOR", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),
  ItemSpec("RADIO_CONTROL_UNIT", MajorItemGroup.TIER_7, MinorItemGroup.BAUXITE_REFINEMENT, Item.Category.PARTS),

  ItemSpec("HAZMAT_SUIT", MajorItemGroup.TIER_7, MinorItemGroup.HAZMAT_SUIT, Item.Category.EQUIPMENT),
  ItemSpec("IODINE_INFUSED_FILTER", MajorItemGroup.TIER_7, MinorItemGroup.HAZMAT_SUIT, Item.Category.PARTS),

  ItemSpec("SULFUR", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.RESOURCES),
  ItemSpec("BLACK_POWDER", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("COMPACTED_COAL", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("TURBOFUEL", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("PACKAGED_TURBOFUEL", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec(
    "PACKAGED_TURBOFUEL_EXPERIMENTAL",
    MajorItemGroup.TIER_7,
    MinorItemGroup.AERONAUTICAL_ENGINEERING,
    Item.Category.PARTS
  ),
  ItemSpec(
    "NOBELISK_DETONATOR",
    MajorItemGroup.TIER_7,
    MinorItemGroup.AERONAUTICAL_ENGINEERING,
    Item.Category.EQUIPMENT
  ),
  ItemSpec("NOBELISK", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("SMOKELESS_POWDER", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("CLUSTER_NOBELISK", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("EXPLOSIVE_REBAR", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("RIFLE", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.EQUIPMENT),
  ItemSpec("RIFLE_AMMO", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("SULFURIC_ACID", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec(
    "PACKAGED_SULFURIC_ACID",
    MajorItemGroup.TIER_7,
    MinorItemGroup.AERONAUTICAL_ENGINEERING,
    Item.Category.PARTS
  ),
  ItemSpec("BATTERY", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec("SUPERCOMPUTER", MajorItemGroup.TIER_7, MinorItemGroup.AERONAUTICAL_ENGINEERING, Item.Category.PARTS),
  ItemSpec(
    "ASSEMBLY_DIRECTOR_SYSTEM",
    MajorItemGroup.TIER_7,
    MinorItemGroup.AERONAUTICAL_ENGINEERING,
    Item.Category.PARTS
  ),

  ItemSpec("HOVER_PACK", MajorItemGroup.TIER_7, MinorItemGroup.HOVER_PACK, Item.Category.EQUIPMENT),
  ItemSpec("HOVER_PACK_EXPERIMENTAL", MajorItemGroup.TIER_7, MinorItemGroup.HOVER_PACK, Item.Category.EQUIPMENT),


  ItemSpec("URANIUM", MajorItemGroup.TIER_8, MinorItemGroup.NUCLEAR_POWER, Item.Category.RESOURCES),
  ItemSpec("ENCASED_URANIUM_CELL", MajorItemGroup.TIER_8, MinorItemGroup.NUCLEAR_POWER, Item.Category.PARTS),
  ItemSpec("ELECTROMAGNETIC_CONTROL_ROD", MajorItemGroup.TIER_8, MinorItemGroup.NUCLEAR_POWER, Item.Category.PARTS),
  ItemSpec("URANIUM_FUEL_ROD", MajorItemGroup.TIER_8, MinorItemGroup.NUCLEAR_POWER, Item.Category.PARTS),
  ItemSpec("URANIUM_WASTE", MajorItemGroup.TIER_8, MinorItemGroup.NUCLEAR_POWER, Item.Category.PARTS),
  ItemSpec("MAGNETIC_FIELD_GENERATOR", MajorItemGroup.TIER_8, MinorItemGroup.NUCLEAR_POWER, Item.Category.PARTS),

  ItemSpec("EMPTY_FLUID_TANK", MajorItemGroup.TIER_8, MinorItemGroup.ADVANCED_ALUMINUM_PRODUCTION, Item.Category.PARTS),
  ItemSpec(
    "PACKAGED_NITROGEN_GAS",
    MajorItemGroup.TIER_8,
    MinorItemGroup.ADVANCED_ALUMINUM_PRODUCTION,
    Item.Category.PARTS
  ),
  ItemSpec("HEAT_SINK", MajorItemGroup.TIER_8, MinorItemGroup.ADVANCED_ALUMINUM_PRODUCTION, Item.Category.PARTS),
  ItemSpec("COOLING_SYSTEM", MajorItemGroup.TIER_8, MinorItemGroup.ADVANCED_ALUMINUM_PRODUCTION, Item.Category.PARTS),
  ItemSpec(
    "FUSED_MODULAR_FRAME",
    MajorItemGroup.TIER_8,
    MinorItemGroup.ADVANCED_ALUMINUM_PRODUCTION,
    Item.Category.PARTS
  ),

  ItemSpec("TURBO_MOTOR", MajorItemGroup.TIER_8, MinorItemGroup.LEADING_EDGE_PRODUCTION, Item.Category.PARTS),
  ItemSpec(
    "THERMAL_PROPULSION_ROCKET",
    MajorItemGroup.TIER_8,
    MinorItemGroup.LEADING_EDGE_PRODUCTION,
    Item.Category.PARTS
  ),


  ItemSpec("WOOD", MajorItemGroup.NATURE, MinorItemGroup.MILESTONES, Item.Category.NATURE),
  ItemSpec("LEAVES", MajorItemGroup.NATURE, MinorItemGroup.MILESTONES, Item.Category.NATURE),
  ItemSpec("FLOWER_PETALS", MajorItemGroup.NATURE, MinorItemGroup.MILESTONES, Item.Category.NATURE),

  ItemSpec("HOG_REMAINS", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.NATURE),
  ItemSpec("HATCHER_REMAINS", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.NATURE),
  ItemSpec("STINGER_REMAINS", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.NATURE),
  ItemSpec("PLASMA_SPITTER_REMAINS", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.NATURE),
  ItemSpec("ALIEN_PROTEIN", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.PARTS),
  ItemSpec("ALIEN_DNA_CAPSULE", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.PARTS),
  ItemSpec("REBAR_GUN", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.EQUIPMENT),
  ItemSpec("IRON_REBAR", MajorItemGroup.NATURE, MinorItemGroup.ALIEN_ORGANISMS, Item.Category.PARTS),

  ItemSpec("MYCELIA", MajorItemGroup.NATURE, MinorItemGroup.MYCELIA, Item.Category.NATURE),

  ItemSpec("BACON_AGARIC", MajorItemGroup.NATURE, MinorItemGroup.NUTRIENTS, Item.Category.NATURE),
  ItemSpec("BERYL_NUT", MajorItemGroup.NATURE, MinorItemGroup.NUTRIENTS, Item.Category.NATURE),
  ItemSpec("PALEBERRY", MajorItemGroup.NATURE, MinorItemGroup.NUTRIENTS, Item.Category.NATURE),
  ItemSpec("MEDICINAL_INHALER", MajorItemGroup.NATURE, MinorItemGroup.NUTRIENTS, Item.Category.EQUIPMENT),

  ItemSpec("BLUE_POWER_SLUG", MajorItemGroup.NATURE, MinorItemGroup.POWER_SLUGS, Item.Category.NATURE),
  ItemSpec("YELLOW_POWER_SLUG", MajorItemGroup.NATURE, MinorItemGroup.POWER_SLUGS, Item.Category.NATURE),
  ItemSpec("PURPLE_POWER_SLUG", MajorItemGroup.NATURE, MinorItemGroup.POWER_SLUGS, Item.Category.NATURE),

  ItemSpec("HARD_DRIVE", MajorItemGroup.NATURE, MinorItemGroup.HARD_DRIVES, Item.Category.NATURE),
)
val ITEM_SPECS_INDEX = ITEM_SPECS.associateBy { it.enumName }
val ITEM_SPEC_ORDER = compareBy<ItemEnum> {
  ITEM_SPECS_INDEX[it.enumName]?.let { ITEM_SPECS.indexOf(it) } ?: Int.MAX_VALUE
}