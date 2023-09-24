package app.common.input.recipe

import app.game.data.Recipe
import mui.material.FormControlVariant
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props

external interface RecipeDisplayProps : Props {
  var value: Recipe
}

val RecipeDisplay = FC<RecipeDisplayProps>("RecipeDisplay") { props ->
  TextField {
    sx { width = RECIPE_AUTOCOMPLETE_WIDTH }
    variant = FormControlVariant.standard
    value = props.value.displayName
    disabled = true
  }
}
