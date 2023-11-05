package app.common.input.item

import app.common.input.autocomplete.createAppAutocomplete
import app.game.data.Item
import web.cssom.px

private val CATEGORY_ORDER = listOf(
  Item.Category.RESOURCES,
  Item.Category.PARTS,
  Item.Category.CONSUMABLES,
  Item.Category.EQUIPMENT,
  Item.Category.NATURE,
  Item.Category.UNCATEGORIZED,
)

val ItemAutocomplete = createAppAutocomplete(
  displayName = "ItemAutocomplete",
  label = "Item",
  grouping = { it.category.displayName },
  render = Item::displayName,
  ordering = compareBy<Item> { CATEGORY_ORDER.indexOf(it.category) }.thenBy { it.ordinal },
  width = 264.px,
)
