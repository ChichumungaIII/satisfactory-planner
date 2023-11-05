package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.routes.plan.usePartition
import app.util.PropsDelegate
import mui.material.Button
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import util.collections.swap
import util.math.q
import web.cssom.px

val PartitionInputs = FC<Props>("PartitionInputs") {
  var partition by usePartition()
  var inputs by PropsDelegate(partition.inputs) { next -> partition = partition.copy(inputs = next) }

  Typography {
    variant = TypographyVariant.h2
    +"Inputs"
  }

  Stack {
    direction = responsive(StackDirection.column)

    partition.inputs.forEachIndexed { i, input ->
      PartitionInput {
        this.input = input
        setInput = { next -> inputs = inputs.toMutableList().also { it[i] = next } }
        onMoveUp = { inputs = inputs.swap(i - 1, i) }.takeIf { i > 0 }
        onMoveDown = { inputs = inputs.swap(i, i + 1) }.takeIf { i + 1 < partition.inputs.size }
        onDelete = { inputs = inputs.toMutableList().also { it.removeAt(i) } }
      }
    }
  }

  Button {
    sx { width = 442.125.px }
    onClick = {
      val newInput = Plan.Input(item = null, quantity = 0.q)
      inputs = inputs + newInput
    }
    +"New Input"
  }
}
