package app.routes.plan.partition.input

import app.api.plan.v1.Plan
import app.common.input.item.ItemAutocomplete
import app.common.input.rational.RationalInput
import app.common.input.rational.RationalInputVariant
import app.game.data.Item
import app.routes.plan.useProgress
import app.util.PropsDelegate
import mui.icons.material.ArrowDropDown
import mui.icons.material.ArrowDropUp
import mui.icons.material.Delete
import mui.icons.material.InfoOutlined
import mui.material.Box
import mui.material.IconButton
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.SvgIconColor
import mui.material.SvgIconSize
import mui.material.Tooltip
import mui.material.TooltipPlacement
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.create
import web.cssom.AlignItems
import web.cssom.px

external interface PartitionInputProps : Props {
  var input: Plan.Input
  var setInput: (Plan.Input) -> Unit

  var onMoveUp: (() -> Unit)?
  var onMoveDown: (() -> Unit)?

  var onDelete: () -> Unit
}

val PartitionInput = FC<PartitionInputProps>("PartitionInput") { props ->
  val progress = useProgress()

  var input by PropsDelegate(props.input, props.setInput)

  Stack {
    sx { alignItems = AlignItems.center }
    direction = responsive(StackDirection.row)
    spacing = responsive(4.px)

    Stack {
      direction = responsive(StackDirection.column)

      IconButton {
        size = Size.small
        disabled = props.onMoveUp == null
        onClick = { props.onMoveUp?.invoke() }
        ArrowDropUp {
          fontSize = SvgIconSize.small
          if (props.onMoveUp == null) {
            color = SvgIconColor.disabled
          }
        }
      }

      IconButton {
        size = Size.small
        disabled = props.onMoveDown == null
        onClick = { props.onMoveDown?.invoke() }
        ArrowDropDown {
          fontSize = SvgIconSize.small
          if (props.onMoveDown == null) {
            color = SvgIconColor.disabled
          }
        }
      }
    }

    RationalInput {
      variant = RationalInputVariant.RATE
      model = input.quantity
      setModel = { next -> input = input.copy(quantity = next) }
    }

    ItemAutocomplete {
      model = input.item
      setModel = { next -> input = input.copy(item = next) }
      options = Item.entries.filter { it.unlock.test(progress) }
    }

    val (_, _, consumption, demand) = input
    if (consumption != null && demand != null) {
      Tooltip {
        arrow = true
        placement = TooltipPlacement.top
        title = Stack.create {
          direction = responsive(StackDirection.column)
          Box { +"$consumption consumed" }
          Box { +"$demand required" }
        }

        InfoOutlined {
          sx {
            width = 20.px
            marginBottom = 16.px
          }
        }
      }
    } else {
      Box {
        sx { width = 20.px }
      }
    }

    IconButton {
      Delete {}
      onClick = { props.onDelete() }
    }
  }
}
