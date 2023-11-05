package com.chichumunga.satisfactory.scripts.conversion

import app.game.data.Item
import app.game.data.Research
import com.chichumunga.satisfactory.scripts.condition.ConditionWriter
import com.chichumunga.satisfactory.scripts.condition.EnumCondition
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.json.ComponentSchema
import com.chichumunga.satisfactory.scripts.json.RecipeSchema
import com.chichumunga.satisfactory.scripts.recipe.RecipeEnum

fun RecipeSchema.convertToRecipeEnum(items: Map<String, ItemEnum>): RecipeEnum {
  val outputs = products.convertToPairs(items)
  val primary = outputs.firstOrNull()?.first
  val enumName = uppercaseUnderscore(name)
  return RecipeEnum(
    primary = primary,
    enumName = enumName,
    displayName = name,
    time = duration,
    inputs = ingredients.convertToPairs(items),
    outputs = outputs,
    power = minPower?.let { min -> maxPower?.let { max -> min to max } },
    unlock = run {
      CONDITION_OVERRIDES[enumName]?.also { return@run it }
      if (alternate) return@run EnumCondition(enumValueOf<Research>(enumName))
      return@run primary?.let { EnumCondition(it) } ?: ConditionWriter.parse(unlockedBy)
    },
    alternate = alternate
  )
}

fun Iterable<ComponentSchema>.convertToPairs(items: Map<String, ItemEnum>) = map { it.convertToPairs(items) }
fun ComponentSchema.convertToPairs(items: Map<String, ItemEnum>): Pair<Item, Pair<Int, Int>> {
  val itemEnum = items[item] ?: error("Unknown item [$item].")
  return enumValueOf<Item>(itemEnum.enumName) to amount.rationalize()
}

private val CONDITION_OVERRIDES = mapOf(
  "POLYESTER_FABRIC" to EnumCondition(Research.SYNTHETIC_POLYESTER_FABRIC),
)
