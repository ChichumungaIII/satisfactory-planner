package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeOutput.Maximization
import app.api.optimize.v2.request.OptimizeOutput.Production
import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeResponse
import app.api.optimize.v2.response.OptimizeResponse.Companion.optimizeResponse
import app.game.data.Item
import app.serialization.AppJson
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import util.collections.merge
import util.math.Rational
import util.math.q

fun Routing.optimizeRouteV2() {
  post("/v2/optimize") {
    try {
      val request = AppJson.decodeFromString<OptimizeRequest>(call.receiveText())
      validate(request)
      call.respondText { AppJson.encodeToString(optimize(request)) }
    } catch (e: Throwable) {
      println("#optimize() call failed: [${e.message}]")
      e.printStackTrace()
      call.respond<String>(HttpStatusCode.InternalServerError, e.message ?: "Unknown error.")
    }
  }
}

private fun validate(request: OptimizeRequest) {
  val (inputs, outputs, _, limits) = request

  inputs.forEach { check(it.amount >= 0.q) { "Illegal input: $it" } }
  outputs.forEach {
    val message = { "Illegal product: $it" }
    when (it) {
      is Production -> check(it.amount >= 0.q, message)
      is Maximization -> check(it.weight > 0.q, message)
    }
  }
  limits.forEach { (recipe, rate) -> check(rate > 0.q) { "Illegal restriction: $recipe" } }

  check(inputs.isNotEmpty()) { "Optimization requires inputs." }
  check(outputs.isNotEmpty()) { "Optimization requires products." }
}

internal fun optimize(request: OptimizeRequest): OptimizeResponse {
  val (inputs, outputs, recipes, limits) = request

  val available = inputs.index({ it.item }) { it.amount }
  val preferred = inputs.filter { it.required }.index({ it.item }) { it.amount }
  val required = outputs.filterIsInstance<Production>().index({ it.item }) { it.amount }

  return optimizeResponse {}
}

fun <T> Iterable<T>.index(key: (T) -> Item, value: (T) -> Rational) =
  fold(mapOf<Item, BigRational>()) { map, e -> map.merge(key(e), value(e).br, BigRational::plus) }
