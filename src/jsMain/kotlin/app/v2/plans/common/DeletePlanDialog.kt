package app.v2.plans.common

import app.v2.plans.data.model.Plan
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import react.FC
import react.Props

external interface DeletePlanDialogProps : Props {
    var plan: Plan?

    var onCancel: () -> Unit
    var onDelete: () -> Unit
}

val DeletePlanDialog = FC<DeletePlanDialogProps>("DeletePlanDialog") { props ->
    Dialog {
        open = props.plan != null

        DialogTitle { +"Delete Plan" }
        DialogContent { +"Are you sure you want to delete ${props.plan?.title}?" }
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
