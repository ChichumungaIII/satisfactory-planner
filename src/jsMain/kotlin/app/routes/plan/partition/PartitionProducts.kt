package app.routes.plan.partition

import app.api.plan.v1.Plan.Product
import app.common.input.ItemAutocomplete
import app.common.input.RationalInput
import app.common.input.RationalInputVariant
import app.common.layout.arrangedlist.createArrangedList
import app.common.util.RationalDisplay
import app.game.data.Item
import app.routes.plan.usePartition
import app.routes.plan.useProgress
import mui.icons.material.InfoOutlined
import mui.material.Button
import mui.material.Checkbox
import mui.material.Tooltip
import mui.material.TooltipPlacement
import mui.material.Typography
import mui.material.styles.TypographyVariant
import react.FC
import react.Props
import react.ReactNode
import react.create
import util.math.q

external interface PartitionProductsProps : Props

private val NEW_PRODUCT = Product(
  item = null,
  maximize = true,
  weight = 1.q,
  amount = 0.q,
)

val PartitionProducts = FC<PartitionProductsProps>("PartitionProducts") {
  var partition by usePartition()

  PartitionProductsList {
    items = partition.products
    setItems = { next -> partition = partition.copy(products = next) }

    header = Typography.create {
      variant = TypographyVariant.h2
      +"Products"
    }
    footer = Button.create {
      onClick = { partition = partition.copy(products = partition.products + NEW_PRODUCT) }
      +"Add Product"
    }
  }
}

private val PartitionProductsList = createArrangedList<Product>("PartitionProductsList") { i, product, props ->
  val progress = useProgress()

  fun setProduct(next: Product) {
    props.setItems(props.items.toMutableList().also { it[i] = next }.toList())
  }


  RationalInput.takeUnless { product.maximize }?.invoke {
    variant = RationalInputVariant.RATE
    model = product.amount
    setModel = { next -> setProduct(product.copy(amount = next)) }
  } ?: run {
    RationalDisplay {
      variant = RationalInputVariant.RATE
      value = product.amount
    }
  }

  ItemAutocomplete {
    model = product.item
    setModel = { next -> setProduct(product.copy(item = next)) }
    options = Item.entries.filterNot { it.category == Item.Category.RESOURCES }.filter { it.unlock.test(progress) }
  }

  Checkbox {
    checked = product.maximize
    onClick = { setProduct(product.copy(maximize = !product.maximize)) }
  }

  RationalInput.takeIf { product.maximize }?.invoke {
    variant = RationalInputVariant.NUMBER
    model = product.weight
    setModel = { next -> setProduct(product.copy(weight = next)) }
  } ?: run {
    RationalDisplay {
      variant = RationalInputVariant.NUMBER
      value = null
    }
  }

  product.potential?.also { potential ->
    Tooltip {
      arrow = true
      placement = TooltipPlacement.top
      title = ReactNode("$potential possible")
      InfoOutlined {}
    }
  }
}
