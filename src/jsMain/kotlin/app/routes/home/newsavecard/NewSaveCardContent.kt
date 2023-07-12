package app.routes.home.newsavecard

import mui.icons.material.Add
import mui.material.Stack
import mui.material.SvgIconSize
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import web.cssom.AlignItems

external interface NewSaveCardContentProps : Props

val NewSaveCardContent = FC<NewSaveCardContentProps>("NewSaveCard") {
  Stack {
    sx { alignItems = AlignItems.center }

    Typography {
      variant = TypographyVariant.h2
      +"New Save"
    }

    Add {
      fontSize = SvgIconSize.large
    }
  }
}
