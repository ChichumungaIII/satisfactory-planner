package com.chichumunga.satisfactory.scripts.conversion

val WORD_BOUNDARY = Regex("""\s*-\s*|\s+""")
fun uppercaseUnderscore(text: String) = normalize(text).replace(WORD_BOUNDARY, "_").uppercase()

val ILLEGAL_CHARACTERS = Regex("""[^a-zA-Z0-9_ -]""")
fun normalize(text: String) = text.replace(ILLEGAL_CHARACTERS, "")


fun Double.rationalize() = when (this) {
  0.5 -> 1 to 2
  0.75 -> 3 to 4
  1.5 -> 3 to 2
  2.5 -> 5 to 2

  else -> downcast() to 1
}

fun Double.downcast() = toInt().takeIf { it.toDouble() == this } ?: error("Cannot downcast [$this].")

fun Pair<Int, Int>.legible() = buildString {
  append("${first.legible()}.q")
  if (second != 1) append(" / ${second.legible()}.q")
}

fun Int.legible(): String {
  val big = (this / 1000).takeIf { it > 0 }
  val small = this % 1000
  return big?.let { "${it.legible()}_${"$small".padStart(3, '0')}" } ?: "$small"
}
