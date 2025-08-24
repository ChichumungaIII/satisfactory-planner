package app.common.input.recipe

import app.game.data.RecipeV2
import mui.material.FormControlVariant
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props

external interface RecipeDisplayProps : Props {
  var value: RecipeV2
}

val RecipeDisplay = FC<RecipeDisplayProps>("RecipeDisplay") { props ->
  TextField {
    sx { width = RECIPE_AUTOCOMPLETE_WIDTH }
    variant = FormControlVariant.standard
    value = buildString {
      if (props.value.type == RecipeV2.Type.GENERATION) append("(Burn) ")
      append(props.value.displayName)
    }
    disabled = true
  }
}
