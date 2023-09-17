package app.routes.plan.partition

import app.api.plan.v1.Plan.Input
import app.common.input.ItemAutocomplete
import app.common.input.RationalInput
import app.common.input.RationalInputVariant
import app.common.layout.arrangedlist.createArrangedList
import app.game.data.Item
import app.routes.plan.usePartition
import app.routes.plan.useProgress
import mui.material.Button
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.create
import util.math.q

private val NEW_INPUT = Input(item = null, quantity = 0.q)

val PartitionInputs = FC<Props>("PartitionInputs") {
  var partition by usePartition()

  PartitionInputsList {
    items = partition.inputs
    setItems = { next -> partition = partition.copy(inputs = next) }

    header = Typography.create {
      variant = TypographyVariant.h2
      +"Inputs"
    }
    footer = Button.create {
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
}
