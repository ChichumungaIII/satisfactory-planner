package app.routes.plan.partition.target

import app.api.plan.v1.Plan
import app.common.input.item.ItemAutocomplete
import app.common.input.rational.RationalDisplay
import app.common.input.rational.RationalInputVariant
import app.util.PropsDelegate
import mui.icons.material.ArrowForward
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import web.cssom.AlignItems
import web.cssom.px

external interface TargetDetailsProps : Props {
  var target: Plan.Target
  var setTarget: (Plan.Target) -> Unit
}

val TargetDetails = FC<TargetDetailsProps>("TargetDetails") { props ->
  var target by PropsDelegate(props.target, props.setTarget)
  val (recipe, rate) = target

  Stack {
    sx { alignItems = AlignItems.center }
    direction = responsive(StackDirection.row)
    spacing = responsive(8.px)

    Stack {
      direction = responsive(StackDirection.column)
      spacing = responsive(4.px)

      recipe.inputs.keys.forEach { item ->
        Stack {
          sx { alignItems = AlignItems.center }
          direction = responsive(StackDirection.row)

          RationalDisplay {
            variant = RationalInputVariant.RATE
            value = -recipe.rates[item]!! * rate
          }

          // TODO: Make a read-only Item display.
          ItemAutocomplete {
            model = item
            setModel = {}
            options = listOf()
          }
        }
      }
    }

    ArrowForward {}

    Stack {
      direction = responsive(StackDirection.column)
      spacing = responsive(4.px)

      recipe.outputs.keys.forEach { item ->
        Stack {
          sx { alignItems = AlignItems.center }
          direction = responsive(StackDirection.row)

          RationalDisplay {
            variant = RationalInputVariant.RATE
            value = recipe.rates[item]!! * rate
          }

          // TODO: Make a read-only Item display.
          ItemAutocomplete {
            model = item
            setModel = {}
            options = listOf()
          }
        }
      }
    }
  }
}
