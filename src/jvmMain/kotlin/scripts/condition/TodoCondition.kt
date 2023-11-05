package com.chichumunga.satisfactory.scripts.condition

import java.io.PrintWriter

data object TodoCondition : ConditionWriter {
  override fun writeTo(writer: PrintWriter) = with(writer) {
    print("Condition.FALSE // TODO: Implement unsupported conditions.")
  }

  override fun writeToInline(writer: PrintWriter) = writeTo(writer)
}
