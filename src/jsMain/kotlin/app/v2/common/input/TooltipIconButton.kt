package app.v2.common.input

import mui.icons.material.SvgIconComponent
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.Tooltip
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
