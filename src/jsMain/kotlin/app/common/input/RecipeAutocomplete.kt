package app.common.input

import app.data.building.Building
import app.data.recipe.Recipe
import app.util.PropsDelegate
import csstype.Margin
import csstype.px
import js.core.jso
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.TextField
import mui.system.sx
import react.FC
import react.PropsWithClassName
import react.ReactNode
import react.create

external interface RecipeAutocompleteProps : PropsWithClassName {
    var recipe: Recipe?
    var setRecipe: (Recipe?) -> Unit

    var building: Building
}

val RecipeAutocomplete = FC<RecipeAutocompleteProps>("RecipeAutocomplete") { props ->
    var recipe by PropsDelegate(props.recipe) { props.setRecipe(it) }

    @Suppress("UPPER_BOUND_VIOLATED") Autocomplete<AutocompleteProps<RecipeAutocompleteOption>> {
        className = props.className
        sx {
            width = 256.px
            margin = Margin(0.px, 6.px)
        }

        disablePortal = true
        renderInput = { params ->
            TextField.create {
                +params
                label = ReactNode("Recipe")
            }
        }

        options = (listOf(recipe) + props.building.recipes).distinct()
            .map { it?.let { RecipeAutocompleteOption(it) } ?: NOT_SELECTED }.toTypedArray()

        autoComplete = true
        autoHighlight = true
        autoSelect = true
        disableClearable = true
        isOptionEqualToValue = { left, right -> left.data == right.data }

        value = recipe?.let { RecipeAutocompleteOption(it) } ?: NOT_SELECTED
        onChange = { _, next: RecipeAutocompleteOption, _, _ ->
            recipe = next.data
        }
    }
}

private external interface RecipeAutocompleteOption {
    var label: String
    var data: Recipe?
}

private val NOT_SELECTED = jso<RecipeAutocompleteOption> {
    label = "Select a recipe"
    data = null
}

private fun RecipeAutocompleteOption(recipe: Recipe): RecipeAutocompleteOption = jso {
    label = recipe.displayName
    data = recipe
}
