package app.sample

import app.themes.SampleTheme
import app.themes.ThemeContext
import mui.icons.material.MenuOpen
import mui.icons.material.Settings
import mui.material.AppBar
import mui.material.Box
import mui.material.IconButton
import mui.material.Toolbar
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.useContext
import react.useLayoutEffectOnce
import react.useState
import web.cssom.number

external interface SamplePageProps : Props

val SamplePage = FC<SamplePageProps>("SamplePage") {
  var theme by useContext(ThemeContext)!!
  useLayoutEffectOnce { theme = SampleTheme }

  var settings by useState(false)

  AppBar {
    Toolbar {
      IconButton {
        MenuOpen {}
      }

      Typography {
        sx { padding = theme.spacing(3, 0) }
        variant = TypographyVariant.h1
        +"Satisfactory Planner"
      }

      Box { sx { flexGrow = number(1.0) } }

      IconButton {
        Settings {}
        onClick = { settings = true }
      }
    }
  }

  SampleDrawer {}

  SampleSettingsDialog {
    open = settings
    setOpen = { next -> settings = next }
  }
}
