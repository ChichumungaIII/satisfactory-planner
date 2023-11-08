package com.chichumunga.satisfactory.app.routes.optimize.v2

import app.game.data.Item
import app.game.data.Recipe


sealed interface OpVar
data class RecipeVar(val recipe: Recipe) : OpVar
data class ItemVar(val item: Item) : OpVar
