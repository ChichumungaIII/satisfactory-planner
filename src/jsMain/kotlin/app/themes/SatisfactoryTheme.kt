package app.themes

import js.core.jso
import mui.material.AppBarPosition
import mui.material.PaletteMode
import mui.material.SvgIconColor
import mui.material.styles.createTheme
import web.cssom.None
import web.cssom.px

val SatisfactoryTheme = createTheme(jso {
  palette = jso {
    mode = PaletteMode.dark

    primary = jso { main = "#f2c800" }
    secondary = jso { main = "#26d3ec" }

    background = jso {
      default = "#141518"
      paper = "#202125"
    }
  }

  spacing = 4

  typography = jso {
    h1 = jso {
      fontWeight = 500
      fontSize = 36.px
      lineHeight = 40.px
    }
  }

  zIndex = jso {
    appBar = 1200
    drawer = 1100
  }

  components = jso {
    MuiAppBar = jso {
      defaultProps = jso {
        position = AppBarPosition.sticky
      }
      styleOverrides = jso {
        root = jso {
          backgroundImage = None.none
        }
      }
    }

    MuiSvgIcon = jso {
      defaultProps = jso {
        color = SvgIconColor.primary
      }
    }
  }
})
