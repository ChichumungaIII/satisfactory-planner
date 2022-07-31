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

val HeaderSpacer = FC<HeaderSpacerProps> { props ->
    val theme by useContext(ThemeContext)

    Box {
        sx {
            transition =
                if (props.isOpen) {
                    theme.transitions.create("margin") {
                        this.easing = theme.transitions.easing.easeOut
                        this.duration = theme.transitions.duration.enteringScreen
                    }
                } else {
                    theme.transitions.create("margin") {
                        this.easing = theme.transitions.easing.sharp
                        this.duration = theme.transitions.duration.leavingScreen
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
