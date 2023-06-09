package app.themes

import csstype.px
import js.core.jso
import mui.material.PaletteMode
import mui.material.styles.Theme
import mui.material.styles.createTheme

val SampleTheme = createTheme(jso {
  palette = jso {
    // Values default to MUI's dark mode.
    mode = PaletteMode.dark

    // Shared custom colors. Includes black and white by default.
    common = jso {}

    /* App colors. {
     *   var main
     *   var light
     *   var dark
     *   var contrastText
     * }
     */
    // primary = jso {}
    // secondary = jso {}
    // error = jso {}
    // warning = jso {}
    // info = jso {}
    // success = jso {}

    // Defined variants of grey; e.g. 50, 100, 200, ..., A200, A400, A700
    grey = jso {}

    // Default text colors.
    text = jso {}

    background = jso {
      paper = "#202125"
      default = "#141518"
    }
  }

  spacing = 4

  typography = jso {
    /* Typography variant defaults. {
     *   var fontWeight
     *   var fontSize
     *   var lineHeight
     *   var letterSpacing
     *   var textTransform
     * }
     */
    h1 = jso {
      fontWeight = 500
      fontSize = 36.px
      lineHeight = 40.px
    }
    h2 = jso {}
    h3 = jso {}
    h4 = jso {}
    h5 = jso {}
    h6 = jso {}
    subtitle1 = jso {}
    subtitle2 = jso {}
    body1 = jso {}
    body2 = jso {}
    button = jso {}
    caption = jso {}
    overline = jso {}
  }

  zIndex = jso {
    appBar = 1200
    drawer = 1100
  }
})
