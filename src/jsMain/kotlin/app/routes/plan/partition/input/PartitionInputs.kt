package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.routes.plan.partition.common.PartitionList
import app.util.PropsDelegate
import mui.material.Typography
import mui.material.styles.Theme
import mui.material.styles.TypographyVariant
import mui.material.useMediaQuery
import mui.system.Breakpoint
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
  val large = useMediaQuery({ theme: Theme -> theme.breakpoints.up(Breakpoint.lg) })

  var inputs by PropsDelegate(props.inputs, props.setInputs)

  val items = inputs.mapIndexed { i, input ->
    PartitionInput.create {
      this.input = input
      setInput = { next -> inputs = inputs.toMutableList().also { it[i] = next }.toList() }
      deleteInput = { inputs = inputs.subList(0, i) + inputs.subList(i + 1, inputs.size) }
    }
  }

  PartitionList {
    onAdd = { inputs = inputs + NEW_INPUT }

    if (large) {
      val title = Typography.create {
        variant = TypographyVariant.subtitle2
        +"Inputs"
      }
      partitionListItems = listOf(title) + items
    } else {
      partitionListItems = items
    }
  }
}
