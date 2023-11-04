package com.chichumunga.satisfactory.scripts.json

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.intOrNull

open class JsonSchema(val json: JsonObject) {
  protected fun <T> objectArray(field: String, transform: (JsonObject) -> T) =
    array(field).mapIndexed { i, element ->
      (element as? JsonObject)?.let(transform) ?: error("Array element [$field[$i]] is not an object: $element")
    }

  protected fun stringArray(field: String) =
    array(field).mapIndexed { i, element ->
      val primitive = (element as? JsonPrimitive) ?: error("Array element [$field[$i]] is not primitive: $element")
      primitive.contentOrNull ?: error("Array element [$field[$i]] is not a string: $element")
    }


  protected fun boolean(field: String) = primitive(field).booleanOrNull
    ?: error("Field [$field] is not boolean: ${primitive(field)}")

  protected fun double(field: String) = primitive(field).doubleOrNull
    ?: error("Field [$field] is not a double: ${primitive(field)}")

  protected fun intOrNull(field: String) = if (primitive(field) is JsonNull) null else int(field)
  protected fun int(field: String) = primitive(field).intOrNull
    ?: error("Field [$field] is not an int: ${primitive(field)}")

  protected fun stringOrNull(field: String) = if (primitive(field) is JsonNull) null else string(field)
  protected fun string(field: String) = primitive(field).contentOrNull
    ?: error("Field [$field] is not a string: ${primitive(field)}")


  private fun array(field: String) = field(field) as? JsonArray
    ?: error("Field [$field] is not an array: ${field(field)}")

  private fun primitive(field: String) = field(field) as? JsonPrimitive
    ?: error("Field [$field] is not primitive: ${field(field)}")

  private fun field(field: String) = json[field]
    ?: error("Field [$field] not found. Available: ${json.keys}")
}
