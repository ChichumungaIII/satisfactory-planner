package app.themes

import kotlinx.js.jso
import mui.material.styles.createTheme

val DefaultTheme = createTheme(
    jso {
        typography = jso {
            h1 = jso {
                fontSize = 36
                fontWeight = 400
                margin = "8px 16px"
            }
            h2 = jso {
                fontSize = 24
            }
        }
    })
