package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.routes.plan.partition.common.PartitionList
import app.util.PropsDelegate
import react.FC
import react.Props
import react.create
import util.math.q

private val NEW_INPUT = Plan.Input(
  item = null,
  quantity = 0.q,
)

external interface PartitionInputsProps : Props {
  var inputs: List<Plan.Input>
  var setInputs: (List<Plan.Input>) -> Unit
}

val PartitionInputs = FC<PartitionInputsProps>("PartitionInputs") { props ->
  var inputs by PropsDelegate(props.inputs, props.setInputs)

  PartitionList {
    onAdd = { inputs = inputs + NEW_INPUT }

    partitionListItems = inputs.mapIndexed { i, input ->
      PartitionInput.create {
        this.input = input
        setInput = { next -> inputs = inputs.toMutableList().also { it[i] = next }.toList() }
        deleteInput = { inputs = inputs.subList(0, i) + inputs.subList(i + 1, inputs.size) }
      }
    }
  }
}
