package app

import app.common.layout.appframe.AppFrameState
import app.common.layout.navigationlist.NavigationListState
import app.data.save.SaveCollectionLoader
import app.data.save.SaveLoaderProvider
import app.redux.AppStoreProvider
import app.routes.save.SavePageState
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
    /* Services */
    app.api.optimize.v1.OptimizeServiceJs.Provider,
    /* Global theme */
    AppThemeContextProvider,
    /* App Data */
    AppStoreProvider,
    // Save
    SaveCollectionLoader.Provider,
    SaveLoaderProvider,
    /* App State */
    AppFrameState.Provider,
    NavigationListState.Provider,
    SavePageState.Manager.Provider,
  ).nest(Outlet.create {})
}

private fun List<FC<PropsWithChildren>>.nest(element: ReactElement<*>): ReactElement<*> =
  foldRight(element) { parent, child -> parent.create { +child } }
