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

external interface RecipeAutocompleteProps : Props {
    var model: Recipe?
    var setModel: (Recipe?) -> Unit

    var building: Building?
}

val RecipeAutocomplete = FC<RecipeAutocompleteProps>("RecipeAutocomplete") { props ->
    var model by PropsDelegate(props.model, props.setModel)

    @Suppress("UPPER_BOUND_VIOLATED") (Autocomplete<AutocompleteProps<RecipeAutocompleteOption>> {
        renderInput = { params ->
            TextField.create {
                +params
                label = ReactNode("Recipe")
            }
        }
        size = "small"

        options = (props.building?.let { listOf(it) } ?: BUILDINGS)
            .flatMap { it.recipes }
            .map { RecipeAutocompleteOption(it) }
            .toTypedArray()

        value = model?.let { RecipeAutocompleteOption(it) }
        isOptionEqualToValue = { x, y -> x.data == y.data }
        onChange = { _, next: RecipeAutocompleteOption?, _, _ -> model = next?.data }

        autoHighlight = true
        autoSelect = true
    })
}

private external interface RecipeAutocompleteOption {
    val label: String
    val data: Recipe
}

private fun RecipeAutocompleteOption(building: Recipe) = object : RecipeAutocompleteOption {
    override val label = building.displayName
    override val data = building
}
