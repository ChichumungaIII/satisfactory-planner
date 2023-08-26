package app.routes.plan.partition.nested

import app.routes.plan.partition.PartitionComponent
import app.routes.plan.partition.PartitionManager
import app.routes.plan.partition.common.PartitionListItem
import app.theme.AppThemeContext
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext
import web.cssom.AlignItems
import web.cssom.Color
import web.cssom.Padding
import web.cssom.number
import web.cssom.px

external interface NestedPartitionProps : Props

val NestedPartition = FC<NestedPartitionProps>("NestedPartition") {
  val appTheme by useContext(AppThemeContext)!!

  val (_, manager) = useContext(PartitionManager.Context)!!

  PartitionListItem {
    alignItems = AlignItems.flexStart
    deleteItem = { manager.delete() }

    Paper {
      sx { flexGrow = number(1.0) }
      variant = PaperVariant.outlined

      Accordion {
        AccordionSummary {
          expandIcon = ExpandMore.create()
          Typography {
            variant = TypographyVariant.subtitle2
            +"Partition"
          }
        }

        AccordionDetails {
          sx {
            backgroundColor = Color(appTheme.palette.background.default)
            padding = Padding(8.px, 8.px)
          }
          PartitionComponent {}
        }
      }
    }
  }
}
