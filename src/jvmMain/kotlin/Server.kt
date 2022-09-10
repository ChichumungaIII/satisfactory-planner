package com.chichumunga.satisfactory

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.title

fun HTML.index() {
    head {
        meta { charset = "UTF-8" }
        title("Satisfactory Planner")

        link {
            rel = "stylesheet"
            href = "https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
        }
    }
    body {
        script { src = "/static/satisfactory-planner.js" }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}
