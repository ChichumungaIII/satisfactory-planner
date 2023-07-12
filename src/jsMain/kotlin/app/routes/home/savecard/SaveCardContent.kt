package app.routes.home.savecard

import app.api.save.v1.Save
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props

external interface SaveCardContentProps : Props {
  var save: Save
}

val SaveCardContent = FC<SaveCardContentProps>("SaveCardContent") { props ->
  Typography {
    variant = TypographyVariant.h2
    +props.save.displayName
  }
}
