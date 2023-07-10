package app.common.layout

import app.theme.AppThemeContext
import mui.material.Box
import mui.material.CircularProgress
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext
import web.cssom.Auto
import web.cssom.Margin
import web.cssom.px

enum class LoadingIndicatorVariant(
  val size: Number,
  val thickess: Number,
) {
  Small(size = 24, thickess = 3),
  Default(size = 48, thickess = 3.6),
  Large(size = 96, thickess = 4.2);

  val component
    get() = CircularProgress.create {
      size = this@LoadingIndicatorVariant.size
      thickness = this@LoadingIndicatorVariant.thickess
    }
}

external interface LoadingIndicatorProps : Props {
  var variant: LoadingIndicatorVariant?
}

val LoadingIndicator = FC<LoadingIndicatorProps>("LoadingIndicator") { props ->
  var appTheme by useContext(AppThemeContext)!!

  Box {
    sx {
      width = 0.px
      margin = Margin(appTheme.spacing(24), Auto.auto)
    }

    +(props.variant ?: LoadingIndicatorVariant.Default).component
  }
}
