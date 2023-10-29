package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Recipe
import com.chichumunga.satisfactory.util.math.BigRational
import util.math.Expression

data class Objective(
  val expression: Expression<Recipe, BigRational>,
  val weight: BigRational,
  val offset: BigRational,
)
