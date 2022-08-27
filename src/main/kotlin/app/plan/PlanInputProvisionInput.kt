package app.plan

import app.common.RationalInput
import app.util.PropsDelegate
import mui.material.Box
import mui.system.PropsWithSx
import react.FC
import react.useState
import util.math.Rational

external interface PlanInputTargetProps : PropsWithSx {
    var provision: Rational
    var setProvision: (Rational) -> Unit

    var minimum: Rational?
}

val PlanInputProvisionInput = FC<PlanInputTargetProps>("PlanInputTarget") { props ->
    var provision by PropsDelegate(props.provision) { next -> props.setProvision(next) }

    var error by useState(false)
    var errorMessage by useState("")

    val checkError = { value: Rational ->
        val minimum = props.minimum
        if (minimum == null || value >= minimum) {
            error = false
        } else {
            error = true
            errorMessage = "Input must be at least ${minimum.toDecimal(4)} ($minimum)"
        }
    }

    Box {
        RationalInput {
            value = provision
            setValue = { next ->
                next?.let {
                    provision = it
                    checkError(it)
                }
            }

            this.error = error
            setError = { next ->
                error = next
                if (!next) checkError(provision)
            }
            this.errorMessage = errorMessage
            setErrorMessage = { next -> errorMessage = next }
        }
    }
}
