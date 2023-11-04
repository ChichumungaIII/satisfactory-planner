package com.chichumunga.satisfactory.scripts.conversion

val WORD_BOUNDARY = Regex("""\s+|\s*-\s*""")
fun uppercaseUnderscore(text: String) = normalize(text).replace(WORD_BOUNDARY, "_").uppercase()

val ILLEGAL_CHARACTERS = Regex("""[^a-zA-Z_ -]""")
fun normalize(text: String) = text.replace(ILLEGAL_CHARACTERS, "")


fun Double.rationalize() = when (this) {
  0.5 -> 1 to 2
  0.75 -> 3 to 4

  else -> downcast() to 1
}

fun Double.downcast() = toInt().takeIf { it.toDouble() == this } ?: error("Cannot downcast [$this].")
