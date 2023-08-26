package app.routes.plan

import app.data.plan.PlanManager
import app.routes.plan.partition.PartitionComponent
import app.routes.plan.partition.PartitionManager
import react.FC
import react.Props
import react.useContext

external interface PlanPageProps : Props

val PlanPage = FC<PlanPageProps>("PlanPage") {
  val (plan, manager) = useContext(PlanManager)!!

  PartitionManager.Provider {
    partition = plan.partition
    setPartition = { next -> manager.update { it.copy(partition = next) } }

    PartitionComponent {}
  }
}
