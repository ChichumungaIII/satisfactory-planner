package app.v2.plans.plan.inputs

import app.data.Item
import app.util.PropsDelegate
import app.v2.plans.data.model.PlanInput
import csstype.ClassName
import csstype.px
import mui.icons.material.Add
import mui.material.Fab
import mui.material.FabColor
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.SvgIconSize
import mui.material.Tooltip
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import util.math.Rational

external interface PlanInputsComponentProps : Props {
  var inputs: List<PlanInput>
  var setInputs: (List<PlanInput>) -> Unit

  var consumed: Map<Item, Rational>?
  var minimums: Map<Item, Rational>?
}

val PlanInputsComponent = FC<PlanInputsComponentProps>("PlanInputsComponent") { props ->
  var inputs by PropsDelegate(props.inputs, props.setInputs)

  Stack {
    className = ClassName("plan-inputs")
    direction = responsive(StackDirection.column)
    spacing = responsive(10.px)

    inputs.withIndex().forEach { (index, input) ->
      fun before() = inputs.subList(0, index)
      fun after() = inputs.subList(index + 1, inputs.size)
      fun setInput(input: PlanInput) {
        inputs = before() + input + after()
      }

      PlanInputComponent {
        item = input.item
        setItem = { next -> setInput(input.copy(item = next)) }

        amount = input.amount
        setAmount = { next -> setInput(input.copy(amount = next)) }

        onDelete = { inputs = before() + after() }

        consumed = props.consumed
        minimums = props.minimums
      }
    }

    Tooltip {
      className = ClassName("plan-inputs__add-input")
      title = ReactNode("Add Input")

      Fab {
        color = FabColor.primary
        size = Size.small
        Add { fontSize = SvgIconSize.medium }

        onClick = { inputs = inputs + PlanInput() }
      }
    }
  }
}
