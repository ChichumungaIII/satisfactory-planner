package app.plan

import csstype.AlignItems
import csstype.Display
import csstype.Flex
import csstype.Padding
import csstype.number
import csstype.px
import mui.icons.material.Delete
import mui.icons.material.Edit
import mui.material.Box
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogContentText
import mui.material.DialogTitle
import mui.material.Fab
import mui.material.FabColor
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.TextField
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.dom.onChange
import react.useState

external interface PlanHeaderProps : Props {
    var title: String
    var setTitle: (String) -> Unit
    var onDelete: () -> Unit
}

val PlanHeader = FC<PlanHeaderProps>("PlanHeader") { props ->
    var edit by useState(false)
    var delete by useState(false)

    Box {
        sx {
            padding = Padding(12.px, 12.px)

            display = Display.flex
            alignItems = AlignItems.flexEnd
        }
        Typography {
            sx { padding = Padding(0.px, 12.px, 0.px, 0.px) }

            variant = TypographyVariant.h2
            +props.title
        }

        IconButton {
            color = IconButtonColor.default
            size = Size.medium
            Edit { fontSize = SvgIconSize.inherit }

            onClick = { edit = true }
        }

        Box { sx { flex = Flex(number(1.0), number(0.0)) } }

        Fab {
            color = FabColor.warning
            size = Size.medium
            Delete { fontSize = SvgIconSize.medium }

            onClick = { delete = true }
        }
    }

    Dialog {
        var newTitle = props.title

        open = edit

        DialogTitle { +"Edit Plan Title" }
        DialogContent {
            TextField {
                autoFocus = true
                defaultValue = newTitle
                onChange = { event ->
                    newTitle = event.target.asDynamic().value as String
                }
            }
        }
        DialogActions {
            Button {
                variant = ButtonVariant.outlined
                onClick = { edit = false }

                +"Cancel"
            }
            Button {
                variant = ButtonVariant.contained
                onClick = {
                    props.setTitle(newTitle)
                    edit = false
                }

                +"Save"
            }
        }
    }

    Dialog {
        open = delete

        DialogTitle { +"Delete Plan" }
        DialogContent {
            DialogContentText {
                +"Are you sure you want to delete \"${props.title}\"?"
            }
        }
        DialogActions {
            Button {
                variant = ButtonVariant.outlined
                onClick = { delete = false }

                +"Cancel"
            }
            Button {
                variant = ButtonVariant.contained
                onClick = {
                    props.onDelete()
                }

                +"Delete"
            }
        }
    }
}
