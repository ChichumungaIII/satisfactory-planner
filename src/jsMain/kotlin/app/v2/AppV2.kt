package app.v2

import app.themes.ThemeModule
import app.v2.data.FactoryStoreContextProvider
import app.v2.data.service.FactoryServiceContextProvider
import app.v2.factories.FactoriesContextProvider
import app.v2.factory.FactoryContextProvider
import app.v2.frame.FrameComponent
import app.v2.frame.FrameMenuOptionsContextComponent
import app.v2.frame.title.TitleContextComponent
import kotlinx.coroutines.MainScope
import react.FC
import react.Props
import react.PropsWithChildren
import react.ReactElement
import react.create

external interface AppV2Props : Props

val AppScope = MainScope()

val AppV2 = FC<AppV2Props>("AppV2") { _ ->
    +listOf(
        // Global theme
        ThemeModule,
        // Global data
        FactoryServiceContextProvider,
        FactoryStoreContextProvider,
        // Frame data
        TitleContextComponent,
        FrameMenuOptionsContextComponent,
        // Specific data
        FactoriesContextProvider,
        FactoryContextProvider,
    ).nest(FrameComponent.create {})
}

private fun List<FC<PropsWithChildren>>.nest(element: ReactElement<*>): ReactElement<*> =
    foldRight(element) { parent, child -> parent.create { +child } }
