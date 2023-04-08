package com.chichumunga.satisfactory

import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.routing.Routing

fun Routing.staticRoute() {
  static("/static") { resources() }
}
