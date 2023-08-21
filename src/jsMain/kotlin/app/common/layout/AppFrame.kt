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
import mui.material.styles.create
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import web.cssom.number
import web.cssom.px

external interface AppFrameProps : Props {
  var title: ReactNode
  var actions: List<ReactNode>?
  var content: ReactNode
}

val AppFrame = FC<AppFrameProps>("AppFrame") { props ->
  val appTheme by useContext(AppThemeContext)!!
  val drawerOpenState = useContext(AppFrameDrawerOpenContext)!!
  val drawerOpen by drawerOpenState

  AppBar {
    Toolbar {
      Toggle {
        value = drawerOpenState
        active = MenuOpen
        inactive = Menu
      }

      Box {
        sx { marginLeft = appTheme.spacing(3) }
        +props.title
      }

      Box { sx { flexGrow = number(1.0) } }

      (props.actions ?: listOf()).forEach { +it }
    }
  }

  Drawer {
    variant = DrawerVariant.persistent
    open = drawerOpen
    PaperProps = jso {
      sx {
        paddingTop = appTheme.constants.toolbarHeight
        width = appTheme.constants.navigationDrawerWidth
      }
    }

    NavigationList {}
  }

  Box {
    sx {
      marginLeft = appTheme.constants.navigationDrawerWidth.takeIf { drawerOpen } ?: 0.px
      transition = appTheme.transitions.create("margin") {
        if (drawerOpen) {
          easing = appTheme.transitions.easing.easeOut
          duration = appTheme.transitions.duration.enteringScreen
        } else {
          easing = appTheme.transitions.easing.sharp
          duration = appTheme.transitions.duration.leavingScreen
        }
      }
    }

    +props.content
  }
}
