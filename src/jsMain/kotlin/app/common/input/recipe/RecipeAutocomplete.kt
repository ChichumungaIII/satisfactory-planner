package app.common.input.recipe

import app.common.input.autocomplete.createAppAutocomplete
import app.game.data.RecipeV2
import web.cssom.px

val RECIPE_AUTOCOMPLETE_WIDTH = 264.px
val RecipeAutocomplete = createAppAutocomplete(
  displayName = "RecipeAutocomplete",
  label = "Recipe",
  grouping = { "" },
  render = RecipeV2::displayName,
  ordering = compareBy { it },
  width = RECIPE_AUTOCOMPLETE_WIDTH,
)
