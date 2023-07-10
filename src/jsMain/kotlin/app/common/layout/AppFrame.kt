package app.common.layout

import app.common.input.Toggle
import mui.icons.material.Menu
import mui.icons.material.MenuOpen
import mui.material.AppBar
import mui.material.Box
import mui.material.Drawer
import mui.material.DrawerVariant
import mui.material.Toolbar
import react.FC
import react.Props
import react.ReactNode
import react.useState

external interface AppFrameProps : Props {
  var title: ReactNode
  var content: ReactNode
}

val AppFrame = FC<AppFrameProps>("AppFrame") { props ->
  val drawerOpenState = useState(true)
  val drawerOpen by drawerOpenState

  AppBar {
    Toolbar {
      Toggle {
        value = drawerOpenState
        active = MenuOpen
        inactive = Menu
      }

      +props.title
    }
  }

  Drawer {
    variant = DrawerVariant.persistent
    open = drawerOpen
  }

  Box {
    +props.content
  }
}
