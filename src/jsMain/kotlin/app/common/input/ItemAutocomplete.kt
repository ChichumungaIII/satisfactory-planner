package app.common.input

import app.common.input.autocomplete.createAppAutocomplete
import app.game.data.Item
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

val ItemAutocomplete = createAppAutocomplete(
  displayName = "ItemAutocomplete",
  label = "Item",
  grouping = { it.category.displayName },
  render = Item::displayName,
  ordering = ITEM_COMPARATOR,
  width = 238.px,
)
