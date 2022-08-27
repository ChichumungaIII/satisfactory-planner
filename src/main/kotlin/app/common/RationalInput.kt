package app.common

import app.util.PropsDelegate
import csstype.px
import mui.material.FormControlVariant
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import util.math.Rational

external interface RationalInputProps : Props {
    var value: Rational?
    var setValue: (Rational?) -> Unit

    var text: String?
    var setText: ((String) -> Unit)?

    var error: Boolean?
    var setError: ((Boolean) -> Unit)?
    var errorMessage: String?
    var setErrorMessage: ((String) -> Unit)?
}

val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
    var text by PropsDelegate(props.text ?: props.value?.toString() ?: "") { next -> props.setText?.invoke(next) }
    var error by PropsDelegate(props.error ?: false) { next -> props.setError?.invoke(next) }
    var errorMessage by PropsDelegate(props.errorMessage ?: "") { next -> props.setErrorMessage?.invoke(next) }

    TextField {
        sx {
            if (!error) marginBottom = (22.91).px
        }

        variant = FormControlVariant.outlined
        this.error = error
        if (error) helperText = ReactNode(errorMessage)

        value = text
        onChange = { event ->
            val nextText = event.target.asDynamic().value as String
            text = nextText

            val next = Rational.parse(nextText)
            props.setValue(next)

            if (nextText.isEmpty()) {
                error = false
            } else if (next == null) {
                error = true
                errorMessage = "Value must be rational."
            }
        }
    }
}
