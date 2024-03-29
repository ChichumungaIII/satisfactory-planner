package com.chichumunga.satisfactory.scripts.conversion

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import com.chichumunga.satisfactory.scripts.condition.CompoundCondition
import com.chichumunga.satisfactory.scripts.condition.ConditionWriter
import com.chichumunga.satisfactory.scripts.condition.EnumCondition
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.json.ItemSchema

fun ItemSchema.convertToItemEnum(): ItemEnum {
  val suffix = "_EXPERIMENTAL".takeIf { experimental && !stable } ?: ""
  val enumName = "${uppercaseUnderscore(name)}$suffix"
  return ItemEnum(
    enumName = enumName,
    displayName = name,
    category = getItemCategory(enumName),
    stack = stackSize.takeIf { it > 0 && form == "solid" },
    sink = sinkPoints.takeIf { it > 0 && form == "solid" },
    energy = energy.takeIf { it > 0.0 }?.downcast(),
    radiation = radioactive.takeIf { it > 0.0 }?.rationalize(),
    stable = stable,
    experimental = experimental,
    unlock = CONDITION_OVERRIDES[enumName]?.also { println("Overriding condition for [$enumName].") }
      ?: ConditionWriter.parse(unlockedBy),
  )
}

private val CONDITION_OVERRIDES = mapOf(
  "IRON_ORE" to EnumCondition(Phase.GAME_START),
  "COPPER_ORE" to EnumCondition(Milestone.HUB_UPGRADE_2),
  "LIMESTONE" to EnumCondition(Milestone.HUB_UPGRADE_3),
  "COAL" to CompoundCondition(
    "any",
    listOf(
      EnumCondition(Milestone.COAL_POWER),
      EnumCondition(Research.COMPACTED_COAL),
    )
  ),
  "WATER" to CompoundCondition(
    "any",
    listOf(
      EnumCondition(Milestone.COAL_POWER),
      EnumCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
      EnumCondition(Milestone.BAUXITE_REFINEMENT),
      EnumCondition(Milestone.AERONAUTICAL_ENGINEERING),
      EnumCondition(Milestone.PARTICLE_ENRICHMENT),
    )
  ),
  "CRUDE_OIL" to CompoundCondition(
    "any",
    listOf(
      EnumCondition(Milestone.OIL_PROCESSING),
      EnumCondition(Milestone.ALTERNATIVE_FLUID_TRANSPORT),
    )
  ),
  "CATERIUM_ORE" to CompoundCondition(
    "any",
    listOf(
      EnumCondition(Milestone.EXPANDED_POWER_INFRASTRUCTURE),
      EnumCondition(Research.UNKNOWN_METAL),
    )
  ),
  "RAW_QUARTZ" to CompoundCondition(
    "any",
    listOf(
      EnumCondition(Milestone.BAUXITE_REFINEMENT),
      EnumCondition(Research.UNKNOWN_CRYSTALLINE_MATERIAL),
    )
  ),
  "BAUXITE" to EnumCondition(Milestone.BAUXITE_REFINEMENT),
  "SULFUR" to CompoundCondition(
    "any",
    listOf(
      EnumCondition(Milestone.AERONAUTICAL_ENGINEERING),
      EnumCondition(Research.UNKNOWN_CHEMICAL_ELEMENT),
    )
  ),
  "URANIUM" to EnumCondition(Milestone.NUCLEAR_POWER),
  "URANIUM_WASTE" to EnumCondition(Milestone.NUCLEAR_POWER),
  "PLUTONIUM_WASTE" to EnumCondition(Milestone.PARTICLE_ENRICHMENT),
)
