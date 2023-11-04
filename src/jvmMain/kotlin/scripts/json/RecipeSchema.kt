package com.chichumunga.satisfactory.scripts.json

import kotlinx.serialization.json.JsonObject

class RecipeSchema(json: JsonObject) : JsonSchema(json) {
  val className = string("className")
  val name = string("name")
  val unlockedBy = string("unlockedBy")
  val duration = int("duration")
  val ingredients = objectArray("ingredients") { ComponentSchema(it) }
  val products = objectArray("products") { ComponentSchema(it) }
  val producedIn = stringArray("producedIn")
  val inCraftBench = boolean("inCraftBench")
  val inWorkshop = boolean("inWorkshop")
  val inBuildGun = boolean("inBuildGun")
  val inCustomizer = boolean("inCustomizer")
  val manualCraftingMultiplier = double("manualCraftingMultiplier")
  val alternate = boolean("alternate")
  val minPower = intOrNull("minPower")
  val maxPower = intOrNull("maxPower")
  val seasons = stringArray("seasons")
  val stable = boolean("stable")
  val experimental = boolean("experimental")
}

class ComponentSchema(json: JsonObject) : JsonSchema(json) {
  val item = string("item")
  val amount = double("amount")
}
