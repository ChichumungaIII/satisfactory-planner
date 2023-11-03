package com.chichumunga.satisfactory.scripts

import app.game.data.Item
import app.game.data.Milestone
import app.game.data.Research
import java.io.Writer

data class WriteableItem(
  val displayName: String,
  val category: Item.Category,
  val stack: Int?,
  val sink: Int?,
  val energy: Int?,
  val radiation: Pair<Int, Int>?,
  val unlock: List<WriteableUnlock>,
) {
  interface WriteableUnlock {
    fun writeConditionTo(writer: Writer)
    fun writeValueTo(writer: Writer)
  }

  data class UnlockMilestone(val milestone: Milestone) : WriteableUnlock {
    override fun writeConditionTo(writer: Writer) {
      writer.write("MilestoneCondition(Milestone.$milestone)")
    }

    override fun writeValueTo(writer: Writer) {
      writer.write("Milestone.$milestone")
    }
  }

  data class UnlockResearch(val research: Research) : WriteableUnlock {
    override fun writeConditionTo(writer: Writer) {
      writer.write("ResearchCondition(Research.$research)")
    }

    override fun writeValueTo(writer: Writer) {
      writer.write("Research.$research")
    }
  }

  companion object {
    private val WORD_BOUNDARY = Regex("""\s+|\s*-\s*""")
  }

  val enumName = displayName.uppercase().replace(WORD_BOUNDARY, "_")

  fun writeTo(writer: Writer) {
    operator fun String?.unaryPlus() = this?.also { writer.write(it) }

    +"  $enumName(\n"
    +"    \"$displayName\",\n"
    +"    Category.$category,\n"
    +stack?.let { "    stack = $it.q,\n" }
    +sink?.let { "    sink = $it.q,\n" }
    +energy?.let { "    energy = $it.q\n" }
    +radiation?.let { "    radiation = ${it.asRational()}\n" }

    +"    unlock = "
    when (unlock.size) {
      1 -> {
        unlock[0].writeConditionTo(writer)
      }

      else -> {
        +"any {\n"
        unlock.forEach {
          +"      +"
          it.writeValueTo(writer)
          +"\n"
        }
        +"    }"
      }
    }
    +",\n"
    +"  ),\n"
  }

  private fun Pair<Int, Int>.asRational() =
    if (second == 1) "$first.q"
    else "$first.q / $second.q"
}

