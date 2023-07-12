package app.common.util

import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.PropsWithChildren

external interface AppTitleProps : PropsWithChildren

val AppTitle = FC<AppTitleProps>("AppTitle") { props ->
  Typography {
    variant = TypographyVariant.h1
    +props.children
  }
}
