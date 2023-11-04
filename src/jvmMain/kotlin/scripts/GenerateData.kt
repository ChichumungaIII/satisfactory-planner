package com.chichumunga.satisfactory.scripts

import com.chichumunga.satisfactory.scripts.conversion.convertToItemEnum
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.json.ItemSchema
import com.chichumunga.satisfactory.scripts.json.RecipeSchema
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.io.PrintWriter
import java.util.stream.Collectors.joining

fun main(args: Array<String>) {
  val (itemsJsonFilePath, recipesJsonFilePath, itemsEnumFilePath, recipesEnumFilePath) = args

  val items = readAll(itemsJsonFilePath) { ItemSchema(it) }
    .associate { it.className to convertToItemEnum(it) }

  writeItems(itemsEnumFilePath, items.values.toList())

  val recipes = readAll(recipesJsonFilePath) { RecipeSchema(it) }
}

fun <T> readAll(path: String, transform: (JsonObject) -> T) =
  Json.parseToJsonElement(File(path).bufferedReader().lines().collect(joining())).jsonObject
    .values.map { it.jsonArray[0] }
    .map { transform(it.jsonObject) }

var count = 0
fun writeItems(path: String, items: List<ItemEnum>) {
  with(PrintWriter(File(path).also { it.createNewFile() }.bufferedWriter())) {
    ItemEnum.writeDeclarationTo(this)

    items.groupBy { it.majorGroup }.entries
      .sortedBy { (major) -> major }
      .forEach { (major, items) ->

        val asterisks = "*".repeat(major.printName.length + 4)
        println("  /$asterisks/")
        println("  /* ${major.printName} */")
        println("  /$asterisks/")
        println()

        items.groupBy { it.minorGroup }.entries
          .sortedBy { (minor) -> minor }
          .forEach { (minor, items) ->
            println("  /* ${minor.printName} */")
            println()

            items.forEach {
              it.writeTo(this)
              if (++count < items.size) println(",")
            }
          }
      }
    println(";")

    ItemEnum.writeNestedCategoryEnumTo(this)

    // TODO: Unlock conditions

    ItemEnum.writeFinalizationTo(this)

    flush()
  }
}
