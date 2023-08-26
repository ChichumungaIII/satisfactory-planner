package app.routes.plan.partition.common

import app.theme.AppThemeContext
import mui.icons.material.Add
import mui.material.IconButton
import mui.material.Stack
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import web.cssom.Length
import web.cssom.LineStyle
import web.cssom.Margin
import web.cssom.Outline
import web.cssom.number
import web.cssom.px

external interface PartitionListProps : Props {
  var spacing: Length?

  var partitionListItems: List<ReactNode>
  var onAdd: (() -> Unit)?
}

val PartitionList = FC<PartitionListProps>("PartitionList") { props ->
  val appTheme by useContext(AppThemeContext)!!

  Stack {
    sx { flexGrow = number(1.0) }
    props.spacing?.also { spacing = responsive(it) }

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
