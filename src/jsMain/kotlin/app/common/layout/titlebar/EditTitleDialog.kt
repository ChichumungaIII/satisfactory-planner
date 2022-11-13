package app.common.layout.titlebar

import app.util.PropsDelegate
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

external interface EditTitleDialogProps : Props {
    var description: String

    var active: Boolean
    var setActive: (Boolean) -> Unit

    var title: String
    var setTitle: (String) -> Unit
}

val EditTitleDialog = FC<EditTitleDialogProps>("EditTitleDialog") { props ->
    var active by PropsDelegate(props.active) { props.setActive(it) }
    var title by PropsDelegate(props.title) { props.setTitle(it) }
    var edit by useState(title)

    Dialog {
        open = props.active

        DialogTitle { +props.description }
        DialogContent {
            TextField {
                autoFocus = true
                defaultValue = edit
                onChange = { edit = it.target.asDynamic().value as String }
            }
        }
        DialogActions {
            Button {
                variant = ButtonVariant.outlined
                onClick = { active = false }
                +"Cancel"
            }
            Button {
                variant = ButtonVariant.contained
                onClick = {
                    title = edit
                    active = false
                }
                +"Save"
            }
        }
    }
}
