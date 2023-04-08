package app.plan

import app.api.OptimizeRequest
import app.api.client.optimize
import app.appScope
import app.common.layout.titlebar.TitleBar
import app.data.Item
import app.data.recipe.ProductionRecipe
import app.model.PlanInputModel
import app.model.PlanModel
import app.model.PlanProductModel
import app.util.PropsDelegate
import csstype.Color
import csstype.FlexDirection
import csstype.Margin
import csstype.Padding
import csstype.px
import kotlinx.coroutines.launch
import mui.icons.material.Add
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Box
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Fab
import mui.material.FabColor
import mui.material.Size
import mui.material.SvgIconSize
import mui.material.Typography
import mui.system.sx
import react.FC
import react.Props
import react.create

external interface PlanProps : Props {
  var plan: PlanModel
  var setPlan: (PlanModel) -> Unit
  var onDelete: () -> Unit
}

val Plan = FC<PlanProps>("Plan") { props ->
  var plan by PropsDelegate(props.plan) { next -> props.setPlan(next) }

  val nextItem = Item.values().filterNot { item -> plan.inputs.any { input -> item == input.item } }
    .filterNot { item -> plan.products.any { product -> item == product.item } }.firstOrNull()

  TitleBar {
    editDescription = "Edit Plan Title"
    deleteDescription = "Delete Plan"

    title = plan.title
    setTitle = { plan = plan.copy(title = it) }
    onDelete = { props.onDelete() }
  }

  Accordion {
    disableGutters = true
    defaultExpanded = true

    Summary { title = "Inputs" }
    AccordionDetails {
      plan.inputs.withIndex().forEach { (i, planInput) ->
        PlanInput {
          this.plan = plan
          input = planInput
          setInput = { next -> plan = plan.setInput(i, next) }
          onDelete = { plan = plan.removeInput(i) }
        }
      }

      nextItem?.let { item ->
        Box {
          sx { margin = Margin(12.px, 0.px) }

          Fab {
            color = FabColor.primary
            size = Size.large
            Add { fontSize = SvgIconSize.medium }

            onClick = {
              plan = plan.addInput(PlanInputModel(item))
            }
          }
        }
      }
    }
  }

  Accordion {
    disableGutters = true
    defaultExpanded = true

    Summary { title = "Products" }
    AccordionDetails {
      plan.products.withIndex().forEach { (i, planProduct) ->
        PlanProduct {
          this.plan = plan
          product = planProduct
          setProduct = { next -> plan = plan.setProduct(i, next) }
          onDelete = { plan = plan.removeProduct(i) }
        }
      }

      nextItem?.let { item ->
        Box {
          sx { margin = Margin(12.px, 0.px) }

          Fab {
            color = FabColor.primary
            size = Size.large
            Add { fontSize = SvgIconSize.medium }

            onClick = {
              plan = plan.addProduct(PlanProductModel(item))
            }
          }
        }
      }
    }
  }

  Accordion {
    disableGutters = true
    defaultExpanded = true

    Summary { title = "Outcome" }
    AccordionDetails {
      Box {
        sx { margin = Margin(12.px, 0.px) }

        Button {
          variant = ButtonVariant.contained
          disabled = plan.loading

          onClick = {
            plan = plan.setLoading(true)

            appScope.launch {
              val request = OptimizeRequest(
                ProductionRecipe.values().toSet(),
                plan.inputs.map { OptimizeRequest.Input(it.item, it.provision) },
                plan.products.map { OptimizeRequest.Product(it.item, it.requirement, it.limit) })

              val response = optimize(request)
              plan = plan.update(response)
            }
          }

          if (plan.outcome == null) {
            +"Calculate"
          } else {
            +"Recalculate"
          }
        }
      }

      plan.outcome?.let {
        PlanOutcomeComponent {
          outcome = it
        }
      }
    }
  }
}

external interface SummaryProps : Props {
  var title: String
}

val Summary = FC<SummaryProps> { props ->
  AccordionSummary {
    sx {
      flexDirection = FlexDirection.rowReverse
      backgroundColor = Color("#f4f4f4")
    }

    expandIcon = ExpandMore.create()
    Typography {
      sx { padding = Padding(0.px, 4.px) }
      +props.title
    }
  }
}
