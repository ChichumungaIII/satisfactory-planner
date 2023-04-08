package app.plan

import app.common.ItemAutocomplete
import app.common.RationalInput
import app.common.RationalValidation
import app.model.PlanModel
import app.model.PlanProductModel
import app.util.PropsDelegate
import csstype.ClassName
import csstype.Display
import csstype.Margin
import csstype.px
import mui.icons.material.Close
import mui.material.Box
import mui.material.Chip
import mui.material.ChipColor
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useState
import util.math.q

external interface PlanProductProps : Props {
  var plan: PlanModel
  var product: PlanProductModel
  var setProduct: (PlanProductModel) -> Unit
  var onDelete: () -> Unit
}

val PlanProduct = FC<PlanProductProps>("PlanProduct") { props ->
  var product by PropsDelegate(props.product) { next -> props.setProduct(next) }

  var text by useState(product.requirement.toString())

  Box {
    sx {
      display = Display.flex
      margin = Margin(12.px, 0.px)
    }

    IconButton {
      sx { height = 56.px }

      color = IconButtonColor.default
      size = Size.large
      Close { fontSize = SvgIconSize.inherit }

      autoFocus = true
      onClick = { props.onDelete() }
    }

    ItemAutocomplete {
      item = product.item
      setItem = { next -> product = product.copy(item = next) }
    }

    RationalInput {
      label = ReactNode("Minimum required")

      value = product.requirement
      setValue = { next -> next?.let { product = product.copy(requirement = it) } }

      this.text = text
      setText = { next -> text = next }

      validators = listOf({ value ->
        val maximum = product.maximum
        if (maximum == null || value <= maximum) RationalValidation.pass()
        else RationalValidation.fail("Maximum yield is $maximum")
      }, { value ->
        val limit = product.limit
        if (limit == null || value <= limit) RationalValidation.pass()
        else RationalValidation.fail("Requirement cannot exceed limit.")
      })

      onBlur = {
        if (text.isEmpty()) {
          product = product.copy(requirement = 0.q)
          text = "0"
        }
      }
    }

    RationalInput {
      label = ReactNode("Maximum desired")

      value = product.limit
      setValue = { next -> product = product.copy(limit = next) }

      validators = listOf({ value ->
        val maximum = product.maximum
        if (maximum == null || value <= maximum) RationalValidation.pass()
        else RationalValidation.fail("Maximum yield is $maximum")
      }, { value ->
        if (value >= product.requirement) RationalValidation.pass()
        else RationalValidation.fail("Limit must exceed requirement.")
      })
    }

    product.target?.let { target ->
      Chip {
        className = ClassName("plan-product__chip")
        color = ChipColor.success

        label = ReactNode("$target produced")
      }
    }
    product.maximum?.let { maximum ->
      Chip {
        className = ClassName("plan-product__chip")
        color = ChipColor.secondary

        label = ReactNode("$maximum possible")
      }
    }
  }
}
