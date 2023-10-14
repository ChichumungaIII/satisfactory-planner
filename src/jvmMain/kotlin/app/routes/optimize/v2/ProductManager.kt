package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.OptimizeRequest
import app.game.data.Item
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br

sealed interface ProductManager {
  companion object {
    fun create(product: OptimizeRequest.Product) = when (product.objective.kind) {
      OptimizeRequest.Product.Objective.Kind.AMOUNT -> Fixed(product.item, product.objective.toAmount().br)
      OptimizeRequest.Product.Objective.Kind.WEIGHT -> Dynamic(product.item, product.objective.toWeight().br)
    }

    data class Fixed(
      override val item: Item,
      val requirement: BigRational,
      override val amount: BigRational = 0.br,
    ) : ProductManager {
      override fun demand() = requirement - amount
      override fun provide(amount: BigRational) = Fixed(
        item = item,
        requirement = requirement,
        amount = this.amount + amount
      )
    }

    data class Dynamic(
      override val item: Item,
      val weight: BigRational,
      override val amount: BigRational = 0.br,
    ) : ProductManager {
      override fun demand() = 0.br
      override fun provide(amount: BigRational) = this

      fun distribute(amount: BigRational) = Dynamic(
        item = item,
        weight = weight,
        amount = amount,
      )
    }
  }

  val item: Item
  val amount: BigRational

  fun demand(): BigRational
  fun provide(amount: BigRational): ProductManager
}
