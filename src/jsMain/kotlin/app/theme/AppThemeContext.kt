package app.theme

import mui.material.CssBaseline
import mui.system.ThemeProvider
import react.FC
import react.PropsWithChildren
import react.StateInstance
import react.createContext
import react.useState

val AppThemeContext = createContext<StateInstance<AppTheme>>()

val AppThemeContextProvider = FC<PropsWithChildren> { props ->
  val appThemeState = useState(SatisfactoryAppTheme)
  val appTheme by appThemeState

  AppThemeContext.Provider {
    value = appThemeState

    ThemeProvider {
      theme = appTheme.delegate
      CssBaseline {}

      +props.children
    }
  }
}
