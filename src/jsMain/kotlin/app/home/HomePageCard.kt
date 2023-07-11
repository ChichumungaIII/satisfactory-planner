package app.home

import mui.material.ButtonBaseProps
import mui.material.Card
import mui.material.CardActionArea
import mui.material.CardActions
import mui.material.CardContent
import mui.material.Grid
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.JustifyContent
import web.cssom.px

external interface HomePageCardProps : Props {
  var disabled: Boolean?
  var onClick: () -> Unit

  var content: ReactNode
  var actions: ReactNode?
}

val HomePageCard = FC<HomePageCardProps>("HomePageCard") { props ->
  Grid {
    item = true

    Card {
      sx { width = 256.px }

      CardActionArea {
        sx {
          height = (264 + (props.actions?.let { 0 } ?: 56)).px
          display = Display.flex
          justifyContent = JustifyContent.center
          alignItems = AlignItems.center
        }
        this.unsafeCast<ButtonBaseProps>().run {
          disabled = props.disabled
          onClick = { props.onClick() }
        }

        CardContent { +props.content }
      }

      props.actions?.let { CardActions { +it } }
    }
  }
}
