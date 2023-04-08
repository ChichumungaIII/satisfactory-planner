package app.themes

import js.core.jso
import mui.material.styles.createTheme

val DefaultTheme = createTheme(
  jso {
    typography = jso {
      h1 = jso {
        fontSize = 36
        fontWeight = 400
      }
      h2 = jso {
        fontSize = 30
      }
    }
  })
