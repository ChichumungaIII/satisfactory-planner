package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Item
import app.game.data.Recipe


sealed interface OpVar {
  companion object {
    fun create(recipe: Recipe): OpVar = RecipeVar(recipe)
    fun create(item: Item): OpVar = ItemVar(item)
  }
}

data class RecipeVar(val recipe: Recipe) : OpVar
data class ItemVar(val item: Item) : OpVar
