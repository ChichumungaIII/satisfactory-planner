package app

import app.themes.ThemeContext
import csstype.px
import mui.material.Box
import mui.material.styles.create
import mui.system.sx
import react.FC
import react.PropsWithChildren
import react.useContext

external interface HeaderSpacerProps : PropsWithChildren {
    var isOpen: Boolean
}

val HeaderSpacer = FC<HeaderSpacerProps>("HeaderSpacer") { props ->
    val theme by useContext(ThemeContext)

    Box {
        sx {
            transition =
                if (props.isOpen) {
                    theme.mui.transitions.create("margin") {
                        this.easing = theme.mui.transitions.easing.easeOut
                        this.duration = theme.mui.transitions.duration.enteringScreen
                    }
                } else {
                    theme.mui.transitions.create("margin") {
                        this.easing = theme.mui.transitions.easing.sharp
                        this.duration = theme.mui.transitions.duration.leavingScreen
                    }
                }

            marginTop = 66.px
            if (props.isOpen) {
                marginLeft = 156.px
            }
        }

        +props.children
    }
}
