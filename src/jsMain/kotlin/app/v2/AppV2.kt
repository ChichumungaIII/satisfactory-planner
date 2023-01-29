package app.v2

import app.themes.ThemeModule
import app.v2.data.FactoryServiceContextProvider
import app.v2.data.FactoryStoreContextProvider
import app.v2.frame.FrameComponent
import app.v2.frame.title.TitleContextComponent
import react.FC
import react.Props

external interface AppV2Props : Props

val AppV2 = FC<AppV2Props>("AppV2") { props ->
    // Global theme
    ThemeModule {
        // Global data
        FactoryServiceContextProvider {
            FactoryStoreContextProvider {
                // Frame data
                TitleContextComponent {
                    FrameComponent {}
                }
            }
        }
    }
}
