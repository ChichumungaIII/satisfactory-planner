package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequest
import app.api.optimize.v2.OptimizeResponse
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
import util.collections.augment
import util.collections.join
import util.collections.merge
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

internal suspend fun optimize(request: OptimizeRequest): OptimizeResponse {
  val providedInputs =
    ItemPool(request.inputs.fold(mapOf()) { inputs, (item, quantity) ->
      inputs.merge(item, quantity.br, BigRational::plus)
    })
  val emptyRegistry = ProductRegistry(request.products.map { ProductManager.create(it) })

  val (optimizeRegistry, optimizeInputs) = emptyRegistry.allocateFixed(providedInputs)

  val plan = optimize(
    optimizeInputs.items,
    optimizeRegistry.requirements(),
    optimizeRegistry.weights(),
    request.restrictions.associate { it.recipe to it.rate.br })

  val inputTotals = InputTotals(
    plan.consumption.join(optimizeRegistry.production(), BigRational::plus),
    plan.demand.join(optimizeRegistry.production(), BigRational::plus),
  )
  val (responseInputs) = request.inputs.augment(
    inputTotals,
    { responses: List<OptimizeResponse.Input> -> responses }) { total, (item, quantity) ->
    val response = OptimizeResponse.Input(
      item = item,
      quantity = quantity,
      consumption = minOf(total.consumption[item] ?: 0.br, quantity.br).toRational(),
      demand = minOf(total.demand[item] ?: 0.br, quantity.br).toRational(),
    )
    add(response)
    InputTotals(
      consumption = total.consumption.merge(item, response.consumption.br, BigRational::minus),
      demand = total.demand.merge(item, response.demand.br, BigRational::minus),
    )
  }

  val (fixedRegistry, surplus) = optimizeRegistry.allocateFixed(optimizeInputs + ItemPool(plan.production))
  val (responseRegistry) = fixedRegistry.allocateDynamic(surplus)

  return OptimizeResponse(
    inputs = responseInputs,
    products = responseRegistry.products(plan.potential),
    byproducts = mapOf(),
    rates = plan.rates.mapValues { (_, rate) -> rate.toRational() }
  )
}

data class InputTotals(
  val consumption: Map<Item, BigRational>,
  val demand: Map<Item, BigRational>,
)
