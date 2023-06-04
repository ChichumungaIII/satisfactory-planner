package app.v2.plans.plan.common

import csstype.ClassName
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.PropsWithChildren

external interface PlanHeadingProps : PropsWithChildren

val PlanHeading = FC<PlanHeadingProps>("PlanHeading") { props ->
  Typography {
    className = ClassName("plan-heading")

    variant = TypographyVariant.h2
    +props.children
  }
}
