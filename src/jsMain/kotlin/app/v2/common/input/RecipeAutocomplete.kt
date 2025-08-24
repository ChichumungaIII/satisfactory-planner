package app.v2.common.input

import app.game.data.Building
import app.game.data.RecipeV2
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
  var model: RecipeV2?
  var setModel: (RecipeV2?) -> Unit

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

    options = (props.building?.let { listOf(it) } ?: Building.entries)
      .flatMap { building -> building.recipes.flatMap { it.recipes }.map { RecipeAutocompleteOption(it, building) } }
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
  val recipe: RecipeV2
  val building: Building?
}

private fun RecipeAutocompleteOption(recipe: RecipeV2, building: Building?) = object : RecipeAutocompleteOption {
  override val recipe = recipe
  override val building = building
}
