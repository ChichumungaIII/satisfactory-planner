package app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeResponse.Companion.optimizeResponse
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
      maximize(Item.IRON_PLATE, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )
      produce(Item.IRON_PLATE, 40.q, 40.q)

      Recipe.IRON_INGOT clock 200.q
      Recipe.IRON_PLATE clock 200.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_fixedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      produce(Item.IRON_PLATE, 30.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 45.q,
        demand = 45.q,
      )
      produce(Item.IRON_PLATE, 30.q, 40.q)

      Recipe.IRON_INGOT clock 150.q
      Recipe.IRON_PLATE clock 150.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_balancedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      maximize(Item.IRON_PLATE, 1.q)
      maximize(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 120.q,
        consumed = 120.q,
        demand = 120.q,
      )
      produce(Item.IRON_PLATE, 48.q, 48.q)
      produce(Item.IRON_ROD, 48.q, 48.q)

      Recipe.IRON_INGOT clock 400.q
      Recipe.IRON_PLATE clock 240.q
      Recipe.IRON_ROD clock 320.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_imbalancedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      maximize(Item.IRON_PLATE, 2.q)
      maximize(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 120.q,
        consumed = 120.q,
        demand = 120.q,
      )
      produce(Item.IRON_PLATE, 60.q, 60.q)
      produce(Item.IRON_ROD, 30.q, 30.q)

      Recipe.IRON_INGOT clock 400.q
      Recipe.IRON_PLATE clock 300.q
      Recipe.IRON_ROD clock 200.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_offsetWeightedOutput() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      produce(Item.IRON_PLATE, 20.q)
      maximize(Item.IRON_PLATE, 1.q)
      maximize(Item.IRON_ROD, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 120.q,
        consumed = 120.q,
        demand = 120.q,
      )
      produce(Item.IRON_PLATE, 20.q, 20.q)
      produce(Item.IRON_PLATE, 36.q, 36.q)
      produce(Item.IRON_ROD, 36.q, 36.q)

      Recipe.IRON_INGOT clock 400.q
      Recipe.IRON_PLATE clock 280.q
      Recipe.IRON_ROD clock 240.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withoutAlternateRecipes_ignoresAlternates() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      maximize(Item.SCREW, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )
      produce(Item.SCREW, 240.q, 240.q)

      Recipe.IRON_INGOT clock 200.q
      Recipe.IRON_ROD clock 400.q
      Recipe.SCREW clock 600.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withAlternateRecipes_usesAlternates() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      maximize(Item.SCREW, 1.q)
      allow(Recipe.CAST_SCREW)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )
      produce(Item.SCREW, 240.q, 240.q)

      Recipe.IRON_INGOT clock 200.q
      Recipe.CAST_SCREW clock 480.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withRestriction_limitsRecipeUse() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      maximize(Item.SCREW, 1.q)
      allow(Recipe.CAST_SCREW)
      Recipe.CAST_SCREW limitToClock 240.q
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )
      produce(Item.SCREW, 240.q, 240.q)

      Recipe.IRON_INGOT clock 200.q
      Recipe.IRON_ROD clock 200.q
      Recipe.SCREW clock 300.q
      Recipe.CAST_SCREW clock 240.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_multipleInputs_differentPotentialOutputs() {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 120.q)
      input(Item.COPPER_ORE, 60.q)

      maximize(Item.IRON_PLATE, 1.q)
      maximize(Item.CABLE, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 120.q,
        consumed = 90.q,
        demand = 90.q,
      )
      consume(
        Item.COPPER_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )

      produce(Item.IRON_PLATE, 60.q, 80.q)
      produce(Item.CABLE, 60.q, 60.q)

      Recipe.IRON_INGOT clock 300.q
      Recipe.IRON_PLATE clock 300.q

      Recipe.COPPER_INGOT clock 200.q
      Recipe.WIRE clock 400.q
      Recipe.CABLE clock 200.q
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

      produce(Item.IRON_PLATE, 4.q)
      produce(Item.REINFORCED_IRON_PLATE, 4.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 15.q,
        consumed = 15.q,
        demand = 9.q,
      )
      consume(
        Item.IRON_ORE, amount = 15.q,
        consumed = 15.q,
        demand = 9.q,
      )
      consume(
        Item.IRON_ORE, amount = 15.q,
        consumed = 15.q,
        demand = 9.q,
      )
      consume(
        Item.IRON_ORE, amount = 15.q,
        consumed = 9.q,
        demand = 9.q,
      )

      produce(Item.IRON_PLATE, 4.q, 8.q)
      produce(Item.REINFORCED_IRON_PLATE, 4.q, 45.q / 10.q)

      Recipe.IRON_INGOT clock 180.q
      Recipe.IRON_PLATE clock 140.q
      Recipe.IRON_ROD clock 80.q
      Recipe.SCREW clock 120.q
      Recipe.REINFORCED_IRON_PLATE clock 80.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_passthroughInput_pipesInputToProduct() {
    // Example assuming a 30 iron ore -> 30 ingot partition while maximizing plates.
    val request = optimizeRequest {
      input(Item.IRON_ORE, 30.q)
      input(Item.IRON_INGOT, 30.q)

      maximize(Item.IRON_PLATE, 1.q)
      produce(Item.IRON_ORE, 30.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 30.q,
        consumed = 30.q,
        demand = 30.q,
      )
      consume(
        Item.IRON_INGOT, amount = 30.q,
        consumed = 30.q,
        demand = 30.q,
      )

      produce(Item.IRON_PLATE, 20.q, 20.q)
      produce(Item.IRON_ORE, 30.q, 30.q)

      Recipe.IRON_PLATE clock 100.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_multipleOutputProducts_returnsByproducts() {
    val request = optimizeRequest {
      input(Item.CRUDE_OIL, 300.q)

      maximize(Item.PLASTIC, 1.q)
      maximize(Item.RUBBER, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(Item.CRUDE_OIL, 300.q, 300.q, 300.q)

      produce(Item.PLASTIC, 100.q, 100.q)
      produce(Item.RUBBER, 100.q, 100.q)

      Item.HEAVY_OIL_RESIDUE byproduct 150.q

      Recipe.PLASTIC clock 500.q
      Recipe.RUBBER clock 500.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_bannedByproduct_selectsAlternative() {
    val request = optimizeRequest {
      input(Item.CRUDE_OIL, 300.q)

      maximize(Item.PLASTIC, 1.q)
      maximize(Item.RUBBER, 1.q)
      produce(Item.HEAVY_OIL_RESIDUE, 0.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(Item.CRUDE_OIL, 300.q, 300.q, 300.q)

      produce(Item.PLASTIC, 100.q, 100.q)
      produce(Item.RUBBER, 100.q, 100.q)
      produce(Item.HEAVY_OIL_RESIDUE, 0.q, 150.q)

      Item.FUEL byproduct 100.q

      Recipe.PLASTIC clock 500.q
      Recipe.RUBBER clock 500.q
      Recipe.RESIDUAL_FUEL clock 250.q
    }
    assertEquals(expected, response)
  }

  private fun optimizeRequest(init: OptimizeRequest.Builder.() -> Unit) =
    OptimizeRequest.optimizeRequest {
      allowAll(Recipe.entries.filterNot { it.alternate })
      init()
    }
}
