package app.v2.plans.plan.products

import app.util.PropsDelegate
import app.v2.plans.data.model.PlanProduct
import csstype.ClassName
import csstype.px
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

external interface PlanProductsComponentProps : Props {
  var products: List<PlanProduct>
  var setProducts: (List<PlanProduct>) -> Unit
}

val PlanProductsComponent = FC<PlanProductsComponentProps>("PlanProductsComponent") { props ->
  var products by PropsDelegate(props.products, props.setProducts)

  Stack {
    className = ClassName("plan-products")
    direction = responsive(StackDirection.column)
    spacing = responsive(10.px)

    products.withIndex().forEach { (index, product) ->
      fun before() = products.subList(0, index)
      fun after() = products.subList(index + 1, products.size)
      fun setProduct(product: PlanProduct) {
        products = before() + product + after()
      }

      PlanProductComponent {
        item = product.item
        setItem = { next -> setProduct(product.copy(item = next)) }

        exact = product.exact
        setExact = { next -> setProduct(product.copy(exact = next)) }

        amount = product.amount
        setAmount = { next -> setProduct(product.copy(amount = next)) }

        maximum = product.maximum
        setMaximum = { next -> setProduct(product.copy(maximum = next)) }

        onDelete = { products = before() + after() }
      }
    }

    Tooltip {
      className = ClassName("plan-products__add-input")
      title = ReactNode("Add Product")

      Fab {
        color = FabColor.primary
        size = Size.small
        Add { fontSize = SvgIconSize.medium }

        onClick = { products = products + PlanProduct() }
      }
    }
  }
}
