package com.chichumunga.satisfactory.scripts.condition

import java.io.PrintWriter

data class EnumCondition<E : Enum<E>>(val value: E) : ConditionWriter {
  override fun writeTo(writer: PrintWriter) = with(writer) {
    print("${enumClass()}Condition(${enumValue()})")
  }

  override fun writeToInline(writer: PrintWriter) = with(writer) {
    print(enumValue())
  }

  private fun enumValue() = "${enumClass()}.${value.name}"
  private fun enumClass() = (value::class).simpleName
}
