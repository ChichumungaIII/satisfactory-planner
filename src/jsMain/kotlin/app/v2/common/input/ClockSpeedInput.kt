package app.v2.common.input

import app.common.RationalInput
import app.common.RationalValidation.Companion.fail
import app.common.RationalValidation.Companion.pass
import app.util.PropsDelegate
import csstype.ClassName
import csstype.px
import mui.material.Size
import react.FC
import react.Props
import react.ReactNode
import react.useEffect
import react.useState
import util.math.Rational
import util.math.q

external interface ClockSpeedInputProps : Props {
  var model: Rational
  var setModel: (Rational) -> Unit

  var disabled: Boolean?
}

val ClockSpeedInput = FC<ClockSpeedInputProps>("ClockSpeedInput") { props ->
  var model by PropsDelegate(props.model, props.setModel)
  var text by useState((model * 100.q).toString())

  useEffect(model) {
    (model * 100.q).takeUnless { it == Rational.parse(text) }
      ?.also { text = it.toString() }
  }

  RationalInput {
    className = ClassName("clock-speed-input")
    label = ReactNode("Clock Speed")

    setValue = { next -> next?.let { model = it / 100.q } }

    disabled = props.disabled ?: false

    this.text = text
    setText = { next -> text = next }

    validators = listOf({ value ->
      value.takeIf { it < 0.q }
        ?.let { fail("Cannot be negative.") }
        ?: pass()
    }, { value ->
      value.takeIf { it > 250.q }
        ?.let { fail("Cannot exceed 250%.") }
        ?: pass()
    })

    onBlur = {
      if (text.isEmpty()) {
        model = 0.q
      }
    }

    size = Size.small
    width = { null }
    adornment = "%"
    errorSpacer = 0.px
  }
}
