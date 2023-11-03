package com.chichumunga.satisfactory.scripts

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.io.StringWriter
import java.util.stream.Collectors.joining


fun main(args: Array<String>) {
  val (src, dest) = args

  val itemsJson = File(src).bufferedReader().lines().collect(joining())
  val writer = StringWriter()

  Json.parseToJsonElement(itemsJson).jsonObject.values
    .map { it.jsonArray }
    .map { ItemSchema(it[0].jsonObject) }
    .mapNotNull { it.toWriteable() }
    .forEach { it.writeTo(writer) }
  println(writer.toString())
}
