package com.chichumunga.satisfactory.scripts.item

import app.game.data.Item
import com.chichumunga.satisfactory.scripts.condition.ConditionWriter
import com.chichumunga.satisfactory.scripts.condition.TrueCondition
import com.chichumunga.satisfactory.scripts.conversion.legible
import java.io.PrintWriter
import java.io.Writer

data class ItemEnum(
  val enumName: String,
  val displayName: String,
  val category: Item.Category,
  val stack: Int?,
  val sink: Int?,
  val energy: Int?,
  val radiation: Pair<Int, Int>?,
  val stable: Boolean,
  val experimental: Boolean,
  val unlock: ConditionWriter,
) {
  companion object {
    val MANUAL_ITEM_HARD_DRIVE = ItemEnum(
      "HARD_DRIVE",
      "Hard Drive",
      Item.Category.NATURE,
      stack = 100,
      sink = null,
      energy = null,
      radiation = null,
      stable = true,
      experimental = true,
      unlock = TrueCondition,
    )

    fun writeDeclarationTo(writer: Writer) {
      writer.write(
        """
        |package app.game.data
        |
        |import app.game.logic.Condition
        |import app.game.logic.Condition.Companion.any
        |import app.game.logic.Condition.MilestoneCondition
        |import app.game.logic.Condition.PhaseCondition
        |import app.game.logic.Condition.ResearchCondition
        |import util.math.Rational
        |import util.math.q
        |
        |enum class Item(
        |  val displayName: String,
        |  val category: Category,
        |  val stack: Rational? = null,
        |  val sink: Rational? = null,
        |  val energy: Rational? = null,
        |  val radiation: Rational? = null,
        |  val stable: Boolean = true,
        |  val experimental: Boolean = true,
        |) {
        |
        """.trimMargin()
      )
    }

    fun writeNestedCategoryEnumTo(writer: Writer) {
      writer.write(
        """
        |  enum class Category(
        |    val displayName: String,
        |  ) {
        |    PARTS("Components"),
        |    EQUIPMENT("Equipment"),
        |    CONSUMABLES("Consumables"),
        |    NATURE("Nature"),
        |    RESOURCES("Resources"),
        |    UNCATEGORIZED("Uncategorized"),
        |  }
        |
        """.trimMargin()
      )
    }

    fun writeUnlockPrefixTo(writer: Writer) {
      writer.write(
        """
        |  val unlock by lazy {
        |    when (this) {
        |
        """.trimMargin()
      )
    }

    fun writeUnlockSuffixTo(writer: Writer) {
      writer.write(
        """
        |    }
        |  }
        |
        """.trimMargin()
      )
    }

    fun writeFinalizationTo(writer: Writer) {
      writer.write(
        """
        |}
        |
        """.trimMargin()
      )
    }
  }

  fun writeEnumDeclarationTo(writer: PrintWriter) = with(writer) {
    println("  $enumName(")
    println("    \"$displayName\",")
    println("    Category.${category.name},")
    stack?.also { println("    stack = ${it.legible()}.q,") }
    sink?.also { println("    sink = ${it.legible()}.q,") }
    energy?.also { println("    energy = ${it.legible()}.q,") }
    radiation?.also { println("    radiation = ${it.legible()},") }
    if (!stable) println("    stable = false,")
    if (!experimental) println("    experimental = false,")
    print("  )")
  }

  fun writeUnlockConditionTo(writer: PrintWriter) = with(writer) {
    print("      $enumName -> ")
    unlock.writeTo(this)
  }
}
