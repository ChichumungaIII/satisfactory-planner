package app

import app.themes.ThemeModule
import react.FC
import react.Props
import react.router.Outlet

val App = FC<Props>("App") {
    ThemeModule {
        Outlet {}
    }
}
