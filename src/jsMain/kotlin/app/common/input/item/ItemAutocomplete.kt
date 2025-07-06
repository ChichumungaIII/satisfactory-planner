package app.common.input.item

import app.common.input.autocomplete.createAppAutocomplete
import app.game.data.Item
import web.cssom.px

val ItemAutocomplete = createAppAutocomplete(
  displayName = "ItemAutocomplete",
  label = "Item",
  grouping = { it.category.displayName },
  render = Item::displayName,
  ordering = compareBy<Item> { it.category }.thenBy { it.ordinal },
  width = 264.px,
)
