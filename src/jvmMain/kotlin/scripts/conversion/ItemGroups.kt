package com.chichumunga.satisfactory.scripts.conversion

import app.game.data.Item.Category
import com.chichumunga.satisfactory.scripts.conversion.CategorizedItem.Companion.of
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.recipe.RecipeEnum


data class CategorizedItem(val enumName: String, val category: Category) {
  companion object {
    infix fun String.of(category: Category) = CategorizedItem(this, category)
  }
}

private val ITEM_GROUPS = mapOf(
  "Tier 0" to mapOf(
    "Game Start" to listOf(
      "IRON_ORE" of Category.RESOURCES,
      "IRON_INGOT" of Category.PARTS,
      "IRON_PLATE" of Category.PARTS,
      "IRON_ROD" of Category.PARTS,
    ),
    "HUB Upgrade 1" to listOf(
      "PORTABLE_MINER" of Category.EQUIPMENT,
    ),
    "HUB Upgrade 2" to listOf(
      "COPPER_ORE" of Category.RESOURCES,
      "COPPER_INGOT" of Category.PARTS,
      "WIRE" of Category.PARTS,
      "CABLE" of Category.PARTS,
    ),
    "HUB Upgrade 3" to listOf(
      "LIMESTONE" of Category.RESOURCES,
      "CONCRETE" of Category.PARTS,
      "SCREW" of Category.PARTS,
      "REINFORCED_IRON_PLATE" of Category.PARTS,
    ),
    "HUB Upgrade 6" to listOf(
      "BIOMASS" of Category.PARTS,
    ),
  ),
  "Tier 1" to mapOf(
    "Field Research" to listOf(
      "OBJECT_SCANNER" of Category.EQUIPMENT,
      "BEACON" of Category.EQUIPMENT,
    ),
  ),
  "Tier 2" to mapOf(
    "Part Assembly" to listOf(
      "COPPER_SHEET" of Category.PARTS,
      "ROTOR" of Category.PARTS,
      "MODULAR_FRAME" of Category.PARTS,
      "SMART_PLATING" of Category.PARTS,
    ),
    "Obstacle Clearing" to listOf(
      "CHAINSAW" of Category.EQUIPMENT,
      "SOLID_BIOFUEL" of Category.PARTS,
    ),
    "Resource Sink Bonus Program" to listOf(
      "COLOR_CARTRIDGE" of Category.PARTS,
    ),
  ),
  "Tier 3" to mapOf(
    "Coal Power" to listOf(
      "COAL" of Category.RESOURCES,
      "WATER" of Category.RESOURCES,
    ),
    "Basic Steel Production" to listOf(
      "STEEL_INGOT" of Category.PARTS,
      "STEEL_BEAM" of Category.PARTS,
      "STEEL_PIPE" of Category.PARTS,
      "VERSATILE_FRAMEWORK" of Category.PARTS,
    ),
  ),
  "Tier 4" to mapOf(
    "Advanced Steel Production" to listOf(
      "ENCASED_INDUSTRIAL_BEAM" of Category.PARTS,
      "STATOR" of Category.PARTS,
      "MOTOR" of Category.PARTS,
      "AUTOMATED_WIRING" of Category.PARTS,
      "HEAVY_MODULAR_FRAME" of Category.PARTS,
    ),
  ),
  "Tier 5" to mapOf(
    "Oil Processing" to listOf(
      "CRUDE_OIL" of Category.RESOURCES,
      "PLASTIC" of Category.PARTS,
      "HEAVY_OIL_RESIDUE" of Category.PARTS,
      "RUBBER" of Category.PARTS,
      "FUEL" of Category.PARTS,
      "POLYMER_RESIN" of Category.PARTS,
      "PETROLEUM_COKE" of Category.PARTS,
      "CIRCUIT_BOARD" of Category.PARTS,
    ),
    "Industrial Manufacturing" to listOf(
      "COMPUTER" of Category.PARTS,
      "MODULAR_ENGINE" of Category.PARTS,
      "ADAPTIVE_CONTROL_UNIT" of Category.PARTS,
    ),
    "Alternative Fluid Transport" to listOf(
      "EMPTY_CANISTER" of Category.PARTS,
      "PACKAGED_WATER" of Category.PARTS,
      "PACKAGED_OIL" of Category.PARTS,
      "PACKAGED_FUEL" of Category.PARTS,
      "PACKAGED_FUEL_EXPERIMENTAL" of Category.PARTS,
      "PACKAGED_HEAVY_OIL_RESIDUE" of Category.PARTS,
      "PACKAGED_LIQUID_BIOFUEL" of Category.PARTS,
      "PACKAGED_LIQUID_BIOFUEL_EXPERIMENTAL" of Category.PARTS,
      "LIQUID_BIOFUEL" of Category.PARTS,
    ),
  ),
  "Tier 6" to mapOf(
    "Expanded Power Infrastructure" to listOf(
      "CATERIUM_ORE" of Category.RESOURCES,
      "CATERIUM_INGOT" of Category.PARTS,
      "QUICKWIRE" of Category.PARTS,
      "ZIPLINE" of Category.EQUIPMENT,
      "AI_LIMITER" of Category.PARTS,
      "HIGH_SPEED_CONNECTOR" of Category.PARTS,
    ),
  ),
  "Tier 7" to mapOf(
    "Bauxite Refinement" to listOf(
      "BAUXITE" of Category.RESOURCES,
      "ALUMINA_SOLUTION" of Category.PARTS,
      "PACKAGED_ALUMINA_SOLUTION" of Category.PARTS,
      "ALUMINUM_SCRAP" of Category.PARTS,
      "ALUMINUM_INGOT" of Category.PARTS,
      "ALCLAD_ALUMINUM_SHEET" of Category.PARTS,
      "ALUMINUM_CASING" of Category.PARTS,
      "RAW_QUARTZ" of Category.RESOURCES,
      "QUARTZ_CRYSTAL" of Category.PARTS,
      "SILICA" of Category.PARTS,
      "CRYSTAL_OSCILLATOR" of Category.PARTS,
      "RADIO_CONTROL_UNIT" of Category.PARTS,
    ),
    "Aeronautical Engineering" to listOf(
      "SULFUR" of Category.RESOURCES,
      "BLACK_POWDER" of Category.PARTS,
      "COMPACTED_COAL" of Category.PARTS,
      "TURBOFUEL" of Category.PARTS,
      "PACKAGED_TURBOFUEL" of Category.PARTS,
      "PACKAGED_TURBOFUEL_EXPERIMENTAL" of Category.PARTS,
      "SMOKELESS_POWDER" of Category.PARTS,
      "SULFURIC_ACID" of Category.PARTS,
      "PACKAGED_SULFURIC_ACID" of Category.PARTS,
      "BATTERY" of Category.PARTS,
      "SUPERCOMPUTER" of Category.PARTS,
      "ASSEMBLY_DIRECTOR_SYSTEM" of Category.PARTS,
    ),
  ),
  "Tier 8" to mapOf(
    "Nuclear Power" to listOf(
      "URANIUM" of Category.RESOURCES,
      "ENCASED_URANIUM_CELL" of Category.PARTS,
      "ELECTROMAGNETIC_CONTROL_ROD" of Category.PARTS,
      "URANIUM_FUEL_ROD" of Category.PARTS,
      "URANIUM_WASTE" of Category.PARTS,
      "MAGNETIC_FIELD_GENERATOR" of Category.PARTS,
    ),
    "Advanced Aluminum Production" to listOf(
      "NITROGEN_GAS" of Category.RESOURCES,
      "EMPTY_FLUID_TANK" of Category.PARTS,
      "PACKAGED_NITROGEN_GAS" of Category.PARTS,
      "HEAT_SINK" of Category.PARTS,
      "COOLING_SYSTEM" of Category.PARTS,
      "FUSED_MODULAR_FRAME" of Category.PARTS,
    ),
    "Leading-edge Production" to listOf(
      "TURBO_MOTOR" of Category.PARTS,
      "THERMAL_PROPULSION_ROCKET" of Category.PARTS,
    ),
    "Particle Enrichment" to listOf(
      "NITRIC_ACID" of Category.PARTS,
      "PACKAGED_NITRIC_ACID" of Category.PARTS,
      "NON_FISSILE_URANIUM" of Category.PARTS,
      "PLUTONIUM_PELLET" of Category.PARTS,
      "ENCASED_PLUTONIUM_CELL" of Category.PARTS,
      "PLUTONIUM_FUEL_ROD" of Category.PARTS,
      "PLUTONIUM_WASTE" of Category.PARTS,
      "COPPER_POWDER" of Category.PARTS,
      "PRESSURE_CONVERSION_CUBE" of Category.PARTS,
      "NUCLEAR_PASTA" of Category.PARTS,
    ),
  ),
  "Equipment" to mapOf(
    "Transportation" to listOf(
      "BLADE_RUNNERS" of Category.EQUIPMENT,
      "PARACHUTE" of Category.EQUIPMENT,
      "PARACHUTE_EXPERIMENTAL" of Category.EQUIPMENT,
      "JETPACK" of Category.EQUIPMENT,
      "JETPACK_EXPERIMENTAL" of Category.EQUIPMENT,
      "HOVER_PACK" of Category.EQUIPMENT,
      "HOVER_PACK_EXPERIMENTAL" of Category.EQUIPMENT,
    ),
    "Health and Safety" to listOf(
      "MEDICINAL_INHALER" of Category.EQUIPMENT,
      "GAS_MASK" of Category.EQUIPMENT,
      "GAS_FILTER" of Category.CONSUMABLES,
      "HAZMAT_SUIT" of Category.EQUIPMENT,
      "IODINE_INFUSED_FILTER" of Category.CONSUMABLES,
    ),
    "Melee Weapons" to listOf(
      "XENO_ZAPPER" of Category.EQUIPMENT,
      "XENO_BASHER" of Category.EQUIPMENT,
    ),
    "Rebar Gun" to listOf(
      "REBAR_GUN" of Category.EQUIPMENT,
      "IRON_REBAR" of Category.CONSUMABLES,
      "STUN_REBAR" of Category.CONSUMABLES,
      "SHATTER_REBAR" of Category.CONSUMABLES,
      "EXPLOSIVE_REBAR" of Category.CONSUMABLES,
    ),
    "Rifle" to listOf(
      "RIFLE" of Category.EQUIPMENT,
      "RIFLE_AMMO" of Category.CONSUMABLES,
      "HOMING_RIFLE_AMMO" of Category.CONSUMABLES,
      "TURBO_RIFLE_AMMO" of Category.CONSUMABLES,
    ),
    "Nobelisk" to listOf(
      "NOBELISK_DETONATOR" of Category.EQUIPMENT,
      "NOBELISK" of Category.CONSUMABLES,
      "GAS_NOBELISK" of Category.CONSUMABLES,
      "PULSE_NOBELISK" of Category.CONSUMABLES,
      "CLUSTER_NOBELISK" of Category.CONSUMABLES,
      "NUKE_NOBELISK" of Category.CONSUMABLES,
    ),
  ),
  "Nature" to mapOf(
    "Milestones" to listOf(
      "WOOD" of Category.NATURE,
      "LEAVES" of Category.NATURE,
      "FLOWER_PETALS" of Category.NATURE,
    ),
    "Alien Organisms" to listOf(
      "HOG_REMAINS" of Category.NATURE,
      "HATCHER_REMAINS" of Category.NATURE,
      "STINGER_REMAINS" of Category.NATURE,
      "PLASMA_SPITTER_REMAINS" of Category.NATURE,
      "ALIEN_PROTEIN" of Category.PARTS,
      "ALIEN_DNA_CAPSULE" of Category.PARTS,
    ),
    "Mycelia" to listOf(
      "MYCELIA" of Category.NATURE,
    ),
    "Nutrients" to listOf(
      "BACON_AGARIC" of Category.NATURE,
      "BERYL_NUT" of Category.NATURE,
      "PALEBERRY" of Category.NATURE,
    ),
    "Power Slugs" to listOf(
      "BLUE_POWER_SLUG" of Category.NATURE,
      "YELLOW_POWER_SLUG" of Category.NATURE,
      "PURPLE_POWER_SLUG" of Category.NATURE,
    ),
    "Specialty" to listOf(
      "HARD_DRIVE" of Category.SPECIALTY,
    ),
  ),
)

private val MAJOR_GROUP_INDEX = ITEM_GROUPS.flatMap { (major, minorMap) ->
  minorMap.values.flatten().map { it.enumName to major }
}.toMap()

fun getMajorGroup(enumName: String) = MAJOR_GROUP_INDEX[enumName] ?: "Uncategorized"

private val MINOR_GROUP_INDEX = ITEM_GROUPS.values.flatMap { minorMap ->
  minorMap.flatMap { (minor, items) -> items.map { it.enumName to minor } }
}.toMap()

fun getMinorGroup(enumName: String) = MINOR_GROUP_INDEX[enumName] ?: "Uncategorized"

private val ITEM_CATEGORIES = ITEM_GROUPS.values.flatMap { it.values }.flatten()
private val ITEM_CATEGORIES_INDEX = ITEM_CATEGORIES.associateBy { it.enumName }
fun getItemCategory(enumName: String) = ITEM_CATEGORIES_INDEX[enumName]?.category ?: Category.UNCATEGORIZED

val ITEM_ENUM_NAME_COMPARATOR = compareBy<String> { enumName ->
  ITEM_CATEGORIES_INDEX[enumName]?.let { ITEM_CATEGORIES.indexOf(it) } ?: Int.MAX_VALUE
}
val ITEM_ENUM_COMPARATOR = compareBy(ITEM_ENUM_NAME_COMPARATOR) { it: ItemEnum -> it.enumName }
val RECIPE_ENUM_COMPARATOR = compareBy(ITEM_ENUM_NAME_COMPARATOR) { it: RecipeEnum -> it.primary!!.name }
