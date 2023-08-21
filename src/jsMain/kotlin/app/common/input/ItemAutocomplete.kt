package app.common.input

import app.game.data.Item
import app.util.PropsDelegate
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.TextField
import react.FC
import react.Props
import react.ReactNode
import react.create

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
    renderInput = { params ->
      TextField.create {
        +params
        label = ReactNode("Item")
      }
    }

    options = props.options.sortedWith(ITEM_COMPARATOR).toTypedArray()
    getOptionLabel = { it.displayName }
    groupBy = { it.category.displayName }

    value = model
    onChange = { _, next: Item?, _, _ -> next?.also { model = it } }

    autoComplete = true
    autoHighlight = true
    clearOnEscape = true
  }
}
