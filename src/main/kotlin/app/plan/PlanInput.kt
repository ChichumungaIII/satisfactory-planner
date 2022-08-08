package app.plan

import app.common.ItemAutocomplete
import app.model.PlanInputModel
import app.util.PropsDelegate
import csstype.Padding
import csstype.px
import mui.material.Box
import mui.system.sx
import react.FC
import react.Props

external interface PlanInputProps : Props {
    var input: PlanInputModel
    var setInput: (PlanInputModel) -> Unit
}

val PlanInput = FC<PlanInputProps>("PlanInput") { props ->
    var input by PropsDelegate(props.input) { next -> props.setInput(next) }

    Box {
        sx { padding = Padding(12.px, 0.px) }

        ItemAutocomplete {
            item = input.item()
            setItem = { next -> input = input.setItem(next) }
        }
    }
}
