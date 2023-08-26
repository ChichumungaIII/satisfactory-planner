package app.routes.plan.partition.common

import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Divider
import mui.material.Orientation
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.Stack
import mui.system.StackDirection
import mui.system.responsive
import react.FC
import react.PropsWithChildren
import react.create
import web.cssom.px

external interface PartitionSectionProps : PropsWithChildren {
  var title: String
}

val PartitionSection = FC<PartitionSectionProps>("PartitionSection") { props ->
  Accordion {
    AccordionSummary {
      Typography {
        variant = TypographyVariant.subtitle1
        +props.title
      }
    }

    AccordionDetails {
      Stack {
        direction = responsive(StackDirection.row)
        divider = Divider.create {
          orientation = Orientation.vertical
          flexItem = true
        }
        spacing = responsive(12.px)

        +props.children
      }
    }
  }
}
