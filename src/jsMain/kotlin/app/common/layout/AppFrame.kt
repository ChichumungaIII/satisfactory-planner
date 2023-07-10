package app.common.layout

import mui.material.AppBar
import mui.material.Box
import mui.material.Toolbar
import react.FC
import react.Props
import react.ReactNode

external interface AppFrameProps : Props {
  var title: ReactNode
  var content: ReactNode
}

val AppFrame = FC<AppFrameProps>("AppFrame") { props ->
  AppBar {
    Toolbar {
      +props.title
    }
  }

  Box {
    +props.content
  }
}
