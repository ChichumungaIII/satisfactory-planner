package app.v2.factory

import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import mui.material.TextField
import react.FC
import react.Props
import react.dom.onChange
import react.useState

external interface EditFactoryNameDialogProps : Props {
    var displayName: String
    var onCancel: () -> Unit
    var onAccept: (String) -> Unit
}

val EditFactoryNameDialog = FC<EditFactoryNameDialogProps>("EditFactoryNameDialog") { props ->
    var displayName by useState(props.displayName)

    Dialog {
        open = true

        DialogTitle { +"Rename Factory" }

        DialogContent {
            TextField {
                autoFocus = true
                defaultValue = displayName
                onChange = { event -> displayName = event.target.asDynamic().value as String }
            }
        }

        DialogActions {
            DialogActions {
                Button {
                    variant = ButtonVariant.outlined
                    onClick = { props.onCancel() }
                    +"Cancel"
                }
                Button {
                    variant = ButtonVariant.contained
                    onClick = { props.onAccept(displayName) }
                    +"Accept"
                }
            }
        }
    }
}
