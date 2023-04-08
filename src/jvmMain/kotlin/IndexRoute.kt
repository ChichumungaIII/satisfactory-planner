package com.chichumunga.satisfactory

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.title

fun Routing.indexRoute() {
  route("/{...}") {
    get {
      call.respondHtml(HttpStatusCode.OK) {
        head {
          meta { charset = "UTF-8" }
          title("Satisfactory Planner")

          link {
            rel = "stylesheet"
            href = "https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          }
        }
        body {
          link { rel = "stylesheet"; href = "/static/satisfactory-planner.css" }
          script { src = "/static/satisfactory-planner.js" }
        }
      }
    }
  }
}
