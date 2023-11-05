package app.routes.plan.partition

import app.api.plan.v1.Plan.Input
import app.common.input.item.ItemAutocomplete
import app.common.input.rational.RationalInput
import app.common.input.rational.RationalInputVariant
import app.common.layout.arrangedlist.createArrangedList
import app.game.data.Item
import app.routes.plan.usePartition
import app.routes.plan.useProgress
import mui.icons.material.InfoOutlined
import mui.material.Box
import mui.material.Button
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Tooltip
import mui.material.TooltipPlacement
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.create
import util.math.q
import web.cssom.Margin
import web.cssom.px

private val NEW_INPUT = Input(item = null, quantity = 0.q)

val PartitionInputsLegacy = FC<Props>("PartitionInputs") {
  var partition by usePartition()

  PartitionInputsList {
    items = partition.inputs
    setItems = { next -> partition = partition.copy(inputs = next) }

    header = Typography.create {
      sx { marginLeft = 66.px }
      variant = TypographyVariant.h2
      +"Inputs"
    }
    footer = Button.create {
      sx {
        margin = Margin(4.px, 0.px, 2.px, 66.px)
        width = 442.125.px
      }
      onClick = { partition = partition.copy(inputs = partition.inputs + NEW_INPUT) }
      +"Add Input"
    }
  }
}

private val PartitionInputsList = createArrangedList<Input>("PartitionInputsList") { i, input, props ->
  val progress = useProgress()

  fun setInput(next: Input) {
    props.setItems(props.items.toMutableList().also { it[i] = next }.toList())
  }

  RationalInput {
    variant = RationalInputVariant.RATE
    model = input.quantity
    setModel = { next -> setInput(input.copy(quantity = next)) }
  }

  ItemAutocomplete {
    model = input.item
    setModel = { next -> setInput(input.copy(item = next)) }
    options = Item.entries.filter { it.unlock.test(progress) }
  }

  val (_, _, consumption, demand) = input
  if (consumption != null && demand != null) {
    Tooltip {
      arrow = true
      placement = TooltipPlacement.top
      title = Stack.create {
        direction = responsive(StackDirection.column)
        Box { +"$consumption consumed" }
        Box { +"$demand required" }
      }
      InfoOutlined {}
    }
  }
}
