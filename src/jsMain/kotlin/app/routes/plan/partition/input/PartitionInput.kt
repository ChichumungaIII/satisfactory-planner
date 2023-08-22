package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.common.input.ItemAutocomplete
import app.common.input.RationalInput
import app.data.save.SaveManager
import app.game.data.Item
import app.game.logic.Condition.ItemCondition
import app.util.PropsDelegate
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.useContext

external interface PartitionInputProps : Props {
  var input: Plan.Input
  var setInput: (Plan.Input) -> Unit
}

val PartitionInput = FC<PartitionInputProps>("PartitionInput") { props ->
  val (save) = useContext(SaveManager)!!

  var input by PropsDelegate(props.input, props.setInput)

  Stack {
    direction = responsive(StackDirection.row)
    spacing = responsive(2)

    RationalInput {
      model = input.quantity
      setModel = { next -> input = input.copy(quantity = next) }
    }

    ItemAutocomplete {
      model = input.item
      setModel = { next -> input = input.copy(item = next) }

      options = Item.entries.filter { ItemCondition(it).test(save.progress) }
    }
  }
}
