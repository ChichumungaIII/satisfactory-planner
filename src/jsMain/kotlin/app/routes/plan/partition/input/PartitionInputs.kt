package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.routes.plan.usePartition
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
import web.cssom.Margin
import web.cssom.px

val PartitionInputs = FC<Props>("PartitionInputs") {
  var partition by usePartition()

  Typography {
    variant = TypographyVariant.h2
    +"Inputs"
  }

  Stack {
    direction = responsive(StackDirection.column)

    partition.inputs.forEachIndexed { i, input ->
      PartitionInput {
        this.input = input
        setInput = { nextInput ->
          val nextInputs = partition.inputs.toMutableList().also { it[i] = nextInput }
          partition = partition.copy(inputs = nextInputs)
        }

        onMoveUp = {
          partition = partition.copy(inputs = partition.inputs.swap(i - 1, i))
        }.takeIf { i > 0 }
        onMoveDown = {
          partition = partition.copy(inputs = partition.inputs.swap(i, i + 1))
        }.takeIf { i + 1 < partition.inputs.size }

        onDelete = {
          val nextInputs = partition.inputs.let { it.subList(0, i) + it.subList(i + 1, it.size) }
          partition = partition.copy(inputs = nextInputs)
        }
      }
    }
  }

  Button {
    sx { width = 442.125.px }
    onClick = { partition = partition.copy(inputs = partition.inputs + Plan.Input(item = null, quantity = 0.q)) }
    +"New Input"
  }
}
