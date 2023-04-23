package app.v2.plans.plan.inputs

import app.util.PropsDelegate
import app.v2.common.input.DetailsToggleButton
import app.v2.common.input.ItemAutocomplete
import app.v2.plans.data.model.PlanInput
import app.v2.plans.plan.PlanComponentContext
import app.v2.plans.plan.common.PlanItemAmountInput
import mui.material.Chip
import mui.material.ChipColor
import mui.material.ChipVariant
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.PropsWithChildren
import react.ReactNode
import react.useContext

external interface PlanInputComponentProps : PropsWithChildren {
  var input: PlanInput
  var setInput: (PlanInput) -> Unit
}

val PlanInputComponent = FC<PlanInputComponentProps>("PlanInputComponent") { props ->
  val plan by useContext(PlanComponentContext)
  var input by PropsDelegate(props.input, props.setInput)

  Stack {
    direction = responsive(StackDirection.column)

    Stack {
      direction = responsive(StackDirection.row)

      +props.children

      PlanItemAmountInput {
        label = ReactNode("Available")
        model = input.amount
        setModel = { next -> input = input.copy(amount = next) }
      }

      ItemAutocomplete {
        model = input.item
        setModel = { next -> input = input.copy(item = next) }
      }

      DetailsToggleButton {
        details = input.details
        setDetails = { next -> input = input.copy(details = next) }
      }
    }

    Stack.takeIf { input.details }?.invoke {
      direction = responsive(StackDirection.row)

      (plan.components[input.item]?.let { -it }?.toString() ?: "?").also { consumed ->
        Chip {
          variant = ChipVariant.filled
          color = ChipColor.secondary
          label = ReactNode("$consumed consumed")
        }
      }

      (plan.minimums?.get(input.item)?.toString() ?: "?").also { required ->
        Chip {
          variant = ChipVariant.filled
          color = ChipColor.primary
          label = ReactNode("$required required")
        }
      }
    }
  }
}
