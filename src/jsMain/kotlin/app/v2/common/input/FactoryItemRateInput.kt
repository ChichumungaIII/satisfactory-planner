package app.v2.common.input

import app.common.RationalInput
import app.common.RationalValidation
import app.util.PropsDelegate
import csstype.ClassName
import csstype.px
import mui.material.Size
import react.FC
import react.Props
import react.useEffect
import react.useState
import util.math.Rational
import util.math.q

external interface FactoryItemRateInputProps : Props {
  var clock: Rational
  var setClock: (Rational) -> Unit

  var count: UInt?
  var rate: Rational
}

val FactoryItemRateInput = FC<FactoryItemRateInputProps>("FactoryItemRateInput") { props ->
  var clock by PropsDelegate(props.clock, props.setClock)

  val scalar = (props.count?.toInt() ?: 1).q * props.rate
  fun convertIn(clock: Rational) = clock * scalar
  fun convertOut(model: Rational) = model / scalar

  var text by useState(convertIn(clock).toString())

  useEffect(clock, props.count?.toInt(), props.rate) {
    convertIn(clock)
      .takeUnless { it == Rational.parse(text) }
      ?.also { text = it.toString() }
  }

  RationalInput {
    className = ClassName("factory-item-rate-input")

    setValue = { next -> next?.let { clock = convertOut(it) } }

    this.text = text
    setText = { next -> text = next }

    disabled = props.count == 0.toUInt()

    validators = listOf { value ->
      value.takeIf { it < 0.q }
        ?.let { RationalValidation.fail("Cannot be negative") }
        ?: RationalValidation.pass()
    }

    onBlur = {
      if (text.isEmpty()) {
        clock = 0.q
      }
    }

    size = Size.small
    width = { null }
    adornment = "/ min"
    errorSpacer = 0.px
  }
}
