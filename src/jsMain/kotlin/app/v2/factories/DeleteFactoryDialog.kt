package app.v2.factories

import app.v2.data.factory.Factory
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import react.FC
import react.Props

external interface DeleteFactoryDialogProps : Props {
  var factory: Factory

  var onCancel: () -> Unit
  var onDelete: () -> Unit
}

val DeleteFactoryDialog = FC<DeleteFactoryDialogProps>("DeleteFactoryDialog") { props ->
  Dialog {
    open = true

    DialogTitle { +"Delete Factory" }

    DialogContent {
      +"Are you sure you want to delete \"${props.factory.displayName}\"?"
    }

    DialogActions {
      Button {
        variant = ButtonVariant.outlined
        onClick = { props.onCancel() }
        +"Cancel"
      }
      Button {
        variant = ButtonVariant.contained
        onClick = { props.onDelete() }
        +"Delete"
      }
    }
  }
}
