package app.v2.common.input

import app.data.building.Building
import app.data.recipe.Recipe
import app.util.PropsDelegate
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.TextField
import react.FC
import react.Props
import react.ReactNode
import react.create
import web.cssom.ClassName

external interface RecipeAutocompleteProps : Props {
  var model: Recipe?
  var setModel: (Recipe?) -> Unit

  var disabled: Boolean?

  var building: Building?
}

@Deprecated("Use the v2 RecipeAutocomplete.")
val RecipeAutocomplete = FC<RecipeAutocompleteProps>("RecipeAutocomplete") { props ->
  var model by PropsDelegate(props.model, props.setModel)

  @Suppress("UPPER_BOUND_VIOLATED") Autocomplete<AutocompleteProps<RecipeAutocompleteOption>> {
    className = ClassName("recipe-autocomplete")

    renderInput = { params ->
      TextField.create {
        +params
        label = ReactNode("Recipe")
      }
    }
    size = "small"

    disabled = props.disabled ?: false

    options = (props.building?.let { listOf(it) } ?: BUILDINGS)
      .flatMap { building -> building.recipes.map { RecipeAutocompleteOption(it, building) } }
      .toTypedArray()
    getOptionLabel = { it.recipe.displayName }

    groupBy = { it.building?.displayName ?: "Selected" }

    value = model?.let { RecipeAutocompleteOption(it, null) }
    isOptionEqualToValue = { x, y -> x.recipe == y.recipe }
    onChange = { _, next: RecipeAutocompleteOption?, _, _ -> model = next?.recipe }

    autoComplete = true
    autoHighlight = true
    clearOnEscape = true
  }
}

private external interface RecipeAutocompleteOption {
  val recipe: Recipe
  val building: Building?
}

private fun RecipeAutocompleteOption(recipe: Recipe, building: Building?) = object : RecipeAutocompleteOption {
  override val recipe = recipe
  override val building = building
}
