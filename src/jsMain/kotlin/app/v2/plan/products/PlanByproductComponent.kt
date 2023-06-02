package app.v2.plan.products

import app.util.PropsDelegate
import app.v2.common.input.TooltipIconButton
import app.v2.common.layout.FauxInputDisplay
import app.v2.common.layout.FauxInputDisplayVariant
import app.v2.data.plan.PlanByproduct
import app.v2.plan.PlanComponentContext
import app.v2.plan.common.PlanContentRow
import csstype.ClassName
import mui.icons.material.AddTask
import mui.material.Checkbox
import mui.material.Size
import mui.material.Tooltip
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

  PlanContentRow {
    Tooltip {
      title = ReactNode("Banned".takeIf { byproduct.banned } ?: "Allowed")

      Checkbox {
        className = ClassName("plan-byproduct__ban-checkbox")
        size = Size.small
        checked = byproduct.banned
        onChange = { _, next -> byproduct = byproduct.copy(banned = next) }
      }
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

    TooltipIconButton {
      className = ClassName("plan-byproduct__convert-button")
      title = "Convert to product"
      icon = AddTask
      onClick = props.onConvert
    }
  }
}
