package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.game.data.Item
import app.theme.AppThemeContext
import app.util.PropsDelegate
import mui.icons.material.Add
import mui.material.IconButton
import mui.material.Stack
import mui.system.sx
import react.FC
import react.Props
import react.useContext
import util.math.q
import web.cssom.LineStyle
import web.cssom.Outline
import web.cssom.px

external interface PartitionInputsProps : Props {
  var inputs: List<Plan.Input>
  var setInputs: (List<Plan.Input>) -> Unit
}

val PartitionInputs = FC<PartitionInputsProps>("PartitionInputs") { props ->
  val appTheme by useContext(AppThemeContext)!!

  var inputs by PropsDelegate(props.inputs, props.setInputs)

  Stack {
    inputs.forEachIndexed { i, input ->
      PartitionInput {
        this.input = input
        setInput = { next -> inputs = inputs.toMutableList().also { it[i] = next }.toList() }
      }
    }

    IconButton {
      sx {
        maxWidth = 34.px
        outline = Outline(1.px, LineStyle.solid, appTheme.palette.primary.main)
      }

      Add {}
      onClick = { inputs = inputs + Plan.Input(Item.IRON_ORE, 0.q) }
    }
  }
}
