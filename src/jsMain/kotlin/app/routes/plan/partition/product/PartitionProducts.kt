package app.routes.plan.partition.product

import app.api.plan.v1.Plan
import app.routes.plan.usePartition
import app.util.PropsDelegate
import mui.material.Button
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import util.collections.swap
import util.math.q
import web.cssom.px

val PartitionProducts = FC<Props>("PartitionProducts") {
  var partition by usePartition()
  var products by PropsDelegate(partition.products) { next -> partition = partition.copy(products = next) }

  Typography {
    variant = TypographyVariant.h2
    +"Products"
  }

  Stack {
    direction = responsive(StackDirection.column)

    partition.products.forEachIndexed { i, product ->
      PartitionProduct {
        this.product = product
        setProduct = { nextProduct -> products = products.toMutableList().also { it[i] = nextProduct } }
        onMoveUp = { products = products.swap(i - 1, i) }.takeIf { i > 0 }
        onMoveDown = { products = products.swap(i, i + 1) }.takeIf { i + 1 < partition.products.size }
        onDelete = { products = products.let { it.subList(0, i) + it.subList(i + 1, it.size) } }
      }
    }
  }

  Button {
    sx { width = 442.125.px }
    onClick = {
      val newProduct = Plan.Product(item = null, maximize = true, weight = 1.q)
      products = partition.products + newProduct
    }
    +"New Product"
  }
}
