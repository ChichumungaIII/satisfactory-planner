package app.common.input

import app.common.input.autocomplete.createAppAutocomplete
import app.game.data.Recipe
import web.cssom.px

val RecipeAutocomplete = createAppAutocomplete(
  displayName = "RecipeAutocomplete",
  label = "Recipe",
  grouping = { "" },
  render = Recipe::displayName,
  ordering = compareBy { it },
  width = 300.px,
)
