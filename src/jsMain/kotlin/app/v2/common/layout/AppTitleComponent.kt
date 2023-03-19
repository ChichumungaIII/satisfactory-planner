package app.v2.common.layout

import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface AppTitleComponentProps : Props {
    var title: String
}

val AppTitleComponent = FC<AppTitleComponentProps>("AppTitleComponent") { props ->
    Typography {
        variant = TypographyVariant.h1
        +props.title
    }
}
