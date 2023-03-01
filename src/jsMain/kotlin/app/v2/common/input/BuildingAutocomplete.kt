package app.v2.common.input

import app.data.building.*
import app.util.PropsDelegate
import csstype.ClassName
import mui.material.*
import react.*

external interface BuildingAutocompleteProps : Props {
    var model: Building?
    var setModel: (Building?) -> Unit
}

val BUILDINGS: List<Building> = (Manufacturer.values() + Generator.values() + Extractor.values()).toList()

val BuildingAutocomplete = FC<BuildingAutocompleteProps>("BuildingAutocomplete") { props ->
    var model by PropsDelegate(props.model, props.setModel)

    @Suppress("UPPER_BOUND_VIOLATED") Autocomplete<AutocompleteProps<Building>> {
        className = ClassName("building-autocomplete")

        renderInput = { params ->
            TextField.create {
                +params
                label = ReactNode("Building")
            }
        }
        size = "small"

        options = BUILDINGS.toTypedArray()
        getOptionLabel = { it.displayName }

        groupBy = { it::class.simpleName ?: throw Error("Building [$it] does not have a class.") }

        value = model
        onChange = { _, next: Building?, _, _ -> model = next }

        autoHighlight = true
        filterSelectedOptions = true
    }
}
