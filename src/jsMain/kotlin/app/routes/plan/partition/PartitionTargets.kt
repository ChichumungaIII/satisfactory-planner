package app.routes.plan.partition

import app.api.plan.v1.Plan.Target
import app.common.input.RationalInput
import app.common.input.RationalInputVariant
import app.common.util.RationalDisplay
import app.routes.plan.usePartition
import app.util.PropsDelegate
import mui.material.Box
import mui.material.Checkbox
import mui.material.ListItem
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import react.FC
import react.Props
import util.math.q


val PartitionTargets = FC<Props>("PartitionTargets") {
  var partition by usePartition()

  Typography {
    variant = TypographyVariant.subtitle1
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

external interface PartitionTargetProps : Props {
  var target: Target
  var setTarget: (Target) -> Unit
}

private val PartitionTarget = FC<PartitionTargetProps>("PartitionTarget") { props ->
  var target by PropsDelegate(props.target, props.setTarget)

  Stack {
    direction = responsive(StackDirection.row)

    Box { +"${target.recipe.displayName} at ${target.rate * 100.q}%" }

    Checkbox {
      checked = target.banned
      onClick = { target = target.copy(banned = !target.banned) }
    }

    RationalInput.takeUnless { target.banned }?.invoke {
      variant = RationalInputVariant.CLOCK_SPEED
      label = "Maximum"
      model = target.restriction?.let { it * 100.q }
      setModel = { next -> target = target.copy(restriction = next?.let { it / 100.q }) }
    } ?: run {
      RationalDisplay {
        variant = RationalInputVariant.CLOCK_SPEED
        value = null
      }
    }
  }
}
