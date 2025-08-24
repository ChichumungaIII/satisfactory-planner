package app.v2.common.layout

import app.game.data.Item
import app.game.data.RecipeV2
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import util.math.Rational
import util.math.q
import web.cssom.ClassName

external interface FauxInputDisplayProps : Props {
  var variant: FauxInputDisplayVariant
  var label: String?
  var value: Any
}

enum class FauxInputDisplayVariant(
  val label: String? = null,
  val adornment: String? = null,
  val render: (Any) -> String = { it.toString() },
) {
  CLOCK("Clock Speed", "%", render = {
    if (it is Rational) (it * 100.q).toString()
    else it.toString()
  }),
  ITEM("Item", render = {
    if (it is Item) it.displayName
    else it.toString()
  }),
  RATE(adornment = "/ min"),
  RECIPE("Recipe", render = {
    if (it is RecipeV2) it.displayName
    else it.toString()
  });

  val className = name.lowercase()
}

val FauxInputDisplay = FC<FauxInputDisplayProps>("FauxInputDisplay") { props ->
  Paper {
    className = ClassName("faux-input-display faux-input-display--${props.variant.className}")
    variant = PaperVariant.outlined

    (props.label ?: props.variant.label)?.also {
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
