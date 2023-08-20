package app.routes.plan.partition

import app.api.plan.v1.Plan
import app.routes.plan.partition.input.PartitionInputs
import app.theme.AppThemeContext
import app.util.PropsDelegate
import mui.material.Divider
import mui.material.Stack
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.create
import react.useContext

external interface PartitionComponentProps : Props {
  var partition: Plan.Partition
  var setPartition: (Plan.Partition) -> Unit
}

val PartitionComponent = FC<PartitionComponentProps>("PartitionComponent") { props ->
  val (appTheme) = useContext(AppThemeContext)!!

  var partition by PropsDelegate(props.partition, props.setPartition)

  Stack {
    sx { padding = appTheme.spacing(2) }
    spacing = responsive(appTheme.spacing(2))
    divider = Divider.create {}

    PartitionInputs {
      inputs = partition.inputs
      setInputs = { next -> partition = partition.copy(inputs = next) }
    }
  }
}
