package app.v2.common.input

import app.data.building.*
import app.util.PropsDelegate
import mui.material.*
import react.*

external interface BuildingAutocompleteProps : Props {
    var model: Building?
    var setModel: (Building?) -> Unit
}

val BuildingAutocomplete = FC<BuildingAutocompleteProps>("BuildingAutocomplete") { props ->
    var model by PropsDelegate(props.model, props.setModel)

    @Suppress("UPPER_BOUND_VIOLATED") Autocomplete<AutocompleteProps<BuildingAutocompleteOption>> {
        renderInput = { params ->
            TextField.create {
                +params
                label = ReactNode("Building")
            }
        }

        options = (Manufacturer.values() + Generator.values() + Extractor.values())
            .map { BuildingAutocompleteOption(it) }
            .toTypedArray()

        value = model?.let { BuildingAutocompleteOption(it) }
        isOptionEqualToValue = { x, y -> x.data == y.data }
        onChange = { _, next: BuildingAutocompleteOption, _, _ -> model = next.data }
    }
}

private external interface BuildingAutocompleteOption {
    val label: String
    val data: Building
}

private fun BuildingAutocompleteOption(building: Building) = object : BuildingAutocompleteOption {
    override val label = building.displayName
    override val data = building
}
