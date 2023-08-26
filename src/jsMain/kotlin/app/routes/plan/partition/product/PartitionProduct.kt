package app.routes.plan.partition.product

import app.api.plan.v1.Plan
import app.common.input.ItemAutocomplete
import app.common.input.RationalInput
import app.data.save.SaveManager
import app.game.data.Item
import app.routes.plan.partition.common.PartitionListItem
import app.util.PropsDelegate
import mui.material.Checkbox
import mui.material.Typography
import react.FC
import react.Props
import react.useContext

val PRODUCT_CATEGORIES = setOf(
  Item.Category.PARTS,
  Item.Category.EQUIPMENT,
)

external interface PartitionProductProps : Props {
  var product: Plan.Product
  var setProduct: (Plan.Product) -> Unit
  var deleteProduct: () -> Unit
}

val PartitionProduct = FC<PartitionProductProps>("PartitionProduct") { props ->
  val (save) = useContext(SaveManager)!!

  var product by PropsDelegate(props.product, props.setProduct)

  PartitionListItem {
    deleteItem = props.deleteProduct

    if (product.maximize) {
      Typography { +(product.amount?.toString() ?: "—") }
    } else {
      RationalInput {
        model = product.amount
        setModel = { next -> product = product.copy(amount = next) }
      }
    }

    ItemAutocomplete {
      model = product.item
      setModel = { next -> product = product.copy(item = next) }

      options = Item.entries.filter { PRODUCT_CATEGORIES.contains(it.category) }
        .filter { it.unlock.test(save.progress) }
    }

    Checkbox {
      checked = product.maximize
      onChange = { _, checked -> product = product.copy(maximize = checked) }
    }

    if (product.maximize) {
      RationalInput {
        model = product.weight
        setModel = { next -> product = product.copy(weight = next) }
      }
    }
  }
}
