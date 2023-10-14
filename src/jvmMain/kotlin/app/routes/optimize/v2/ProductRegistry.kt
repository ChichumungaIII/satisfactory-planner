package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.OptimizeResponse
import app.game.data.Item
import com.chichumunga.satisfactory.app.routes.optimize.v2.ProductManager.Companion.Dynamic
import com.chichumunga.satisfactory.app.routes.optimize.v2.ProductManager.Companion.Fixed
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br
import util.collections.Augmentation
import util.collections.augment
import util.collections.merge

data class ProductRegistry(val managers: List<ProductManager>) {
  companion object {
    private val TO_PRODUCT_REGISTRY = { managers: List<ProductManager> -> ProductRegistry(managers) }
  }

  fun production() = managers.fold(mapOf<Item, BigRational>()) { production, manager ->
    production.merge(manager.item, manager.amount, BigRational::plus)
  }

  fun requirements() = managers.filterIsInstance<Fixed>().fold(mapOf<Item, BigRational>()) { requirements, manager ->
    requirements.merge(manager.item, manager.demand(), BigRational::plus)
  }

  fun weights() = managers.filterIsInstance<Dynamic>().fold(mapOf<Item, BigRational>()) { weights, manager ->
    weights.merge(manager.item, manager.weight, BigRational::plus)
  }

  fun allocateFixed(products: ItemPool) = managers.augment(products, TO_PRODUCT_REGISTRY) { pool, manager ->
    val consumed = minOf(pool[manager.item], manager.demand())
    add(manager.provide(consumed))
    pool.consume(manager.item, consumed)
  }

  fun allocateDynamic(products: ItemPool): Augmentation<ProductRegistry, ItemPool> {
    var scales = managers.filterIsInstance<Dynamic>().fold(mapOf<Item, BigRational>()) { scales, manager ->
      scales.merge(manager.item, manager.weight, BigRational::plus)
    }
    return managers.augment(products, TO_PRODUCT_REGISTRY) { pool, manager ->
      when (manager) {
        is Fixed -> pool.also { add(manager) }
        is Dynamic -> {
          val distribution = pool[manager.item] * manager.weight / scales[manager.item]!!
          scales = scales.merge(manager.item, manager.weight, BigRational::minus)
          add(manager.distribute(distribution))
          pool.consume(manager.item, distribution)
        }
      }
    }
  }

  fun products(potential: Map<Item, BigRational>): List<OptimizeResponse.Product> {
    val requirements = managers.filterIsInstance<Fixed>().fold(mapOf<Item, BigRational>()) { req, manager ->
      req.merge(manager.item, manager.requirement, BigRational::plus)
    }
    return managers.map {
      OptimizeResponse.Product(
        item = it.item,
        amount = it.amount.toRational(),
        potential = when (it) {
          is Fixed -> potential[it.item]!! + it.requirement - requirements[it.item]!!
          is Dynamic -> potential[it.item]!! - (requirements[it.item] ?: 0.br)
        }.toRational(),
      )
    }
  }
}
