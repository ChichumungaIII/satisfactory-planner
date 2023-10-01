package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequest
import app.api.optimize.v2.OptimizeResponse
import app.serialization.AppJson
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
  val (inputs, products, restrictions) = request

  inputs.forEach { check(it.quantity >= 0.q) { "Illegal input: $it" } }
  products.forEach {
    val message = { "Illegal product: $it" }
    check((it.objective.amount ?: it.objective.weight) != null, message)
    it.objective.amount?.also { amount -> check(amount >= 0.q, message) }
    it.objective.weight?.also { weight -> check(weight > 0.q, message) }
  }
  restrictions.forEach { check(it.rate >= 0.q) { "Illegal restriction: ${it.recipe}" } }

  check(inputs.isNotEmpty()) { "Optimization requires inputs." }
  check(products.isNotEmpty()) { "Optimization requires products." }
}

private suspend fun optimize(request: OptimizeRequest): OptimizeResponse {
  val inputs = request.inputs.associate { it.item to it.quantity.br }
  val requirements = request.products.filter { it.objective.kind == OptimizeRequest.Product.Objective.Kind.AMOUNT }
    .associate { it.item to it.objective.toAmount().br }
  val weights = request.products.filter { it.objective.kind == OptimizeRequest.Product.Objective.Kind.WEIGHT }
    .associate { it.item to it.objective.toWeight().br }
  val restrictions = request.restrictions.associate { it.recipe to it.rate.br }

  val plan = optimize(inputs, requirements, weights, restrictions)

  val responseInputs = request.inputs.map { (item, quantity) ->
    OptimizeResponse.Input(
      item = item,
      quantity = quantity,
      consumption = plan.consumption[item]?.toRational() ?: 0.q,
      demand = plan.demand[item]?.toRational() ?: throw Error("Unknown demand: $item"),
    )
  }
  val responseProducts = request.products.map { (item) ->
    OptimizeResponse.Product(
      item = item,
      amount = plan.production[item]?.toRational() ?: 0.q,
      potential = plan.potential[item]?.toRational() ?: throw Error("Unknown potential: $item"),
    )
  }

  return OptimizeResponse(
    inputs = responseInputs,
    products = responseProducts,
    byproducts = mapOf(),
    rates = plan.rates.mapValues { (_, rate) -> rate.toRational() }
  )
}
