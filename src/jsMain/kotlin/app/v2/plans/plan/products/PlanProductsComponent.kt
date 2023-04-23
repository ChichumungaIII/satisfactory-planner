package app.v2.plans.plan.products

import app.v2.common.input.ListItemControls
import app.v2.plans.data.model.PlanProduct
import app.v2.plans.plan.PlanComponentContext
import mui.icons.material.Add
import mui.material.Fab
import mui.material.FabColor
import mui.material.Size
import mui.material.Stack
import mui.material.StackDirection
import mui.material.SvgIconSize
import mui.material.Tooltip
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.useContext

external interface PlanProductsComponentProps : Props

val PlanProductsComponent = FC<PlanProductsComponentProps>("PlanProductsComponent") { props ->
  var plan by useContext(PlanComponentContext)

  Stack {
    direction = responsive(StackDirection.column)

    plan.products.forEachIndexed { i, product ->
      PlanProductComponent {
        this.product = product
        setProduct = { next -> plan = plan.setProduct(i, next) }

        ListItemControls {
          onDelete = { plan = plan.removeProduct(i) }

          onMoveUp = { plan = plan.spliceProducts(i - 1, 2, product, plan.products[i - 1]) }
          disableMoveUp = i == 0

          onMoveDown = { plan = plan.spliceProducts(i, 2, plan.products[i + 1], product) }
          disableMoveDown = i == (plan.products.size - 1)
        }
      }
    }

    Tooltip {
      title = ReactNode("Add Product")

      Fab {
        color = FabColor.primary
        size = Size.small
        Add { fontSize = SvgIconSize.medium }

        onClick = { plan = plan.addProduct(PlanProduct()) }
      }
    }
  }
}
