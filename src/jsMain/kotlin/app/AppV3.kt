package app

import app.api.optimize.v1.OptimizeServiceJs
import app.api.save.v1.SaveServiceJs
import app.theme.AppThemeContextProvider
import react.FC
import react.Props
import react.PropsWithChildren
import react.ReactElement
import react.create
import react.router.Outlet

external interface AppV3Props : Props

val AppV3 = FC<AppV3Props>("AppV3") {
  +listOf(
    // Services
    OptimizeServiceJs.Provider,
    SaveServiceJs.Provider,
    // Global theme
    AppThemeContextProvider,
  ).nest(Outlet.create {})
}

private fun List<FC<PropsWithChildren>>.nest(element: ReactElement<*>): ReactElement<*> =
  foldRight(element) { parent, child -> parent.create { +child } }
