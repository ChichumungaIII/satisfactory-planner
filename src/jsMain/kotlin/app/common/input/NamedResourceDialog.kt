package app.common.input

import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import mui.material.TextField
import react.FC
import react.Props
import react.StateInstance
import react.dom.onChange
import react.useState

external interface NamedResourceDialogProps : Props {
  var openState: StateInstance<Boolean>

  var title: String
  var onCreate: (name: String) -> Unit
}

val NamedResourceDialog = FC<NamedResourceDialogProps>("NamedResourceDialog") { props ->
  var open by props.openState
  var name by useState("")

  Dialog {
    this.open = open

    DialogTitle { +props.title }

    DialogContent {
      TextField {
        autoFocus = true
        value = name
        onChange = { event ->
          name = event.target.asDynamic().value as String
        }
      }
    }

    DialogActions {
      Button {
        variant = ButtonVariant.contained
        disabled = name.isBlank()
        onClick = {
          props.onCreate(name)
          open = false
        }

        +"Create"
      }

      Button {
        variant = ButtonVariant.outlined
        onClick = { open = false }

        +"Cancel"
      }
    }
  }
}
