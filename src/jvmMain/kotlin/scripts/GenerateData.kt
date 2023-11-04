package com.chichumunga.satisfactory.scripts

import com.chichumunga.satisfactory.scripts.json.ItemSchema
import com.chichumunga.satisfactory.scripts.json.RecipeSchema
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.util.stream.Collectors.joining

fun main(args: Array<String>) {
  val (itemsJsonFilePath, recipesJsonFilePath, itemsEnumFilePath, recipesEnumFilePath) = args

  val items = readAll(itemsJsonFilePath) { ItemSchema(it) }
  val recipes = readAll(recipesJsonFilePath) { RecipeSchema(it) }

  println(items)
  println(recipes)
}

fun <T> readAll(path: String, transform: (JsonObject) -> T) =
  Json.parseToJsonElement(File(path).bufferedReader().lines().collect(joining())).jsonObject
    .values.map { it.jsonArray[0] }
    .map { transform(it.jsonObject) }
