package app.v2.common.input

import app.data.building.Building
import app.data.building.Extractor
import app.data.building.Generator
import app.data.building.Manufacturer
import app.util.PropsDelegate
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.TextField
import react.FC
import react.Props
import react.ReactNode
import react.create
import web.cssom.ClassName

external interface BuildingAutocompleteProps : Props {
  var model: Building?
  var setModel: (Building?) -> Unit
}

val BUILDINGS: List<Building> =
  (Manufacturer.entries.toTypedArray() + Generator.entries.toTypedArray() + Extractor.entries.toTypedArray()).toList()

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

    autoComplete = true
    autoHighlight = true
    clearOnEscape = true
  }
}
