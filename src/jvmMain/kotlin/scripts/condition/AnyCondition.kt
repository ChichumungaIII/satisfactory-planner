package com.chichumunga.satisfactory.scripts.condition

import app.game.data.Milestone
import app.game.data.Research
import java.io.PrintWriter

data class AnyCondition(val conditions: List<EnumCondition<*>>) : ConditionWriter {
  private val ENUM_ORDER = listOf(Milestone::class, Research::class)
  private val CHILD_ORDER =
    compareBy<EnumCondition<*>> { ENUM_ORDER.indexOf(it.value::class) }.thenBy { it.value.ordinal }

  override fun writeTo(writer: PrintWriter) = with(writer) {
    println("any {")
    conditions.sortedWith(CHILD_ORDER).forEach {
      print("        +")
      it.writeToInline(this)
    }
    println("      }")
  }

  override fun writeToInline(writer: PrintWriter) = error("AnyCondition should not be inlined.")
}
