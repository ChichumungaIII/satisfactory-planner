package app.v2.plans.plan.products

import app.util.PropsDelegate
import app.v2.data.plan.PlanProduct
import app.v2.plans.plan.common.PlanItemAmountInput
import react.FC
import react.Props
import react.ReactNode

external interface RequirementExactlyComponentProps : Props {
  var requirement: PlanProduct.Exactly
  var setRequirement: (PlanProduct.Exactly) -> Unit
}

val RequirementExactlyComponent = FC<RequirementExactlyComponentProps>("RequirementExactlyComponent") { props ->
  var requirement by PropsDelegate(props.requirement, props.setRequirement)

  PlanItemAmountInput {
    label = ReactNode("Amount")
    model = requirement.amount
    setModel = { next -> requirement = requirement.copy(amount = next) }
  }
}
