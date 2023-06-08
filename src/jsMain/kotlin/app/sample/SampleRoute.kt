package app.sample

import app.themes.SampleTheme
import csstype.None.none
import mui.material.AppBar
import mui.material.CssBaseline
import mui.material.Typography
import mui.material.styles.ThemeProvider
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props

external interface SampleRouteProps : Props

val SampleRoute = FC<SampleRouteProps>("SampleRoute") { props ->
  ThemeProvider {
    val theme = SampleTheme
    this.theme = theme
    CssBaseline {}

    AppBar {
      sx { backgroundImage = none }

      Typography {
        sx { padding = theme.spacing(3) }
        variant = TypographyVariant.h1
        +"Satisfactory Planner"
      }
    }
  }
}
