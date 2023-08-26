package app.common.input

import app.util.PropsDelegate
import js.core.jso
import mui.material.FormControlVariant
import mui.material.InputAdornment
import mui.material.OutlinedTextFieldProps
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.dom.onChange
import react.useEffect
import react.useState
import util.math.Rational
import web.cssom.Width
import web.cssom.px

enum class RationalInputVariant(
  val adornment: String? = null,
  val width: Width = 172.px,
) {
  CLOCK_SPEED(adornment = "%"),
  NUMBER(width = 56.px),
  RATE(adornment = "/ min")
}

external interface RationalInputProps : Props {
  var model: Rational?
  var setModel: (Rational?) -> Unit

  var variant: RationalInputVariant?
}

val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
  var model by PropsDelegate(props.model, props.setModel)
  val variant = props.variant ?: RationalInputVariant.RATE

  var text by useState(model?.toString() ?: "")
  var error by useState(model == null)
  useEffect(model) {
    model.takeIf { it != Rational.parse(text) }?.also {
      text = it.toString()
      error = false
    }
  }

  TextField {
    sx { width = variant.width }

    this.variant = FormControlVariant.outlined
    variant.adornment?.also {
      this.unsafeCast<OutlinedTextFieldProps>().InputProps = jso {
        endAdornment = InputAdornment.create { +it }
      }
    }

    value = text
    onChange = { event ->
      val next = event.target.asDynamic().value as String
      text = next

      Rational.parse(next).also {
        model = it
        error = it == null
      }
    }

    this.error = error
  }
}
