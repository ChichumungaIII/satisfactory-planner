package app.common

import app.data.Item
import app.util.PropsDelegate
import csstype.Margin
import csstype.px
import js.core.jso
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create

external interface ItemAutocompleteProps : Props {
    var item: Item
    var setItem: (Item) -> Unit
}

val ItemAutocomplete = FC<ItemAutocompleteProps>("ItemAutocomplete") { props ->
    var item by PropsDelegate(props.item) { next -> props.setItem(next) }

    @Suppress("UPPER_BOUND_VIOLATED") Autocomplete<AutocompleteProps<ItemAutocompleteOption>> {
        sx {
            width = 256.px
            margin = Margin(0.px, 6.px)
        }

        disablePortal = true
        renderInput = { params ->
            TextField.create {
                +params
                label = ReactNode("Item")
            }
        }

        options = (listOf(item) + Item.values()).distinct().map { ItemAutocompleteOption(it) }.toTypedArray()

        autoComplete = true
        autoHighlight = true
        autoSelect = true
        disableClearable = true
        isOptionEqualToValue = { left, right -> left.data == right.data }

        value = ItemAutocompleteOption(item)
        onChange = { _, next: ItemAutocompleteOption, _, _ ->
            item = next.data
        }
    }
}

private external interface ItemAutocompleteOption {
    var label: String
    var data: Item
}

private fun ItemAutocompleteOption(item: Item): ItemAutocompleteOption = jso {
    label = item.displayName
    data = item
}
