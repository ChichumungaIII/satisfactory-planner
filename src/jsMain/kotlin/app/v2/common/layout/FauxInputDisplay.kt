package app.v2.common.layout

import app.data.Item
import app.data.recipe.Recipe
import csstype.ClassName
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface FauxInputDisplayProps : Props {
  var variant: FauxInputDisplayVariant
  var value: Any
}

enum class FauxInputDisplayVariant(
  val label: String? = null,
  val adornment: String? = null,
  val render: (Any) -> String = { it.toString() },
) {
  CLOCK("Clock Speed", "%"),
  ITEM("Item", render = {
    if (it is Item) it.displayName
    else it.toString()
  }),
  RATE(adornment = "/ min"),
  RECIPE("Recipe", render = {
    if (it is Recipe) it.displayName
    else it.toString()
  });

  val className = name.lowercase()
}

val FauxInputDisplay = FC<FauxInputDisplayProps>("FauxInputDisplay") { props ->
  Paper {
    className = ClassName("faux-input-display faux-input-display--${props.variant.className}")
    variant = PaperVariant.outlined

    props.variant.label?.also {
      Typography {
        className = ClassName("faux-input-display__label")
        variant = TypographyVariant.caption
        +it
      }
    }

    Typography {
      variant = TypographyVariant.body1
      +props.variant.render(props.value)
    }

    props.variant.adornment?.also {
      Typography {
        className = ClassName("faux-input-display__adornment")
        variant = TypographyVariant.body1
        +it
      }
    }
  }
}
