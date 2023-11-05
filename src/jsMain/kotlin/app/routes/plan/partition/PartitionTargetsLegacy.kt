package app.routes.plan.partition

import app.api.plan.v1.Plan.Target
import app.api.plan.v1.Plan.Target.Limit
import app.common.input.rational.RationalDisplay
import app.common.input.rational.RationalInput
import app.common.input.rational.RationalInputVariant
import app.common.input.recipe.RecipeDisplay
import app.routes.plan.usePartition
import app.util.PropsDelegate
import mui.icons.material.Lock
import mui.icons.material.LockClock
import mui.icons.material.LockOpen
import mui.material.Box
import mui.material.IconButton
import mui.material.ListItem
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import util.math.q
import web.cssom.AlignItems
import web.cssom.Padding
import web.cssom.px


val PartitionTargetsLegacy = FC<Props>("PartitionTargets") {
  var partition by usePartition()

  Box {
    sx { padding = Padding(4.px, 4.px) }

    Typography {
      sx { marginLeft = 66.px }
      variant = TypographyVariant.h2
      +"Recipes"
    }

    mui.material.List {
      partition.targets.forEachIndexed { i, target ->
        ListItem {
          PartitionTarget {
            this.target = target
            setTarget = { next ->
              partition = partition.copy(targets = partition.targets.toMutableList().also { it[i] = next }.toList())
            }
          }
        }
      }
    }
  }
}

external interface PartitionTargetProps : Props {
  var target: Target
  var setTarget: (Target) -> Unit
}

private val PartitionTarget = FC<PartitionTargetProps>("PartitionTarget") { props ->
  var target by PropsDelegate(props.target, props.setTarget)

  Stack {
    sx { alignItems = AlignItems.center }
    direction = responsive(StackDirection.row)
    spacing = responsive(6.px)
    useFlexGap = true

    IconButton {
      sx { marginLeft = 24.px }
      when (target.limit) {
        Limit.NONE -> {
          LockOpen {}
          onClick = { target = target.copy(limit = Limit.BANNED) }
        }

        Limit.BANNED -> {
          Lock {}
          onClick = { target = target.copy(limit = Limit.RESTRICTED) }
        }

        Limit.RESTRICTED -> {
          LockClock {}
          onClick = { target = target.copy(limit = Limit.NONE) }
        }
      }
    }

    RecipeDisplay { value = target.recipe }
    RationalDisplay {
      variant = RationalInputVariant.CLOCK_SPEED
      value = target.rate * 100.q
    }

    if (target.limit == Limit.RESTRICTED) {
      RationalInput {
        variant = RationalInputVariant.CLOCK_SPEED
        label = "Limit"
        model = target.restriction?.let { it * 100.q }
        setModel = { next -> target = target.copy(restriction = next?.let { it / 100.q }) }
      }
    }
  }
}
