package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.common.input.RationalInput
import app.util.PropsDelegate
import mui.material.Box
import react.FC
import react.Props

external interface PartitionInputProps : Props {
  var input: Plan.Input
  var setInput: (Plan.Input) -> Unit
}

val PartitionInput = FC<PartitionInputProps>("PartitionInput") { props ->
  var input by PropsDelegate(props.input, props.setInput)

  Box {
    RationalInput {
      value = input.quantity
      setValue = { next -> input = input.copy(quantity = next) }
    }
  }
}
