package com.chichumunga.satisfactory.scripts

import com.chichumunga.satisfactory.scripts.conversion.ITEM_SPEC_ORDER
import com.chichumunga.satisfactory.scripts.conversion.convertToItemEnum
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.item.ItemEnum.Companion.MANUAL_ITEM_HARD_DRIVE
import com.chichumunga.satisfactory.scripts.json.ItemSchema
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.io.PrintWriter
import java.util.stream.Collectors.joining

fun main(args: Array<String>) {
  val (itemsJsonFilePath, recipesJsonFilePath, itemsEnumFilePath, recipesEnumFilePath) = args

  val items = readAll(itemsJsonFilePath) { ItemSchema(it) }.map { convertToItemEnum(it) } + MANUAL_ITEM_HARD_DRIVE
  writeItems(itemsEnumFilePath, items)
}

fun <T> readAll(path: String, transform: (JsonObject) -> T) =
  Json.parseToJsonElement(File(path).bufferedReader().lines().collect(joining())).jsonObject
    .values.flatMap { it.jsonArray }
    .map { transform(it.jsonObject) }

fun writeItems(path: String, items: List<ItemEnum>) {
  with(PrintWriter(File(path).also { it.createNewFile() }.bufferedWriter())) {
    ItemEnum.writeDeclarationTo(this)

    var count = 0
    var firstMajor = true
    items.groupBy { it.majorGroup }.entries
      .sortedBy { (major) -> major.ordinal }
      .forEach { (major, majorGroupItems) ->
        if (firstMajor) firstMajor = false
        else println()

        val asterisks = "*".repeat(major.printName.length)
        println("  /* $asterisks */")
        println("  /* ${major.printName} */")
        println("  /* $asterisks */")
        println()

        var firstMinor = true
        majorGroupItems.groupBy { it.minorGroup }.entries
          .sortedBy { (minor) -> minor.ordinal }
          .forEach { (minor, minorGroupItems) ->
            if (firstMinor) firstMinor = false
            else println()

            println("  /* ${minor.printName} */")
            println()

            minorGroupItems.sortedWith(ITEM_SPEC_ORDER)
              .forEach {
                it.writeEnumDeclarationTo(this)
                if (++count < items.size) println(",")
              }
          }
      }
    println(";")
    println()

    ItemEnum.writeNestedCategoryEnumTo(this)
    println()

    ItemEnum.writeUnlockPrefixTo(this)
    items.sortedWith(ITEM_SPEC_ORDER).forEach { it.writeUnlockConditionTo(this) }
    ItemEnum.writeUnlockSuffixTo(this)

    ItemEnum.writeFinalizationTo(this)

    flush()
  }
}
