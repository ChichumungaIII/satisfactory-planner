package app.theme

import mui.material.styles.Mixins
import mui.material.styles.Palette
import mui.material.styles.Theme
import mui.material.styles.Transitions
import mui.material.styles.ZIndex
import mui.system.Breakpoints
import mui.system.Direction
import mui.system.Shape
import mui.system.Spacing
import mui.system.SxProps
import web.cssom.Length

class AppTheme(
  delegate: Theme,
  val constants: AppThemeConstants,
) : ForwardingTheme(delegate)

interface AppThemeConstants {
  val navigationDrawerWidth: Length
  val toolbarHeight: Length
}

open class ForwardingTheme(
  val delegate: Theme,
) : Theme {
  override var breakpoints: Breakpoints
    get() = delegate.breakpoints
    set(value) {
      delegate.breakpoints = value
    }

  override var components: dynamic
    get() = delegate.components
    set(value) {
      delegate.components = value
    }

  override var direction: Direction
    get() = delegate.direction
    set(value) {
      delegate.direction = value
    }

  override var mixins: Mixins
    get() = delegate.mixins
    set(value) {
      delegate.mixins = value
    }

  override var palette: Palette
    get() = delegate.palette
    set(value) {
      delegate.palette = value
    }

  override var shadows: dynamic
    get() = delegate.shadows
    set(value) {
      delegate.shadows = value
    }

  override var shape: Shape
    get() = delegate.shape
    set(value) {
      delegate.shape = value
    }

  override var spacing: Spacing
    get() = delegate.spacing
    set(value) {
      delegate.spacing = value
    }

  override var transitions: Transitions
    get() = delegate.transitions
    set(value) {
      delegate.transitions = value
    }

  override var typography: dynamic
    get() = delegate.typography
    set(value) {
      delegate.typography = value
    }

  override var unstable_strictMode: Boolean?
    get() = delegate.unstable_strictMode
    set(value) {
      delegate.unstable_strictMode = value
    }

  override var unstable_sx: (props: SxProps<Theme>) -> dynamic
    get() = delegate.unstable_sx
    set(value) {
      delegate.unstable_sx = value
    }

  override var unstable_sxConfig: dynamic
    get() = delegate.unstable_sxConfig
    set(value) {
      delegate.unstable_sxConfig = value
    }

  override var zIndex: ZIndex
    get() = delegate.zIndex
    set(value) {
      delegate.zIndex = value
    }
}

