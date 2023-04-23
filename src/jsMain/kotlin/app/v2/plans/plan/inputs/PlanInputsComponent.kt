package app.v2.plans.plan.inputs

import app.v2.common.input.ListItemControls
import app.v2.plans.data.model.PlanInput
import app.v2.plans.plan.PlanComponentContext
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
import react.useContext

external interface PlanInputsComponentProps : Props

val PlanInputsComponent = FC<PlanInputsComponentProps>("PlanInputsComponent") { props ->
  var plan by useContext(PlanComponentContext)

  Stack {
    direction = responsive(StackDirection.column)

    plan.inputs.forEachIndexed { i, input ->
      PlanInputComponent {
        this.input = input
        setInput = { next -> plan = plan.setInput(i, next) }

        ListItemControls {
          onDelete = { plan = plan.removeInput(i) }

          onMoveUp = { plan = plan.spliceInputs(i - 1, 2, input, plan.inputs[i - 1]) }
          disableMoveUp = i == 0

          onMoveDown = { plan = plan.spliceInputs(i, 2, plan.inputs[i + 1], input) }
          disableMoveDown = i == (plan.inputs.size - 1)
        }
      }
    }

    Tooltip {
      title = ReactNode("Add Input")

      Fab {
        color = FabColor.primary
        size = Size.small
        Add { fontSize = SvgIconSize.medium }

        onClick = { plan = plan.addInput(PlanInput()) }
      }
    }
  }
}
