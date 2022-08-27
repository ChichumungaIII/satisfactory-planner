package app.plan

import app.common.ItemAutocomplete
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

        PlanInputProvisionInput {
            sx { display = Display.contents }

            provision = input.provision
            setProvision = { next -> input = input.copy(provision = next) }

            minimum = input.minimum
        }
    }
}
