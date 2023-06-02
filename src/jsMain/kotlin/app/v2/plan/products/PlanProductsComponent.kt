package app.v2.plan.products

import app.v2.common.input.ListItemControls
import app.v2.data.plan.PlanProduct
import app.v2.plan.PlanComponentContext
import app.v2.plan.common.AddElementButton
import app.v2.plan.common.PlanContentRow
import app.v2.plan.common.PlanHeading
import csstype.Margin
import csstype.px
import mui.material.Divider
import mui.material.Stack
import mui.material.StackDirection
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useContext
import util.math.q

external interface PlanProductsComponentProps : Props

val PlanProductsComponent = FC<PlanProductsComponentProps>("PlanProductsComponent") { props ->
  var plan by useContext(PlanComponentContext)

  Stack {
    direction = responsive(StackDirection.column)

    PlanHeading { +"Products" }

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

    PlanContentRow {
      AddElementButton {
        title = ReactNode("Add Product")
        onClick = { plan = plan.addProduct(PlanProduct()) }
      }
    }

    plan.byproducts.takeIf { it.isNotEmpty() }?.also { byproducts ->
      Divider { sx { margin = Margin(2.px, 0.px) } }

      PlanHeading { +"Byproducts" }

      byproducts.forEachIndexed { i, byproduct ->
        PlanByproductComponent {
          this.byproduct = byproduct
          setByproduct = { next -> plan = plan.setByproduct(i, next) }

          onConvert = {
            val product = byproduct.toProduct(plan.produced[byproduct.item] ?: 0.q)
            plan = plan.addProduct(product).removeByproduct(i)
          }
        }
      }
    }
  }
}
