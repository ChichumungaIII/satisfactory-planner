package app.home

import mui.material.ButtonBaseProps
import mui.material.Card
import mui.material.CardActionArea
import mui.material.CardContent
import mui.system.sx
import react.FC
import react.PropsWithChildren
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.JustifyContent
import web.cssom.pct
import web.cssom.px

external interface HomePageCardProps : PropsWithChildren {
  var disabled: Boolean?
  var onClick: () -> Unit
}

val HomePageCard = FC<HomePageCardProps>("HomePageCard") { props ->
  Card {
    sx {
      height = 320.px // 256 + 64
      width = 256.px
    }

    CardActionArea {
      sx {
        height = 100.pct
        display = Display.flex
        justifyContent = JustifyContent.center
        alignItems = AlignItems.center
      }
      this.unsafeCast<ButtonBaseProps>().run {
        disabled = props.disabled
        onClick = { props.onClick() }
      }

      CardContent {
        +props.children
      }
    }
  }
}
