package app.common.input.groupchecklist

import app.common.mui.MuiList
import mui.icons.material.ExpandLess
import mui.icons.material.ExpandMore
import mui.material.Checkbox
import mui.material.Collapse
import mui.material.IconButton
import mui.material.IconButtonEdge
import mui.material.ListItem
import mui.material.ListItemButton
import mui.material.ListItemIcon
import mui.material.ListItemText
import mui.material.SwitchBaseEdge
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.useState
import web.cssom.px

external interface ChecklistGroupProps<T> : Props {
  var title: String
  var elements: List<T>
  var getDisplayName: (T) -> String

  var selected: Set<T>
  var addAll: (Set<T>) -> Unit
  var removeAll: (Set<T>) -> Unit
}

val ChecklistGroup = FC<ChecklistGroupProps<Any>>("ChecklistGroup") { props ->
  var any = props.elements.any { props.selected.contains(it) }
  var all = props.selected.containsAll(props.elements)

  var expanded by useState(any && !all)

  /** Group Row **/
  ListItem {
    /** Check / Uncheck **/
    ListItemButton {
      ListItemIcon {
        Checkbox {
          edge = SwitchBaseEdge.start
          tabIndex = -1
          disableRipple = true
          checked = all
          indeterminate = any && !all
        }
      }

      ListItemText { primary = ReactNode(props.title) }
      onClick = { (if (all) props.removeAll else props.addAll)(props.elements.toSet()) }
    }

    /** Expand / Collapse**/
    secondaryAction = IconButton.create {
      onClick = { expanded = !expanded }
      edge = IconButtonEdge.end
      (ExpandLess.takeIf { expanded } ?: ExpandMore) { }
    }
  }

  /** Individual Elements **/
  Collapse {
    sx { paddingLeft = 24.px }
    `in` = expanded

    MuiList {
      props.elements.associateWith { props.selected.contains(it) }.forEach { (element, selected) ->
        ListItemButton {
          ListItemIcon {
            Checkbox {
              edge = SwitchBaseEdge.start
              tabIndex = -1
              disableRipple = true
              checked = selected
            }
          }

          ListItemText { primary = ReactNode(props.getDisplayName(element)) }
          onClick = { (if (selected) props.removeAll else props.addAll)(setOf(element)) }
        }
      }
    }
  }
}
