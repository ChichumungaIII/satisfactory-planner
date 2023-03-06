package app.v2.common.input

import app.util.PropsDelegate
import mui.icons.material.SvgIconComponent
import react.FC
import react.Props

external interface ToggleIconButtonProps : Props {
    var toggle: Boolean
    var setToggle: (Boolean) -> Unit

    var titleOn: String
    var iconOn: SvgIconComponent

    var titleOff: String
    var iconOff: SvgIconComponent
}

val ToggleIconButton = FC<ToggleIconButtonProps>("ToggleIconButton") { props ->
    var toggle by PropsDelegate(props.toggle, props.setToggle)

    TooltipIconButton {
        title = if (toggle) props.titleOn else props.titleOff
        icon = if (toggle) props.iconOn else props.iconOff
        onClick = { toggle = !toggle }
    }
}
