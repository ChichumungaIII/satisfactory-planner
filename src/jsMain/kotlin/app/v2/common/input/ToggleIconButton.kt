package app.v2.common.input

import app.util.PropsDelegate
import mui.icons.material.SvgIconComponent
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.Tooltip
import react.FC
import react.Props
import react.ReactNode

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

    Tooltip {
        title = ReactNode(if (toggle) props.titleOn else props.titleOff)

        IconButton {
            color = IconButtonColor.default
            size = Size.small
            (if (toggle) props.iconOn else props.iconOff) {
                fontSize = SvgIconSize.medium
            }

            onClick = { toggle = !toggle }
        }
    }
}
