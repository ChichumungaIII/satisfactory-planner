package app.common

import app.data.u5.U5Item
import app.util.PropsDelegate
import csstype.Margin
import csstype.px
import kotlinx.js.jso
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create

external interface ItemAutocompleteProps : Props {
    var item: U5Item
    var setItem: (U5Item) -> Unit
    var restricted: Collection<U5Item>?
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

        val available = U5Item.values().filterNot { props.restricted?.contains(it) ?: false }
        options = (listOf(item) + available).distinct().map { ItemAutocompleteOption(it) }.toTypedArray()

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
    var data: U5Item
}

private fun ItemAutocompleteOption(item: U5Item): ItemAutocompleteOption = jso {
    label = item.displayName
    data = item
}
