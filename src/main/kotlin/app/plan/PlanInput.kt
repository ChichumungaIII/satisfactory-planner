package app.plan

import app.common.ItemAutocomplete
import app.common.RationalInput
import app.common.RationalValidation
import app.model.PlanInputModel
import app.util.PropsDelegate
import csstype.Display
import csstype.Margin
import csstype.px
import mui.icons.material.Close
import mui.material.Box
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode

external interface PlanInputProps : Props {
    var isFirst: Boolean

    var input: PlanInputModel
    var setInput: (PlanInputModel) -> Unit
    var onDelete: () -> Unit
}

val PlanInput = FC<PlanInputProps>("PlanInput") { props ->
    var input by PropsDelegate(props.input) { next -> props.setInput(next) }

    Box {
        sx {
            display = Display.flex
            margin = if (props.isFirst) Margin(0.px, 0.px, 12.px) else Margin(12.px, 0.px)
        }

        IconButton {
            sx { height = 56.px }

            color = IconButtonColor.default
            size = Size.large
            Close { fontSize = SvgIconSize.inherit }

            onClick = { props.onDelete() }
        }

        ItemAutocomplete {
            item = input.item
            setItem = { next -> input = input.copy(item = next) }
        }

        RationalInput {
            label = ReactNode("Amount available")

            value = input.provision
            setValue = { next -> next?.let { input = input.copy(provision = it) } }

            validators = listOf { value ->
                val minimum = input.minimum
                if (minimum == null || value >= minimum) RationalValidation.pass()
                else RationalValidation.fail("Plan requires at least ${minimum.toDecimal(4)} ($minimum)")
            }
        }

        val target = input.target
        if (target != null) {
            Box {
                +"Consumed: ${target.toDecimal(4)} | ($target)"
            }
        }

        val minimum = input.minimum
        if (minimum != null) {
            Box {
                +"Required: ${minimum.toDecimal(4)} ($minimum)"
            }
        }
    }
}
