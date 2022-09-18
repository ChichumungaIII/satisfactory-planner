package com.chichumunga.satisfactory

import app.api.OptimizeRequest
import app.api.OptimizeResponse
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Routing.optimizeRoute() {
    post("/v1/optimize") {
        val request = Json.decodeFromString<OptimizeRequest>(call.receiveText())
        call.respondText { Json.encodeToString(optimize(request)) }
    }
}

private fun optimize(request: OptimizeRequest): OptimizeResponse {
    println("REQUEST: $request")
    return OptimizeResponse(mapOf(), mapOf(), mapOf())
}
