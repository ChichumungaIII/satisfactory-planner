package app

import app.themes.Colors
import csstype.Display
import csstype.FlexDirection
import csstype.Padding
import csstype.integer
import csstype.px
import mui.icons.material.Menu
import mui.icons.material.MenuOpen
import mui.material.AppBar
import mui.material.IconButton
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props

external interface HeaderProps : Props {
    var title: String
    var isOpen: Boolean
    var toggle: () -> Unit
}

val Header = FC<HeaderProps>("Header") { props ->
    AppBar {
        sx {
            padding = Padding(12.px, 12.px)
            zIndex = integer(1300)

            display = Display.flex
            flexDirection = FlexDirection.row
        }

        IconButton {
            (if (props.isOpen) MenuOpen else Menu) { sx { color = Colors.white } }
            onClick = { props.toggle() }
        }

        Typography {
            sx { padding = Padding(0.px, 12.px) }
            variant = TypographyVariant.h1
            +props.title
        }
    }
}
