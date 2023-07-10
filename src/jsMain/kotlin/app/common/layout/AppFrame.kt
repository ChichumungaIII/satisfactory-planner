package app.common.layout

import app.common.input.Toggle
import app.theme.AppThemeContext
import js.core.jso
import mui.icons.material.Menu
import mui.icons.material.MenuOpen
import mui.material.AppBar
import mui.material.Box
import mui.material.Drawer
import mui.material.DrawerVariant
import mui.material.Toolbar
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import react.useState

external interface AppFrameProps : Props {
  var title: ReactNode
  var content: ReactNode
}

val AppFrame = FC<AppFrameProps>("AppFrame") { props ->
  val appTheme by useContext(AppThemeContext)!!

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
    PaperProps = jso {
      sx { paddingTop = appTheme.constants.toolbarHeight }
    }
  }

  Box {
    +props.content
  }
}
