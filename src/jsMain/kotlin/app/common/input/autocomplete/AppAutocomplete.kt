package app.common.input.autocomplete

import app.util.PropsDelegate
import js.core.jso
import mui.material.Autocomplete
import mui.material.AutocompleteProps
import mui.material.ListProps
import mui.material.ListSubheader
import mui.material.TextField
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import web.cssom.Padding
import web.cssom.Width
import web.cssom.px

external interface AppAutocompleteProps<T> : Props {
  var model: T?
  var setModel: (T?) -> Unit

  var options: List<T>
}

fun <T> createAppAutocomplete(
  displayName: String,
  label: String,
  grouping: (T) -> String,
  render: (T) -> String,
  ordering: Comparator<T>,
  width: Width,
) = FC<AppAutocompleteProps<T>>(displayName) { props ->
  var model by PropsDelegate(props.model, props.setModel)

  @Suppress("UPPER_BOUND_VIOLATED")
  Autocomplete<AutocompleteProps<T>> {
    sx { this.width = width }

    renderInput = { params ->
      TextField.create {
        +params
        this.label = ReactNode(label)
      }
    }
    ListboxComponent = mui.material.List
    ListboxProps = jso<ListProps> { sx { padding = Padding(0.px, 0.px) } }

    groupBy = grouping
    renderGroup = { params ->
      mui.material.List.create {
        subheader = ListSubheader.create { +params.group }
        +params.children
      }
    }

    options = props.options.sortedWith(ordering).toTypedArray()
    getOptionLabel = render

    value = model
    onChange = { _, next: T?, _, _ -> model = next }

    autoComplete = true
    autoHighlight = true
    clearOnEscape = true
  }
}
