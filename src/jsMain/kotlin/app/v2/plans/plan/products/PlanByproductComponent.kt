package app.v2.plans.plan.products

import app.util.PropsDelegate
import app.v2.common.input.TooltipIconButton
import app.v2.common.layout.FauxInputDisplay
import app.v2.common.layout.FauxInputDisplayVariant
import app.v2.plans.data.model.PlanByproduct
import app.v2.plans.plan.PlanComponentContext
import mui.icons.material.AddTask
import mui.material.Checkbox
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Tooltip
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.useContext

external interface PlanByproductComponentProps : Props {
  var byproduct: PlanByproduct
  var setByproduct: (PlanByproduct) -> Unit

  var onConvert: () -> Unit
}

val PlanByproductComponent = FC<PlanByproductComponentProps>("PlanByproductComponent") { props ->
  val plan by useContext(PlanComponentContext)
  var byproduct by PropsDelegate(props.byproduct, props.setByproduct)

  Stack {
    direction = responsive(StackDirection.row)

    Tooltip {
      title = ReactNode("Banned".takeIf { byproduct.banned } ?: "Allowed")

      Checkbox {
        size = Size.small
        checked = byproduct.banned
        onChange = { _, next -> byproduct = byproduct.copy(banned = next) }
      }
    }

    TooltipIconButton {
      title = "Convert to product"
      icon = AddTask
      onClick = props.onConvert
    }

    FauxInputDisplay {
      variant = FauxInputDisplayVariant.RATE
      label = "Produced"
      value = plan.produced[byproduct.item]?.toString() ?: "—"
    }

    FauxInputDisplay {
      variant = FauxInputDisplayVariant.ITEM
      value = byproduct.item
    }
  }
}
