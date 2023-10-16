package app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequest
import app.api.optimize.v2.OptimizeResponse
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
    val request = OptimizeRequest(
      inputs = listOf(
        OptimizeRequest.Input(Item.IRON_ORE, 60.q),
      ),
      products = listOf(
        OptimizeRequest.Product.weight(Item.IRON_PLATE, 1.q),
      ),
      restrictions = listOf(),
    )
    val response = optimize(request)

    val expected = OptimizeResponse(
      inputs = listOf(
        OptimizeResponse.Input(Item.IRON_ORE, 60.q, consumption = 60.q, demand = 60.q),
      ),
      products = listOf(
        OptimizeResponse.Product(Item.IRON_PLATE, amount = 40.q, potential = 40.q)
      ),
      byproducts = mapOf(
        Item.IRON_ORE to 60.q, // TODO: This is a bug.
      ),
      rates = mapOf(
        Recipe.IRON_INGOT to 200.q / 100.q,
        Recipe.IRON_PLATE to 200.q / 100.q,
      ),
    )
    assertEquals(expected, response)
  }

  @Test
  fun optimize_singleInput_fixedOutput() = runBlocking {
    val request = OptimizeRequest(
      inputs = listOf(
        OptimizeRequest.Input(Item.IRON_ORE, 60.q),
      ),
      products = listOf(
        OptimizeRequest.Product.amount(Item.IRON_PLATE, 30.q),
      ),
      restrictions = listOf(),
    )
    val response = optimize(request)

    val expected = OptimizeResponse(
      inputs = listOf(
        OptimizeResponse.Input(Item.IRON_ORE, 60.q, consumption = 45.q, demand = 45.q),
      ),
      products = listOf(
        OptimizeResponse.Product(Item.IRON_PLATE, amount = 30.q, potential = 40.q),
      ),
      byproducts = mapOf(
        Item.IRON_ORE to 60.q, // TODO: This is a bug.
      ),
      rates = mapOf(
        Recipe.IRON_INGOT to 150.q / 100.q,
        Recipe.IRON_PLATE to 150.q / 100.q,
      ),
    )
    assertEquals(expected, response)
  }

}
