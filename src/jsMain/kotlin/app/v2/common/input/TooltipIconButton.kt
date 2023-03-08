package app.v2.common.input

import mui.icons.material.SvgIconComponent
import mui.material.*
import react.FC
import react.Props
import react.ReactNode

external interface TooltipIconButtonProps : Props {
    var title: String
    var icon: SvgIconComponent
    var onClick: () -> Unit
}

val TooltipIconButton = FC<TooltipIconButtonProps>("TooltipIconButton") { props ->
    Tooltip {
        title = ReactNode(props.title)

        IconButton {
            color = IconButtonColor.default
            size = Size.small
            props.icon { fontSize = SvgIconSize.medium }
            onClick = { props.onClick() }
        }
    }
}
