package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Item
import app.game.data.RecipeV2
import com.chichumunga.satisfactory.util.math.BigRational
import util.math.Expression

typealias OpExpr = Expression<OpVar, BigRational>

sealed interface OpVar {
  companion object {
    fun create(recipe: RecipeV2): OpVar = RecipeVar(recipe)
    fun create(item: Item): OpVar = ItemVar(item)
  }
}

data class RecipeVar(val recipe: RecipeV2) : OpVar
data class ItemVar(val item: Item) : OpVar
