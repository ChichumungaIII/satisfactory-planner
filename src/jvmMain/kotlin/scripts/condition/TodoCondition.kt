package com.chichumunga.satisfactory.scripts.condition

import java.io.PrintWriter

data object TodoCondition : ConditionWriter {
  override fun writeTo(writer: PrintWriter) = with(writer) {
    println("TODO()")
  }

  override fun writeToInline(writer: PrintWriter) = writeTo(writer)
}
