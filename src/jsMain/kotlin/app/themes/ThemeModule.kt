package app.themes

import mui.material.CssBaseline
import mui.material.styles.Theme
import mui.material.styles.ThemeProvider
import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

typealias ThemeState = StateInstance<Themes>

val ThemeContext = createContext<ThemeState>()

val ThemeModule = FC<PropsWithChildren>("ThemeModule") { props ->
    val state = useState(Themes.DEFAULT)
    val (theme) = state

    ThemeContext(state) {
        ThemeProvider {
            this.theme = theme.mui

            CssBaseline()
            +props.children
        }
    }
}
