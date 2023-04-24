package app.v2.plans.plan.inputs

import app.v2.common.input.ListItemControls
import app.v2.plans.data.model.PlanInput
import app.v2.plans.plan.PlanComponentContext
import app.v2.plans.plan.common.AddElementButton
import app.v2.plans.plan.common.PlanContentRow
import mui.material.Stack
import mui.material.StackDirection
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

    PlanContentRow {
      AddElementButton {
        title = ReactNode("Add Input")
        onClick = { plan = plan.addInput(PlanInput()) }
      }
    }
  }
}
