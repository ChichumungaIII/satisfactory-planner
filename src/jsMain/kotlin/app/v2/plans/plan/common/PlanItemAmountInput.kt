package app.v2.plans.plan.common

import app.common.RationalInput
import app.common.RationalValidation.Companion.fail
import app.common.RationalValidation.Companion.pass
import app.util.PropsDelegate
import csstype.ClassName
import csstype.px
import mui.material.Size
import react.FC
import react.Props
import react.ReactNode
import react.useEffect
import react.useState
import util.math.Rational
import util.math.q

external interface PlanItemAmountInputProps : Props {
    var label: ReactNode

    var model: Rational
    var setModel: (Rational) -> Unit
}

val PlanItemAmountInput = FC<PlanItemAmountInputProps>("PlanItemAmountInput") { props ->
    var model by PropsDelegate(props.model, props.setModel)

    var text by useState(model.toString())
    useEffect(model) {
        if (Rational.parse(text) != model) {
            text = model.toString()
        }
    }

    RationalInput {
        className = ClassName("plan-item-amount-input")
        label = props.label

        setValue = { next -> next?.also { model = it } }

        this.text = text
        setText = { next -> text = next }

        validators = listOf { value ->
            value.takeIf { it < 0.q }
                ?.let { fail("Cannot be negative.") }
                ?: pass()
        }

        onBlur = {
            if (text.isEmpty()) {
                model = 0.q
            }
        }

        size = Size.small
        width = { null }
        adornment = "/ min"
        errorSpacer = 0.px
    }
}
