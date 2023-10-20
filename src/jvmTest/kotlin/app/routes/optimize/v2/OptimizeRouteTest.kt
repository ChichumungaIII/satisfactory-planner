package app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequestBuilder.Companion.optimizeRequest
import app.api.optimize.v2.OptimizeResponseBuilder.Companion.optimizeResponse
import app.game.data.Item
import app.game.data.Recipe
import com.chichumunga.satisfactory.app.routes.optimize.v2.optimize
import util.math.q
import kotlin.test.Test
import kotlin.test.assertEquals

class OptimizeRouteTest {
  @Test
  fun optimize_singleInput_maximizeOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      productWeight(Item.IRON_PLATE, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 60.q) {
        consumption = 60.q
        demand = 60.q
      }
      product(Item.IRON_PLATE, 40.q, 40.q)

      rate(Recipe.IRON_INGOT, 200.q)
      rate(Recipe.IRON_PLATE, 200.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_fixedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      productAmount(Item.IRON_PLATE, 30.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 60.q) {
        consumption = 45.q
        demand = 45.q
      }
      product(Item.IRON_PLATE, 30.q, 40.q)

      rate(Recipe.IRON_INGOT, 150.q)
      rate(Recipe.IRON_PLATE, 150.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_balancedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      productWeight(Item.IRON_PLATE, 1.q)
      productWeight(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 120.q) {
        consumption = 120.q
        demand = 120.q
      }
      product(Item.IRON_PLATE, 48.q, 48.q)
      product(Item.IRON_ROD, 48.q, 48.q)

      rate(Recipe.IRON_INGOT, 400.q)
      rate(Recipe.IRON_PLATE, 240.q)
      rate(Recipe.IRON_ROD, 320.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_imbalancedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      productWeight(Item.IRON_PLATE, 2.q)
      productWeight(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 120.q) {
        consumption = 120.q
        demand = 120.q
      }
      product(Item.IRON_PLATE, 60.q, 60.q)
      product(Item.IRON_ROD, 30.q, 30.q)

      rate(Recipe.IRON_INGOT, 400.q)
      rate(Recipe.IRON_PLATE, 300.q)
      rate(Recipe.IRON_ROD, 200.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_offsetWeightedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      productAmount(Item.IRON_PLATE, 20.q)
      productWeight(Item.IRON_PLATE, 1.q)
      productWeight(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 120.q) {
        consumption = 120.q
        demand = 120.q
      }
      product(Item.IRON_PLATE, 20.q, 56.q) // TODO: Fix "potential" calculation.
      product(Item.IRON_PLATE, 36.q, 36.q)
      product(Item.IRON_ROD, 36.q, 36.q)

      rate(Recipe.IRON_INGOT, 400.q)
      rate(Recipe.IRON_PLATE, 280.q)
      rate(Recipe.IRON_ROD, 240.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withoutAlternateRecipes_ignoresAlternates() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      productWeight(Item.SCREW, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 60.q) {
        consumption = 60.q
        demand = 60.q
      }
      product(Item.SCREW, 240.q, 240.q)

      rate(Recipe.IRON_INGOT, 200.q)
      rate(Recipe.IRON_ROD, 400.q)
      rate(Recipe.SCREW, 600.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withAlternateRecipes_usesAlternates() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      productWeight(Item.SCREW, 1.q)
      alternate(Recipe.CAST_SCREW)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 60.q) {
        consumption = 60.q
        demand = 60.q
      }
      product(Item.SCREW, 240.q, 240.q)

      rate(Recipe.IRON_INGOT, 200.q)
      rate(Recipe.CAST_SCREW, 480.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withRestriction_limitsRecipeUse() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      productWeight(Item.SCREW, 1.q)
      alternate(Recipe.CAST_SCREW)
      restriction(Recipe.CAST_SCREW, 240.q / 100.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 60.q) {
        consumption = 60.q
        demand = 60.q
      }
      product(Item.SCREW, 240.q, 240.q)

      rate(Recipe.IRON_INGOT, 200.q)
      rate(Recipe.IRON_ROD, 200.q)
      rate(Recipe.SCREW, 300.q)
      rate(Recipe.CAST_SCREW, 240.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_multipleInputs_differentPotentialOutputs() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      input(Item.COPPER_ORE, 60.q)

      productWeight(Item.IRON_PLATE, 1.q)
      productWeight(Item.CABLE, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 120.q) {
        consumption = 90.q
        demand = 90.q
      }
      input(Item.COPPER_ORE, 60.q) {
        consumption = 60.q
        demand = 60.q
      }

      product(Item.IRON_PLATE, 60.q, 80.q)
      product(Item.CABLE, 60.q, 60.q)

      rate(Recipe.IRON_INGOT, 300.q)
      rate(Recipe.IRON_PLATE, 300.q)

      rate(Recipe.COPPER_INGOT, 200.q)
      rate(Recipe.WIRE, 400.q)
      rate(Recipe.CABLE, 200.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_repeatedInput_distributedConsumption() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 15.q)
      input(Item.IRON_ORE, 15.q)
      input(Item.IRON_ORE, 15.q)
      input(Item.IRON_ORE, 15.q)

      productAmount(Item.IRON_PLATE, 4.q)
      productAmount(Item.REINFORCED_IRON_PLATE, 4.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 15.q) {
        consumption = 15.q
        demand = 15.q
      }
      input(Item.IRON_ORE, 15.q) {
        consumption = 15.q
        demand = 15.q
      }
      input(Item.IRON_ORE, 15.q) {
        consumption = 15.q
        demand = 15.q
      }
      input(Item.IRON_ORE, 15.q) {
        consumption = 9.q
        demand = 9.q
      }

      product(Item.IRON_PLATE, 4.q, 8.q)
      product(Item.REINFORCED_IRON_PLATE, 4.q, 45.q / 10.q)

      rate(Recipe.IRON_INGOT, 180.q)
      rate(Recipe.IRON_PLATE, 140.q)
      rate(Recipe.IRON_ROD, 80.q)
      rate(Recipe.SCREW, 120.q)
      rate(Recipe.REINFORCED_IRON_PLATE, 80.q)
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_passthroughInput_pipesInputToProduct() {
    // Example assuming a 30 iron ore -> 30 ingot partition while maximizing plates.
    val request = optimizeRequest {
      input(Item.IRON_ORE, 30.q)
      input(Item.IRON_INGOT, 30.q)

      productWeight(Item.IRON_PLATE, 1.q)
      productAmount(Item.IRON_ORE, 30.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      input(Item.IRON_ORE, 30.q) {
        consumption = 30.q
        demand = 30.q
      }
      input(Item.IRON_INGOT, 30.q) {
        consumption = 30.q
        demand = 30.q
      }

      product(Item.IRON_PLATE, 20.q, 20.q)
      product(Item.IRON_ORE, 30.q, 0.q) // TODO: Potential should also be 30 here.

      rate(Recipe.IRON_PLATE, 100.q)
    }
    assertEquals(expected, response)
  }
}
