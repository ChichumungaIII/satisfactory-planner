package app.themes

import js.core.JsoDsl
import js.core.jso
import mui.material.styles.ThemeOptions
import mui.material.styles.createTheme

enum class Themes(private val block: @JsoDsl ThemeOptions.() -> Unit) {
    DEFAULT({ typography = commonTypography }),
    SATISFACTORY({
        palette = jso {
            mode = mui.material.PaletteMode.dark
            primary = jso { main = "#e67d22" }
        }
        typography = commonTypography
    });

    val mui by lazy { createTheme(jso(block)) }
}

private val commonTypography = jso<dynamic> {
    h1 = jso {
        fontSize = 36
        fontWeight = 400
    }
    h2 = jso {
        fontSize = 30
    }
}
