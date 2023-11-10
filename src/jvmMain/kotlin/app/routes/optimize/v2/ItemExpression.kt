package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Item
import com.chichumunga.satisfactory.util.math.BigRational
import util.math.Expression

data class ItemExpression(
  val item: Item,
  val production: Expression<OpVar, BigRational>,
  val consumption: Expression<OpVar, BigRational>,
)
