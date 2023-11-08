package com.chichumunga.satisfactory.app.routes.optimize.v2

import com.chichumunga.satisfactory.util.math.BigRational
import util.math.Expression

data class Objective(
  val expression: Expression<OpVar, BigRational>,
  val weight: BigRational,
  val offset: BigRational,
)
