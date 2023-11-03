package com.chichumunga.satisfactory.scripts

import app.game.data.Item
import app.game.data.Milestone
import app.game.data.Research
import com.chichumunga.satisfactory.scripts.WriteableItem.UnlockMilestone
import com.chichumunga.satisfactory.scripts.WriteableItem.UnlockResearch
import com.chichumunga.satisfactory.scripts.WriteableItem.WriteableUnlock
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive

data class ItemSchema(private val json: JsonObject) {
  private val name = json["name"]?.jsonPrimitive?.content ?: error("name")
  private val unlockedBy by lazy { string("unlockedBy") }
  private val stackSize by lazy { int("stackSize") }
  private val energy by lazy { double("energy") }
  private val radioactive by lazy { double("radioactive") }
  private val canBeDiscarded by lazy { boolean("canBeDiscarded") }
  private val sinkPoints by lazy { int("sinkPoints") }
  private val form by lazy { string("form") }
  private val stable by lazy { boolean("stable") }

  fun toWriteable() =
    if (stable)
      WriteableItem(
        displayName = name,
        category = Item.Category.PARTS, // TODO
        stack = stackSize.takeUnless { form == "liquid" },
        sink = sinkPoints.takeIf { canBeDiscarded },
        energy = energy.takeIf { it > 0.0 }?.downcast(),
        radiation = radioactive.takeIf { it > 0.0 }?.split(),
        unlock = unlockConditions(),
      )
    else null

  private fun unlockConditions() =
    unlockedBy.split("OR").map { unlockCondition(it) }

  private fun unlockCondition(text: String): WriteableUnlock =
    text.substringAfter("-").trim().replace(Regex("""\s+|\s*-\s*"""), "_")
      .let { name ->
        val milestone = enumValueOfOrNull<Milestone>(name)?.let { UnlockMilestone(it) }
        val research = enumValueOfOrNull<Research>(name)?.let { UnlockResearch(it) }
        return milestone ?: research ?: error("Unhandled condition: [$text]")
      }

  private fun Double.split(): Pair<Int, Int> {
    return try {
      downcast() to 1
    } catch (e: IllegalStateException) {
      when (this) {
        0.5 -> 1 to 2
        0.75 -> 3 to 4
        else -> error("split($this)")
      }
    }
  }

  private fun Double.downcast(): Int {
    val int = toInt()
    return int.takeIf { it.toDouble() == this }
      ?: error("#downcast($this) --> $int")
  }

  private inline fun <reified E : Enum<E>> enumValueOfOrNull(name: String) =
    try {
      enumValueOf<E>(name)
    } catch (e: IllegalArgumentException) {
      null
    }

  private fun boolean(field: String) = primitive(field).booleanOrNull ?: error("$name.$field -- boolean")
  private fun double(field: String) = primitive(field).doubleOrNull ?: error("$name.$field -- double")
  private fun int(field: String) = primitive(field).intOrNull ?: error("$name.$field -- int")
  private fun string(field: String) = primitive(field).content

  private fun primitive(field: String) = json[field]?.jsonPrimitive ?: error("$name.$field")
}