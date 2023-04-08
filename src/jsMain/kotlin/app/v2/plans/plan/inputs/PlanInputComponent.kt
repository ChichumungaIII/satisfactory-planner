package app.v2.plans.plan.inputs

import app.data.Item
import app.util.PropsDelegate
import app.v2.common.input.ItemAutocomplete
import app.v2.common.input.TooltipIconButton
import app.v2.plans.plan.common.PlanItemAmountInput
import csstype.ClassName
import csstype.px
import mui.icons.material.Clear
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import util.math.Rational

external interface PlanInputComponentProps : Props {
  var item: Item?
  var setItem: (Item?) -> Unit

  var amount: Rational
  var setAmount: (Rational) -> Unit

  var onDelete: () -> Unit
}

val PlanInputComponent = FC<PlanInputComponentProps>("PlanInputComponent") { props ->
  var item by PropsDelegate(props.item, props.setItem)
  var amount by PropsDelegate(props.amount, props.setAmount)

  Stack {
    className = ClassName("plan-inputs__input")
    direction = responsive(StackDirection.row)
    spacing = responsive(4.px)

    TooltipIconButton {
      title = "Delete"
      icon = Clear
      onClick = { props.onDelete() }
    }

    ItemAutocomplete {
      model = item
      setModel = { next -> item = next }
    }

    PlanItemAmountInput {
      label = ReactNode("Amount available")
      model = amount
      setModel = { next -> amount = next }
    }
  }
}
