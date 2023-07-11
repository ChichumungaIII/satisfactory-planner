package app.common.layout

import app.theme.AppThemeContext
import mui.material.Container
import mui.system.sx
import react.FC
import react.Props
import react.useContext

external interface RouteLoadingIndicatorProps : Props

val RouteLoadingIndicator = FC<RouteLoadingIndicatorProps>("RouteLoadingIndicator") { props ->
  val appTheme by useContext(AppThemeContext)!!

  Container {
    sx { marginTop = appTheme.spacing(24) }
    LoadingIndicator { variant = LoadingIndicatorVariant.Large }
  }
}
