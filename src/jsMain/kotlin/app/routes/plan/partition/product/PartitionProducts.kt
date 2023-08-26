package app.routes.plan.partition.product

import app.api.plan.v1.Plan
import app.routes.plan.partition.common.PartitionList
import app.util.PropsDelegate
import react.FC
import react.Props
import react.create
import util.math.q

private val NEW_PRODUCT = Plan.Product(
  item = null,
  maximize = true,
  weight = 1.q,
)

external interface PartitionProductsProps : Props {
  var products: List<Plan.Product>
  var setProducts: (List<Plan.Product>) -> Unit
}

val PartitionProducts = FC<PartitionProductsProps>("PartitionProducts") { props ->
  var products by PropsDelegate(props.products, props.setProducts)

  PartitionList {
    onAdd = { products = products + NEW_PRODUCT }

    partitionListItems = products.mapIndexed { i, product ->
      PartitionProduct.create {
        this.product = product
        setProduct = { next -> products = products.toMutableList().also { it[i] = next }.toList() }
        deleteProduct = { products = products.subList(0, i) + products.subList(i + 1, products.size) }
      }
    }
  }
}
