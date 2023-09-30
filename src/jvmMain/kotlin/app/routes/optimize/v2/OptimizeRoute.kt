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
import util.math.Rational
import util.math.q

fun Routing.optimizeRouteV2() {
  post("/v2/optimize") {
    try {
      val request = AppJson.decodeFromString<OptimizeRequest>(call.receiveText())
      validate(request)
      call.respondText { AppJson.encodeToString(optimize(request)) }
    } catch (e: Exception) {
      println("#optimize() call failed: [${e.message}]")
      e.printStackTrace()
      call.respond<String>(HttpStatusCode.InternalServerError, e.message ?: "Unknown error.")
    }
  }
}

private fun validate(request: OptimizeRequest) {
  val (provisions, requirements, restrictions, objectives) = request

  provisions.forEach { check(it.quantity >= 0.q) { "Illegal provision: ${it.item}" } }
  requirements.forEach { check(it.amount >= 0.q) { "Illegal requirement: ${it.item}" } }
  restrictions.forEach { check(it.rate >= 0.q) { "Illegal restriction: ${it.recipe}" } }
  objectives.forEach { check(it.weight > 0.q) { "Illegal objective: ${it.item}" } }

  check(requirements.size + objectives.size > 0) { "Optimization requires products." }
}

private suspend fun optimize(request: OptimizeRequest): OptimizeResponse {
  val requestProvisions = request.provisions.foldToItemMap({ it.item }) { it.quantity }
  val requestRequirements = request.requirements.foldToItemMap({ it.item }) { it.amount }
  val netItems = (requestProvisions.keys + requestRequirements.keys).associateWith { item ->
    requestProvisions.getOrDefault(item, 0.br) - requestRequirements.getOrDefault(item, 0.br)
  }

  // TODO: Ensure all outputs are maximized, even if their net value puts them on the wrong side.
  val plan = optimize(
    inputs = netItems.filterValues { it > 0.br },
    requirements = netItems.filterValues { it < 0.br }.mapValues { (_, requirement) -> -requirement },
    weights = request.objectives.foldToItemMap({ it.item }) { it.weight },
    restrictions = request.restrictions.associate { it.recipe to it.rate.br }
  )

  val planDemands = plan.demand.mapValues { (item, demand) -> OptimizeResponse.Demand(item, demand.toRational()) }
  val demands = request.provisions.map { planDemands[it.item] ?: OptimizeResponse.Demand(it.item, it.quantity) }

  val productions = plan.production.map { (item, amount) -> OptimizeResponse.Production(item, amount.toRational()) } +
      plan.consumption.map { (item, amount) -> OptimizeResponse.Production(item, -amount.toRational()) }

  return OptimizeResponse(
    demands = demands,
    productions = productions,
    potentials = plan.potential.map { (item, potential) -> OptimizeResponse.Potential(item, potential.toRational()) },
    rates = plan.target.map { (item, rate) -> OptimizeResponse.Rate(item, rate.toRational()) }
  )
}

private fun <T> Iterable<T>.foldToItemMap(getItem: (T) -> Item, getRational: (T) -> Rational) =
  fold(mutableMapOf<Item, BigRational>()) { map, element ->
    map.also { it.merge(getItem(element), getRational(element).br, BigRational::plus) }
  }.toMap()
