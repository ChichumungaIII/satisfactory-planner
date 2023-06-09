package app.sample

import app.themes.SampleTheme
import app.themes.ThemeContext
import csstype.None
import csstype.pct
import csstype.px
import mui.icons.material.AccountTree
import mui.icons.material.Add
import mui.icons.material.ExpandMore
import mui.icons.material.Factory
import mui.icons.material.MenuOpen
import mui.material.AppBar
import mui.material.AppBarPosition
import mui.material.Box
import mui.material.Collapse
import mui.material.Drawer
import mui.material.DrawerVariant
import mui.material.IconButton
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.ListSubheader
import mui.material.Toolbar
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext
import react.useLayoutEffectOnce

external interface SamplePageProps : Props

val SamplePage = FC<SamplePageProps>("SamplePage") {
  var theme by useContext(ThemeContext)
  useLayoutEffectOnce { theme = SampleTheme }

  AppBar {
    sx { backgroundImage = None.none }
    position = AppBarPosition.sticky

    Toolbar {
      IconButton {
        MenuOpen {}
      }

      Typography {
        sx { padding = theme.spacing(3, 0) }
        variant = TypographyVariant.h1
        +"Satisfactory Planner"
      }
    }
  }

  SampleDrawer {}
}
