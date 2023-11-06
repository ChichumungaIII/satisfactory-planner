package app.routes.plan.partition.target

import app.routes.plan.usePartition
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import web.cssom.Padding
import web.cssom.px

val PartitionTargets = FC<Props>("PartitionTargets") {
  var partition by usePartition()

  Typography {
    variant = TypographyVariant.h2
    +"Result"
  }

  Stack {
    sx { padding = Padding(6.px, 6.px) }
    direction = responsive(StackDirection.column)
    spacing = responsive(6.px)

    partition.targets.forEachIndexed { i, target ->
      PartitionTarget {
        this.target = target
        setTarget = { next ->
          partition = partition.copy(targets = partition.targets.toMutableList().also { it[i] = next })
        }
      }
    }
  }
}
