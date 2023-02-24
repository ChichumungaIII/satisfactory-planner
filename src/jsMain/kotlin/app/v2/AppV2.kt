package app.v2

import app.themes.ThemeModule
import app.v2.data.service.FactoryServiceContextProvider
import app.v2.data.FactoryStoreContextProvider
import app.v2.factories.FactoriesContextProvider
import app.v2.factory.FactoryContextProvider
import app.v2.frame.FrameComponent
import app.v2.frame.title.TitleContextComponent
import kotlinx.coroutines.MainScope
import react.FC
import react.Props

external interface AppV2Props : Props

val AppScope = MainScope()

val AppV2 = FC<AppV2Props>("AppV2") { _ ->
    // Global theme
    ThemeModule {
        // Global data
        FactoryServiceContextProvider {
            FactoryStoreContextProvider {
                // Frame data
                TitleContextComponent {
                    // Specific data
                    FactoriesContextProvider {
                        FactoryContextProvider {
                            // App
                            FrameComponent {}
                        }
                    }
                }
            }
        }
    }
}
