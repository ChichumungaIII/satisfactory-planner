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
import react.useState
import util.math.Rational

external interface RationalInputProps : Props {
    var label: ReactNode?

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
    var managedText by PropsDelegate(props.text) { next -> next?.let { props.setText?.invoke(it) } }
    var stateText by useState(props.value?.toString() ?: "")

    var error by PropsDelegate(props.error ?: false) { next -> props.setError?.invoke(next) }
    var errorMessage by PropsDelegate(props.errorMessage ?: "") { next -> props.setErrorMessage?.invoke(next) }

    TextField {
        sx {
            if (!error) marginBottom = (22.91).px
        }

        variant = FormControlVariant.outlined
        props.label?.let { label = it }

        this.error = error
        if (error) helperText = ReactNode(errorMessage)

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
            }
        }
    }
}
