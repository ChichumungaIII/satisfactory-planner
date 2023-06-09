package app.sample

import app.themes.ThemeContext
import js.core.jso
import mui.material.Box
import mui.material.Button
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import mui.material.TextField
import mui.material.styles.createTheme
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import react.useContext
import react.useState

external interface SampleSettingsDialogProps : Props {
  var open: Boolean
  var setOpen: (Boolean) -> Unit
}

val SampleSettingsDialog = FC<SampleSettingsDialogProps>("SampleSettingsDialog") { props ->
  var theme by useContext(ThemeContext)

  var spacing by useState("4")

  fun syncTheme() {
    val overrides = createTheme(jso {
      this.spacing = spacing.toInt()
    })

    theme = createTheme(jso {}, theme, jso {
      this.spacing = overrides.spacing
    })
  }

  Dialog {
    open = props.open

    DialogTitle { +"Sample Theme Settings" }

    DialogContent {
      sx { padding = theme.spacing(0) }
      Box {
        sx { padding = theme.spacing(4) }

        TextField {
          label = ReactNode("Spacing")
          value = spacing
          onChange = { event ->
            spacing = event.target.asDynamic().value as String
          }
          onBlur = { syncTheme() }
        }
      }
    }

    DialogActions {
      Button {
        onClick = { props.setOpen(false) }
        +"Close"
      }
    }
  }
}
