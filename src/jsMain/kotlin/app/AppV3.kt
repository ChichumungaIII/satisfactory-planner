package app

import app.api.optimize.v1.OptimizeServiceJs
import app.api.save.v1.SaveServiceJs
import app.common.layout.AppFrameDrawerOpenContextProvider
import app.data.save.SaveCache
import app.data.save.SaveLoader
import app.data.save.SavesListService
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
    // App State
    AppFrameDrawerOpenContextProvider,
    // App Data
    SaveCache.Provider,
    SavesListService.Provider,
    SaveLoader.Provider,
  ).nest(Outlet.create {})
}

private fun List<FC<PropsWithChildren>>.nest(element: ReactElement<*>): ReactElement<*> =
  foldRight(element) { parent, child -> parent.create { +child } }
