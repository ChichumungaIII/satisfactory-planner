package app.common

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
    var value: Rational?
    var setValue: (Rational?) -> Unit
}

val RationalInput = FC<RationalInputProps>("RationalInput") { props ->
    var text by useState(props.value?.toString() ?: "")
    var error by useState(false)

    TextField {
        sx {
            if (!error) marginBottom = (22.91).px
        }

        variant = FormControlVariant.outlined
        this.error = error
        if (error) helperText = ReactNode("Value must be a rational ")

        value = text
        onChange = { event ->
            val nextText = event.target.asDynamic().value as String
            text = nextText

            val next = Rational.parse(nextText)
            props.setValue(next)

            error = nextText.isNotEmpty() && next == null
        }
    }
}
