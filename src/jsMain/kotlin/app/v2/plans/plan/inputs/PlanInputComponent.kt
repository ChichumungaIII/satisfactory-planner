package app.v2.plans.plan.inputs

import app.util.PropsDelegate
import app.v2.common.input.ExpandCollapseToggle
import app.v2.common.input.ItemAutocomplete
import app.v2.data.plan.PlanInput
import app.v2.plans.plan.PlanComponentContext
import app.v2.plans.plan.common.PlanContentRow
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

    PlanContentRow {
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

      ExpandCollapseToggle {
        expanded = input.details
        setExpanded = { next -> input = input.copy(details = next) }
      }
    }

    PlanContentRow.takeIf { input.details }?.invoke {
      indent = true

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
