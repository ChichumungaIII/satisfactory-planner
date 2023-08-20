package app.common.input

import app.util.PropsDelegate
import mui.material.TextField
import react.FC
import react.Props
import react.dom.onChange
import react.useEffect
import react.useState
import util.math.Rational

external interface RationalInputProps : Props {
  var value: Rational
  var setValue: (Rational) -> Unit
}

val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
  var value by PropsDelegate(props.value, props.setValue)

  var text by useState(value.toString())
  var error by useState(false)
  useEffect(value) {
    value.takeIf { it != Rational.parse(text) }?.also {
      text = it.toString()
      error = false
    }
  }


  TextField {
    this.value = text
    onChange = { event ->
      val next = event.target.asDynamic().value as String
      text = next

      Rational.parse(next)?.also {
        value = it
        error = false
      } ?: run { error = true }
    }

    this.error = error
  }
}
