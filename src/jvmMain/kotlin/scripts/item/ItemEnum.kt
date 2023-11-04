package com.chichumunga.satisfactory.scripts.item

import app.game.data.Item
import java.io.PrintWriter
import java.io.Writer

data class ItemEnum(
  val majorGroup: MajorItemGroup,
  val minorGroup: MinorItemGroup,
  val enumName: String,
  val displayName: String,
  val category: Item.Category?,
  val stack: Int?,
  val sink: Int?,
  val energy: Int?,
  val radiation: Pair<Int, Int>?,
  val stable: Boolean,
  val experimental: Boolean,
) {
  companion object {
    val MANUAL_ITEM_HARD_DRIVE = ItemEnum(
      MajorItemGroup.NATURE,
      MinorItemGroup.HARD_DRIVES,
      "HARD_DRIVE",
      "Hard Drive",
      Item.Category.NATURE,
      stack = 100,
      sink = null,
      energy = null,
      radiation = null,
      stable = true,
      experimental = true
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
        |    NATURE("Nature"),
        |    RESOURCES("Resources"),
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
    category?.let { "Category.${it.name}" }?.also { println("    $it,") } ?: run { println("    TODO(),") }
    stack?.also { println("    stack = ${legible(it)}.q,") }
    sink?.also { println("    sink = ${legible(it)}.q,") }
    energy?.also { println("    energy = ${legible(it)}.q,") }
    radiation?.also { (n, d) ->
      print("    radiation = ${legible(n)}.q")
      if (d != 1) print(" / ${legible(d)}.q")
      println(",")
    }
    if (!stable) println("    stable = false,")
    if (!experimental) println("    experimental = false,")
    print("  )")
  }

  fun writeUnlockConditionTo(writer: PrintWriter) = with(writer) {
    print("      $enumName -> ")
    println("TODO()")
  }

  private fun legible(value: Int): String {
    val big = (value / 1000).takeIf { it > 0 }
    val small = value % 1000
    return big?.let { "${legible(it)}_${"$small".padStart(3, '0')}" } ?: "$small"
  }
}
