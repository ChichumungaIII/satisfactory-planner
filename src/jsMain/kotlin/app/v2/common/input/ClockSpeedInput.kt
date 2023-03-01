package app.v2.common.input

import app.common.RationalInput
import app.common.RationalValidation.Companion.fail
import app.common.RationalValidation.Companion.pass
import app.util.PropsDelegate
import app.v2.AppScope
import csstype.ClassName
import csstype.px
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mui.material.Size
import react.FC
import react.Props
import react.ReactNode
import react.useState
import util.math.Rational
import util.math.q

external interface ClockSpeedInputProps : Props {
    var model: Rational
    var setModel: (Rational) -> Unit
}

val ClockSpeedInput = FC<ClockSpeedInputProps>("ClockSpeedInput") { props ->
    var model by PropsDelegate(props.model, props.setModel)

    var text by useState((model * 100.q).toString())

    RationalInput {
        className = ClassName("clock-speed-input")
        label = ReactNode("Clock Speed")

        value = model
        setValue = { next -> next?.let { model = it / 100.q } }

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
                text = "0"
            }
        }

        size = Size.small
        width = { null }
        adornment = "%"
        errorSpacer = 0.px
    }
}
