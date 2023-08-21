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
  var model: Rational
  var setModel: (Rational) -> Unit
}

val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
  var model by PropsDelegate(props.model, props.setModel)

  var text by useState(model.toString())
  var error by useState(false)
  useEffect(model) {
    model.takeIf { it != Rational.parse(text) }?.also {
      text = it.toString()
      error = false
    }
  }

  TextField {
    value = text
    onChange = { event ->
      val next = event.target.asDynamic().value as String
      text = next

      Rational.parse(next)?.also {
        model = it
        error = false
      } ?: run { error = true }
    }

    this.error = error
  }
}
