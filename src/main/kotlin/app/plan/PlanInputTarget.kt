package app.plan

import app.common.RationalInput
import app.util.PropsDelegate
import mui.material.Box
import mui.system.PropsWithSx
import react.FC
import util.math.Rational

external interface PlanInputTargetProps : PropsWithSx {
    var target: Rational
    var setTarget: (Rational) -> Unit

    var minimum: Rational?
    var maximum: Rational
}

val PlanInputTarget = FC<PlanInputTargetProps>("PlanInputTarget") { props ->
    var target by PropsDelegate(props.target) { next -> props.setTarget(next) }

    Box {
        RationalInput {
            value = target
            setValue = { next ->
                if (next != null) {
                    target = next
                }
            }
        }
    }
}
