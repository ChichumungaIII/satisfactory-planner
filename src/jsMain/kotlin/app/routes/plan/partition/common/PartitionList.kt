package app.routes.plan.partition.common

import app.theme.AppThemeContext
import mui.icons.material.Add
import mui.material.IconButton
import mui.system.Stack
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import web.cssom.LineStyle
import web.cssom.Margin
import web.cssom.Outline
import web.cssom.px

external interface PartitionListProps : Props {
  var partitionListItems: List<ReactNode>
  var onAdd: (() -> Unit)?
}

val PartitionList = FC<PartitionListProps>("PartitionList") { props ->
  val appTheme by useContext(AppThemeContext)!!

  Stack {
    props.partitionListItems.forEach { +it }
    props.onAdd?.also { onAdd ->
      IconButton {
        sx {
          margin = Margin(8.px, 0.px, 4.px)
          outline = Outline(1.px, LineStyle.solid, appTheme.palette.primary.main)
        }

        Add {}
        onClick = { onAdd() }
      }
    }
  }
}
