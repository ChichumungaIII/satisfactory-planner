package app.common.input

import app.game.data.Item
import app.util.PropsDelegate
import js.core.jso
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.ListProps
import mui.material.ListSubheader
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import web.cssom.Padding
import web.cssom.px

private val CATEGORY_ORDER = listOf(
  Item.Category.RESOURCES,
  Item.Category.PARTS,
  Item.Category.EQUIPMENT,
  Item.Category.NATURE,
)
private val ITEM_COMPARATOR =
  compareBy<Item> {
    CATEGORY_ORDER.indexOf(it.category)
  }.thenBy { it.ordinal }

external interface ItemAutocompleteProps : Props {
  var model: Item
  var setModel: (Item) -> Unit

  var options: List<Item>
}

val ItemAutocomplete = FC<ItemAutocompleteProps>("ItemAutocomplete") { props ->
  var model by PropsDelegate(props.model, props.setModel)

  @Suppress("UPPER_BOUND_VIOLATED")
  Autocomplete<AutocompleteProps<Item>> {
    sx { width = 256.px }

    renderInput = { params ->
      TextField.create {
        +params
        label = ReactNode("Item")
      }
    }
    ListboxComponent = mui.material.List
    ListboxProps = jso<ListProps> {
      sx { padding = Padding(0.px, 0.px) }
    }

    groupBy = { it.category.displayName }
    renderGroup = { params ->
      mui.material.List.create {
        subheader = ListSubheader.create { +params.group }
        +params.children
      }
    }

    options = props.options.sortedWith(ITEM_COMPARATOR).toTypedArray()
    getOptionLabel = { it.displayName }

    value = model
    onChange = { _, next: Item?, _, _ -> next?.also { model = it } }

    autoComplete = true
    autoHighlight = true
    disableClearable = true
  }
}
