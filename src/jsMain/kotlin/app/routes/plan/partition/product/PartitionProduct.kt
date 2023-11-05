package app.routes.plan.partition.product

import app.api.plan.v1.Plan
import app.common.input.item.ItemAutocomplete
import app.common.input.rational.RationalDisplay
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
import mui.material.Checkbox
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
import react.ReactNode
import web.cssom.AlignItems
import web.cssom.px

external interface PartitionProductProps : Props {
  var product: Plan.Product
  var setProduct: (Plan.Product) -> Unit

  var onMoveUp: (() -> Unit)?
  var onMoveDown: (() -> Unit)?

  var onDelete: () -> Unit
}

val PartitionProduct = FC<PartitionProductProps>("PartitionProduct") { props ->
  val progress = useProgress()

  var product by PropsDelegate(props.product, props.setProduct)

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

    RationalInput.takeUnless { product.maximize }?.invoke {
      variant = RationalInputVariant.RATE
      model = product.amount
      setModel = { next -> product = product.copy(amount = next) }
    } ?: run {
      RationalDisplay {
        variant = RationalInputVariant.RATE
        value = product.amount
      }
    }

    ItemAutocomplete {
      model = product.item
      setModel = { next -> product = product.copy(item = next) }
      options = Item.entries.filter { it.unlock.test(progress) }
    }

    val potential = product.potential
    if (potential != null) {
      Tooltip {
        arrow = true
        placement = TooltipPlacement.top
        title = ReactNode("$potential possible")

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

    Checkbox {
      checked = product.maximize
      onClick = { product = product.copy(maximize = !product.maximize) }
    }

    RationalInput.takeIf { product.maximize }?.invoke {
      variant = RationalInputVariant.NUMBER
      model = product.weight
      setModel = { next -> product = product.copy(weight = next) }
    } ?: run {
      RationalDisplay {
        variant = RationalInputVariant.NUMBER
        value = null
      }
    }

    IconButton {
      Delete {}
      onClick = { props.onDelete() }
    }
  }
}
