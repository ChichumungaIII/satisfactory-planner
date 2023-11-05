package com.chichumunga.satisfactory.scripts.condition

import app.game.data.Milestone
import app.game.data.Phase
import app.game.data.Research
import com.chichumunga.satisfactory.scripts.conversion.uppercaseUnderscore
import java.io.PrintWriter

interface ConditionWriter {
  companion object {
    private val UNSUPPORTED_CONDITIONS = setOf(
      // AWESOME Shop unlocks.
      "[[AWESOME Shop]] - FICSIT Factory Cart™",
      "[[AWESOME Shop]] - Golden FICSIT Factory Cart™",
      // Weird and FICSMAS.
      "Equipment: Fireworks",
      // Removed from game.
      "[[Flower Petals Research|MAM Flower Petals Research]] - Color Cartridges",
    )

    private val UNSUPPORTED_RESEARCH = setOf(
      "A_FRIEND",
      "CANDY_CANE_BASHER",
      "CANDY_CANE_DECOR",
      "FICSMAS_GIFT_TREE",
      "FICSMAS_LIGHTS",
      "FICSMAS_TREE_BASE",
      "FICSMAS_WREATH",
      "GIANT_FICSMAS_TREE_UPGRADE_1",
      "ITS_SNOWING",
      "SNOWFIGHT",
    )

    private val RESEARCH_OVERRIDES = mapOf(
      "TURBO_FUEL" to Research.TURBOFUEL
    )

    private val PARSERS = mapOf<Regex, (MatchResult) -> ConditionWriter>(
      Regex("""Onboarding""") to { _ -> EnumCondition(Phase.GAME_START) },
      Regex("""\[\[Tier \d]] - (.+)""") to { match ->
        EnumCondition(enumValueOf<Milestone>(uppercaseUnderscore(match.groups[1]!!.value)))
      },
      Regex("""\[\[(.+) Research\|MAM (.+) Research]] - (.+)""") to { match ->
        val value = uppercaseUnderscore(match.groups[3]!!.value)
        if (UNSUPPORTED_RESEARCH.contains(value)) TodoCondition
        else EnumCondition(RESEARCH_OVERRIDES[value] ?: enumValueOf<Research>(value))
      }
    )

    fun parse(unlockedBy: String): ConditionWriter {
      val conditions = unlockedBy.split("OR<br>").map { parseConjunction(it.trim()) }
      return if (conditions.size == 1) conditions[0]
      else CompoundCondition("any", conditions)
    }

    private fun parseConjunction(value: String): ConditionWriter {
      val conditions = value.split("AND<br>").map { parseCondition(it.trim()) }
      return if (conditions.size == 1) conditions[0]
      else CompoundCondition("all", conditions)
    }

    private fun parseCondition(value: String): ConditionWriter {
      if (value.isEmpty()) return TrueCondition
      if (UNSUPPORTED_CONDITIONS.contains(value)) return TodoCondition

      PARSERS.entries.forEach { (regex, handler) ->
        val condition = regex.matchEntire(value)?.let(handler)
        if (condition != null) return condition
      }

      error("Unknown condition: [$value]")
    }
  }

  fun writeTo(writer: PrintWriter)
  fun writeToInline(writer: PrintWriter)
}

