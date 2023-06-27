package app.v2.common.input

import app.util.PropsDelegate
import mui.material.FormControlVariant
import mui.material.Size
import mui.material.TextField
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import react.useEffect
import react.useState
import web.cssom.ClassName

external interface CountInputProps : Props {
  var model: UInt
  var setModel: (UInt) -> Unit
}

val CountInput = FC<CountInputProps>("CountInput") { props ->
  var model by PropsDelegate(props.model, props.setModel)
  var text by useState(model.toString())
  var error by useState(false)

  useEffect(model.toInt()) {
    text = model.toString()
  }

  TextField {
    className = ClassName("count-input")

    variant = FormControlVariant.outlined
    size = Size.small
    label = ReactNode("Count")

    this.error = error

    value = text
    onChange = { event ->
      val next = event.target.asDynamic().value as String
      text = next

      next.toUIntOrNull()?.also {
        model = it
        error = false
      } ?: run {
        error = true
      }
    }

    onBlur = {
      if (error) {
        model = 0.toUInt()
        text = "0"
        error = false
      }
    }
  }
}
