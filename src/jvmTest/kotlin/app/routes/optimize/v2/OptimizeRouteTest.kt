package app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeRequest
import app.api.optimize.v2.response.OptimizeResponse.Companion.optimizeResponse
import app.game.data.Item
import app.game.data.RecipeV2
import com.chichumunga.satisfactory.app.routes.optimize.v2.optimize
import kotlinx.coroutines.runBlocking
import util.math.Rational
import util.math.q
import kotlin.test.Test
import kotlin.test.assertEquals

class OptimizeRouteTest {
  @Test
  fun optimize_singleInput_maximizeOutput() = runBlocking {
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

      RecipeV2.IRON_INGOT clock 200.q
      RecipeV2.IRON_PLATE clock 200.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_fixedOutput() = runBlocking {
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

      RecipeV2.IRON_INGOT clock 150.q
      RecipeV2.IRON_PLATE clock 150.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_balancedOutput() = runBlocking {
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

      RecipeV2.IRON_INGOT clock 400.q
      RecipeV2.IRON_PLATE clock 240.q
      RecipeV2.IRON_ROD clock 320.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_imbalancedOutput() = runBlocking {
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

      RecipeV2.IRON_INGOT clock 400.q
      RecipeV2.IRON_PLATE clock 300.q
      RecipeV2.IRON_ROD clock 200.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_offsetWeightedOutput() = runBlocking {
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

      RecipeV2.IRON_INGOT clock 400.q
      RecipeV2.IRON_PLATE clock 280.q
      RecipeV2.IRON_ROD clock 240.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withoutAlternateRecipes_ignoresAlternates() = runBlocking {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      maximize(Item.SCREWS, 1.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )
      produce(Item.SCREWS, 240.q, 240.q)

      RecipeV2.IRON_INGOT clock 200.q
      RecipeV2.IRON_ROD clock 400.q
      RecipeV2.SCREWS clock 600.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withAlternateRecipes_usesAlternates() = runBlocking {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      maximize(Item.SCREWS, 1.q)
      allow(RecipeV2.CAST_SCREWS)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )
      produce(Item.SCREWS, 240.q, 240.q)

      RecipeV2.IRON_INGOT clock 200.q
      RecipeV2.CAST_SCREWS clock 480.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_withRestriction_limitsRecipeUse() = runBlocking {
    val request = optimizeRequest {
      input(Item.IRON_ORE, 60.q)
      maximize(Item.SCREWS, 1.q)
      allow(RecipeV2.CAST_SCREWS)
      RecipeV2.CAST_SCREWS limitToClock 240.q
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      consume(
        Item.IRON_ORE, amount = 60.q,
        consumed = 60.q,
        demand = 60.q,
      )
      produce(Item.SCREWS, 240.q, 240.q)

      RecipeV2.IRON_INGOT clock 200.q
      RecipeV2.IRON_ROD clock 200.q
      RecipeV2.SCREWS clock 300.q
      RecipeV2.CAST_SCREWS clock 240.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_multipleInputs_differentPotentialOutputs() = runBlocking {
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

      RecipeV2.IRON_INGOT clock 300.q
      RecipeV2.IRON_PLATE clock 300.q

      RecipeV2.COPPER_INGOT clock 200.q
      RecipeV2.WIRE clock 400.q
      RecipeV2.CABLE clock 200.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_repeatedInput_distributedConsumption() = runBlocking {
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

      RecipeV2.IRON_INGOT clock 180.q
      RecipeV2.IRON_PLATE clock 140.q
      RecipeV2.IRON_ROD clock 80.q
      RecipeV2.SCREWS clock 120.q
      RecipeV2.REINFORCED_IRON_PLATE clock 80.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_passthroughInput_pipesInputToProduct() = runBlocking {
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

      RecipeV2.IRON_PLATE clock 100.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_multipleOutputProducts_returnsByproducts() = runBlocking {
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

      RecipeV2.PLASTIC clock 500.q
      RecipeV2.RUBBER clock 500.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_bannedByproduct_selectsAlternative() = runBlocking {
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

      RecipeV2.PLASTIC clock 500.q
      RecipeV2.RUBBER clock 500.q
      RecipeV2.RESIDUAL_FUEL clock 250.q
    }
    assertEquals(expected, response)
  }

  @Test
  fun optimize_example_heavyModularFrames() = runBlocking {
    val request = optimizeRequest {
      // Primary
      input(Item.IRON_ORE, 1440.q)
      input(Item.COAL, 900.q)
      input(Item.COPPER_ORE, 480.q)
      input(Item.LIMESTONE, 480.q)
      // Partitioned
      input(Item.ENCASED_INDUSTRIAL_BEAM, 32.q, required = true)
      input(Item.MOTOR, 8.q, required = true)

      // Primary
      produce(Item.MOTOR, 8.q)
      maximize(Item.ENCASED_INDUSTRIAL_BEAM, 1.q)
      produce(Item.HEAVY_MODULAR_FRAME, 4.q)
      // Partition: EIB
      require(Item.IRON_ORE, 512.q)
      require(Item.COAL, 512.q)
      require(Item.LIMESTONE, 480.q)
      // Partition: Motor
      require(Item.IRON_ORE, 252.q)
      require(Item.COAL, 72.q)
      require(Item.COPPER_ORE, 64.q)
    }
    val response = optimize(request)

    val expected = optimizeResponse {
      // Primary
      consume(Item.IRON_ORE, 1440.q, 1434.q, 1434.q)
      consume(Item.COAL, 900.q, 674.q, 674.q)
      consume(Item.COPPER_ORE, 480.q, 64.q, 64.q)
      consume(Item.LIMESTONE, 480.q, 480.q, 480.q)
      // Partitioned
      consume(Item.ENCASED_INDUSTRIAL_BEAM, 32.q, 32.q, 32.q)
      consume(Item.MOTOR, 8.q, 8.q, Rational.parse("7._809523")!!)

      // Primary
      produce(Item.MOTOR, 8.q, Rational.parse("8._190476")!!)
      produce(Item.ENCASED_INDUSTRIAL_BEAM, 12.q, 12.q)
      produce(Item.HEAVY_MODULAR_FRAME, 4.q, 4.q)
      // Partition: EIB
      produce(Item.IRON_ORE, 512.q, 518.q) // +(1440 - 1436) = +6
      produce(Item.COAL, 512.q, 738.q) // +(900 - 674) = +226
      produce(Item.LIMESTONE, 480.q, 480.q)
      // Partition: Motor
      produce(Item.IRON_ORE, 252.q, 258.q) // +(1440 - 1436) = +6
      produce(Item.COAL, 72.q, 298.q) // +(900 - 674) = +226
      produce(Item.COPPER_ORE, 64.q, 480.q) // +(480 - 64) = +416

      RecipeV2.IRON_INGOT clock Rational.parse("1933._3")!!
      RecipeV2.IRON_PLATE clock 900.q
      RecipeV2.IRON_ROD clock Rational.parse("2066._6")!!
      RecipeV2.SCREWS clock 1_900.q
      RecipeV2.REINFORCED_IRON_PLATE clock 600.q
      RecipeV2.MODULAR_FRAME clock 1_000.q
      RecipeV2.STEEL_INGOT clock 200.q
      RecipeV2.STEEL_PIPE clock 300.q
      RecipeV2.HEAVY_MODULAR_FRAME clock 200.q
    }
    assertEquals(expected, response)
  }

  private fun optimizeRequest(init: OptimizeRequest.Builder.() -> Unit) = OptimizeRequest.optimizeRequest {
    allowAll(RecipeV2.allProductionRecipes().filterNot { it.alternate })
    init()
  }
}
