package app.common.layout.arrangedlist

import app.util.PropsDelegate
import mui.material.ListItem
import mui.system.sx
import react.ChildrenBuilder
import react.FC
import react.Props
import react.ReactNode
import web.cssom.Padding
import web.cssom.px

external interface ArrangedListProps<T> : Props {
  var items: List<T>
  var setItems: (List<T>) -> Unit

  var header: ReactNode
  var footer: ReactNode
}

fun <T> createArrangedList(
  displayName: String,
  render: ChildrenBuilder.(index: Int, item: T, props: ArrangedListProps<T>) -> Unit,
) = FC<ArrangedListProps<T>>(displayName) { props ->
  var items by PropsDelegate(props.items, props.setItems)

  fun swapAt(index: Int) {
    items = items.subList(0, index) +
        items[index + 1] +
        items[index] +
        items.subList(index + 2, items.size)
  }

  mui.material.List {
    sx { padding = Padding(4.px, 4.px) }

    ListItem { +props.header }

    props.items.forEachIndexed { i, item ->
      ArrangedListItem {
        onDelete = { items = items.subList(0, i) + items.subList(i + 1, items.size) }

        canMoveUp = i > 0
        onMoveUp = { swapAt(i - 1) }

        canMoveDown = i + 1 < items.size
        onMoveDown = { swapAt(i) }

        render(i, item, props)
      }
    }

    ListItem { +props.footer }
  }
}
