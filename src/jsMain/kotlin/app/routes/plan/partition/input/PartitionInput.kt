package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.common.input.ItemAutocomplete
import app.common.input.RationalInput
import app.data.save.SaveManager
import app.game.data.Item
import app.game.logic.Condition.ItemCondition
import app.routes.plan.partition.common.PartitionListItem
import app.util.PropsDelegate
import react.FC
import react.Props
import react.useContext

external interface PartitionInputProps : Props {
  var input: Plan.Input
  var setInput: (Plan.Input) -> Unit
  var deleteInput: () -> Unit
}

val PartitionInput = FC<PartitionInputProps>("PartitionInput") { props ->
  val (save) = useContext(SaveManager)!!

  var input by PropsDelegate(props.input, props.setInput)

  PartitionListItem {
    deleteItem = props.deleteInput

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
