package app.plan

import app.common.RationalInput
import app.common.RationalValidation
import app.util.PropsDelegate
import mui.material.Box
import mui.system.PropsWithSx
import react.FC
import react.ReactNode
import util.math.Rational

external interface PlanProductRequirementInputProps : PropsWithSx {
    var requirement: Rational
    var setRequirement: (Rational) -> Unit

    var maximum: Rational?
}

val PlanProductRequirementInput = FC<PlanProductRequirementInputProps>("PlanProductRequirementInput") { props ->
    var requirement by PropsDelegate(props.requirement) { next -> props.setRequirement(next) }

    Box {
        RationalInput {
            label = ReactNode("Minimum desired")

            value = requirement
            setValue = { next -> next?.let { requirement = it } }

            validators = listOf { value ->
                val maximum = props.maximum
                if (maximum == null || value <= maximum) RationalValidation.pass()
                else RationalValidation.fail("Maximum yield is ${maximum.toDecimal(4)} (${maximum})")
            }
        }
    }
}
