package app.routes.plan.partition.product

import app.api.plan.v1.Plan
import app.common.input.item.ItemAutocomplete
import app.common.input.rational.RationalDisplay
import app.common.input.rational.RationalInputVariant
import app.util.PropsDelegate
import mui.icons.material.KeyboardDoubleArrowUp
import mui.material.Checkbox
import mui.material.IconButton
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Tooltip
import mui.material.TooltipPlacement
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import web.cssom.AlignItems
import web.cssom.px

external interface PartitionByproductProps : Props {
  var byproduct: Plan.Byproduct
  var setByproduct: (Plan.Byproduct) -> Unit
  var onPromote: () -> Unit
}

val PartitionByproduct = FC<PartitionByproductProps>("PartitionByproduct") { props ->
  var byproduct by PropsDelegate(props.byproduct, props.setByproduct)

  Stack {
    sx { alignItems = AlignItems.center }
    direction = responsive(StackDirection.row)
    spacing = responsive(4.px)

    Checkbox {
      checked = byproduct.banned
      onClick = { byproduct = byproduct.copy(banned = !byproduct.banned) }
    }

    RationalDisplay {
      variant = RationalInputVariant.RATE
      value = byproduct.amount
    }

    // TODO: Make a read-only Item display.
    ItemAutocomplete {
      model = byproduct.item
      setModel = {}
      options = listOf()
    }

    Tooltip {
      placement = TooltipPlacement.top
      title = ReactNode("Promote to product")

      IconButton {
        KeyboardDoubleArrowUp {}
        onClick = { props.onPromote() }
      }
    }
  }
}
