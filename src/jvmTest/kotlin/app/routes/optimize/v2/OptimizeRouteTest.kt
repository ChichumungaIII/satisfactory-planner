package app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequestBuilder.Companion.optimizeRequest
import app.api.optimize.v2.OptimizeResponseBuilder.Companion.optimizeResponse
import app.game.data.Item
import app.game.data.Recipe
import com.chichumunga.satisfactory.app.routes.optimize.v2.optimize
import kotlinx.coroutines.runBlocking
import util.math.q
import kotlin.test.Test
import kotlin.test.assertEquals

class OptimizeRouteTest {
  @Test
  fun optimize_singleInput_maximizeOutput() = runBlocking {
    val request = optimizeRequest {
      +input(Item.IRON_ORE, 60.q)
      +productWeight(Item.IRON_PLATE, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      +input(Item.IRON_ORE, 60.q) {
        consumption = 60.q
        demand = 60.q
      }
      +product(Item.IRON_PLATE, 40.q, 40.q)

      Recipe.IRON_INGOT += 200.q / 100.q
      Recipe.IRON_PLATE += 200.q / 100.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_fixedOutput() = runBlocking {
    val request = optimizeRequest {
      +input(Item.IRON_ORE, 60.q)
      +productAmount(Item.IRON_PLATE, 30.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      +input(Item.IRON_ORE, 60.q) {
        consumption = 45.q
        demand = 45.q
      }
      +product(Item.IRON_PLATE, 30.q, 40.q)

      Recipe.IRON_INGOT += 150.q / 100.q
      Recipe.IRON_PLATE += 150.q / 100.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_balancedOutput() = runBlocking {
    val request = optimizeRequest {
      +input(Item.IRON_ORE, 120.q)
      +productWeight(Item.IRON_PLATE, 1.q)
      +productWeight(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      +input(Item.IRON_ORE, 120.q) {
        consumption = 120.q
        demand = 120.q
      }
      +product(Item.IRON_PLATE, 48.q, 48.q)
      +product(Item.IRON_ROD, 48.q, 48.q)

      Recipe.IRON_INGOT += 400.q / 100.q
      Recipe.IRON_PLATE += 240.q / 100.q
      Recipe.IRON_ROD += 320.q / 100.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_imbalancedOutput() = runBlocking {
    val request = optimizeRequest {
      +input(Item.IRON_ORE, 120.q)
      +productWeight(Item.IRON_PLATE, 2.q)
      +productWeight(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      +input(Item.IRON_ORE, 120.q) {
        consumption = 120.q
        demand = 120.q
      }
      +product(Item.IRON_PLATE, 60.q, 60.q)
      +product(Item.IRON_ROD, 30.q, 30.q)

      Recipe.IRON_INGOT += 400.q / 100.q
      Recipe.IRON_PLATE += 300.q / 100.q
      Recipe.IRON_ROD += 200.q / 100.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_offsetWeightedOutput() = runBlocking {
    val request = optimizeRequest {
      +input(Item.IRON_ORE, 120.q)
      +productAmount(Item.IRON_PLATE, 20.q)
      +productWeight(Item.IRON_PLATE, 1.q)
      +productWeight(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      +input(Item.IRON_ORE, 120.q) {
        consumption = 120.q
        demand = 120.q
      }
      +product(Item.IRON_PLATE, 20.q, 56.q) // TODO: Fix "potential" calculation.
      +product(Item.IRON_PLATE, 36.q, 36.q)
      +product(Item.IRON_ROD, 36.q, 36.q)

      Recipe.IRON_INGOT += 400.q / 100.q
      Recipe.IRON_PLATE += 280.q / 100.q
      Recipe.IRON_ROD += 240.q / 100.q
    }
    assertEquals(expected, response)
  }
}
