package app.common

import app.util.PropsDelegate
import js.core.jso
import mui.material.BaseSize
import mui.material.FormControlVariant
import mui.material.InputAdornment
import mui.material.OutlinedTextFieldProps
import mui.material.TextField
import mui.system.sx
import react.FC
import react.PropsWithClassName
import react.ReactNode
import react.create
import react.dom.onChange
import react.useState
import util.math.Rational
import web.cssom.Length
import web.cssom.Margin
import web.cssom.px

external interface RationalInputProps : PropsWithClassName {
  var label: ReactNode?

  var value: Rational?
  var setValue: (Rational?) -> Unit

  var text: String?
  var setText: ((String) -> Unit)?

  var disabled: Boolean?

  var validators: List<(Rational) -> RationalValidation>
  var onBlur: (() -> Unit)?

  var size: BaseSize?
  var width: (() -> Length?)?
  var adornment: String?
  var errorSpacer: Length?
}

@Deprecated("V2 RationalInput.")
val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
  var managedText by PropsDelegate(props.text) { next -> next?.let { props.setText?.invoke(it) } }
  var stateText by useState(props.value?.toString() ?: "")

  var error by useState(false)
  var errorMessage by useState<String?>(null)

  TextField {
    className = props.className
    sx {
      (props.width ?: { 256.px }).invoke()?.also { width = it }
      margin = Margin(0.px, 6.px, if (error) 0.px else (props.errorSpacer ?: (22.91).px), 6.px)
    }
    size = props.size
    props.adornment?.also { adornment ->
      this.unsafeCast<OutlinedTextFieldProps>().InputProps = jso {
        this.endAdornment = InputAdornment.create { +adornment }
      }
    }

    variant = FormControlVariant.outlined
    props.label?.let { label = it }

    disabled = props.disabled
    this.error = error
    if (error) errorMessage?.let { helperText = ReactNode(it) }

    value = managedText ?: stateText
    onChange = { event ->
      val nextText = event.target.asDynamic().value as String
      if (managedText == null) {
        stateText = nextText
      } else {
        managedText = nextText
      }

      val next = Rational.parse(nextText)
      props.setValue(next)

      if (nextText.isEmpty()) {
        error = false
      } else if (next == null) {
        error = true
        errorMessage = "Value must be rational."
      } else {
        val validation = props.validators.map { it(next) }.firstOrNull { it.error }
        if (validation == null) {
          error = false
        } else {
          error = true
          errorMessage = validation.message
        }
      }
    }

    onBlur = { _ -> props.onBlur?.invoke() }
  }
}

data class RationalValidation(
  val error: Boolean, val message: String?,
) {
  companion object {
    private val PASS = RationalValidation(false, null)

    fun pass() = PASS
    fun fail(message: String) = RationalValidation(true, message)
  }
}
