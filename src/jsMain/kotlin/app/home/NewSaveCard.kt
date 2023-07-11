package app.home

import mui.icons.material.Add
import mui.material.Stack
import mui.material.SvgIconSize
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface NewSaveCardProps : Props

val NewSaveCardContent = FC<NewSaveCardProps>("NewSaveCard") { props ->
  Stack {
    Typography {
      variant = TypographyVariant.h2
      +"New Save"
    }

    Add {
      fontSize = SvgIconSize.large
    }
  }
}
