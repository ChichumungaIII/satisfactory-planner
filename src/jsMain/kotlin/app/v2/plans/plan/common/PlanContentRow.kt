package app.v2.plans.plan.common

import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.PropsWithChildren
import web.cssom.ClassName
import web.cssom.px

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
