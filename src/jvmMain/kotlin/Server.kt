package com.chichumunga.satisfactory

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            indexRoute()
            staticRoute()
        }
    }.start(wait = true)
}
