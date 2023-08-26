package app.routes.plan.partition

import app.api.plan.v1.Plan
import app.routes.plan.partition.common.PartitionSection
import app.routes.plan.partition.input.PartitionInputs
import app.routes.plan.partition.product.PartitionProducts
import app.util.PropsDelegate
import mui.material.styles.Theme
import mui.material.useMediaQuery
import mui.system.Breakpoint
import react.FC
import react.Props
import react.create

external interface PartitionComponentProps : Props {
  var partition: Plan.Partition
  var setPartition: (Plan.Partition) -> Unit
}

val PartitionComponent = FC<PartitionComponentProps>("PartitionComponent") { props ->
  val large = useMediaQuery({ theme: Theme -> theme.breakpoints.up(Breakpoint.lg) })

  var partition by PropsDelegate(props.partition, props.setPartition)

  val inputsComponent = PartitionInputs.create {
    inputs = partition.inputs
    setInputs = { next -> partition = partition.copy(inputs = next) }
  }
  val productsComponent = PartitionProducts.create {
    products = partition.products
    setProducts = { next -> partition = partition.copy(products = next) }
  }

  if (large) {
    PartitionSection {
      title = "Inputs and Products"
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
}
