package app.common.layout.titlebar

import app.util.PropsDelegate
import csstype.ClassName
import mui.icons.material.Delete
import mui.icons.material.Edit
import mui.material.Box
import mui.material.Fab
import mui.material.FabColor
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.useState

external interface TitleBarProps : Props {
    var editDescription: String
    var deleteDescription: String

    var title: String
    var setTitle: (String) -> Unit
    var onDelete: () -> Unit
}

val TitleBar = FC<TitleBarProps>("TitleBar") { props ->
    var title by PropsDelegate(props.title) { props.setTitle(it) }
    var edit by useState(false)
    var delete by useState(false)

    Box {
        className = ClassName("title-bar")

        Typography {
            className = ClassName("title-bar__title")

            variant = TypographyVariant.h2
            +title
        }

        IconButton {
            color = IconButtonColor.default
            size = Size.medium
            Edit { fontSize = SvgIconSize.inherit }

            onClick = { edit = true }
        }

        Box {
            className = ClassName("title-bar__spacer")
        }

        Fab {
            color = FabColor.warning
            size = Size.medium
            Delete { fontSize = SvgIconSize.medium }

            onClick = { delete = true }
        }
    }

    EditTitleDialog {
        description = props.editDescription
        active = edit
        setActive = { edit = it }
        this.title = title
        setTitle = { title = it }
    }

    DeleteConfirmationDialog {
        description = props.deleteDescription
        active = delete
        setActive = { delete = it }
        this.title = title
        onDelete = { props.onDelete() }
    }
}
