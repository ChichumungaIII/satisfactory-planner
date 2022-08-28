package app.plan

import app.common.RationalInput
import app.common.RationalValidation
import app.util.PropsDelegate
import mui.material.Box
import mui.system.PropsWithSx
import react.FC
import react.ReactNode
import util.math.Rational

external interface PlanInputTargetProps : PropsWithSx {
    var provision: Rational
    var setProvision: (Rational) -> Unit

    var minimum: Rational?
}

val PlanInputProvisionInput = FC<PlanInputTargetProps>("PlanInputTarget") { props ->
    var provision by PropsDelegate(props.provision) { next -> props.setProvision(next) }

    Box {
        RationalInput {
            label = ReactNode("Amount available")

            value = provision
            setValue = { next -> next?.let { provision = it } }

            validators = listOf { value ->
                val minimum = props.minimum
                if (minimum == null || value >= minimum) RationalValidation.pass()
                else RationalValidation.fail("Input must be at least ${minimum.toDecimal(4)}($minimum)")
            }
        }
    }
}
