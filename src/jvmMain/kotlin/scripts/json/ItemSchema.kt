package com.chichumunga.satisfactory.scripts.json

import kotlinx.serialization.json.JsonObject

class ItemSchema(json: JsonObject) : JsonSchema(json) {
  val className = string("className")
  val name = string("name")
  val description = string("description")
  val unlockedBy = string("unlockedBy")
  val stackSize = int("stackSize")
  val energy = double("energy")
  val radioactive = double("radioactive")
  val canBeDiscarded = boolean("canBeDiscarded")
  val sinkPoints = int("sinkPoints")
  val abbreviation = stringOrNull("abbreviation")
  val form = string("form")
  val fluidColor = string("fluidColor")
  val stable = boolean("stable")
  val experimental = boolean("experimental")
}
