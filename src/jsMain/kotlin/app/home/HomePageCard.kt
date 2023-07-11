package app.home

import mui.material.Card
import mui.material.CardContent
import mui.system.sx
import react.FC
import react.PropsWithChildren
import web.cssom.px

external interface HomePageCardProps : PropsWithChildren

val HomePageCard = FC<HomePageCardProps>("HomePageCard") { props ->
  Card {
    sx {
      height = 352.px // 256 + 128 - 32
      width = 256.px
    }

    CardContent {
      +props.children
    }
  }
}
