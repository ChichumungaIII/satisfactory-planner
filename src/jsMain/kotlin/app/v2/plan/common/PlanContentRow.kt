package app.v2.plan.common

import csstype.ClassName
import csstype.px
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.PropsWithChildren

external interface PlanContentRowProps : PropsWithChildren {
  var indent: Boolean?
}

val PlanContentRow = FC<PlanContentRowProps>("PlanContentRow") { props ->
  Stack {
    className = ClassName(buildString {
      append("plan-content-row")
      props.indent?.takeIf { it }?.also { append(" plan-content-row--indent") }
    })

    direction = responsive(StackDirection.row)
    spacing = responsive(4.px)

    +props.children
  }
}
