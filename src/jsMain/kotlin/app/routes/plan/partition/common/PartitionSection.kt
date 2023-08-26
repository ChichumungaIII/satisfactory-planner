package app.routes.plan.partition.common

import app.theme.AppThemeContext
import mui.icons.material.ExpandMore
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
import mui.system.sx
import react.FC
import react.PropsWithChildren
import react.create
import react.useContext
import web.cssom.Color
import web.cssom.px

external interface PartitionSectionProps : PropsWithChildren {
  var title: String
}

val PartitionSection = FC<PartitionSectionProps>("PartitionSection") { props ->
  val appTheme by useContext(AppThemeContext)!!

  Accordion {
    AccordionSummary {
      expandIcon = ExpandMore.create {}
      Typography {
        variant = TypographyVariant.subtitle1
        +props.title
      }
    }

    AccordionDetails {
      sx { backgroundColor = Color(appTheme.palette.background.default) }
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
