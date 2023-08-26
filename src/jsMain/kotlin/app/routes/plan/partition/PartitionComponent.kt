package app.routes.plan.partition

import app.routes.plan.partition.common.PartitionSection
import app.routes.plan.partition.input.PartitionInputs
import app.routes.plan.partition.nested.NestedPartitions
import app.routes.plan.partition.product.PartitionProducts
import mui.material.styles.Theme
import mui.material.useMediaQuery
import react.FC
import react.Props
import react.create
import react.useContext

external interface PartitionComponentProps : Props

val PartitionComponent: FC<PartitionComponentProps> = FC("PartitionComponent") {
  val large = useMediaQuery<Theme>({ "(min-width: 1200px)" })

  val (partition, manager) = useContext(PartitionManager.Context)!!

  val inputsComponent = PartitionInputs.create {
    inputs = partition.inputs
    setInputs = { next -> manager.update { it.copy(inputs = next) } }
  }
  val productsComponent = PartitionProducts.create {
    products = partition.products
    setProducts = { next -> manager.update { it.copy(products = next) } }
  }

  if (large) {
    PartitionSection {
      title = "Items"
      +inputsComponent
      +productsComponent
    }
  } else {
    PartitionSection {
      title = "Inputs"
      +inputsComponent
    }
    PartitionSection {
      title = "Products"
      +productsComponent
    }
  }

  PartitionSection {
    title = "Partitions"
    NestedPartitions {}
  }

  PartitionSection {
    title = "Recipes"
  }
}
