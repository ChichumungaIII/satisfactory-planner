package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.common.input.ItemAutocomplete
import app.common.input.RationalInput
import app.data.save.SaveManager
import app.game.data.Item
import app.game.logic.Condition.ItemCondition
import app.util.PropsDelegate
import mui.icons.material.Delete
import mui.material.IconButton
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.useContext
import web.cssom.AlignItems

external interface PartitionInputProps : Props {
  var input: Plan.Input
  var setInput: (Plan.Input) -> Unit
  var deleteInput: () -> Unit
}

val PartitionInput = FC<PartitionInputProps>("PartitionInput") { props ->
  val (save) = useContext(SaveManager)!!

  var input by PropsDelegate(props.input, props.setInput)

  Stack {
    sx { alignItems = AlignItems.center }
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

    IconButton {
      Delete {}
      onClick = { props.deleteInput() }
    }
  }
}
