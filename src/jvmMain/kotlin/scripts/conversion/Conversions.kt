package com.chichumunga.satisfactory.scripts.conversion

val WORD_BOUNDARY = Regex("""\s+|\s*-\s*""")
fun capitalizeUnderscore(text: String) = normalize(text).replace(WORD_BOUNDARY, "_").uppercase()

val ILLEGAL_CHARACTERS = Regex("""[^a-zA-Z_-]""")
fun normalize(text: String) = text.replace(ILLEGAL_CHARACTERS, "")
