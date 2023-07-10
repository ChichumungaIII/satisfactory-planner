package app.home

import app.common.layout.AppFrame
import mui.material.Box
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create

external interface HomeRouteProps : Props

val HomeRoute = FC<HomeRouteProps>("HomeRoute") {
  AppFrame {
    title = Typography.create {
      variant = TypographyVariant.h1
      +"Satisfactory Planner"
    }

    content = Box.create { +"Content" }
  }
}
