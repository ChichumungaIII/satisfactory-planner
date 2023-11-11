package app.common.input.groupchecklist

import app.common.mui.MuiList
import app.util.PropsDelegate
import react.ChildrenBuilder
import react.FC
import react.Props

external interface GroupedChecklistProps<T> : Props {
  var elements: List<T>
  var getGroup: (T) -> String
  var getDisplayName: (T) -> String

  var selected: Set<T>
  var setSelected: (Set<T>) -> Unit
}

fun <T> GroupedChecklist() = GroupedChecklist.unsafeCast<FC<GroupedChecklistProps<T>>>()

private val GroupedChecklist = FC<GroupedChecklistProps<Any>>("GroupedChecklist") { props ->
  var selected by PropsDelegate(props.selected, props.setSelected)

  MuiList.invoke() {
    props.elements.groupBy(props.getGroup).entries.forEach { (title, group) ->
      ChecklistGroup {
        this.title = title
        this.elements = group
        this.getDisplayName = props.getDisplayName

        this.selected = selected
        this.addAll = { items -> selected = selected + items }
        this.removeAll = { items -> selected = selected - items }
      }
    }
  }
}
