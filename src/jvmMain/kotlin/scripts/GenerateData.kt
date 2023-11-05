package com.chichumunga.satisfactory.scripts

import com.chichumunga.satisfactory.scripts.conversion.ITEM_ENUM_COMPARATOR
import com.chichumunga.satisfactory.scripts.conversion.RECIPE_ENUM_COMPARATOR
import com.chichumunga.satisfactory.scripts.conversion.convertToItemEnum
import com.chichumunga.satisfactory.scripts.conversion.convertToRecipeEnum
import com.chichumunga.satisfactory.scripts.conversion.getMajorGroup
import com.chichumunga.satisfactory.scripts.conversion.getMinorGroup
import com.chichumunga.satisfactory.scripts.item.ItemEnum
import com.chichumunga.satisfactory.scripts.item.ItemEnum.Companion.MANUAL_ITEM_HARD_DRIVE
import com.chichumunga.satisfactory.scripts.json.ItemSchema
import com.chichumunga.satisfactory.scripts.json.RecipeSchema
import com.chichumunga.satisfactory.scripts.recipe.RecipeEnum
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.io.PrintWriter
import java.util.stream.Collectors.joining

fun main(args: Array<String>) {
  val (itemsJsonFilePath, recipesJsonFilePath, itemsEnumFilePath, recipesEnumFilePath) = args

  val items = readAll(itemsJsonFilePath) { ItemSchema(it) }.associate { it.className to it.convertToItemEnum() }
  writeItems(itemsEnumFilePath, items.values + MANUAL_ITEM_HARD_DRIVE)

  val recipes = readAll(recipesJsonFilePath) { RecipeSchema(it) }
    .filterNot { it.inBuildGun }
    .filterNot { it.inCustomizer }
    .filterNot { it.className == "Recipe_CartridgeChaos_Packaged_C" }
    .map { it.convertToRecipeEnum(items) }
  writeRecipes(recipesEnumFilePath, recipes)
}

fun <T> readAll(path: String, transform: (JsonObject) -> T) =
  Json.parseToJsonElement(File(path).bufferedReader().lines().collect(joining())).jsonObject
    .values.map { it.jsonArray[0] }
    .map { transform(it.jsonObject) }

fun writeItems(path: String, items: List<ItemEnum>) {
  with(PrintWriter(File(path).also { it.createNewFile() }.bufferedWriter())) {
    ItemEnum.writeDeclarationTo(this)

    var count = 0
    var firstMajor = true
    items.sortedWith(ITEM_ENUM_COMPARATOR)
      .groupBy { getMajorGroup(it.enumName) }.entries
      .forEach { (major, majorGroupItems) ->
        if (firstMajor) firstMajor = false
        else println()

        val asterisks = "*".repeat(major.length + 4)
        println("  /*$asterisks*/")
        println("  /** $major **/")
        println("  /*$asterisks*/")
        println()

        var firstMinor = true
        majorGroupItems.groupBy { getMinorGroup(it.enumName) }.entries
          .forEach { (minor, minorGroupItems) ->
            if (firstMinor) firstMinor = false
            else println()

            println("  /* $minor */")
            println()

            minorGroupItems.forEach {
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
    items.sortedWith(ITEM_ENUM_COMPARATOR).forEach {
      it.writeUnlockConditionTo(this)
      println()
    }
    ItemEnum.writeUnlockSuffixTo(this)

    ItemEnum.writeFinalizationTo(this)
    flush()
  }
}

fun writeRecipes(path: String, recipes: List<RecipeEnum>) {
  with(PrintWriter(File(path).also { it.createNewFile() }.bufferedWriter())) {
    RecipeEnum.writeDeclarationTo(this)

    var count = 0
    var first = true
    recipes.sortedWith(RECIPE_ENUM_COMPARATOR)
      .groupBy { it.primary }
      .values.forEach { primaryRecipes ->
        if (first) first = false
        else println()

        primaryRecipes.sortedBy { it.enumName }
          .sortedBy { it.alternate }
          .forEach { recipe ->
            recipe.writeEnumDeclarationTo(this)
            if (++count < recipes.size) println(",")
          }
      }
    println(";")

    RecipeEnum.writeFinalizationTo(this)
    flush()
  }
}
