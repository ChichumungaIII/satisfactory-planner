package app.v2.common.input

import app.data.Item
import app.util.PropsDelegate
import csstype.ClassName
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.TextField
import react.FC
import react.Props
import react.ReactNode
import react.create

external interface ItemAutocompleteProps : Props {
    var model: Item?
    var setModel: (Item?) -> Unit
}

val ItemAutocomplete = FC<ItemAutocompleteProps>("ItemAutocomplete") { props ->
    var model by PropsDelegate(props.model, props.setModel)

    @Suppress("UPPER_BOUND_VIOLATED") Autocomplete<AutocompleteProps<Item>> {
        className = ClassName("item-autocomplete")

        renderInput = { params ->
            TextField.create {
                +params
                label = ReactNode("Item")
            }
        }
        size = "small"

        options = Item.values()
        getOptionLabel = { it.displayName }

        value = model
        onChange = { _, next: Item?, _, _ -> model = next }

        autoComplete = true
        autoHighlight = true
        clearOnEscape = true
    }
}
