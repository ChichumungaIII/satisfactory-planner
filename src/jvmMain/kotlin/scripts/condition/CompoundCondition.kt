package com.chichumunga.satisfactory.scripts.condition

import app.game.data.Milestone
import app.game.data.Research
import java.io.PrintWriter

data class CompoundCondition(val type: String, val conditions: List<ConditionWriter>) : ConditionWriter {
  private val ENUM_ORDER = listOf(Milestone::class, Research::class)
  private val ENUM_CONDITION_ORDER =
    compareBy<EnumCondition<*>> { ENUM_ORDER.indexOf(it.value::class) }.thenBy { it.value.ordinal }
  private val CHILD_ORDER =
    Comparator<ConditionWriter> { l, r ->
      if (l is EnumCondition<*>)
        if (r is EnumCondition<*>)
          ENUM_CONDITION_ORDER.compare(l, r)
        else -1
      else if (r is EnumCondition<*>) 1
      else 0
    }

  override fun writeTo(writer: PrintWriter) = with(writer) {
    println("$type {")
    conditions.sortedWith(CHILD_ORDER).forEach {
      print("        +")
      it.writeToInline(this)
      println()
    }
    print("      }")
  }

  override fun writeToInline(writer: PrintWriter) = with(writer) {
    print("+")
    writeTo(this)
  }
}
