package app.v2.plans.plan.products

import app.util.PropsDelegate
import app.v2.data.plan.PlanProduct
import app.v2.plans.plan.common.PlanItemAmountInput
import react.FC
import react.Props
import react.ReactNode

external interface RequirementBetweenComponentProps : Props {
  var requirement: PlanProduct.Between
  var setRequirement: (PlanProduct.Between) -> Unit
}

val RequirementBetweenComponent = FC<RequirementBetweenComponentProps>("RequirementBetweenComponent") { props ->
  var requirement by PropsDelegate(props.requirement, props.setRequirement)

  PlanItemAmountInput {
    label = ReactNode("Minimum")
    model = requirement.minimum
    setModel = { next -> requirement = requirement.copy(minimum = next) }
  }

  PlanItemAmountInput {
    label = ReactNode("Maximum")
    model = requirement.maximum
    setModel = { next -> requirement = requirement.copy(maximum = next) }
  }
}
