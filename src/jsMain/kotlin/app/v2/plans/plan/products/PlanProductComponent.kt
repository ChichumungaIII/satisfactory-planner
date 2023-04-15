package app.v2.plans.plan.products

import app.data.Item
import app.util.PropsDelegate
import app.v2.common.input.ItemAutocomplete
import app.v2.common.input.ToggleIconButton
import app.v2.common.input.TooltipIconButton
import app.v2.common.layout.FauxInputDisplay
import app.v2.common.layout.FauxInputDisplayVariant
import app.v2.plans.data.model.PlanProduct
import app.v2.plans.plan.common.PlanItemAmountInput
import csstype.ClassName
import csstype.px
import mui.icons.material.Clear
import mui.icons.material.GpsFixed
import mui.icons.material.GpsNotFixed
import mui.icons.material.LastPage
import mui.icons.material.Start
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import util.math.Rational

external interface PlanProductComponentProps : Props {
  var product: PlanProduct
  var setProduct: (PlanProduct) -> Unit

  var onDelete: () -> Unit

  var produced: Map<Item, Rational>?
  var maximums: Map<Item, Rational>?
}

val PlanProductComponent = FC<PlanProductComponentProps>("PlanProductComponent") { props ->
  var product by PropsDelegate(props.product, props.setProduct)
  val (item, exact, amount, maximum) = product

  Stack {
    className = ClassName("plan-products__product")
    direction = responsive(StackDirection.row)
    spacing = responsive(4.px)

    TooltipIconButton {
      title = "Delete"
      icon = Clear
      onClick = { props.onDelete() }
    }

    ItemAutocomplete {
      model = item
      setModel = { next ->
        product = product.copy(item = next)
      }
    }

    ToggleIconButton {
      toggle = exact
      setToggle = { next ->
        product = product.copy(exact = next, maximum = maximum?.takeUnless { exact })
      }

      titleOn = "Exactly"
      iconOn = GpsFixed

      titleOff = "At least"
      iconOff = GpsNotFixed
    }

    PlanItemAmountInput {
      label = ReactNode(if (exact) "Target" else "Minimum")
      model = amount
      setModel = { next ->
        product = product.copy(amount = next)
      }
    }

    if (!exact) {
      ToggleIconButton {
        toggle = maximum != null
        setToggle = { next ->
          product = product.copy(maximum = amount.takeIf { next })
        }

        titleOn = "Up to"
        iconOn = LastPage

        titleOff = "Unlimited"
        iconOff = Start
      }

      maximum?.also {
        PlanItemAmountInput {
          label = ReactNode("Maximum")
          model = it
          setModel = { next ->
            product = product.copy(maximum = next)
          }
        }
      }

      props.produced?.let { it[item] }?.also {
        FauxInputDisplay {
          variant = FauxInputDisplayVariant.RATE
          label = "Produced"
          value = it
        }
      }
    }

    props.maximums?.let { it[item] }?.also {
      FauxInputDisplay {
        variant = FauxInputDisplayVariant.RATE
        label = "Possible"
        value = it
      }
    }
  }
}
