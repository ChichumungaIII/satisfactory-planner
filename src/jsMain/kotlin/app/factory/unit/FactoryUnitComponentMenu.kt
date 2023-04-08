package app.factory.unit

import app.common.layout.titlebar.DeleteConfirmationDialog
import app.common.layout.titlebar.EditTitleDialog
import app.factory.model.FactoryUnit
import app.util.PropsDelegate
import mui.material.Divider
import mui.material.Menu
import mui.material.MenuItem
import mui.material.PopoverOrigin
import react.FC
import react.Props
import react.useState
import web.dom.Element

external interface FactoryUnitComponentMenuProps : Props {
  var unit: FactoryUnit

  var parent: Element?
  var onClose: () -> Unit

  var expanded: Boolean
  var setExpanded: (Boolean) -> Unit

  var details: Boolean
  var setDetails: (Boolean) -> Unit

  var setTitle: ((String) -> Unit)?

  var onWrapInContainer: () -> Unit
  var onWrapInArray: () -> Unit

  var onDelete: () -> Unit
}

val FactoryUnitComponentMenu = FC<FactoryUnitComponentMenuProps>("FactoryUnitComponentMenu") { props ->
  var expanded by PropsDelegate(props.expanded) { props.setExpanded(it) }
  var details by PropsDelegate(props.details) { props.setDetails(it) }

  var editTitle by useState(false)

  var confirmDelete by useState(false)

  Menu {
    open = props.parent != null
    anchorEl = props.parent?.let { anchor -> { anchor } }
    anchorOrigin = object : PopoverOrigin {
      override var vertical = "top"
      override var horizontal = "left"
    }
    transformOrigin = object : PopoverOrigin {
      override var vertical = "top"
      override var horizontal = "right"
    }

    onClose = { props.onClose() }

    MenuItem {
      if (expanded) {
        +"Collapse"
        onClick = { expanded = false }
      } else {
        +"Expand"
        onClick = { expanded = true }
      }
    }

    MenuItem {
      if (details) {
        +"Hide details"
        onClick = { details = false }
      } else {
        +"Show details"
        onClick = { details = true }
      }
    }

    if (props.setTitle != null) {
      Divider {}

      MenuItem {
        +"Edit title"
        onClick = { editTitle = true }
      }
    }

    Divider {}

    MenuItem {
      +"Wrap in Container"
      onClick = { props.onWrapInContainer() }
    }

    MenuItem {
      +"Wrap in Array"
      onClick = { props.onWrapInArray() }
    }

    Divider {}

    MenuItem {
      +"Delete"
      onClick = { confirmDelete = true }
    }
  }

  if (props.setTitle != null) {
    EditTitleDialog {
      description = "Rename Factory Unit"

      active = editTitle
      setActive = { editTitle = it }

      this.title = props.unit.title
      this.setTitle = { props.setTitle?.invoke(it) }
    }
  }

  DeleteConfirmationDialog {
    description = "Delete Factory Unit"

    active = confirmDelete
    setActive = { confirmDelete = it }
    title = props.unit.title

    onDelete = {
      props.onDelete()
      confirmDelete = false
    }
  }
}
