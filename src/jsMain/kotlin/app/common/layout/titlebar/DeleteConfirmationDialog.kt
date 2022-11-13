package app.common.layout.titlebar

import app.util.PropsDelegate
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import react.FC
import react.Props

external interface DeleteConfirmationDialogProps : Props {
    var description: String

    var active: Boolean
    var setActive: (Boolean) -> Unit
    var title: String

    var onDelete: () -> Unit
}

val DeleteConfirmationDialog = FC<DeleteConfirmationDialogProps>("DeleteConfirmationDialog") { props ->
    var active by PropsDelegate(props.active) { props.setActive(it) }

    Dialog {
        open = active

        DialogTitle { +props.description }
        DialogContent { +"Are you sure you want to delete \"${props.title}\"?" }
        DialogActions {
            Button {
                variant = ButtonVariant.outlined
                onClick = { active = false }
                +"Cancel"
            }
            Button {
                variant = ButtonVariant.contained
                onClick = {
                    props.onDelete()
                    active = false
                }
                +"Delete"
            }
        }
    }
}
