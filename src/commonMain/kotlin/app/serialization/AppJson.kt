package app.serialization

import kotlinx.serialization.json.Json

val AppJson = Json {
  allowStructuredMapKeys = true
  useArrayPolymorphism = true
}
