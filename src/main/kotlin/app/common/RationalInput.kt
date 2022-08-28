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

    var validators: List<(Rational) -> RationalValidation>
}

val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
    var managedText by PropsDelegate(props.text) { next -> next?.let { props.setText?.invoke(it) } }
    var stateText by useState(props.value?.toString() ?: "")

    var error by useState(false)
    var errorMessage by useState<String?>(null)

    TextField {
        sx {
            if (!error) marginBottom = (22.91).px
        }

        variant = FormControlVariant.outlined
        props.label?.let { label = it }

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
    }
}

data class RationalValidation(
    val error: Boolean,
    val message: String?
) {
    companion object {
        private val PASS = RationalValidation(false, null)

        fun pass() = PASS
        fun fail(message: String) = RationalValidation(true, message)
    }
}
