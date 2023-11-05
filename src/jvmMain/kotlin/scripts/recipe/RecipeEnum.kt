package com.chichumunga.satisfactory.scripts.recipe

import app.game.data.Item
import com.chichumunga.satisfactory.scripts.condition.ConditionWriter
import com.chichumunga.satisfactory.scripts.conversion.legible
import java.io.PrintWriter
import java.io.Writer

class RecipeEnum(
  val primary: Item?,
  val enumName: String,
  val displayName: String,
  val time: Int,
  val inputs: List<Pair<Item, Pair<Int, Int>>>,
  val outputs: List<Pair<Item, Pair<Int, Int>>>,
  val power: Pair<Int, Int>?,
  val unlock: ConditionWriter,
  val alternate: Boolean,
) {
  companion object {
    fun writeDeclarationTo(writer: Writer) {
      writer.write(
        """
        |package app.game.data
        |
        |import app.game.logic.Condition
        |import app.game.logic.Condition.ItemCondition
        |import app.game.logic.Condition.ResearchCondition
        |import util.math.Rational
        |import util.math.q
        |
        |enum class Recipe(
        |  val displayName: String,
        |  val time: Rational,
        |  val inputs: Map<Item, Rational>,
        |  val outputs: Map<Item, Rational>,
        |  val power: ClosedRange<Rational>? = null,
        |  val unlock: Condition,
        |  val alternate: Boolean = false,
        |) {
        |
        """.trimMargin()
      )
    }

    fun writeFinalizationTo(writer: Writer) {
      writer.write(
        """
        |
        |  val rates = (inputs.mapValues { (_, amount) -> -amount } + outputs).mapValues { (_, amount) -> amount * 60.q / time }
        |}
        |
        """.trimMargin()
      )
    }
  }

  fun writeEnumDeclarationTo(writer: PrintWriter) = with(writer) {
    println("  $enumName(")
    println("    \"$displayName\",")
    println("    time = $time.q,")

    print("    inputs = ")
    writeComponents(inputs)

    print("    outputs = ")
    writeComponents(outputs)

    power?.also { println("    power = Range.closed(${it.first}.q, ${it.second}.q),") }

    print("    unlock = ")
    unlock.writeTo(this)
    println(",")

    if (alternate) println("    alternate = true,")
    print("  )")
  }

  private fun PrintWriter.writeComponents(components: List<Pair<Item, Pair<Int, Int>>>) {
    print("mapOf(")

    val text = components.map { it.toComponentText() }
    print(
      when (text.size) {
        0 -> ""
        1 -> text[0]

        else -> {
          text.joinToString(
            separator = ",\n      ",
            prefix = "\n      ",
            postfix = ",\n    ",
          )
        }
      }
    )

    println("),")
  }

  private fun Pair<Item, Pair<Int, Int>>.toComponentText() =
    "Item.${first.name} to ${second.legible()}"
}
