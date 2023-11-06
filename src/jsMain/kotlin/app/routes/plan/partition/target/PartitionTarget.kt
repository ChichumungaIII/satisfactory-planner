package app.routes.plan.partition.target

import app.api.plan.v1.Plan
import app.common.input.rational.RationalDisplay
import app.common.input.rational.RationalInput
import app.common.input.rational.RationalInputVariant
import app.common.input.recipe.RecipeDisplay
import app.util.PropsDelegate
import mui.icons.material.Visibility
import mui.icons.material.VisibilityOutlined
import mui.material.FormControl
import mui.material.IconButton
import mui.material.InputLabel
import mui.material.MenuItem
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Select
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import util.math.q
import web.cssom.AlignItems
import web.cssom.px

external interface PartitionTargetProps : Props {
  var target: Plan.Target
  var setTarget: (Plan.Target) -> Unit
}

val PartitionTarget = FC<PartitionTargetProps>("PartitionTarget") { props ->
  var target by PropsDelegate(props.target, props.setTarget)

  Paper {
    variant = PaperVariant.outlined

    Stack {
      sx { alignItems = AlignItems.center }
      direction = responsive(StackDirection.row)
      spacing = responsive(6.px)

      IconButton {
        if (target.details) Visibility {}
        else VisibilityOutlined {}
        onClick = { target = target.copy(details = !target.details) }
      }

      RecipeDisplay { value = target.recipe }

      RationalDisplay {
        variant = RationalInputVariant.CLOCK_SPEED
        value = target.rate * 100.q
      }

      FormControl {
        InputLabel { +"Recipe limit" }
        Select {
          sx { width = 96.px }
          label = ReactNode("Recipe limit")

          value = target.limit.name
          onChange = { event, _ ->
            val limit = Plan.Target.Limit.valueOf(event.target.value.unsafeCast<String>())
            target = target.copy(limit = limit)
          }

          MenuItem {
            value = Plan.Target.Limit.NONE.name
            +"None"
          }
          MenuItem {
            value = Plan.Target.Limit.RESTRICTED.name
            +"Limit to"
          }
          MenuItem {
            value = Plan.Target.Limit.BANNED.name
            +"Banned"
          }
        }
      }

      if (target.limit == Plan.Target.Limit.RESTRICTED) {
        RationalInput {
          variant = RationalInputVariant.CLOCK_SPEED
          label = "Limit"
          model = target.restriction?.let { it * 100.q }
          setModel = { next -> target = target.copy(restriction = next?.let { it / 100.q }) }
        }
      }
    }

    if (target.details) {
      TargetDetails {
        this.target = target
        setTarget = { next -> target = next }
      }
    }
  }
}
