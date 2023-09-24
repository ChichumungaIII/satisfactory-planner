package app.common.input.rational

import js.core.jso
import mui.material.FormControlVariant
import mui.material.InputAdornment
import mui.material.StandardTextFieldProps
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.create
import util.math.Rational
import web.cssom.AlignItems
import web.cssom.Padding
import web.cssom.px

private const val NULL_DISPLAY = "—"

external interface RationalDisplayProps : Props {
  var variant: RationalInputVariant?
  var value: Rational?
}

val RationalDisplay = FC<RationalDisplayProps>("RationalDisplay") { props ->
  val variant = props.variant ?: RationalInputVariant.RATE

  TextField {
    sx { width = variant.width }

    this.variant = FormControlVariant.standard
    variant.adornment?.also {
      this.unsafeCast<StandardTextFieldProps>().InputProps = jso {
        sx {
          alignItems = AlignItems.baseline
          padding = Padding(7.5.px, 14.px, 3.5.px)
        }
        endAdornment = InputAdornment.create { +it }
      }
    }

    value = props.value?.let {
      it.exact() ?: "${it.toDouble().asDynamic().toFixed(6)}..."
    } ?: NULL_DISPLAY
    disabled = true
  }
}
