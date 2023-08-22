package app

import app.api.optimize.v1.OptimizeServiceJs
import app.api.plan.v1.PlanServiceJs
import app.api.save.v1.SaveServiceJs
import app.common.layout.appframe.AppFrameState
import app.common.layout.navigationlist.NavigationListContextProvider
import app.data.plan.PlanCacheProvider
import app.data.plan.PlanCollectionCacheProvider
import app.data.plan.PlanCollectionLoader
import app.data.plan.PlanLoaderProvider
import app.data.save.SaveCacheProvider
import app.data.save.SaveCollectionLoader
import app.data.save.SaveLoaderProvider
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
    PlanServiceJs.Provider,
    // Global theme
    AppThemeContextProvider,
    // App Data
    SaveCacheProvider,
    SaveCollectionLoader.Provider,
    SaveLoaderProvider,
    PlanCacheProvider,
    PlanCollectionCacheProvider,
    PlanCollectionLoader.Provider,
    PlanLoaderProvider,
    // App State
    AppFrameState.Provider,
    NavigationListContextProvider,
  ).nest(Outlet.create {})
}

private fun List<FC<PropsWithChildren>>.nest(element: ReactElement<*>): ReactElement<*> =
  foldRight(element) { parent, child -> parent.create { +child } }
