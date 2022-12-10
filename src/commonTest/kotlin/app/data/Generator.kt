package app.data

import app.data.u6.U6Item
import app.data.u6.U6Recipe
import util.math.Rational
import util.math.q
import kotlin.test.Test

class Generator {
    @Test
    fun generateItems() {
        println(U6Item.values().joinToString("\n") {
            it.run {
                val energy = if (energy == 0.q) "" else ", energy = $energy.q"
                """$name("$displayName"$energy),"""
            }
        })
    }

    @Test
    fun generateRecipes() {
        println(U6Recipe.values().joinToString("\n") {
            it.run {
                """
                    |$name(
                    |  "$displayName",
                    |  time = $time.q,
                    |  inputs = mapOf(${components.toEntry(-(1.q))}),
                    |  outputs = mapOf(${components.toEntry(1.q)}),
                    |),
                """.trimMargin("|")
            }
        })
    }

    fun Map<U6Item, Rational>.toEntry(signum: Rational): String {
        val entries = filterValues { it < 0.q == signum < 0.q }.entries
        if (entries.isEmpty()) return ""

        return entries.joinToString(
            prefix = if (entries.size > 1) "\n    " else "",
            separator = ",\n    ",
            postfix = if (entries.size > 1) ",\n  " else "",
        ) { (item, count) ->
            "Item.${item.name} to ${count * signum}.q"
        }
    }
}
