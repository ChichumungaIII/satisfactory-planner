package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.api.optimize.v2.request.OptimizeOutput.Production
import app.game.data.Item
import com.chichumunga.satisfactory.util.math.BigRational
import com.chichumunga.satisfactory.util.math.br

data class Requirement(
  val item: Item,
  val amount: BigRational,
  val exact: Boolean,
) {
  companion object {
    fun from(production: Production) = Requirement(production.item, production.amount.br, production.exact)
  }

  fun merge(other: Requirement): Requirement {
    check(item == other.item) { "Cannot merge requirements [$item] and [${other.item}]." }
    return Requirement(item, amount + other.amount, exact || other.exact)
  }
}
