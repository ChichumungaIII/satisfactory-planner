package app.v2.plans.plan.products

import app.util.PropsDelegate
import app.v2.common.input.DetailsToggleButton
import app.v2.common.input.ItemAutocomplete
import app.v2.common.input.TooltipIconButton
import app.v2.common.layout.FauxInputDisplay
import app.v2.common.layout.FauxInputDisplayVariant
import app.v2.plans.data.model.PlanProduct
import app.v2.plans.plan.PlanComponentContext
import mui.icons.material.Cancel
import mui.material.Chip
import mui.material.ChipColor
import mui.material.ChipVariant
import mui.material.MenuItem
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.TextField
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.dom.onChange
import react.useContext

external interface PlanProductComponentProps : Props {
  var product: PlanProduct
  var setProduct: (PlanProduct) -> Unit
  var onDelete: () -> Unit
}

val PlanProductComponent = FC<PlanProductComponentProps>("PlanProductComponent") { props ->
  val plan by useContext(PlanComponentContext)
  var product by PropsDelegate(props.product, props.setProduct)

  Stack {
    direction = responsive(StackDirection.column)

    Stack {
      direction = responsive(StackDirection.row)

      TooltipIconButton {
        title = "Delete product"
        icon = Cancel
        onClick = props.onDelete
      }

      FauxInputDisplay {
        variant = FauxInputDisplayVariant.RATE
        label = "Produced"
        value = plan.produced[product.item]?.toString() ?: "—"
      }

      ItemAutocomplete {
        model = product.item
        setModel = { next -> product = product.copy(item = next) }
      }

      DetailsToggleButton {
        details = product.details
        setDetails = { next -> product = product.copy(details = next) }
      }
    }

    Stack.takeIf { product.details }?.invoke {
      direction = responsive(StackDirection.row)

      TextField {
        size = Size.small
        select = true
        value = when (product.requirement) {
          is PlanProduct.AtLeast -> "AT_LEAST"
          is PlanProduct.Exactly -> "EXACTLY"
          is PlanProduct.Between -> "BETWEEN"
        }
        onChange = { event ->
          product = product.copy(
            requirement = product.requirement.let {
              when (val value = event.target.asDynamic().value as String) {
                "AT_LEAST" -> it.toAtLeast()
                "EXACTLY" -> it.toExactly()
                "BETWEEN" -> it.toBetween()
                else -> throw Error("Unrecognized option: [$value]")
              }
            }
          )
        }

        MenuItem { value = "AT_LEAST"; +"At least" }
        MenuItem { value = "EXACTLY"; +"Exactly" }
        MenuItem { value = "BETWEEN"; +"Between" }
      }

      when (val requirement = product.requirement) {
        is PlanProduct.AtLeast -> RequirementAtLeastComponent {
          this.requirement = requirement
          setRequirement = { next -> product = product.copy(requirement = next) }
        }

        is PlanProduct.Exactly -> RequirementExactlyComponent {
          this.requirement = requirement
          setRequirement = { next -> product = product.copy(requirement = next) }
        }

        is PlanProduct.Between -> RequirementBetweenComponent {
          this.requirement = requirement
          setRequirement = { next -> product = product.copy(requirement = next) }
        }
      }

      (plan.maximums?.get(product.item)?.toString() ?: "?").also { maximum ->
        Chip {
          variant = ChipVariant.filled
          color = ChipColor.secondary
          label = ReactNode("$maximum possible")
        }
      }
    }
  }
}
