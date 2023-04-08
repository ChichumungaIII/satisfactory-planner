package app

import app.themes.ThemeModule
import kotlinx.coroutines.MainScope
import react.FC
import react.Props
import react.router.Outlet

val appScope = MainScope()

val App = FC<Props>("App") {
  ThemeModule {
    Outlet {}
  }
}
