package app.v2.plans.plan.products

import app.util.PropsDelegate
import app.v2.plans.data.model.PlanProduct
import app.v2.plans.plan.common.PlanItemAmountInput
import react.FC
import react.Props
import react.ReactNode

external interface RequirementAtLeastComponentProps : Props {
  var requirement: PlanProduct.AtLeast
  var setRequirement: (PlanProduct.AtLeast) -> Unit
}

val RequirementAtLeastComponent = FC<RequirementAtLeastComponentProps>("RequirementAtLeastComponent") { props ->
  var requirement by PropsDelegate(props.requirement, props.setRequirement)

  PlanItemAmountInput {
    label = ReactNode("Minimum")
    model = requirement.minimum
    setModel = { next -> requirement = requirement.copy(minimum = next) }
  }
}
