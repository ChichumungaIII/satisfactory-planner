package com.chichumunga.satisfactory.scripts.condition

import java.io.PrintWriter

data object TrueCondition : ConditionWriter {
  override fun writeTo(writer: PrintWriter) = with(writer) {
    println("Condition.TRUE")
  }

  override fun writeToInline(writer: PrintWriter) = error("Condition.TRUE should not be inlined.")
}
